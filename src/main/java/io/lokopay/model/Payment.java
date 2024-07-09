package io.lokopay.model;

import com.google.gson.annotations.SerializedName;
import io.lokopay.exception.LokoException;
import io.lokopay.net.*;
import io.lokopay.param.PaymentCreateParams;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class Payment extends ApiResource implements HasId { //MetadataStore<Payment>,

//    @Getter(onMethod_ = {@Override})
    @SerializedName("id")
    String id;

    @SerializedName("object")
    String object;

    @SerializedName("object_secret")
    String objectSecret;

    @SerializedName("amount")
    String amount;

    @SerializedName("currency")
    String currency;

    @SerializedName("description")
    String description;

    @SerializedName("customer")
    @Getter(lombok.AccessLevel.NONE)
    @Setter(lombok.AccessLevel.NONE)
    ExpandableField<Customer> customer;

    @SerializedName("amount_due")
    String amountDue;

    @SerializedName("currency_due")
    String currencyDue;

    @SerializedName("currency_due_network")
    String currencyDueNetwork;

    @SerializedName("currency_due_address")
    String currencyDueAddress;

    @SerializedName("amount_paid")
    String amountPaid;

    @SerializedName("currency_paid")
    String currencyPaid;

    @SerializedName("supported_cryptocurrencies")
    List<CryptoCurrency> supportedCryptocurrencies;

    @SerializedName("blockchain_transaction_details")
//    @Getter(lombok.AccessLevel.NONE)
//    @Setter(lombok.AccessLevel.NONE)
    List<BlockchainTransaction> blockchainTransactionDetails;

    @SerializedName("failed_reason")
    String failedReason;

    @SerializedName("status")
    String status;

    @SerializedName("price_expires_at")
    Long priceExpiresAt;

    @SerializedName("expires_at")
    Long expiresAt;

    @SerializedName("created_at")
    Long createdAt;

//    @Getter(onMethod_ = {@Override})
//    @SerializedName("metadata")
//    Map<String, String> metadata;

    /** Get ID of expandable {@code customer} object. */
    public String getCustomer() {
        return (this.customer != null) ? this.customer.getId() : null;
    }

    public void setCustomer(String id) {
        this.customer = ApiResource.setExpandableFieldId(id, this.customer);
    }

    /** Get expanded {@code customer}. */
    public Customer getCustomerObject() {
        return (this.customer != null) ? this.customer.getExpanded() : null;
    }

    public void setCustomerObject(Customer expandableObject) {
        this.customer = new ExpandableField<Customer>(expandableObject.getId(), expandableObject);
    }

    public CryptoCurrency getCryptoCurrency(String id) {
        for (CryptoCurrency cryptoCurrency : this.supportedCryptocurrencies) {
            if (cryptoCurrency.getId().equals(id)) {
                return cryptoCurrency;
            }
        }

        return null;
    }


//    public static Payment create(PaymentCreateParams params) throws LokoException {
//        return create(params,  null);
//    }
//
//    public static Payment create(PaymentCreateParams params, RequestOptions options)
//            throws LokoException {
//        String path = "/v1/payments";
//        ApiResource.checkNullTypedParams(path, params);
//        ApiRequest request =
//                new ApiRequest(
//                        BaseAddress.API,
//                        ApiResource.RequestMethod.POST,
//                        path,
//                        ApiRequestParams.paramsToMap(params),
//                        options
//                );
////                        ApiMode.V1);
//        return getGlobalResponseGetter().request(request, Payment.class);
//    }
//
//    @Override
//    public Payment update(Map<String, Object> params) throws LokoException {
//        return null;
//    }
//
//    @Override
//    public Payment update(Map<String, Object> params, RequestOptions options) throws LokoException {
//        return null;
//    }
}
