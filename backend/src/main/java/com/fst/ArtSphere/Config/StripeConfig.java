package com.fst.ArtSphere.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.stripe.Stripe;

@Configuration
public class StripeConfig {
    @Value("${stripe.secret.key}")
    private String stripeSecretKey;

    @Bean
    public Stripe stripe() {
        Stripe.apiKey = stripeSecretKey;
        return null;
    }
} 