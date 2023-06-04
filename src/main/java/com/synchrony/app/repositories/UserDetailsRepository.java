package com.synchrony.app.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.synchrony.app.dto.UserDetailsDTO;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetailsDTO, Long> {
	Optional<UserDetailsDTO> findByUsername(String username);
}
