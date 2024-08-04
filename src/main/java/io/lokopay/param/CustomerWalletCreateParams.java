package io.lokopay.param;

import com.google.gson.annotations.SerializedName;
import io.lokopay.net.ApiRequestParams;

public class CustomerWalletCreateParams extends ApiRequestParams {

    @SerializedName("currency")
    String currency;

    @SerializedName("network")
    String network;

    @SerializedName("customer")
    CustomerParams customer;

    private CustomerWalletCreateParams(
            String currency,
            String network,
            CustomerParams customer
    ) {
        this.currency = currency;
        this.network = network;
        this.customer = customer;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String currency;

        private String network;

        private CustomerParams customer;

        public CustomerWalletCreateParams build() {
            return new CustomerWalletCreateParams(
                    this.currency,
                    this.network,
                    this.customer
            );
        }

        public Builder setCurrency(String currency) {
            this.currency = currency;
            return this;
        }

        public Builder setNetwork(String network) {
            this.network = network;
            return this;
        }

        public Builder setCustomer(CustomerParams customer) {
            this.customer = customer;
            return this;
        }
    }
}
