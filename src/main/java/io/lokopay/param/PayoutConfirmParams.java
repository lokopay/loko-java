package io.lokopay.param;

import com.google.gson.annotations.SerializedName;
import io.lokopay.model.BlockchainNetwork;
import io.lokopay.net.ApiRequestParams;
import lombok.Getter;

@Getter
public class PayoutConfirmParams extends ApiRequestParams {

    @SerializedName("destination_network_detail")
    BlockchainNetwork destinationNetworkDetail;

    private PayoutConfirmParams(BlockchainNetwork destinationNetworkDetail) {
        this.destinationNetworkDetail = destinationNetworkDetail;
    }
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private  BlockchainNetwork destinationNetworkDetail;

        public PayoutConfirmParams build() {
            return new PayoutConfirmParams(
                    this.destinationNetworkDetail
            );
        }

        public Builder setDestinationNetworkDetail(
                BlockchainNetwork destinationNetworkDetail
        ) {
            this.destinationNetworkDetail = destinationNetworkDetail;
            return this;
        }

        public Builder setId(String blockchainNetworkId) {
            if (this.destinationNetworkDetail == null) {
                this.destinationNetworkDetail = new BlockchainNetwork();
            }

            this.destinationNetworkDetail.setId(blockchainNetworkId);
            return this;
        }

        public Builder setAmount(String amount) {
            if (this.destinationNetworkDetail == null) {
                this.destinationNetworkDetail = new BlockchainNetwork();
            }

            this.destinationNetworkDetail.setDestinationAmount(amount);
            return this;
        }

        public Builder setCurrency(String currency) {
            if (this.destinationNetworkDetail == null) {
                this.destinationNetworkDetail = new BlockchainNetwork();
            }

            this.destinationNetworkDetail.setDestinationCurrency(currency);
            return this;
        }

        public Builder setNetwork(String network) {
            if (this.destinationNetworkDetail == null) {
                this.destinationNetworkDetail = new BlockchainNetwork();
            }

            this.destinationNetworkDetail.setDestinationNetwork(network);
            return this;
        }

        public Builder setNetworkFee(String networkFee) {
            if (this.destinationNetworkDetail == null) {
                this.destinationNetworkDetail = new BlockchainNetwork();
            }

            this.destinationNetworkDetail.setDestinationNetworkFee(networkFee);
            return this;
        }

        public Builder setNetworkFeeCurrency(String networkFeeCurrency) {
            if (this.destinationNetworkDetail == null) {
                this.destinationNetworkDetail = new BlockchainNetwork();
            }

            this.destinationNetworkDetail.setDestinationNetworkFeeCurrency(networkFeeCurrency);
            return this;
        }

        public Builder setNetworkFeeMonetary(String networkFeeMonetary) {
            if (this.destinationNetworkDetail == null) {
                this.destinationNetworkDetail = new BlockchainNetwork();
            }
            this.destinationNetworkDetail.setDestinationNetworkFeeMonetary(networkFeeMonetary);
            return this;
        }
    }


//    @SerializedName("id")
//    private String id;
//
//    @SerializedName("amount_send")
//    private String amountSend;
//
//    @SerializedName("currency_send")
//    private String currencySend;
//
//    @SerializedName("destination_network_fee")
//    private String destinationNetworkFee;
//
//    @SerializedName("destination_network_fee_currency")
//    private String destinationNetworkFeeCurrency;
//
//    @SerializedName("destination_network_fee_monetary")
//    private String destinationNetworkFeeMonetary;
//
//    private PayoutConfirmParams(
//            String id,
//            String amountSend,
//            String currencySend,
//            String destinationNetworkFee,
//            String destinationNetworkFeeCurrency,
//            String destinationNetworkFeeMonetary
//    ) {
//        this.id = id;
//        this.amountSend = amountSend;
//        this.currencySend = currencySend;
//        this.destinationNetworkFee = destinationNetworkFee;
//        this.destinationNetworkFeeCurrency = destinationNetworkFeeCurrency;
//        this.destinationNetworkFeeMonetary = destinationNetworkFeeMonetary;
//    }

//    public static Builder builder() {
//        return new Builder();
//    }
//
//    public static class Builder {
//
//        private String id;
//
//        private String amountSend;
//
//        private String currencySend;
//
//        private String destinationNetworkFee;
//
//        private String destinationNetworkFeeCurrency;
//
//        private String destinationNetworkFeeMonetary;
//
//        public PayoutConfirmParams build() {
//            return new PayoutConfirmParams(
//                    this.id,
//                    this.amountSend,
//                    this.currencySend,
//                    this.destinationNetworkFee,
//                    this.destinationNetworkFeeCurrency,
//                    this.destinationNetworkFeeMonetary
//            );
//        }
//
//        public Builder setId(String id) {
//            this.id = id;
//            return this;
//        }
//
//        public Builder setAmountSend(String amountSend) {
//            this.amountSend = amountSend;
//            return this;
//        }
//
//        public Builder setCurrencySend(String currencySend) {
//            this.currencySend = currencySend;
//            return this;
//        }
//
//        public Builder setDestinationNetworkFee(String destinationNetworkFee) {
//            this.destinationNetworkFee = destinationNetworkFee;
//            return this;
//        }
//
//        public Builder setDestinationNetworkFeeCurrency(String destinationNetworkFeeCurrency) {
//            this.destinationNetworkFeeCurrency = destinationNetworkFeeCurrency;
//            return this;
//        }
//
//        public Builder setDestinationNetworkFeeMonetary(String destinationNetworkFeeMonetary) {
//            this.destinationNetworkFeeMonetary = destinationNetworkFeeMonetary;
//            return this;
//        }
//    }
}
