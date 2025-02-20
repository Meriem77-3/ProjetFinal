package com.fst.ArtSphere.controllers;

import com.fst.ArtSphere.dto.ProduitDTO;
import com.fst.ArtSphere.services.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produits")
@CrossOrigin(origins = "http://localhost:3000")
public class ProduitController {

    @Autowired
    private ProduitService produitService;

    @PostMapping
    public ResponseEntity<ProduitDTO> createProduit(@RequestBody ProduitDTO produitDTO) {
        ProduitDTO newProduit = produitService.createProduit(produitDTO);
        return new ResponseEntity<>(newProduit, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProduitDTO>> getAllProduits() {
        List<ProduitDTO> produits = produitService.getAllProduits();
        return ResponseEntity.ok(produits);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProduitDTO> getProduitById(@PathVariable int id) {
        ProduitDTO produit = produitService.getProduitById(id);
        return ResponseEntity.ok(produit);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProduitDTO> updateProduit(@PathVariable int id, @RequestBody ProduitDTO produitDTO) {
        ProduitDTO updatedProduit = produitService.updateProduit(id, produitDTO);
        return ResponseEntity.ok(updatedProduit);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduit(@PathVariable int id) {
        produitService.deleteProduit(id);
        return ResponseEntity.noContent().build();
    }

    /*@PutMapping("/{id}/stock/increment")
    public ResponseEntity<ProduitDTO> incrementStock(@PathVariable int id, @RequestParam int quantity) {
        ProduitDTO produit = produitService.incrementStock(id, quantity);
        return ResponseEntity.ok(produit);
    }

    @PutMapping("/{id}/stock/decrement")
    public ResponseEntity<ProduitDTO> decrementStock(@PathVariable int id, @RequestParam int quantity) {
        ProduitDTO produit = produitService.decrementStock(id, quantity);
        return ResponseEntity.ok(produit);
    }*/
}
