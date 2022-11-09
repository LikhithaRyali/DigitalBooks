package com.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.user.entity.ERole;
import com.user.entity.Role;

@Repository
public interface IRoleRepo extends JpaRepository<Role, String> {

	Optional<Role> findByName(ERole role);
}
