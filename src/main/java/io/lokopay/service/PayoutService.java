package io.lokopay.service;

import com.google.gson.reflect.TypeToken;
import io.lokopay.exception.LokoException;
import io.lokopay.model.EncryptableField;
import io.lokopay.model.LokoCollection;
import io.lokopay.model.Payout;
import io.lokopay.net.*;
import io.lokopay.param.CustomerParams;
import io.lokopay.param.ListParams;
import io.lokopay.param.PayoutConfirmParams;
import io.lokopay.param.PayoutCreateParams;
import io.lokopay.util.Security;

import java.lang.reflect.Field;

public final class PayoutService extends ApiService {

    public PayoutService(LokoResponseGetter responseGetter) {
        super(responseGetter);
    }

    public Payout create(PayoutCreateParams params) throws LokoException {
        return create(params, null);
    }

    public Payout create(PayoutCreateParams params, RequestOptions options) throws LokoException {

        try {
            encryptField(params, (LokoResponseGetter) this.getResponseGetter());
        } catch ( Exception e) {
            throw new LokoException(e.getMessage(), "0", 0);
        }

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

    private void encryptField(Object params, LokoResponseGetter responseGetter) throws Exception {

        for (Field field : params.getClass().getDeclaredFields()) {
            if (CustomerParams.class.isAssignableFrom(field.getType())) {
                field.setAccessible(true);
                CustomerParams customerParams = (CustomerParams) field.get(params);

                for (Field field2 : field.getType().getDeclaredFields()) {
                    if (field2.isAnnotationPresent(EncryptableField.class)) {
                         field2.setAccessible(true);
                         String plaintText = (String) field2.get(customerParams);
                         String encrypteddat = Security.AESEncrypt(plaintText, responseGetter.getSecrityKey());
                         field2.set(customerParams, encrypteddat);
                    }
                }
            }
        }
    }
}
