package io.lokopay.model;

import com.google.gson.annotations.SerializedName;
import io.lokopay.net.ApiResource;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class WalletAddress extends ApiResource implements HasId {

    @SerializedName("id")
    String id;

    @SerializedName("description")
    String description;

    @EncryptableField
    @SerializedName("address")
    String address;

    @SerializedName("address_currency")
    String addressCurrency;

    @SerializedName("address_network")
    String addressNetwork;
}
