package io.lokopay;

import io.lokopay.net.LokoResponseGetter;
import io.lokopay.net.LokoResponseGetterOptions;
import lombok.Getter;

public class LokoClient {

    private final LokoResponseGetter responseGetter;

//    public LokoClient(String apiKey) {
//        this.responseGetter =
//                new LokoResponseGetter(builder().setApiKey(apiKey).buildOptions(), null);
//    }

    public LokoClient(Boolean liveMode, String apiSecretKey, String apiPublishKey) {
        this.responseGetter =
                new LokoResponseGetter(
                        builder(liveMode)
                                .setApiSecretKey(apiSecretKey)
                                .setApiPublicKey(apiPublishKey)
                                .buildOptions(),
                        null
                );
    }

    public LokoClient(LokoResponseGetter responseGetter) {
        this.responseGetter = responseGetter;
    }


    public void PrintSomething() {
        System.out.println("Print something !");
    }

    public io.lokopay.service.PaymentService payments() {
        return new io.lokopay.service.PaymentService(this.responseGetter);
    }

    public io.lokopay.service.PayoutService payouts() {
        return new io.lokopay.service.PayoutService(this.responseGetter);
    }

    static class ClientLokoResponseGetterOptions extends LokoResponseGetterOptions {

        @Getter(onMethod_ = {@Override})
        private final String apiSecretKey;

        @Getter(onMethod_ = {@Override})
        private final String apiPublicKey;

        @Getter(onMethod_ = {@Override})
        private final int connectTimeout;

        @Getter(onMethod_ = {@Override})
        private final int readTimeout;

        @Getter(onMethod_ = {@Override})
        private final int maxNetworkRetries;

        @Getter(onMethod_ = {@Override})
        private final String apiBase;

        @Getter(onMethod_ = {@Override})
        private final String connectBase;

        ClientLokoResponseGetterOptions(
//                String apiKey,
                String apiSecretKey,
                String apiPublicKey,
                int connectTimeout,
                int readTimeout,
                int maxNetworkRetries,
                String apiBase,
                String connectBase
        ) {
//            this.apiKey = apiKey;
            this.apiSecretKey = apiSecretKey;
            this.apiPublicKey = apiPublicKey;
            this.connectTimeout = connectTimeout;
            this.readTimeout = readTimeout;
            this.maxNetworkRetries = maxNetworkRetries;
            this.apiBase = apiBase;
            this.connectBase = connectBase;
        }

    }

    public static LokoClientBuilder builder(Boolean liveMode) {
        return new LokoClientBuilder(liveMode);
    }

    public static final class LokoClientBuilder {


        @Getter
        private String apiSecretKey;
        @Getter
        private String apiPublicKey;
        @Getter
        private Boolean liveMode;

        private int connectTimeout = Loko.DEFAULT_CONNECTION_TIMEOUT;
        private int readTimeout = Loko.DEFAULT_READ_TIMEOUT;
        private int maxNetworkRetries;
        private String apiBase;  //= Loko.getApiBase();
        private String connectBase = Loko.CONNECT_API_BASE;

        public LokoClientBuilder (Boolean liveMode) {
            this.liveMode = liveMode;
            this.apiBase = this.liveMode ? Loko.LIVE_API_BASE : Loko.TEST_API_BASE;
        }

        public LokoClientBuilder setApiSecretKey(String apiSecretKey) {
            this.apiSecretKey = apiSecretKey;
            return this;
        }

        public LokoClientBuilder setApiPublicKey(String apiPublicKey) {
            this.apiPublicKey = apiPublicKey;
            return this;
        }

        public int getConnectTimeout() { return connectTimeout; }

        public LokoClientBuilder setConnectTimeout(int timeout) {
            this.connectTimeout = timeout;
            return this;
        }

        public int getReadTimeout() { return readTimeout; }

        public LokoClientBuilder setReadTimeout(int timeout) {
            this.readTimeout = timeout;
            return this;
        }

        public int getMaxNetworkRetries() { return maxNetworkRetries; }

        public LokoClientBuilder setMaxNetworkRetries(int maxNetworkRetries) {
            this.maxNetworkRetries = maxNetworkRetries;
            return this;
        }

        public String getApiBase() { return this.apiBase; }

        public LokoClientBuilder setApiBase(String apiBase) {
            this.apiBase = apiBase;
            return this;
        }

        public String getConnectBase() { return this.connectBase; }

        public LokoClientBuilder setConnectBase(String connectBase) {
            this.connectBase = connectBase;
            return this;
        }

        public LokoClient build() {
            return new LokoClient(new LokoResponseGetter(buildOptions(), null));
        }

        LokoResponseGetterOptions buildOptions() {
            if (this.apiSecretKey == null || this.apiPublicKey == null) {
                throw new IllegalArgumentException("API Keys are required");
            }

            return new ClientLokoResponseGetterOptions(
//                    this.apiKey,
                    this.apiSecretKey,
                    this.apiPublicKey,
                    connectTimeout,
                    readTimeout,
                    maxNetworkRetries,
                    apiBase,
                    connectBase
            );
        }
    }

}
