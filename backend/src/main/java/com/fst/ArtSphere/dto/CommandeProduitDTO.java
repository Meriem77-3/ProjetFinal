package com.fst.ArtSphere.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommandeProduitDTO {
    private int id;
    private Double prix;
    private Integer quantite;
    private int commandeId;
    private int productId;
}
