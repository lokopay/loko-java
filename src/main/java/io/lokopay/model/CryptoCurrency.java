package io.lokopay.model;

import com.google.gson.annotations.SerializedName;
import io.lokopay.net.ApiResource;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class CryptoCurrency extends ApiResource implements HasId{

    @SerializedName("id")
    String id;

    @SerializedName("price")
    Double price;

    @SerializedName("price_pair")
    String pair;

    @SerializedName("amount")
    String amount;

    @SerializedName("currency")
    String currency;

    @SerializedName("network")
    String network;

    @SerializedName("description")
    String description;
}
