package com.user.repository;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.user.entity.User;


@Repository
public interface IUserRepo extends JpaRepository<User, Long>{

	Optional<User> findByUsername(String name);
	
	Boolean existsByUsername(String username);
	
	Boolean existsByEmail(String email);
	
	//Users findByEmail(String mail);
	
//	Users findByUsernameAndEmail(String name, String email);
//	
//	Users findByEmailAndPassword(String name, String password);
}
