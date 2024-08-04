package io.lokopay.model;

import com.google.gson.annotations.SerializedName;
import io.lokopay.net.ApiResource;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@EqualsAndHashCode(callSuper = false)
public class CustomerWallet extends ApiResource implements HasId {

    @SerializedName("id")
    String id;

    @SerializedName("object")
    String object;

    @SerializedName("object_secret")
    String objectSecret;

    @SerializedName("description")
    String description;

    @SerializedName("customer")
    Customer customer;

    @SerializedName("supported_cryptocurrencies")
    List<CryptoCurrency> supportedCryptocurrencies;

    @SerializedName("wallet_addresses")
    List<WalletAddress> walletAddresses;
}
