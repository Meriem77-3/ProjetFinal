package com.fst.ArtSphere.dto;

import lombok.Data;

@Data
public class PaymentRequest {
    private String token;
    private Long amount;
    private String currency;
} 