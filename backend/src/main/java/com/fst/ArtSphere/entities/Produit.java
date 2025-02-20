package com.fst.ArtSphere.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "produit")
@EqualsAndHashCode(exclude = {"products", "categorie", "artiste"}) 
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    private String titre;
    private String description;
    private double prix;
    private String typeOeuvre;
    private boolean disponibilite;
    private String imageURL;
    private String dimension;
    private String format;

    private Integer stock;

    @ManyToOne(cascade = CascadeType.ALL) 
    @JoinColumn(name = "id_categorie") 
    private Categorie categorie;

    @ManyToOne(cascade = CascadeType.ALL) 
    @JsonBackReference
    @JoinColumn(name = "id_artiste") 
    private User artiste; 

    @OneToMany(mappedBy = "produit", cascade = CascadeType.ALL, orphanRemoval = true) 
    private List<CommandeProduit> products = new ArrayList<>();
}
