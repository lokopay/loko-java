package io.lokopay.model;

import com.google.gson.annotations.SerializedName;
import io.lokopay.net.ApiResource;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class BlockchainNetwork extends ApiResource implements HasId {

    @SerializedName("id")
    private String id;

    @SerializedName("destination_amount")
    private String destinationAmount;

    @SerializedName("destination_currency")
    private String destinationCurrency;

    @SerializedName("destination_network_fee")
    private String destinationNetworkFee;

    @SerializedName("destination_network_fee_currency")
    private String destinationNetworkFeeCurrency;

    @SerializedName("destination_network_fee_monetary")
    private String destinationNetworkFeeMonetary;
}
