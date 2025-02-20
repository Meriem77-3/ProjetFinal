package com.fst.ArtSphere.Config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fst.ArtSphere.dto.ProduitDTO;
import com.fst.ArtSphere.entities.Produit;
import com.fst.ArtSphere.entities.Categorie;
import com.fst.ArtSphere.entities.User;
@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
            .setSkipNullEnabled(true)
            .setMatchingStrategy(MatchingStrategies.STANDARD);

        // Mapping explicite pour ProduitDTO vers Produit
        modelMapper.typeMap(ProduitDTO.class, Produit.class)
            .addMappings(mapper -> {
                mapper.map(ProduitDTO::getId_categorie, (dest, v) -> {
                    if (v != null) {
                        Categorie categorie = new Categorie();
                        categorie.setId((Integer) v);
                        dest.setCategorie(categorie);
                    } else {
                        dest.setCategorie(null);
                    }
                });
                mapper.map(ProduitDTO::getId_artiste, (dest, v) -> {
                    if (v != null) {
                        User artist = new User();
                        artist.setId((Integer) v);
                        dest.setArtiste(artist);
                    } else {
                        dest.setArtiste(null);
                    }
                });
            });

        return modelMapper;
    }
}
