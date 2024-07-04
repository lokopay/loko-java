package io.lokopay.net;

import java.io.IOException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.time.Instant;
import java.util.*;

import io.lokopay.Loko;
import io.lokopay.exception.ApiConnectionException;
import io.lokopay.exception.AuthenticationException;
import io.lokopay.exception.LokoException;
import io.lokopay.util.StringUtils;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.experimental.Accessors;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

@Value
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Accessors(fluent = true)
public class LokoRequest {
    ApiResource.RequestMethod method;

    URL url;

    HttpContent content;

    HttpHeaders headers;

    Map<String, Object> params;

    RequestOptions options;

    String signature;

    String nonce;

    Long timestamp;

    public LokoRequest(
            ApiResource.RequestMethod method,
            String url,
            Map<String, Object> params,
            RequestOptions options
    ) throws LokoException {

        this.nonce = UUID.randomUUID().toString();
        this.timestamp = Instant.now().getEpochSecond();

        try {

            this.params = (params != null) ? Collections.unmodifiableMap(params) : null;
            this.options = (options != null) ? options : RequestOptions.getDefault();
            this.method = method;
            this.url = buildURL(method, url, params);
            this.content = buildContent(method, params);
            this.signature = buildSignature(method, this.url.toString(), this.params, this.nonce, this.timestamp, this.options);
            this.headers = buildHeaders(method, this.options, this.signature, this.timestamp, this.nonce);

            System.out.println(signature);

        } catch (NoSuchAlgorithmException | InvalidKeyException | IOException e) {
            throw new ApiConnectionException(
                    String.format(
                            "IOException during API request to Loko (%s): %s",
                            Loko.getApiBase(),
                            e.getMessage()
                    ),
                    e
            );

        }
    }

    private static URL buildURL(
            ApiResource.RequestMethod method, String spec, Map<String, Object> params
    ) throws IOException {
        StringBuilder sb = new StringBuilder();

        sb.append(spec);

        URL specUrl = new URL(spec);

        String specQueryString = specUrl.getQuery();

        if ((method != ApiResource.RequestMethod.POST) && (params != null)) {
            String queryString = FormEncoder.createQueryString(params);

            if (queryString != null && !queryString.isEmpty()) {
                if (specQueryString != null && !specQueryString.isEmpty()) {
                    sb.append('&');
                } else {
                    sb.append('?');
                }
                sb.append(queryString);
            }
        }

        return new URL(sb.toString());
    }

    private static HttpContent buildContent(
            ApiResource.RequestMethod method, Map<String, Object> params
    ) throws IOException {
        if (method != ApiResource.RequestMethod.POST) {
            return null;
        }

//        return FormEncoder.createHttpContent(params);
        return JsonEncoder.createHttpContent(params);
    }

    private static HttpHeaders buildHeaders(
            ApiResource.RequestMethod method, RequestOptions options, String signature, long timestamp, String nonce
    ) throws AuthenticationException {

        Map<String, List<String>> headerMap = new HashMap<>();

        headerMap.put("Accept", Arrays.asList("application/json"));
        headerMap.put("Accept-Charset", Arrays.asList(ApiResource.CHARSET.name()));



        String publicKey = options.getApiPublicKey();
        if (publicKey == null) {

            throw new AuthenticationException(
                    "No API key provided.",
                    null,
                    0
            );
        }  else if (publicKey.isEmpty()) {
            throw new AuthenticationException(
                    "Your API Key is invalid, as it is an empty string.",
                    null,
                    0
            );
        } else if (StringUtils.containsWhitespace(publicKey)) {
            throw new AuthenticationException(
                    "Your API Key is invalid.",
                    null,
                    0
            );
        }
        headerMap.put("loko-publishable-key", List.of(publicKey));

        headerMap.put("loko-signature", Arrays.asList(signature));
//        headerMap.put("Authorization", Arrays.asList(String.format("Bearer %s", apiKey)));


        if (options.getIdempotencyKey() != null) {
            headerMap.put("loko-idempotent-key", Arrays.asList(options.getIdempotencyKey()));
        } else if (method == ApiResource.RequestMethod.POST) {
            headerMap.put("loko-idempotent-key", Arrays.asList(UUID.randomUUID().toString()));
        }

        headerMap.put("loko-timestamp", Arrays.asList(String.valueOf(timestamp)));
        headerMap.put("loko-nonce", Arrays.asList(nonce));

        return HttpHeaders.of(headerMap);
    }

    private static String buildSignature(
            ApiResource.RequestMethod method,
            String url, Map<String, Object> params,
            String nonce,
            long timestamp,
            RequestOptions options
    ) throws NoSuchAlgorithmException, InvalidKeyException, IOException {

        String content = "";
        if (params != null && method != ApiResource.RequestMethod.GET) {
            content = JsonEncoder.createJsonString(params);
        }

        String data = url + content + nonce + timestamp;

        System.out.println(data);
        System.out.println(options.getApiSecretKey());

        Mac mac = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKeySpec = new SecretKeySpec(options.getApiSecretKey().getBytes(), "HmacSHA256");
        mac.init(secretKeySpec);

        byte[] hmacBytes = mac.doFinal(data.getBytes());

        return Base64.getEncoder().encodeToString(hmacBytes);
    }
}
