package com.fst.ArtSphere.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.FetchType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.ArrayList;
@Entity
@Table(name = "user")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;
    private String prenom;
    private String motDePasse;
    private String email;
    private String adresse;
    private String telephone;
    private String token;
    private String biographie;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    @Builder.Default
    private List<Role> roles = new ArrayList<>();
  @JsonManagedReference
  @OneToMany(mappedBy = "artiste", cascade = CascadeType.ALL)
  private List<Produit> produits = new ArrayList<>();

  @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
  private List<Commande> commandes = new ArrayList<>();

  @OneToMany(mappedBy = "client")
  private List<Notification> notifications = new ArrayList<>();
        
    


 
  

}
