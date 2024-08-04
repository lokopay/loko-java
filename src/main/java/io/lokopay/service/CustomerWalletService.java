package io.lokopay.service;

import io.lokopay.exception.LokoException;
import io.lokopay.model.CustomerWallet;
import io.lokopay.net.*;
import io.lokopay.param.CustomerWalletCreateParams;

public class CustomerWalletService extends ApiService {

    public CustomerWalletService(LokoResponseGetter responseGetter) {
        super(responseGetter);
    }

    public CustomerWallet create(CustomerWalletCreateParams params) throws LokoException {
        String path = "/v1/customer_wallets";

        ApiRequest request = new ApiRequest(
                BaseAddress.API,
                ApiResource.RequestMethod.POST,
                path,
                ApiRequestParams.paramsToMap(params),
                null
        );

        return this.request(request, CustomerWallet.class);
    }
}
