package io.lokopay.param;

import com.google.gson.annotations.SerializedName;
import io.lokopay.model.Customer;
import io.lokopay.net.ApiRequestParams;
import lombok.Getter;

@Getter
public class PaymentCreateParams extends ApiRequestParams {

    @SerializedName("amount")
    String amount;

    @SerializedName("currency")
    String currency;

    @SerializedName("description")
    String description;

    @SerializedName("customer")
    CustomerParams customer;

    private PaymentCreateParams(
            String amount,
            String currency,
            String description,
            CustomerParams customer
    ) {
        this.amount = amount;
        this.currency = currency;
        this.description = description;
        this.customer = customer;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String amount;

        private String currency;

        private String description;

        private CustomerParams customer;

        public PaymentCreateParams build() {
            return new PaymentCreateParams(
                    this.amount,
                    this.currency,
                    this.description,
                    this.customer
            );
        }

        public Builder setAmount(String amount) {
            this.amount = amount;
            return this;
        }

        public Builder setCurrency(String currency) {
            this.currency = currency;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setCustomer(CustomerParams customer) {
            this.customer = customer;
            return this;
        }
    }
}
