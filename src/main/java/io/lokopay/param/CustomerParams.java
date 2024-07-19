package io.lokopay.param;

import com.google.gson.annotations.SerializedName;
import io.lokopay.model.EncryptableField;
import io.lokopay.net.ApiRequestParams;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CustomerParams extends ApiRequestParams {

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

    public CustomerParams(
            String id,
            String ipAddress,
            String email,
            String address,
            String network,
            String currency
    ) {
        this.id = id;
        this.ipAddress = ipAddress;
        this.email = email;
        this.destinationAddress = address;
        this.destinationNetwork = network;
        this.destinationCurrency = currency;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String id;

        private String ipAddress;

        private String email;

        private String destinationAddress;

        private String destinationNetwork;

        private String destinationCurrency;

        public CustomerParams build() {
            return new CustomerParams(
                    this.id,
                    this.ipAddress,
                    this.email,
                    this.destinationAddress,
                    this.destinationNetwork, this.destinationCurrency
            );
        }

        public Builder setId(String id) {
            this.id = id;
            return this;
        }

        public Builder setIpAddress(String ipAddress) {
            this.ipAddress = ipAddress;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setDestinationAddress(String destinationAddress) {
            this.destinationAddress = destinationAddress;
            return this;
        }

        public Builder setDestinationNetwork(String destinationNetwork) {
            this.destinationNetwork = destinationNetwork;
            return this;
        }

        public Builder setDestinationCurrency(String destinationCurrency) {
            this.destinationCurrency = destinationCurrency;
            return this;
        }
    }

}
