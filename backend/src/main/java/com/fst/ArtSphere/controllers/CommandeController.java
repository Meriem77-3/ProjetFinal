package com.fst.ArtSphere.controllers;

import com.fst.ArtSphere.entities.Commande;
import com.fst.ArtSphere.services.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/commandes")
@CrossOrigin(origins = "*")
public class CommandeController {

    @Autowired
    private CommandeService commandeService;

    // Création d'une commande
    @PostMapping
    public ResponseEntity<?> creerCommande(@RequestBody Map<String, Object> request) {
        try {
            int clientId = (int) request.get("clientId");
            @SuppressWarnings("unchecked")
            Map<Integer, Integer> panier = (Map<Integer, Integer>) request.get("panier");
            double montantTotal = ((Number) request.get("montantTotal")).doubleValue();

            if (panier == null || panier.isEmpty()) {
                return new ResponseEntity<>("Le panier ne peut pas être vide", HttpStatus.BAD_REQUEST);
            }

            Commande commande = commandeService.creerCommandeAvecVerification(clientId, panier, montantTotal);
            return new ResponseEntity<>(commande, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Erreur lors de la création de la commande: " + e.getMessage(), 
                HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Mise à jour d'une commande
    @PutMapping("/{commandeId}")
    public ResponseEntity<?> updateCommande(
            @PathVariable int commandeId,
            @RequestBody Commande updatedCommande) {
        try {
            Commande commande = commandeService.updateCommande(commandeId, updatedCommande);
            return new ResponseEntity<>(commande, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // Suppression d'une commande
    @DeleteMapping("/{commandeId}")
    public ResponseEntity<?> deleteCommande(@PathVariable int commandeId) {
        try {
            commandeService.deleteCommande(commandeId);
            return new ResponseEntity<>("Commande supprimée avec succès", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // Historique des commandes
    @GetMapping("/historique/{clientId}")
    public ResponseEntity<List<Commande>> getHistoriqueCommandes(@PathVariable int clientId) {
        List<Commande> historique = commandeService.getHistoriqueCommandes(clientId);
        return new ResponseEntity<>(historique, HttpStatus.OK);
    }

    // Historique par état
    @GetMapping("/historique/{clientId}/etat/{etat}")
    public ResponseEntity<?> getHistoriqueCommandesParEtat(
            @PathVariable int clientId,
            @PathVariable String etat) {
        try {
            List<Commande> historique = commandeService.getHistoriqueCommandesParEtat(clientId, etat);
            return new ResponseEntity<>(historique, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Historique par période
    @GetMapping("/historique/{clientId}/periode")
    public ResponseEntity<?> getHistoriqueCommandesParPeriode(
            @PathVariable int clientId,
            @RequestParam LocalDate debut,
            @RequestParam LocalDate fin) {
        try {
            if (debut.isAfter(fin)) {
                return new ResponseEntity<>("La date de début doit être antérieure à la date de fin", 
                    HttpStatus.BAD_REQUEST);
            }
            List<Commande> historique = commandeService.getHistoriqueCommandesParPeriode(clientId, debut, fin);
            return new ResponseEntity<>(historique, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Obtenir une commande par ID
    @GetMapping("/{commandeId}")
    public ResponseEntity<?> getCommandeById(@PathVariable int commandeId) {
        try {
            Commande commande = commandeService.getCommandeById(commandeId);
            return new ResponseEntity<>(commande, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // Obtenir une commande par ID et client
    @GetMapping("/client/{clientId}/commande/{commandeId}")
    public ResponseEntity<?> getCommandeByIdAndClient(
            @PathVariable int commandeId,
            @PathVariable int clientId) {
        try {
            Commande commande = commandeService.getCommandeById(commandeId, clientId);
            return new ResponseEntity<>(commande, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // Mise à jour de l'état
    @PatchMapping("/{commandeId}/etat")
    public ResponseEntity<?> updateEtatCommande(
            @PathVariable int commandeId,
            @RequestParam String nouvelEtat) {
        try {
            Commande commande = commandeService.updateEtatCommande(commandeId, nouvelEtat);
            return new ResponseEntity<>(commande, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // Suivi de commande
    @GetMapping("/suivi/{commandeId}/{clientId}")
    public ResponseEntity<?> suiviCommande(
            @PathVariable int commandeId,
            @PathVariable int clientId) {
        try {
            String message = commandeService.suiviCommande(commandeId, clientId);
            return new ResponseEntity<>(message, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // Toutes les commandes
    @GetMapping
    public ResponseEntity<List<Commande>> getAllCommandes() {
        List<Commande> commandes = commandeService.getAllCommandes();
        return new ResponseEntity<>(commandes, HttpStatus.OK);
    }

    // Commandes par état
    @GetMapping("/etat/{etat}")
    public ResponseEntity<?> getCommandesParEtat(@PathVariable String etat) {
        try {
            List<Commande> commandes = commandeService.getCommandesParEtat(etat);
            return new ResponseEntity<>(commandes, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}