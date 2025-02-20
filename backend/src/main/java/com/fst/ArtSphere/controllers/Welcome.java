package com.fst.ArtSphere.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.http.ResponseEntity;
import java.util.Map;
import java.util.HashMap;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/artsphere")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class Welcome {

    @GetMapping("/welcome")
 
    public ResponseEntity<Map<String, String>> welcome() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "API is running successfully");
        response.put("welcomeMessage", "Welcome to ArtSphere - Your Digital Art Gallery");
        response.put("backgroundImageUrl", "https://postimg.cc/HrzCPM8k");
        response.put("welcomeMessage", "Welcome to ArtSphere - Your Digital Art Gallery");
        response.put("backgroundImageUrl", "https://postimg.cc/HrzCPM8k");
        return ResponseEntity.ok(response);
    }
    
}
