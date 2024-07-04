package io.lokopay;

import io.lokopay.exception.LokoException;
import io.lokopay.model.CryptoCurrency;
import io.lokopay.model.Customer;
import io.lokopay.model.LokoCollection;
import io.lokopay.model.Payment;
import io.lokopay.param.ListParams;
import io.lokopay.param.PaymentConfirmParams;
import io.lokopay.param.PaymentCreateParams;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");

        for (int i = 1; i <= 5; i++) {
            //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
            // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
            System.out.println("i = " + i);
        }

        LokoClient client = new LokoClient(
                "gKpGuaGYxDmcsGwqbEnXSeWcJwYUKsjW",
                "mGGvXEfkVrFMQQDhcGCqNyOMcKWjWyIV"
        );


        Customer customer = new Customer();
        customer.setId("0123456789");

        PaymentCreateParams createParams =
                PaymentCreateParams
                        .builder()
                        .setAmount("10000")
                        .setCurrency("USDC")
                        .setDescription("Order #123")
                        .setCustomer(customer)
                        .build();

        Payment payment = new Payment();
        try {
            payment = client.payments().create(createParams);
            System.out.println(payment);
            System.out.println("payment id: " + payment.getId());
        } catch (LokoException e) {
            e.printStackTrace();
        }

        try {
            payment = client.payments().retrieve(payment.getId());
            System.out.println(payment);
        } catch (LokoException e) {
            e.printStackTrace();
        }

        CryptoCurrency crypto = payment.getSupportedCryptocurrencies().get(0);

        PaymentConfirmParams confirmParams =
                PaymentConfirmParams
                        .builder()
                        .setAmountDue(crypto.getAmount())
                        .setCurrencyDue(crypto.getCurrency())
                        .setSymbol(crypto.getSymbol())
                        .build();
        try {
            payment = client.payments().confirm(payment.getId(), confirmParams);
            System.out.println("confirmed payment: " + payment);
        } catch (LokoException e) {
            e.printStackTrace();
        }

        try {
            payment = client.payments().retrieve(payment.getId());
            System.out.println("address: " +  payment.getCurrencyDueAddress());
        } catch (LokoException e) {
            e.printStackTrace();
        }

        ListParams listParams =
                ListParams
                        .builder()
                        .setLimit(10L)
                        .setEndingBefore("266265c1-4112-4aa7-9ea9-108708546cbc") //payment.getId()
                        .build();

        try {
            LokoCollection<Payment> payments = client.payments().list(listParams);
            System.out.println("Payments: " + payments);
//            System.out.println("The last Payment" + payments.);
        } catch (LokoException e) {
            e.printStackTrace();
        }

        client.PrintSomething();
    }
}