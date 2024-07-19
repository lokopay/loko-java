package io.lokopay.model;

import com.google.gson.annotations.SerializedName;
import io.lokopay.net.ApiResource;
import io.lokopay.util.Security;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class Customer extends ApiResource implements HasId {

    @SerializedName("id")
    String id;

    @SerializedName("ip_address")
    String ipAddress;

    @SerializedName("email")
    String email;

    @EncryptableField
    @SerializedName("destination_address")
    String destinationAddress;

    @SerializedName("destination_network")
    String destinationNetwork;

    @SerializedName("destination_currency")
    String destinationCurrency;
}