package io.lokopay.net;

import io.lokopay.Loko;
import io.lokopay.exception.ApiConnectionException;
import lombok.Cleanup;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpURLConnectionClient extends HttpClient {
  /** Initializes a new instance of the {@link HttpURLConnectionClient}. */
  public HttpURLConnectionClient() {
    super();
  }

  /**
   * Sends the given request to Loko's API.
   *
   * @param request the request
   * @return the response
   * @throws ApiConnectionException if an error occurs when sending or receiving
   */
  @Override
  public LokoResponseStream requestStream(LokoRequest request) throws ApiConnectionException {
    try {
      final HttpURLConnection conn = createLokoConnection(request);

      // Calling `getResponseCode()` triggers the request.
      final int responseCode = conn.getResponseCode();

      final HttpHeaders headers = HttpHeaders.of(conn.getHeaderFields());

      final InputStream responseStream =
          (responseCode >= 200 && responseCode < 300)
              ? conn.getInputStream()
              : conn.getErrorStream();

      return new LokoResponseStream(responseCode, headers, responseStream);

    } catch (IOException e) {
      throw new ApiConnectionException(
          String.format(
              "IOException during API request to Loko (%s): %s "
                  + "Please check your internet connection and try again. If this problem persists,"
                  + "you should check Loko's service status at https://twitter.com/Lokostatus,"
                  + " or let us know at support@Loko.com.",
              Loko.getApiBase(), e.getMessage()),
          e);
    }
  }

  /**
   * Sends the given request to Loko's API, and returns a buffered response.
   *
   * @param request the request
   * @return the response
   * @throws ApiConnectionException if an error occurs when sending or receiving
   */
  @Override
  public LokoResponse request(LokoRequest request) throws ApiConnectionException {
    final LokoResponseStream responseStream = requestStream(request);
    try {
      return responseStream.unstream();
    } catch (IOException e) {
      throw new ApiConnectionException(
          String.format(
              "IOException during API request to Loko (%s): %s "
                  + "Please check your internet connection and try again. If this problem persists,"
                  + "you should check Loko's service status at https://twitter.com/lokostatus,"
                  + " or let us know at support@loko.com.",
              Loko.getApiBase(), e.getMessage()),
          e);
    }
  }

  static HttpHeaders getHeaders(LokoRequest request) {
    Map<String, List<String>> userAgentHeadersMap = new HashMap<>();

    userAgentHeadersMap.put("User-Agent", Arrays.asList(buildUserAgentString()));
    userAgentHeadersMap.put(
        "X-Loko-Client-User-Agent", Arrays.asList(buildXLokoClientUserAgentString()));

    return request.headers().withAdditionalHeaders(userAgentHeadersMap);
  }

  private static HttpURLConnection createLokoConnection(LokoRequest request)
      throws IOException, ApiConnectionException {
    HttpURLConnection conn = null;

//    if (request.options().getConnectionProxy() != null) {
//      conn =
//          (HttpURLConnection) request.url().openConnection(request.options().getConnectionProxy());
//      Authenticator.setDefault(
//          new Authenticator() {
//            @Override
//            protected PasswordAuthentication getPasswordAuthentication() {
//              return request.options().getProxyCredential();
//            }
//          });
//    } else {
      conn = (HttpURLConnection) request.url().openConnection();
//    }

    conn.setConnectTimeout(request.options().getConnectTimeout());
    conn.setReadTimeout(request.options().getReadTimeout());
    conn.setUseCaches(false);
    for (Map.Entry<String, List<String>> entry : getHeaders(request).map().entrySet()) {
      conn.setRequestProperty(entry.getKey(), String.join(",", entry.getValue()));
    }

    conn.setRequestMethod(request.method().name());

    if (request.content() != null) {
      conn.setDoOutput(true);
      conn.setRequestProperty("Content-Type", request.content().contentType());

      @Cleanup OutputStream output = conn.getOutputStream();
      output.write(request.content().byteArrayContent());
    }

    return conn;
  }
}
