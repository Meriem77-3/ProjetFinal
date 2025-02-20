package com.fst.ArtSphere.repositories;

import com.fst.ArtSphere.entities.User;
import com.fst.ArtSphere.entities.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArtistRepository extends JpaRepository<User, Integer> {
    @Query("SELECT u FROM User u JOIN u.roles r WHERE r.name = 'Artiste'")
    List<User> findAllArtistes();
    
    @Query("SELECT u FROM User u JOIN u.roles r WHERE r.name = 'Artiste' AND u.email = :email")
    Optional<User> findArtisteByEmail(String email);
    
    @Query("SELECT p FROM Produit p WHERE p.artiste.id = :artisteId")
    List<Produit> findProduitsByArtisteId(int artisteId);
}
