package io.lokopay.net;

import com.google.gson.TypeAdapterFactory;

import java.util.ArrayList;
import java.util.List;

final class ApiResourceTypeAdapterFactoryProvider {
    private static final List<TypeAdapterFactory> factories = new ArrayList<>();

    static {
//        factories.add(new BalanceTransactionSourceTypeAdapterFactory());
//        factories.add(new ExternalAccountTypeAdapterFactory());
//        factories.add(new PaymentSourceTypeAdapterFactory());
    }

    public static List<TypeAdapterFactory> getAll() {
        return factories;
    }
}
