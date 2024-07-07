package io.lokopay;

import io.lokopay.exception.LokoException;
import io.lokopay.model.*;
import io.lokopay.param.ListParams;
import io.lokopay.param.PaymentConfirmParams;
import io.lokopay.param.PaymentCreateParams;
import io.lokopay.param.PayoutCreateParams;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.println("Hello and welcome!");

        // initial loko client
        LokoClient client = new LokoClient(
                true,
                "gKpGuaGYxDmcsGwqbEnXSeWcJwYUKsjW",
                "mGGvXEfkVrFMQQDhcGCqNyOMcKWjWyIV"
        );

        PaymentConfirmParams params =
                PaymentConfirmParams
                        .builder()
                        .setAmountDue("304000")
                        .setCurrencyDue("BTC")
                        .setSymbol("BTC")
                        .build();

        Payment payment = new Payment();
        try {
            payment = client.payments().confirm("c7b8c80d-e7e5-431b-bf9d-4f626ee4947d", params);
        } catch (LokoException e) {

            System.out.println(e.getMessage());
            System.out.println("Error Code: " + e.getCode());
        }

//        // create customer
//        Customer customer = new Customer();
//        customer.setId("0123456789");
//        customer.setEmail("ryo@lokopay.io");
//
//
//       // create payment params
//       PaymentCreateParams createParams =
//               PaymentCreateParams
//                       .builder()
//                       .setAmount("10000")
//                       .setCurrency("USDC")
//                       .setDescription("Order #123")
//                       .setCustomer(customer)
//                       .build();
//
//       // create new payment
//       Payment payment = new Payment();
//       try {
//           payment = client.payments().create(createParams);
//           System.out.println("payment id: " + payment.getId());
//           System.out.println("payment status: " + payment.getStatus());
//       } catch (LokoException e) {
//           e.printStackTrace();
//       }
//
//       // retrieve payment for prices
//       try {
//           payment = client.payments().retrieve(payment.getId());
//           System.out.println("payment supported cryptos: " + payment.getSupportedCryptocurrencies());
//           System.out.println("payment status: " + payment.getStatus());
//
//       } catch (LokoException e) {
//           e.printStackTrace();
//       }
//
//       CryptoCurrency crypto = payment.getSupportedCryptocurrencies().get(0);
//
//       // confirm payment
//       PaymentConfirmParams confirmParams =
//               PaymentConfirmParams
//                       .builder()
//                       .setAmountDue(crypto.getAmount())
//                       .setCurrencyDue(crypto.getCurrency())
//                       .setSymbol(crypto.getSymbol())
//                       .build();
//       try {
//           payment = client.payments().confirm(payment.getId(), confirmParams);
//           System.out.println("payment status: " + payment.getStatus());
//       } catch (LokoException e) {
//           e.printStackTrace();
//       }
//
//       // retrieve payment for address
//       try {
//           payment = client.payments().retrieve(payment.getId());
//           System.out.println("address: " +  payment.getCurrencyDueAddress());
//           System.out.println("payment status: " + payment.getStatus());
//       } catch (LokoException e) {
//           e.printStackTrace();
//       }
//
//       // retrieve payment history
//       ListParams listParams =
//               ListParams
//                       .builder()
//                       .setLimit(10L)
//                       .setEndingBefore(payment.getId()) //payment.getId()
//                       .build();
//
//       try {
//           LokoCollection<Payment> payments = client.payments().list(listParams);
//           System.out.println("Payments: " + payments);
//       } catch (LokoException e) {
//           e.printStackTrace();
//       }
//
//
//        customer.setDestinationAddress("tb1q6st7m3pss43lznpanadzaswd8g0n8gcgftshud");
//        customer.setDestinationNetwork("Ethereum");
//        // payout
//        PayoutCreateParams payoutParams =
//                PayoutCreateParams
//                        .builder()
//                        .setAmount("1000")
//                        .setCurrency("USDC")
//                        .setDescription("withdraw #1234")
//                        .setCustomer(customer)
//                        .build();
//
//        // create new payout
//        Payout payout = new Payout();
//        try {
//            payout = client.payouts().create(payoutParams);
//            System.out.println("payout id: " + payout.getId());
//            System.out.println("payout status: " + payout.getStatus());
//        } catch (LokoException e) {
//            e.printStackTrace();
//        }
//
//
//
//
//        try {
//            Thread.sleep(4000);
//            payout = client.payouts().retrieve(payout.getId());
//
//            System.out.println("payout status: " + payout.getStatus());
//            System.out.println("payout network details: " + payout.getBLockchainNetworkDeatils());
//        } catch (LokoException | InterruptedException e) {
//            e.printStackTrace();
//        }

        client.PrintSomething();
    }
}