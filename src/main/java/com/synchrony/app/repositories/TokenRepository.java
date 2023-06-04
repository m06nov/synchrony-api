package com.synchrony.app.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.synchrony.app.dto.TokenDTO;

@Repository
public interface TokenRepository extends CrudRepository<TokenDTO, Long> {

}
