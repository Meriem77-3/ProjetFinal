package com.fst.ArtSphere.services;

import com.fst.ArtSphere.dto.ProduitDTO;
import com.fst.ArtSphere.entities.Categorie;
import com.fst.ArtSphere.entities.Produit;
import com.fst.ArtSphere.entities.User;
import com.fst.ArtSphere.repositories.ProduitRepository;
import com.fst.ArtSphere.execeptions.ResourceNotFoundException;
import com.fst.ArtSphere.execeptions.StockException;
import org.modelmapper.ModelMapper;
import com.fst.ArtSphere.repositories.CategorieRepository;
import com.fst.ArtSphere.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProduitService {

    @Autowired
    private ProduitRepository produitRepository;
    @Autowired
    private CategorieRepository categorieRepository; 
    @Autowired
    private UserRepository userRepository; 

    @Autowired
    private ModelMapper modelMapper;

    public ProduitDTO createProduit(ProduitDTO produitDTO) {
        Produit produit = modelMapper.map(produitDTO, Produit.class);
    
       
        User artist = userRepository.findById(produitDTO.getId_artiste())
                .orElseThrow(() -> new ResourceNotFoundException("Artist not found with ID: " + produitDTO.getId_artiste()));
        produit.setArtiste(artist);
    
        Categorie categorie = categorieRepository.findById(produitDTO.getId_categorie())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with ID: " + produitDTO.getId_categorie()));
        produit.setCategorie(categorie);
    
        produit.setDisponibilite(produit.getStock() > 0);
        Produit savedProduit = produitRepository.save(produit);
        return modelMapper.map(savedProduit, ProduitDTO.class);
    }
    public List<ProduitDTO> getAllProduits() {
        return produitRepository.findAll()
                .stream()
                .map(produit -> modelMapper.map(produit, ProduitDTO.class))
                .collect(Collectors.toList());
    }

    public ProduitDTO getProduitById(int id) {
        Produit produit = produitRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produit non trouvé avec l'id: " + id));
        return modelMapper.map(produit, ProduitDTO.class);
    }

    public ProduitDTO updateProduit(int id, ProduitDTO produitDTO) {
        Produit existingProduit = produitRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produit non trouvé avec l'id: " + id));

        existingProduit.setTitre(produitDTO.getTitre());
        existingProduit.setDescription(produitDTO.getDescription());
        existingProduit.setPrix(produitDTO.getPrix());
        existingProduit.setTypeOeuvre(produitDTO.getTypeOeuvre());
        existingProduit.setImageURL(produitDTO.getImageURL());
        existingProduit.setDimension(produitDTO.getDimension());
        existingProduit.setFormat(produitDTO.getFormat());
        existingProduit.setStock(produitDTO.getStock());
        existingProduit.setDisponibilite(produitDTO.getStock() > 0);

        if (produitDTO.getId_categorie() != null) {
            Categorie categorie = new Categorie();
            categorie.setId(produitDTO.getId_categorie());
            existingProduit.setCategorie(categorie);
        }
        if (produitDTO.getId_artiste() != null) {
            User artist = new User();
            artist.setId(produitDTO.getId_artiste());
            existingProduit.setArtiste(artist);
        }

        Produit updatedProduit = produitRepository.save(existingProduit);
        return modelMapper.map(updatedProduit, ProduitDTO.class);
    }

    public void deleteProduit(int id) {
        if (!produitRepository.existsById(id)) {
            throw new ResourceNotFoundException("Produit non trouvé avec l'id: " + id);
        }
        produitRepository.deleteById(id);
    }

   /*  // Méthodes additionnelles pour la gestion du stock
    @Transactional
    public ProduitDTO updateStock(int id, int quantity) {
        Produit produit = produitRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produit non trouvé avec l'id: " + id));
        
        int newStock = produit.getStock() + quantity;
        if (newStock < 0) {
            throw new StockException("Stock insuffisant");
        }
        
        produit.setStock(newStock);
        produit.setDisponibilite(newStock > 0);
        
        Produit updatedProduit = produitRepository.save(produit);
        return modelMapper.map(updatedProduit, ProduitDTO.class);
    }

    @Transactional
    public ProduitDTO decrementStock(int id, int quantity) {
        return updateStock(id, -quantity);
    }

    @Transactional
    public ProduitDTO incrementStock(int id, int quantity) {
        return updateStock(id, quantity);
    }*/
}