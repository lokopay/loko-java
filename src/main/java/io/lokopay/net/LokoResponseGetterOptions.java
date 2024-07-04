package io.lokopay.net;

public abstract class LokoResponseGetterOptions {
//    public abstract String getApiKey();

    public abstract String getApiSecretKey();

    public abstract String getApiPublicKey();

    public abstract int getConnectTimeout();

    public abstract int getReadTimeout();

    public abstract int getMaxNetworkRetries();

    public abstract String getApiBase();

    public abstract String getConnectBase();

//    public abstract String getClientId();

//    public abstract Proxy getConnectionProxy();

//    public abstract PasswordAuthentication getProxyCredential();

//    public abstract String getFilesBase();
}
