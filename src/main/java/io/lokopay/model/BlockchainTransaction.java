package io.lokopay.model;

import com.google.gson.annotations.SerializedName;
import io.lokopay.exception.LokoException;
import io.lokopay.net.ApiResource;
import io.lokopay.net.RequestOptions;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class BlockchainTransaction extends ApiResource implements HasId {

    @SerializedName("id")
    String id;

    @SerializedName("amount")
    String amount;

    @SerializedName("currency")
    String currency;

    @SerializedName("network")
    String network;

    @EncryptableField
    @SerializedName("address")
    String destinationAddress;

    @EncryptableField
    @SerializedName("tx_hash")
    String txHash;

    @SerializedName("confirmations")
    Long confirmations;

    @SerializedName("block_height")
    Long blockHeight;

    @SerializedName("block_time")
    Long blockTime;
}
