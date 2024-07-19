package io.lokopay;

import io.lokopay.exception.LokoException;
import io.lokopay.model.*;
import io.lokopay.param.*;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.println("Hello and welcome!");

        // initial loko client
        LokoClient client = new LokoClient(
                false,
                "gKpGuaGYxDmcsGwqbEnXSeWcJwYUKsjW",
                "mGGvXEfkVrFMQQDhcGCqNyOMcKWjWyIV"
        );


        paymentProcess(client);
//        payoutProcess(client);
//        networkfeeProcess(client);



        System.out.println("============ end of process ============");
    }

    private static void paymentProcess(LokoClient client) {

        // ============ start payment process ============
        System.out.println("============ start payment process ============");

        CustomerParams customerParams =
                CustomerParams
                        .builder()
                        .setId("0123456")
                        .setEmail("ryo@lokopay.io")
                        .build();

        // create payment params
        PaymentCreateParams createParams =
                PaymentCreateParams
                        .builder()
                        .setAmount("10000")
                        .setCurrency("USDC")
                        .setDescription("Order #123")
                        .setCustomer(customerParams)
                        .build();

        // create a new payment
        Payment payment = new Payment();
        try {
            payment = client.payments().create(createParams);
            System.out.println("payment id: " + payment.getId());
            System.out.println("payment status: " + payment.getStatus());
        } catch (LokoException e) {
            e.printStackTrace();
        }

        // retrieve the payment for prices
        try {

            payment = client.payments().retrieve(payment.getId());
            System.out.println("payment supported cryptos: " + payment.getSupportedCryptocurrencies());
            System.out.println("payment status: " + payment.getStatus());

        } catch (LokoException e) {
            e.printStackTrace();
        }


        List<CryptoCurrency> cryptos = payment.getSupportedCryptocurrencies();
        CryptoCurrency pickedCrypto = new CryptoCurrency();

        for (CryptoCurrency cryptoCurrency : cryptos) {
            if (cryptoCurrency.getPair().equals("USDC-USDC")) {
                pickedCrypto = cryptoCurrency;
                break;
            }
        }

        // confirm the payment with chosen cryptocurrency
        PaymentConfirmParams confirmParams =
                PaymentConfirmParams
                        .builder()
                        .setCryptocurrency(pickedCrypto)
                        .build();
        try {
            payment = client.payments().confirm(payment.getId(), confirmParams);
            System.out.println("payment status: " + payment.getStatus());

        } catch (LokoException e) {
            e.printStackTrace();
        }

        // retrieve the payment for address
        try {
            Thread.sleep(4000);
            payment = client.payments().retrieve(payment.getId());
            System.out.println("payment status: " + payment.getStatus());
            System.out.println("address: " +  payment.getCurrencyDueAddress());
            System.out.println("payment customer: " + payment.getCustomer());

        } catch (LokoException | InterruptedException e) {
            e.printStackTrace();
        }

        // retrieve payment history


        Instant now = Instant.now();

        // Calculate the time 10 days ago
        Instant tenDaysAgo = now.minus(10, ChronoUnit.DAYS);
        ListParams listParams =
                ListParams
                        .builder()
                        .setLimit(10L)
                        .setCreatedFrom(tenDaysAgo.getEpochSecond())
//                        .setEndingBefore(payment.getId()) //payment.getId()
                        .build();

        try {
            LokoCollection<Payment> payments = client.payments().list(listParams);
            System.out.println("Payments: " + payments);
        } catch (LokoException e) {
            e.printStackTrace();
        }
    }

    private static void payoutProcess(LokoClient client) {
        // ============ start payout process ============
        System.out.println("============ start payout process ============");

        // setup customer wallet info
        CustomerParams customerParams =
                CustomerParams
                        .builder()
                        .setId("0123456")
                        .setEmail("ryo@lokopay.io")
                        .setDestinationCurrency("USDC")
                        .setDestinationNetwork("Ethereum")
                        .setDestinationAddress("0x1aBB9515E78C516AFC1A6F2222401da183654d67")
                        .build();

        // create payout params
        PayoutCreateParams payoutParams =
                PayoutCreateParams
                        .builder()
                        .setAmount("2000")
                        .setCurrency("USDC")
                        .setDescription("withdraw #1234")
                        .setCustomer(customerParams)
                        .build();

        // create a new payout
        Payout payout = new Payout();
        try {
            payout = client.payouts().create(payoutParams);
            System.out.println("payout id: " + payout.getId());
            System.out.println("payout status: " + payout.getStatus());
        } catch (LokoException e) {
            System.out.println("error code: " + e.getCode());
            System.out.println("error message: " + e.getMessage());
            return;
        }

        // retrieve the payout for network fee
        try {

            int count = 0;
            while(payout.getDestinationNetworkDetails() == null) {
                Thread.sleep(4000);
                payout = client.payouts().retrieve(payout.getId());

                System.out.println("payout status: " + payout.getStatus() + " count " + count);
                count ++;
            }

            System.out.println("payout supported networks: " + payout.getDestinationNetworkDetails());
            System.out.println("payout status: " + payout.getStatus());
        } catch (LokoException | InterruptedException e) {
            e.printStackTrace();
        }

        // pick the network want to receive crypto, for now always the USDC
        BlockchainNetwork network = payout.getDestinationNetworkDetails().get(0);

        System.out.println("network_detail: " + network);
        // confirm the payout
//        PayoutConfirmParams confirmPayoutParams =
//                PayoutConfirmParams
//                        .builder()
//                        .setDestinationNetworkDetail(network)
//                        .build();
//
//        //or set with following
//        PayoutConfirmParams confirmParams =
//                PayoutConfirmParams
//                        .builder()
//                        .setId(network.getId())
//                        .setAmount(network.getDestinationAmount())
//                        .setCurrency(network.getDestinationCurrency())
//                        .setNetwork(network.getDestinationNetwork())
//                        .setNetworkFee(network.getDestinationNetworkFee())
//                        .setNetworkFeeCurrency(network.getDestinationNetworkFeeCurrency())
//                        .setNetworkFeeMonetary(network.getDestinationNetworkFeeMonetary())
//                        .build();
//
//        try {
//            payout = client.payouts().confirm(payout.getId(), confirmPayoutParams);
//        } catch (LokoException e) {
//            e.printStackTrace();
//        }
//
//        // retrieve the payout for network fee
//        try {
//            Thread.sleep(4000);
//            payout = client.payouts().retrieve(payout.getId());
//            System.out.println("payout status: " + payout.getStatus());
//
//        } catch (LokoException | InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    private static void networkfeeProcess(LokoClient client) {
        // ============ start payout process ============
        System.out.println("============ start network fee process ============");

        try {
            NetworkFee networkFees = client.networkFees().list();
            System.out.println(networkFees);
        } catch (LokoException e) {
            e.printStackTrace();
        }
    }
}