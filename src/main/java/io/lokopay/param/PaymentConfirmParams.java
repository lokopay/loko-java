package io.lokopay.param;

import com.google.gson.annotations.SerializedName;
import io.lokopay.net.ApiRequestParams;
import lombok.Getter;


@Getter
public class PaymentConfirmParams extends ApiRequestParams {

    @SerializedName("amount_due")
    private String amountDue;

    @SerializedName("currency_due")
    private String currencyDue;

    @SerializedName("symbol")
    private String symbol;

    private PaymentConfirmParams(
            String amountDue,
            String currencyDue,
            String symbol
    ) {
        this.amountDue = amountDue;
        this.currencyDue = currencyDue;
        this.symbol = symbol;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String amountDue;

        private String currencyDue;

        private String symbol;

        public PaymentConfirmParams build() {
            return new PaymentConfirmParams(
                    this.amountDue, this.currencyDue, this.symbol
            );

        }

        public Builder setAmountDue(String amountDue) {
            this.amountDue = amountDue;
            return this;
        }

        public Builder setCurrencyDue(String currencyDue) {
            this.currencyDue = currencyDue;
            return this;
        }

        public Builder setSymbol(String symbol) {
            this.symbol = symbol;
            return this;
        }
    }
}
