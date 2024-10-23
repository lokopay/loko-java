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
    String id;

    @SerializedName("destination_amount")
    String destinationAmount;

    @SerializedName("destination_currency")
    String destinationCurrency;

    @SerializedName("destination_network")
    String destinationNetwork;

    @SerializedName("destination_transaction_fee_fixed")
    String destinationTransactionFeeFixed;

    @SerializedName("destination_transaction_fee_percentage")
    String destinationTransactionFeePercentage;

    @SerializedName("destination_transaction_fee_type")
    String destinationTransactionFeeType;

    @SerializedName("destination_transaction_fee_currency")
    String destinationTransactionFeeCurrency;

    @SerializedName("destination_network_fee")
    String destinationNetworkFee;

    @SerializedName("destination_network_fee_currency")
    String destinationNetworkFeeCurrency;

    @SerializedName("destination_network_fee_monetary")
    String destinationNetworkFeeMonetary;

    @SerializedName("transfer_with_native_token")
    TransferWithNativeToken transferWithNativeToken;
}
