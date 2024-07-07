package io.lokopay.model;

import com.google.gson.annotations.SerializedName;
import io.lokopay.exception.ApiException;
import io.lokopay.exception.LokoException;
import io.lokopay.net.*;
import io.lokopay.param.PayoutCreateParams;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class Payout extends ApiResource implements HasId, MetadataStore<Payout> {

    @SerializedName("id")
    String id;

    @SerializedName("object")
    String object;

    @SerializedName("amount")
    String amount;

    @SerializedName("currency")
    String currency;

    @SerializedName("description")
    String description;

    @SerializedName("customer")
    @Getter(lombok.AccessLevel.NONE)
    @Setter(lombok.AccessLevel.NONE)
    ExpandableField<Customer> customer;

    @SerializedName("destination_network_details")
    BlockchainNetwork destinationNetworkDetails;

    @SerializedName("blockchain_transaction_details")
    List<BlockchainTransaction> blockchainTransactionDetails;

    @SerializedName("status")
    String status;

    @SerializedName("expires_at")
    Long expiresAt;

    @SerializedName("created_at")
    Long createdAt;

    @Getter(onMethod_ = {@Override})
    @SerializedName("metadata")
    Map<String, String> metadata;

//    public void setCustomer(Customer customer) {
//        if (customer == null) {}
//    }

    public Customer getCustomer() {
        return (this.customer != null) ? this.customer.getExpanded() : null;
    }

    public BlockchainNetwork getBLockchainNetworkDeatils() {
        return (this.destinationNetworkDetails != null) ? this.destinationNetworkDetails : null;
    }

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
