package io.lokopay.model;

import com.google.gson.annotations.SerializedName;
import io.lokopay.net.ApiResource;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class TransferWithNativeToken extends ApiResource {
    @SerializedName("enabled")
    Boolean enabled;

    @SerializedName("amount")
    String amount;

    @SerializedName("network")
    String network;

    @SerializedName("currency")
    String currency;
}
