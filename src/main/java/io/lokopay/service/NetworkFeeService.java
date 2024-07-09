package io.lokopay.service;

import com.google.gson.reflect.TypeToken;
import io.lokopay.exception.LokoException;
import io.lokopay.model.BlockchainNetwork;
import io.lokopay.model.NetworkFee;
import io.lokopay.net.*;

import java.util.List;

public final class NetworkFeeService extends ApiService {

    public NetworkFeeService(ResponseGetter responseGetter) {
        super(responseGetter);
    }

    public NetworkFee list() throws LokoException {
        String path = "/v1/networkfees";

        ApiRequest request = new ApiRequest(
                BaseAddress.API,
                ApiResource.RequestMethod.GET,
                path,
                null,
                null
        );

        return this.request(request, NetworkFee.class);
    }
}
