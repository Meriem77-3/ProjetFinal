package com.fst.ArtSphere.services;

import com.fst.ArtSphere.entities.Commande;
import com.fst.ArtSphere.entities.Produit;
import com.fst.ArtSphere.entities.User;
import com.fst.ArtSphere.repositories.CommandeRepository;
import com.fst.ArtSphere.repositories.ProduitRepository;
import com.fst.ArtSphere.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class CommandeService {

    @Autowired
    private CommandeRepository commandeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProduitRepository produitRepository;

    // ===== Méthodes de création et modification =====

    @Transactional
    public Commande creerCommandeAvecVerification(int clientId, Map<Integer, Integer> panier, double montantTotal) {
        // Récupérer l'utilisateur
        User client = userRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client non trouvé avec l'ID: " + clientId));

        // Vérifier le stock pour chaque produit
        for (Map.Entry<Integer, Integer> entry : panier.entrySet()) {
            int produitId = entry.getKey();
            int quantite = entry.getValue();

            Produit produit = produitRepository.findById(produitId)
                    .orElseThrow(() -> new RuntimeException("Produit non trouvé avec l'ID: " + produitId));

            if (produit.getStock() < quantite) {
                throw new RuntimeException("Stock insuffisant pour le produit: " + produit.getTitre()
                        + ". Stock disponible: " + produit.getStock());
            }
        }

        // Créer la commande
        Commande commande = new Commande();
        commande.setClient(client);
        commande.setDateCommande(LocalDate.now());
        commande.setEtatCommande("EN_ATTENTE");
        commande.setMontantTotal(montantTotal);

        commande = commandeRepository.save(commande);

        // Mettre à jour le stock
        for (Map.Entry<Integer, Integer> entry : panier.entrySet()) {
            int produitId = entry.getKey();
            int quantite = entry.getValue();

            Produit produit = produitRepository.findById(produitId)
                    .orElseThrow(() -> new RuntimeException("Produit non trouvé avec l'ID: " + produitId));

            produit.setStock(produit.getStock() - quantite);
            produitRepository.save(produit);
        }

        return commande;
    }

    public Commande updateCommande(int commandeId, Commande updatedCommande) {
        Commande existingCommande = commandeRepository.findById(commandeId)
                .orElseThrow(() -> new RuntimeException("Commande non trouvée"));

        existingCommande.setEtatCommande(updatedCommande.getEtatCommande());
        existingCommande.setDateCommande(updatedCommande.getDateCommande());
        existingCommande.setMontantTotal(updatedCommande.getMontantTotal());

        return commandeRepository.save(existingCommande);
    }

    // ===== Méthodes de consultation =====

    public List<Commande> getHistoriqueCommandes(int clientId) {
        return commandeRepository.findByClientIdOrderByDateCommandeDesc(clientId);
    }

    public List<Commande> getHistoriqueCommandesParEtat(int clientId, String etat) {
        if (!isEtatValide(etat)) {
            throw new IllegalArgumentException("État de commande invalide");
        }
        return commandeRepository.findByClientIdAndEtatCommandeOrderByDateCommandeDesc(clientId, etat);
    }

    public List<Commande> getHistoriqueCommandesParPeriode(int clientId, LocalDate debut, LocalDate fin) {
        return commandeRepository.findByClientIdAndDateCommandeBetweenOrderByDateCommandeDesc(clientId, debut, fin);
    }

    public List<Commande> getCommandesParMontantMinimum(int clientId, Double montantMin) {
        return commandeRepository.findByClientIdAndMontantTotalGreaterThanEqual(clientId, montantMin);
    }

    public List<Commande> getCommandesRecentes(int clientId, int nombreJours) {
        LocalDate dateDebut = LocalDate.now().minusDays(nombreJours);
        return commandeRepository.findRecentCommandesByClientId(clientId, dateDebut);
    }

    // ===== Méthodes de recherche spécifique =====

    public Commande getCommandeById(int commandeId) {
        return commandeRepository.findById(commandeId)
                .orElseThrow(() -> new RuntimeException("Commande non trouvée"));
    }

    public Commande getCommandeById(int commandeId, int clientId) {
        return commandeRepository.findByIdAndClientId(commandeId, clientId)
                .orElseThrow(() -> new RuntimeException("Commande non trouvée"));
    }

    // ===== Méthodes de gestion d'état =====

    public Commande updateEtatCommande(int commandeId, String nouvelEtat) {
        if (!isEtatValide(nouvelEtat)) {
            throw new IllegalArgumentException("État de commande invalide");
        }

        Commande commande = commandeRepository.findById(commandeId)
                .orElseThrow(() -> new RuntimeException("Commande non trouvée"));

        commande.setEtatCommande(nouvelEtat);
        return commandeRepository.save(commande);
    }

    public long getNombreCommandesParEtat(int clientId, String etat) {
        if (!isEtatValide(etat)) {
            throw new IllegalArgumentException("État de commande invalide");
        }
        return commandeRepository.countByClientIdAndEtatCommande(clientId, etat);
    }

    // ===== Méthodes de suivi =====

    public String suiviCommande(int commandeId, int clientId) {
        Commande commande = commandeRepository.findByIdAndClientId(commandeId, clientId)
                .orElseThrow(() -> new RuntimeException("Commande non trouvée"));

        String etat = commande.getEtatCommande();
        String message;

        switch (etat) {
            case "EN_ATTENTE":
                message = "Votre commande est en attente de confirmation";
                break;
            case "CONFIRMEE":
                message = "Votre commande a été confirmée et est en cours de préparation";
                break;
            case "EN_LIVRAISON":
                message = "Votre commande est en cours de livraison";
                break;
            case "LIVREE":
                message = "Votre commande a été livrée le " + commande.getDateCommande();
                break;
            case "ANNULEE":
                message = "Votre commande a été annulée";
                break;
            default:
                message = "État de la commande inconnu";
        }

        return message + " (Commande #" + commandeId + ")";
    }

    // ===== Méthodes statistiques =====

    public Double getMontantMoyenCommandes(int clientId) {
        return commandeRepository.calculateAverageOrderAmount(clientId);
    }

    // ===== Méthodes de suppression =====

    public void deleteCommande(int commandeId) {
        if (!commandeRepository.existsById(commandeId)) {
            throw new RuntimeException("Commande non trouvée");
        }
        commandeRepository.deleteById(commandeId);
    }

    // ===== Méthodes utilitaires =====

    private boolean isEtatValide(String etat) {
        return etat != null && (
                etat.equals("EN_ATTENTE") ||
                etat.equals("CONFIRMEE") ||
                etat.equals("EN_LIVRAISON") ||
                etat.equals("LIVREE") ||
                etat.equals("ANNULEE")
        );
    }

    public List<Commande> getAllCommandes() {
        return commandeRepository.findAll();
    }

    public List<Commande> getCommandesParEtat(String etat) {
        return commandeRepository.findByEtatCommande(etat);
    }
}