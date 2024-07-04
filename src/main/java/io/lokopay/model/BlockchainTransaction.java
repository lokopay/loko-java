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
public class BlockchainTransaction extends ApiResource implements HasId, MetadataStore<BlockchainTransaction> {

    @SerializedName("id")
    String id;

    @SerializedName("amount")
    String amount;

    @SerializedName("currency")
    String currency;

    @SerializedName("address")
    String address;

    @SerializedName("tx_hash")
    String txHash;

    @SerializedName("confirmations")
    Long confirmations;

    @Getter(onMethod_ = {@Override})
    @SerializedName("metadata")
    Map<String, String> metadata;

    @Override
    public BlockchainTransaction update(Map<String, Object> params) throws LokoException {
        return null;
    }

    @Override
    public BlockchainTransaction update(Map<String, Object> params, RequestOptions options) throws LokoException {
        return null;
    }
}
