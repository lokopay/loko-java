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
public class Payout extends ApiResource implements HasId {

    @SerializedName("id")
    String id;

    @SerializedName("object")
    String object;

    @SerializedName("object_secret")
    String objectSecret;

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
    List<BlockchainNetwork> destinationNetworkDetails;

    @SerializedName("blockchain_transaction_details")
    List<BlockchainTransaction> blockchainTransactionDetails;

    @SerializedName("status")
    String status;

    @SerializedName("expires_at")
    Long expiresAt;

    @SerializedName("created_at")
    Long createdAt;

//    public void setCustomer(Customer customer) {
//        if (customer == null) {}
//    }

    public Customer getCustomer() {
        return (this.customer != null) ? this.customer.getExpanded() : null;
    }

}
