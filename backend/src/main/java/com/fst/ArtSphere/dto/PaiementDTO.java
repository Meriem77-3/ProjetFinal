package com.fst.ArtSphere.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaiementDTO {
    private int id;
    private LocalDate datePaiement;
    private String methodePaiement;
    private Double montant;
    private String statutPaiement;
    private int commandeId;
}
