package com.fst.ArtSphere.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProduitDTO {
    private Integer id;
    private String titre;
    private String description;
    private Double prix;
    private String typeOeuvre;
    private boolean disponibilite;
    private String imageURL;
    private String dimension;
    private String format;
    private Integer stock;
    private Integer id_categorie;
    private Integer id_artiste;
}
