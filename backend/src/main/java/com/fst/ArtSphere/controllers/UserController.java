package com.fst.ArtSphere.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fst.ArtSphere.entities.User;
import com.fst.ArtSphere.services.ClientServices;

@RestController
@RequestMapping("/artsphere/users/client")
@CrossOrigin(origins = "http://localhost:3000") 
public class UserController {
    @Autowired
    private ClientServices utilisateurService;


      @GetMapping("/welcome")
    public ResponseEntity<Map<String, String>> welcome() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "API is running successfully");
        response.put("welcomeMessage", "Welcome to ArtSphere - Your Digital Art Gallery");
        response.put("backgroundImageUrl", "https://postimg.cc/HrzCPM8k");
        
        return ResponseEntity.ok(response);
    }

    @PostMapping("/add")
    public ResponseEntity<User> createUser(@RequestBody User utilisateur) {
        try {
            User createdUser = utilisateurService.addUser(utilisateur);
            System.out.println("User added: " + createdUser);
            return ResponseEntity.ok(createdUser);
        } catch (Exception e) {
            System.err.println("Error adding user: " + e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    
    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestBody User utilisateur)
    {
    	try {
    		User user =utilisateurService.loginUser(utilisateur) ;
    		
    		if(user!=null)
    		{
    			System.out.println("logged in");
    			return ResponseEntity.ok(user);
    		}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
        return ResponseEntity.badRequest().build();
    	
    }
}