package com.fst.ArtSphere.services;

import com.fst.ArtSphere.dto.PaymentRequest;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class StripeService {

    @Value("${stripe.secret.key}")
    private String secretKey;

    @PostConstruct
    public void init() {
        Stripe.apiKey = secretKey;
    }

    public String createPaymentIntent(PaymentRequest paymentRequest) throws StripeException {
        Map<String, Object> params = new HashMap<>();
        params.put("amount", paymentRequest.getAmount());
        params.put("currency", paymentRequest.getCurrency());
        params.put("payment_method", paymentRequest.getToken());
        params.put("confirm", true);

        PaymentIntent paymentIntent = PaymentIntent.create(params);
        return paymentIntent.getId();
    }
} 