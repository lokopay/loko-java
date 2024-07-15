// File generated from our OpenAPI spec
package io.lokopay.model;

import com.google.gson.annotations.SerializedName;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class LokoError extends LokoObject {

  @SerializedName("code")
  String code;

  /**
   * For some errors that could be handled programmatically, a short string indicating the <a
   * href="https://Loko.com/docs/error-codes">error code</a> reported.
   *
   * <p>One of {@code cancel_payment_payment_invalid}, {@code cancel_payment_payment_status_error}, {@code
   * cancel_payout_payout_invalid}, {@code cancel_payout_payout_status_error},
   * {@code confirm_payment_crypto_currency_invalid}，
   * {@code confirm_payment_crypto_currency_required}, {@code confirm_payment_payment_expired},
   * {@code confirm_payment_payment_invalid}, {@code confirm_payment_payment_status_error},
   * {@code confirm_payment_price_expired}, {@code confirm_payout_destination_network_detail_invalid},
   * {@code confirm_payout_destination_network_detail_required}, {@code confirm_payout_insufficient_balance},
   * {@code confirm_payout_payout_invalid}, {@code confirm_payout_payout_status_error},
   * {@code create_payment_amount_invalid}, {@code create_payment_amount_required},
   * {@code create_payment_currency_required}, {@code create_payment_customer_id_required},
   * {@code create_payment_not_support_currency}, {@code create_payout_amount_invalid},
   * {@code create_payout_amount_required},  {@code create_payout_currency_required},
   * {@code create_payout_customer_id_required},  {@code create_payout_destination_address_required},
   * {@code create_payout_destination_currency_required}, {@code create_payout_destination_network_required}
   * {@code create_payout_invalid_address}, {@code create_payout_not_support_currency}, {@code create_payout_not_support_network},
   * {@code get_payment_payment_invalid}, {@code get_payout_payout_invalid}, {@code server_error},
   * {@code signature_error}
   */
  @SerializedName("code_name")
  String codeName;


  /**
   * A human-readable message providing more details about the error. For card errors, these
   * messages can be shown to your users.
   */
  @SerializedName("message")
  String message;

//  /**
//   * If the error is parameter-specific, the parameter related to the error. For example, you can
//   * use this to display a message near the correct form field.
//   */
//  @SerializedName("param")
//  String param;
//
//  /**
//   * A PaymentIntent guides you through the process of collecting a payment from your customer. We
//   * recommend that you create exactly one PaymentIntent for each order or customer session in your
//   * system. You can reference the PaymentIntent later to see the history of payment attempts for a
//   * particular session.
//   *
//   * <p>A PaymentIntent transitions through <a
//   * href="https://Loko.com/docs/payments/intents#intent-statuses">multiple statuses</a>
//   * throughout its lifetime as it interfaces with Loko.js to perform authentication flows and
//   * ultimately creates at most one successful charge.
//   *
//   * <p>Related guide: <a href="https://Loko.com/docs/payments/payment-intents">Payment Intents
//   * API</a>
//   */
//  @SerializedName("payment_intent")
//  PaymentIntent paymentIntent;
//
//  /**
//   * PaymentMethod objects represent your customer's payment instruments. You can use them with <a
//   * href="https://Loko.com/docs/payments/payment-intents">PaymentIntents</a> to collect payments
//   * or save them to Customer objects to store instrument details for future payments.
//   *
//   * <p>Related guides: <a href="https://Loko.com/docs/payments/payment-methods">Payment
//   * Methods</a> and <a href="https://Loko.com/docs/payments/more-payment-scenarios">More Payment
//   * Scenarios</a>.
//   */
//  @SerializedName("payment_method")
//  PaymentMethod paymentMethod;
//
//  /**
//   * If the error is specific to the type of payment method, the payment method type that had a
//   * problem. This field is only populated for invoice-related errors.
//   */
//  @SerializedName("payment_method_type")
//  String paymentMethodType;
//
//  /** A URL to the request log entry in your dashboard. */
//  @SerializedName("request_log_url")
//  String requestLogUrl;
//
//  /**
//   * A SetupIntent guides you through the process of setting up and saving a customer's payment
//   * credentials for future payments. For example, you can use a SetupIntent to set up and save your
//   * customer's card without immediately collecting a payment. Later, you can use <a
//   * href="https://Loko.com/docs/api#payment_intents">PaymentIntents</a> to drive the payment
//   * flow.
//   *
//   * <p>Create a SetupIntent when you're ready to collect your customer's payment credentials. Don't
//   * maintain long-lived, unconfirmed SetupIntents because they might not be valid. The SetupIntent
//   * transitions through multiple <a
//   * href="https://docs.Loko.com/payments/intents#intent-statuses">statuses</a> as it guides you
//   * through the setup process.
//   *
//   * <p>Successful SetupIntents result in payment credentials that are optimized for future
//   * payments. For example, cardholders in <a
//   * href="https://Loko.com/guides/strong-customer-authentication">certain regions</a> might need
//   * to be run through <a href="https://docs.Loko.com/strong-customer-authentication">Strong
//   * Customer Authentication</a> during payment method collection to streamline later <a
//   * href="https://docs.Loko.com/payments/setup-intents">off-session payments</a>. If you use the
//   * SetupIntent with a <a
//   * href="https://Loko.com/docs/api#setup_intent_object-customer">Customer</a>, it automatically
//   * attaches the resulting payment method to that Customer after successful setup. We recommend
//   * using SetupIntents or <a
//   * href="https://Loko.com/docs/api#payment_intent_object-setup_future_usage">setup_future_usage</a>
//   * on PaymentIntents to save payment methods to prevent saving invalid or unoptimized payment
//   * methods.
//   *
//   * <p>By using SetupIntents, you can reduce friction for your customers, even as regulations
//   * change over time.
//   *
//   * <p>Related guide: <a href="https://docs.Loko.com/payments/setup-intents">Setup Intents
//   * API</a>
//   */
//  @SerializedName("setup_intent")
//  SetupIntent setupIntent;
//
//  @SerializedName("source")
//  PaymentSource source;

//  /**
//   * The type of error returned. One of {@code api_error}, {@code card_error}, {@code
//   * idempotency_error}, or {@code invalid_request_error}
//   */
  @SerializedName("type")
  String type;
}
