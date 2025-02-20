package com.fst.ArtSphere.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fst.ArtSphere.entities.User;
import com.fst.ArtSphere.repositories.ClientRepository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor @Service
public class ClientServices {
	
	@Autowired
		private ClientRepository utilisateurRepository;
	
	public User addUser(User utilisateur)
	{
		  if (utilisateur.getEmail() == null || utilisateur.getEmail().isEmpty()) {
	            throw new IllegalArgumentException("Email is required");
	        }
		  return this.utilisateurRepository.save(utilisateur);
		
	}
	public User loginUser(User utilisateur)
	{
	
		
		try {
			System.out.println("email : "+utilisateur.getEmail());
				User user = this.utilisateurRepository.findByEmail(utilisateur.getEmail());
			
			if(user==null)
			{
				System.out.println("l'email n'existe pas");
				
				return null;
				
				
			}
			System.out.println(user.getMotDePasse() + " other one      : " + utilisateur.getMotDePasse() );
			if(user.getMotDePasse().equals(utilisateur.getMotDePasse()))
			{
				System.out.println("login success");
				return user;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("password incorrect");
		return null;
	}
	

}
