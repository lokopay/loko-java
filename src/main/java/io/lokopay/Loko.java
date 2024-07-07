package io.lokopay;

import lombok.Getter;

public abstract class Loko {
    public static final int DEFAULT_CONNECTION_TIMEOUT = 30 * 1000;
    public static final int DEFAULT_READ_TIMEOUT = 80 * 1000;

    public static final String API_VERSION = "1.0";
    public static final String LIVE_API_BASE = "https://api.lokopay.io";
    public static final String TEST_API_BASE = "https://api.bitfa.dev";
    public static final String CONNECT_API_BASE = "https://connect.lokopay.io";
    public static final String VERSION = "1.0";


    public static volatile Boolean liveMode = false;
    public static volatile String apiSecretKey;
    public static volatile String apiPublicKey;

    public static volatile boolean enableTelemetry = true;

    private static volatile int connectionTimeout = -1;
    private static volatile int readTimeout = -1;
    private static volatile int maxNetworkRetries = 0;

    private static volatile String apiBase;

    public static void setLiveMode(boolean liveMode) {
        Loko.liveMode = liveMode;
    }

    public static String getApiBase() {
        return liveMode ? LIVE_API_BASE : TEST_API_BASE;
    }

    @Getter
    private static volatile String connectBase = CONNECT_API_BASE;

    public static int getConnectTimeout() {
        if (connectionTimeout == -1) {
            return DEFAULT_CONNECTION_TIMEOUT;
        }
        return connectionTimeout;
    }

    public static void setConnectionTimeout(final int timeout) { connectionTimeout = timeout; }

    public static int getReadTimeout() {
        if (readTimeout == -1) {
            return DEFAULT_READ_TIMEOUT;
        }
        return readTimeout;
    }

    public static void setReadTimeout(final int timeout) { readTimeout = timeout; }

    public static int getMaxNetworkRetries() { return maxNetworkRetries; }

    public static void setMaxNetworkRetries(final int retries) { maxNetworkRetries = retries; }
}
