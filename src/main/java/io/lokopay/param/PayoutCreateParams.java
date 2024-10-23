package io.lokopay.param;

import com.google.gson.annotations.SerializedName;
import io.lokopay.model.Customer;
import io.lokopay.model.TransferWithNativeToken;
import io.lokopay.net.ApiRequestParams;
import lombok.Getter;

@Getter
public class PayoutCreateParams extends ApiRequestParams {

    @SerializedName("amount")
    String amount;

    @SerializedName("currency")
    String currency;

    @SerializedName("description")
    String description;

    @SerializedName("customer")
    CustomerParams customer;

    @SerializedName("transfer_with_native_token")
    TransferWithNativeToken transferNativeToken;

    private PayoutCreateParams(
            String amount,
            String currency,
            String description,
            CustomerParams customer,
            TransferWithNativeToken transferNativeToken
    ) {
        this.amount = amount;
        this.currency = currency;
        this.description = description;
        this.customer = customer;
        this.transferNativeToken = transferNativeToken;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String amount;

        private String currency;

        private String description;

        private CustomerParams customer;

        private TransferWithNativeToken transferNativeToken;

        public PayoutCreateParams build() {
            return new PayoutCreateParams(
                    this.amount,
                    this.currency,
                    this.description,
                    this.customer,
                    this.transferNativeToken
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

        public Builder setTransferNativeToken(Boolean enable) {

            if (enable) {
                this.transferNativeToken = new TransferWithNativeToken();
                this.transferNativeToken.setEnabled(true);
            }
            return this;
        }
    }
}
