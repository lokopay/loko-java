package io.lokopay.net;

import com.google.gson.JsonSyntaxException;
import io.lokopay.Loko;
import io.lokopay.exception.ApiConnectionException;
import io.lokopay.exception.ApiException;
import io.lokopay.exception.LokoException;
import io.lokopay.model.LokoCollectionInterface;
import io.lokopay.model.LokoObjectInterface;
import io.lokopay.util.Stopwatch;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class LokoResponseGetter implements ResponseGetter {
    private final HttpClient httpClient;
    private final LokoResponseGetterOptions options;

    public LokoResponseGetter() {
        this(null, null);
    }

    public LokoResponseGetter(HttpClient httpClient) {
        this( null, httpClient);
    }

    public LokoResponseGetter(LokoResponseGetterOptions options, HttpClient httpClient) {
        this.options = options; //!= null ? options : GlobalLokoResponseGetterOptions.INSTANCE;
        this.httpClient = (httpClient != null) ? httpClient : buildDefaultHttpClient();

    }

    private LokoRequest toLokoRequest(ApiRequest apiRequest, RequestOptions mergedOptions)
            throws LokoException {
        String fullUrl = fullUrl(apiRequest);

        LokoRequest request =
                new LokoRequest(apiRequest.getMethod(), fullUrl, apiRequest.getParams(), mergedOptions);

        return request;
    }

    @Override
    public <T extends LokoObjectInterface> T request(ApiRequest apiRequest, Type typeToken)
        throws LokoException {
        RequestOptions mergedOptions = RequestOptions.merge(this.options, apiRequest.getOptions());

        LokoRequest request = toLokoRequest(apiRequest, mergedOptions);
        LokoResponse response = sendWithTelemetry(request, apiRequest.getUsage(), r -> httpClient.requestWithRetries(r));

        int responseCode = response.code();
        String responseBody = (String) response.body();

        if (responseCode < 200 || responseCode >= 300) {
            handleError(response);
        }

        T resource = null;
        try {
            resource = (T) ApiResource.deserializeLokoObject(responseBody, typeToken, this);
        } catch (JsonSyntaxException e) {
            raiseMalformedJsonError(responseBody, responseCode, e);
        }

        if (resource instanceof LokoCollectionInterface<?>) {
            ((LokoCollectionInterface<?>) resource).setRequestOptions(apiRequest.getOptions());
            ((LokoCollectionInterface<?>) resource).setRequestParams(apiRequest.getParams());
        }

        resource.setLastResponse(response);

        return resource;
    }

    @Override
    @SuppressWarnings({"TypeParameterUnusedInFormals", "unchecked"})
    public <T extends LokoObjectInterface> T request(
            BaseAddress baseAddress,
            ApiResource.RequestMethod method,
            String path,
            Map<String, Object> params,
            Type typeToken,
            RequestOptions options
    ) throws  LokoException {
        return this.request(new ApiRequest(baseAddress, method, path, params, options), typeToken);
    }

    @Override
    @SuppressWarnings({"TypeParameterUnusedInFormals", "deprecation"})
    public InputStream requestStream(
            BaseAddress baseAddress,
            ApiResource.RequestMethod method,
            String path,
            Map<String, Object> params,
            RequestOptions options
    ) throws LokoException {
        return this.requestStream(new ApiRequest(baseAddress, method, path, params, options));
    }

    @Override
    public InputStream requestStream(ApiRequest apiRequest) throws LokoException {
        RequestOptions mergedOptions = RequestOptions.merge(this.options, apiRequest.getOptions());

        LokoRequest request = toLokoRequest(apiRequest, mergedOptions);

        LokoResponseStream responseStream = sendWithTelemetry(
                request, apiRequest.getUsage(), r -> httpClient.requestStream(r)
        );

        int responseCode = responseStream.code();

        if (responseCode < 200 || responseCode >= 300) {
            LokoResponse response;
            try {
                response = responseStream.unstream();
            } catch (IOException e) {
                throw new ApiConnectionException(
                  String.format(
                          "IOException during API request to Loko (%s): %s",
                          Loko.getApiBase(), e.getMessage()
                  ), e);
            }
            handleError(response);
        }

        return responseStream.body();
    }

    @FunctionalInterface
    private interface RequestSendFunction<R> {
        R apply(LokoRequest request) throws LokoException;
    }

    private <T extends AbstractLokoResponse<?>> T sendWithTelemetry(
            LokoRequest request, List<String> usage, RequestSendFunction<T> send
    ) throws LokoException {
        Stopwatch stopwatch = Stopwatch.startNew();

        T response = send.apply(request);

        stopwatch.stop();

        return response;
    }

    private String fullUrl(BaseApiRequest apiRequest) {
        BaseAddress baseAddress = apiRequest.getBaseAddress();
        RequestOptions options = apiRequest.getOptions();
        String relativeUrl = apiRequest.getPath();
        String baseUrl;
        switch (baseAddress) {
            case API:
                baseUrl = this.options.getApiBase();
                break;
            case CONNECT:
                baseUrl = this.options.getConnectBase();
                break;
//            case FILES:
//                baseUrl = this.options.getFilesBase();
//                break;
            default:
                throw new IllegalArgumentException("Unknown base address " + baseAddress);
        }
        if (options != null && options.getBaseUrl() != null) {
            baseUrl = options.getBaseUrl();
        }
        return String.format("%s%s", baseUrl, relativeUrl);
    }

    private static void raiseMalformedJsonError(
            String responseBody, int responseCode,
//            String requestId,
            Throwable e) throws ApiException {
        String details = e == null ? "none" : e.getMessage();
        throw new ApiException(
                String.format(
                        "Invalid response object from API: %s. (HTTP response code was %d). Additional details: %s.",
                        responseBody, responseCode, details),
//                requestId,
                null,
                responseCode,
                e);
    }

    private void handleError(LokoResponse response) throws LokoException {
//        if (apiMode == ApiMode.V1) {
//            handleApiError(response);
//        } else if (apiMode == ApiMode.OAuth) {
//            handleOAuthError(response);
//        }

        System.out.println("handleError: " + response);

    }

    private void handleApiError(LokoResponse response) throws LokoException {
//        LokoError error = null;
//        StripeException exception = null;
//
//        try {
//            JsonObject jsonObject =
//                    ApiResource.INTERNAL_GSON
//                            .fromJson(response.body(), JsonObject.class)
//                            .getAsJsonObject("error");
//            error = ApiResource.deserializeStripeObject(jsonObject.toString(), StripeError.class, this);
//        } catch (JsonSyntaxException e) {
//            raiseMalformedJsonError(response.body(), response.code(), response.requestId(), e);
//        }
//        if (error == null) {
//            raiseMalformedJsonError(response.body(), response.code(), response.requestId(), null);
//        }
//
//        error.setLastResponse(response);
//
//        switch (response.code()) {
//            case 400:
//            case 404:
//                if ("idempotency_error".equals(error.getType())) {
//                    exception =
//                            new IdempotencyException(
//                                    error.getMessage(), response.requestId(), error.getCode(), response.code());
//                } else {
//                    exception =
//                            new InvalidRequestException(
//                                    error.getMessage(),
//                                    error.getParam(),
//                                    response.requestId(),
//                                    error.getCode(),
//                                    response.code(),
//                                    null);
//                }
//                break;
//            case 401:
//                exception =
//                        new AuthenticationException(
//                                error.getMessage(), response.requestId(), error.getCode(), response.code());
//                break;
//            case 402:
//                exception =
//                        new CardException(
//                                error.getMessage(),
//                                response.requestId(),
//                                error.getCode(),
//                                error.getParam(),
//                                error.getDeclineCode(),
//                                error.getCharge(),
//                                response.code(),
//                                null);
//                break;
//            case 403:
//                exception =
//                        new PermissionException(
//                                error.getMessage(), response.requestId(), error.getCode(), response.code());
//                break;
//            case 429:
//                exception =
//                        new RateLimitException(
//                                error.getMessage(),
//                                error.getParam(),
//                                response.requestId(),
//                                error.getCode(),
//                                response.code(),
//                                null);
//                break;
//            default:
//                exception =
//                        new ApiException(
//                                error.getMessage(), response.requestId(), error.getCode(), response.code(), null);
//                break;
//        }
//
//        exception.setStripeError(error);

//        throw exception;
    }

    private static HttpClient buildDefaultHttpClient() {
        return new HttpURLConnectionClient();
    }
}
