package io.lokopay.param;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

@Getter
public class PayoutConfirmParams {

    @SerializedName("id")
    private String id;

    @SerializedName("amount_send")
    private String amountSend;

    @SerializedName("currency_send")
    private String currencySend;

    @SerializedName("destination_network_fee")
    private String destinationNetworkFee;

    @SerializedName("destination_network_fee_currency")
    private String destinationNetworkFeeCurrency;

    @SerializedName("destination_network_fee_monetary")
    private String destinationNetworkFeeMonetary;

    private PayoutConfirmParams(
            String id,
            String amountSend,
            String currencySend,
            String destinationNetworkFee,
            String destinationNetworkFeeCurrency,
            String destinationNetworkFeeMonetary
    ) {
        this.id = id;
        this.amountSend = amountSend;
        this.currencySend = currencySend;
        this.destinationNetworkFee = destinationNetworkFee;
        this.destinationNetworkFeeCurrency = destinationNetworkFeeCurrency;
        this.destinationNetworkFeeMonetary = destinationNetworkFeeMonetary;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String id;

        private String amountSend;

        private String currencySend;

        private String destinationNetworkFee;

        private String destinationNetworkFeeCurrency;

        private String destinationNetworkFeeMonetary;

        public PayoutConfirmParams build() {
            return new PayoutConfirmParams(
                    this.id,
                    this.amountSend,
                    this.currencySend,
                    this.destinationNetworkFee,
                    this.destinationNetworkFeeCurrency,
                    this.destinationNetworkFeeMonetary
            );
        }

        public Builder setId(String id) {
            this.id = id;
            return this;
        }

        public Builder setAmountSend(String amountSend) {
            this.amountSend = amountSend;
            return this;
        }

        public Builder setCurrencySend(String currencySend) {
            this.currencySend = currencySend;
            return this;
        }

        public Builder setDestinationNetworkFee(String destinationNetworkFee) {
            this.destinationNetworkFee = destinationNetworkFee;
            return this;
        }

        public Builder setDestinationNetworkFeeCurrency(String destinationNetworkFeeCurrency) {
            this.destinationNetworkFeeCurrency = destinationNetworkFeeCurrency;
            return this;
        }

        public Builder setDestinationNetworkFeeMonetary(String destinationNetworkFeeMonetary) {
            this.destinationNetworkFeeMonetary = destinationNetworkFeeMonetary;
            return this;
        }
    }
}
