package com.fst.ArtSphere.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fst.ArtSphere.entities.User;

@Repository
public interface ClientRepository extends JpaRepository<User, Integer>{
	
	User findByEmail(String email);

}
