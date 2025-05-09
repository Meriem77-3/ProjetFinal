package com.fst.ArtSphere.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.fst.ArtSphere.entities.*;
import com.fst.ArtSphere.services.*;


import java.util.*;

@RestController
@RequestMapping("/artsphere/users/artist")
@CrossOrigin(origins = "http://localhost:3000")
public class ArtistController {

    @Autowired
    private ArtistService artisteService;

    @PostMapping
    public ResponseEntity<User> createArtiste(@RequestBody User artiste) {
        User newArtiste = artisteService.createArtiste(artiste);
        return new ResponseEntity<>(newArtiste, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<User>> getAllArtistes() {
        List<User> artistes = artisteService.getAllArtistes();
        return new ResponseEntity<>(artistes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getArtisteById(@PathVariable int id) {
        Optional<User> artiste = artisteService.getArtisteById(id);
        return artiste.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateArtiste(@PathVariable int id, @RequestBody User artisteDetails) {
        User updatedArtiste = artisteService.updateArtiste(id, artisteDetails);
        if (updatedArtiste != null) {
            return new ResponseEntity<>(updatedArtiste, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArtiste(@PathVariable int id) {
        artisteService.deleteArtiste(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}/produits")
    public ResponseEntity<List<Produit>> getProduitsByArtiste(@PathVariable int id) {
        List<Produit> produits = artisteService.getProduitsByArtisteId(id);
        return new ResponseEntity<>(produits, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginRequest) {
        String email = loginRequest.get("email");
        String password = loginRequest.get("password");
        Optional<User> artiste = artisteService.login(email, password);
        if (artiste.isPresent()) {
            return ResponseEntity.ok(artiste.get());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email ou mot de passe incorrect");
        }
    }
}
