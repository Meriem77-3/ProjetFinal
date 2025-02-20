package com.fst.ArtSphere.services;

import java.util.Arrays;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.fst.ArtSphere.dto.UserDTO;
import com.fst.ArtSphere.repositories.UserRepository;
import com.fst.ArtSphere.repositories.RoleRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Data;
import com.fst.ArtSphere.dto.AuthResponseDTO;

import lombok.NoArgsConstructor;
import com.fst.ArtSphere.entities.User;
import com.fst.ArtSphere.entities.Role;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import com.fst.ArtSphere.security.JWTGenerator;
import org.springframework.security.core.Authentication;



@Data   @Service @NoArgsConstructor
public class UserService {
    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private  BCryptPasswordEncoder passwordEncoder;
    @Autowired
   private RoleRepository roleRepository;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTGenerator jwtGenerator;
    
   
    
    public User registerUser(@RequestBody UserDTO userDTO) {
        if(userRepository.existsByEmail(userDTO.getEmail())){
           return null;
           
        }

        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setMotDePasse(passwordEncoder.encode(userDTO.getPassword()));
        user.setNom(userDTO.getNom());
        user.setPrenom(userDTO.getPrenom());
        user.setAdresse(userDTO.getAddress());
        user.setBiographie(userDTO.getBiographie());
        Role role = roleRepository.findByName(userDTO.getRole())
            .orElseThrow(() -> new RuntimeException("Role not found"));
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
        return user;
       
    }
    public AuthResponseDTO loginUser(@RequestBody UserDTO userDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                userDTO.getEmail(),
                userDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return new AuthResponseDTO(token);

    }




    
}
