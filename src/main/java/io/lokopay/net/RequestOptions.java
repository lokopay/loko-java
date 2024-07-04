package io.lokopay.net;

import io.lokopay.Loko;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.net.PasswordAuthentication;
import java.net.Proxy;

@Getter
@EqualsAndHashCode(callSuper = false)
public class RequestOptions {
//    private final String apiKey;
    private final String apiSecretKey;
    private final String apiPublicKey;
    private final String idempotencyKey;
    private final String baseUrl;
    private final String lokoVersion = Loko.API_VERSION;
    private final Integer connectTimeout;
    private final Integer readTimeout;
    private final Integer maxNetworkRetries;

    public static RequestOptions getDefault() {
        return new RequestOptions(null, null, null, null, null, null, null);
    }

    private RequestOptions(
            String apiSecretKey,
            String apiPublicKey,
            String idempotencyKey,
            String baseUrl,
            Integer connectTimeout,
            Integer readTimeout,
            Integer maxNetworkRetries) {
//        this.apiKey = apiKey;
        this.apiSecretKey = apiSecretKey;
        this.apiPublicKey = apiPublicKey;
        this.idempotencyKey = idempotencyKey;
        this.baseUrl = baseUrl;
        this.connectTimeout = connectTimeout;
        this.readTimeout = readTimeout;
        this.maxNetworkRetries = maxNetworkRetries;
    }

    static RequestOptions merge(LokoResponseGetterOptions clientOptions, RequestOptions options) {
        if (options == null) {
            return new RequestOptions(
                    clientOptions.getApiSecretKey(),
                    clientOptions.getApiPublicKey(),
//                    clientOptions.getApiKey(), // authenticator
                    // clientOptions.getClientId(), // clientId
                    null, // idempotencyKey
                    // null, // stripeAccount
                    // null, // stripeVersionOverride
                    null, // baseUrl
                    clientOptions.getConnectTimeout(), // connectTimeout
                    clientOptions.getReadTimeout(), // readTimeout
                    clientOptions.getMaxNetworkRetries() // maxNetworkRetries
                    // clientOptions.getConnectionProxy(), // connectionProxy
                    // clientOptions.getProxyCredential() // proxyCredential
            );
        }
        return new RequestOptions(
//                options.getApiKey() != null ? options.getApiKey() : clientOptions.getApiKey(),
//                options.getClientId() != null ? options.getClientId() : clientOptions.getClientId(),
                options.getApiSecretKey() != null ? options.getApiSecretKey() : clientOptions.getApiSecretKey(),
                options.getApiPublicKey() != null ? options.getApiPublicKey() : clientOptions.getApiPublicKey(),
                options.getIdempotencyKey(),
//                options.getStripeAccount(),
//                RequestOptions.unsafeGetStripeVersionOverride(options),
                options.getBaseUrl(),
                options.getConnectTimeout() != null
                        ? options.getConnectTimeout()
                        : clientOptions.getConnectTimeout(),
                options.getReadTimeout() != null
                        ? options.getReadTimeout()
                        : clientOptions.getReadTimeout(),
                options.getMaxNetworkRetries() != null
                        ? options.getMaxNetworkRetries()
                        : clientOptions.getMaxNetworkRetries()
//                options.getConnectionProxy() != null
//                        ? options.getConnectionProxy()
//                        : clientOptions.getConnectionProxy(),
//                options.getProxyCredential() != null
//                        ? options.getProxyCredential()
//                        : clientOptions.getProxyCredential()
        );
    }


}
