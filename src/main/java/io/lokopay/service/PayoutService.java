package io.lokopay.service;

import com.google.gson.reflect.TypeToken;
import io.lokopay.exception.LokoException;
import io.lokopay.model.LokoCollection;
import io.lokopay.model.Payout;
import io.lokopay.net.*;
import io.lokopay.param.ListParams;
import io.lokopay.param.PayoutConfirmParams;
import io.lokopay.param.PayoutCreateParams;

public final class PayoutService extends ApiService {

    public PayoutService(ResponseGetter responseGetter) {
        super(responseGetter);
    }

    public Payout create(PayoutCreateParams params) throws LokoException {
        return create(params, null);
    }

    public Payout create(PayoutCreateParams params, RequestOptions options) throws LokoException {
        String path = "/v1/payouts";
        ApiRequest request = new ApiRequest(
                BaseAddress.API,
                ApiResource.RequestMethod.POST,
                path,
                ApiRequestParams.paramsToMap(params),
                options
        );

        return this.request(request, Payout.class);
    }

    public Payout retrieve(String payoutId) throws LokoException {
        String path = String.format("/v1/payouts/%s", payoutId);
        ApiRequest request = new ApiRequest(
                BaseAddress.API,
                ApiResource.RequestMethod.GET,
                path,
                null,
                null
        );

        return this.request(request, Payout.class);
    }

    public Payout confirm(String payoutId, PayoutConfirmParams params) throws LokoException {
        return confirm(payoutId, params, null);
    }

    public Payout confirm(String payoutId, PayoutConfirmParams params, RequestOptions options) throws LokoException {
        String path = String.format("/v1/payouts/%s/confirm", payoutId);
        ApiRequest request = new ApiRequest(
                BaseAddress.API,
                ApiResource.RequestMethod.POST,
                path,
                ApiRequestParams.paramsToMap(params),
                options
        );

        return this.request(request, Payout.class);
    }

    public LokoCollection<Payout> list(ListParams params) throws LokoException {
        String path = "/v1/payouts";
        ApiRequest request = new ApiRequest(
                BaseAddress.API,
                ApiResource.RequestMethod.GET,
                path,
                ApiRequestParams.paramsToMap(params),
                null
        );

        return this.request(request, new TypeToken<LokoCollection<Payout>>() {}.getType());
    }
}
