package com.JWT.JWToken.repository;

import com.JWT.JWToken.model.JWTRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JPARepository extends JpaRepository<JWTRequest,Long> {

    Optional<JWTRequest> findEmployeeById(Long id);
}
