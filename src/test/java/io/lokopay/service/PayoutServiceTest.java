package io.lokopay.service;

import io.lokopay.LokoClient;
import io.lokopay.exception.LokoException;
import io.lokopay.model.Payment;
import io.lokopay.param.CustomerParams;
import io.lokopay.param.PaymentCreateParams;

import static org.junit.jupiter.api.Assertions.*;

class PayoutServiceTest {

    @org.junit.jupiter.api.Test
    void create() {

        CustomerParams customerParams =
                CustomerParams
                        .builder()
                        .setId("0123456")
                        .setEmail("ryo@lokopay.io")
                        .build();


        PaymentCreateParams createParams =
                PaymentCreateParams
                        .builder()
                        .setAmount("10000")
                        .setCurrency("USDC")
                        .setDescription("Order #123")
                        .setCustomer(customerParams)
                        .build();

        try {
            Payment payment = newClient().payments().create(createParams);
            assertNotNull(payment);
            assertNotNull(payment.getId());
            assertNotNull(payment.getCreatedAt());
        } catch (LokoException e) {
            fail(e.getMessage());
        }
    }

    @org.junit.jupiter.api.Test
    void testCreate() {
    }

    @org.junit.jupiter.api.Test
    void retrieve() {
    }

    @org.junit.jupiter.api.Test
    void confirm() {
    }

    @org.junit.jupiter.api.Test
    void testConfirm() {
    }

    @org.junit.jupiter.api.Test
    void list() {
    }

    LokoClient newClient() {
        return new LokoClient(
                false,
                "gKpGuaGYxDmcsGwqbEnXSeWcJwYUKsjW",
                "mGGvXEfkVrFMQQDhcGCqNyOMcKWjWyIV"
        );
    }
}