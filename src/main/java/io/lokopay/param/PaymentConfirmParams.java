package io.lokopay.param;

import com.google.gson.annotations.SerializedName;
import io.lokopay.model.CryptoCurrency;
import io.lokopay.net.ApiRequestParams;
import lombok.Getter;


@Getter
public class PaymentConfirmParams extends ApiRequestParams {

    @SerializedName("cryptocurrency")
    CryptoCurrency cryptocurrency;

//    @SerializedName("amount_due")
//    private String amountDue;
//
//    @SerializedName("currency_due")
//    private String currencyDue;
//
//    @SerializedName("symbol")
//    private String symbol;

    private PaymentConfirmParams(
            CryptoCurrency cryptocurrency
    ) {
        this.cryptocurrency = cryptocurrency;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private CryptoCurrency cryptocurrency;

        public PaymentConfirmParams build() {
            return new PaymentConfirmParams(
                    this.cryptocurrency
            );
        }

        public Builder setCryptocurrency(CryptoCurrency cryptocurrency) {
            this.cryptocurrency = cryptocurrency;
            return this;
        }

//        public Builder setAmountDue(String amountDue) {
//            this.amountDue = amountDue;
//            return this;
//        }
//
//        public Builder setCurrencyDue(String currencyDue) {
//            this.currencyDue = currencyDue;
//            return this;
//        }
//
//        public Builder setSymbol(String symbol) {
//            this.symbol = symbol;
//            return this;
//        }
    }
}
