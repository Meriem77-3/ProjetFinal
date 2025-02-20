package com.fst.ArtSphere.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/artsphere/users/admin")
@CrossOrigin(origins = "http://localhost:3000") 
public class AdminController {

      @GetMapping("/welcome")
    public ResponseEntity<Map<String, String>> welcome() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "API is running successfully");
        response.put("welcomeMessage", "Welcome to ArtSphere - Your Digital Art Gallery");
        response.put("backgroundImageUrl", "https://postimg.cc/HrzCPM8k");
        
        return ResponseEntity.ok(response);
    }

    
}
