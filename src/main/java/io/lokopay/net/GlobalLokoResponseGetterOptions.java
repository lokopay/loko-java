package io.lokopay.net;

import io.lokopay.Loko;
import java.net.PasswordAuthentication;
import java.net.Proxy;

/**
 * The {@link LokoResponseGetterOptions} implementation that pulls values from the global options
 * in the Loko class
 */
public class GlobalLokoResponseGetterOptions extends LokoResponseGetterOptions {
    public static final GlobalLokoResponseGetterOptions INSTANCE =
            new GlobalLokoResponseGetterOptions();

    private GlobalLokoResponseGetterOptions() {}

    @Override
    public String getApiSecretKey() {
        return Loko.apiSecretKey;
    }

    @Override
    public String getApiPublicKey() {
        return Loko.apiPublicKey;
    }

    @Override
    public int getReadTimeout() {
        return Loko.getReadTimeout();
    }

    @Override
    public int getConnectTimeout() {
        return Loko.getConnectTimeout();
    }

    @Override
    public int getMaxNetworkRetries() {
        return Loko.getMaxNetworkRetries();
    }

    @Override
    public String getApiBase() {
        return Loko.getApiBase();
    }

    @Override
    public String getConnectBase() {
        return Loko.getConnectBase();
    }
}
