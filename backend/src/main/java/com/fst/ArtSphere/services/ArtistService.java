package com.fst.ArtSphere.services;

import com.fst.ArtSphere.entities.Produit;
import com.fst.ArtSphere.repositories.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fst.ArtSphere.entities.User;
import com.fst.ArtSphere.entities.Role;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistService {

    @Autowired
    private ArtistRepository artisteRepository;

    public User createArtiste(User artiste) {
        // Ajouter le rôle "Artiste" avant d'enregistrer
        Role artisteRole = new Role();
        artisteRole.setName("Artiste");
        artiste.getRoles().add(artisteRole);
        return artisteRepository.save(artiste);
    }

    public List<User> getAllArtistes() {
        return artisteRepository.findAllArtistes();
    }

    public Optional<User> getArtisteById(int id) {
        Optional<User> artiste = artisteRepository.findById(id);
        // Vérifier que l'utilisateur a le rôle "Artiste"
        return artiste.filter(u -> u.getRoles().stream().anyMatch(r -> r.getName().equals("Artiste")));
    }

    public User updateArtiste(int id, User artisteDetails) {
        Optional<User> artiste = getArtisteById(id);
        if (artiste.isPresent()) {
            User existingArtiste = artiste.get();
            existingArtiste.setNom(artisteDetails.getNom());
            existingArtiste.setPrenom(artisteDetails.getPrenom());
            existingArtiste.setAdresse(artisteDetails.getAdresse());
            existingArtiste.setEmail(artisteDetails.getEmail());
            existingArtiste.setMotDePasse(artisteDetails.getMotDePasse());
            existingArtiste.setBiographie(artisteDetails.getBiographie());
            return artisteRepository.save(existingArtiste);
        }
        return null;
    }

    public void deleteArtiste(int id) {
        Optional<User> artiste = getArtisteById(id);
        artiste.ifPresent(artisteRepository::delete);
    }

    public Optional<User> login(String email, String password) {
        Optional<User> artiste = artisteRepository.findArtisteByEmail(email);
        if (artiste.isPresent() && artiste.get().getMotDePasse().equals(password)) {
            return artiste;
        }
        return Optional.empty();
    }

    public List<Produit> getProduitsByArtisteId(int artisteId) {
        return artisteRepository.findProduitsByArtisteId(artisteId);
    }
}
