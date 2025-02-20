// CommandeRepository.java
package com.fst.ArtSphere.repositories;

import com.fst.ArtSphere.entities.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface CommandeRepository extends JpaRepository<Commande, Integer> {
    // Méthodes corrigées pour utiliser client.id au lieu de userId
    List<Commande> findByClientIdOrderByDateCommandeDesc(int clientId);
    
    Optional<Commande> findByIdAndClientId(int commandeId, int clientId);
    
    List<Commande> findByEtatCommande(String etat);
    
    List<Commande> findByClientIdAndEtatCommandeOrderByDateCommandeDesc(int clientId, String etat);
    
    List<Commande> findByClientIdAndDateCommandeBetweenOrderByDateCommandeDesc(
        int clientId, 
        LocalDate debut, 
        LocalDate fin
    );

    // Requêtes JPQL corrigées
    @Query("SELECT c FROM Commande c WHERE c.client.id = :clientId AND c.montantTotal >= :montantMin")
    List<Commande> findByClientIdAndMontantTotalGreaterThanEqual(int clientId, Double montantMin);
    
    @Query("SELECT COUNT(c) FROM Commande c WHERE c.client.id = :clientId AND c.etatCommande = :etat")
    long countByClientIdAndEtatCommande(int clientId, String etat);
    
    @Query("SELECT c FROM Commande c WHERE c.client.id = :clientId AND c.dateCommande >= :date")
    List<Commande> findRecentCommandesByClientId(int clientId, LocalDate date);
    
    @Query("SELECT AVG(c.montantTotal) FROM Commande c WHERE c.client.id = :clientId")
    Double calculateAverageOrderAmount(int clientId);
}