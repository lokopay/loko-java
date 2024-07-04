package io.lokopay.model;

import com.google.gson.annotations.SerializedName;
import io.lokopay.exception.ApiException;
import io.lokopay.exception.LokoException;
import io.lokopay.net.*;
import io.lokopay.param.PayoutCreateParams;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class Payout extends ApiResource implements MetadataStore<Payout> {

    @SerializedName("id")
    String id;

    @SerializedName("amount")
    String amount;

    @SerializedName("currency")
    String currency;

    @SerializedName("customer")
    @Getter(lombok.AccessLevel.NONE)
    @Setter(lombok.AccessLevel.NONE)
    ExpandableField<Customer> customer;

    @SerializedName("created")
    Long created;

    @Getter(onMethod_ = {@Override})
    @SerializedName("metadata")
    Map<String, String> metadata;

    public static Payout create(PayoutCreateParams params) throws LokoException {
        return create(params, null);
    }

    public static Payout create(PayoutCreateParams params, RequestOptions options)
            throws LokoException {
        String path = "/v1/payouts";
        ApiResource.checkNullTypedParams(path, params);
        ApiRequest request =
                new ApiRequest(
                        BaseAddress.API,
                        RequestMethod.POST,
                        path,
                        ApiRequestParams.paramsToMap(params),
                        options
                );
        return getGlobalResponseGetter().request(request, Payout.class);
    }

    @Override
    public Payout update(Map<String, Object> params) throws LokoException {
        return null;
    }

    @Override
    public Payout update(Map<String, Object> params, RequestOptions options) throws LokoException {
        return null;
    }
}
