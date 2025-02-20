
package com.fst.ArtSphere.dto;

import java.time.LocalDate;
import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommandeDTO {
    private int id;
    private LocalDate dateCommande;
    private String etatCommande;
    private Double montantTotal;
    private int userId;
    private List<CommandeProduitDTO> products;
}
