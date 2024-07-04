package io.lokopay.service;

import com.google.gson.reflect.TypeToken;
import io.lokopay.exception.LokoException;
import io.lokopay.model.LokoCollection;
import io.lokopay.model.Payment;
import io.lokopay.net.*;
import io.lokopay.param.ListParams;
import io.lokopay.param.PaymentConfirmParams;
import io.lokopay.param.PaymentCreateParams;

public final class PaymentService extends ApiService {

    public PaymentService(ResponseGetter responseGetter) {
        super(responseGetter);
    }

    public Payment create(PaymentCreateParams params) throws LokoException {
        return create(params, null);
    }

    public Payment create(PaymentCreateParams params, RequestOptions options) throws LokoException {
        String path = "/v1/payments";
        ApiRequest request = new ApiRequest(
                BaseAddress.API,
                ApiResource.RequestMethod.POST,
                path,
                ApiRequestParams.paramsToMap(params),
                options
        );

        return this.request(request, Payment.class);
    }

    public Payment retrieve(String paymentId) throws LokoException {
        String path = String.format("/v1/payments/%s", paymentId);
        ApiRequest request = new ApiRequest(
                BaseAddress.API,
                ApiResource.RequestMethod.GET,
                path,
                null,
                null
        );

        return this.request(request, Payment.class);
    }

    public Payment confirm(String paymentId, PaymentConfirmParams params) throws LokoException {
        return confirm(paymentId, params, null);
    }
    public Payment confirm(String paymentId, PaymentConfirmParams params, RequestOptions options) throws LokoException {
        String path = String.format("/v1/payments/%s/confirm", paymentId);
        ApiRequest request = new ApiRequest(
                BaseAddress.API,
                ApiResource.RequestMethod.POST,
                path,
                ApiRequestParams.paramsToMap(params),
                options
        );

        return this.request(request, Payment.class);
    }

    public LokoCollection<Payment> list(ListParams params) throws LokoException {

        String path = "/v1/payments";
        ApiRequest request = new ApiRequest(
                BaseAddress.API,
                ApiResource.RequestMethod.GET,
                path,
                ApiRequestParams.paramsToMap(params),
                null
        );

        return this.request(request, new TypeToken<LokoCollection<Payment>>() {}.getType());
    }
}
