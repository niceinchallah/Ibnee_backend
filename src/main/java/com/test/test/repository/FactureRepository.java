package com.test.test.repository;

import com.test.test.entity.Facture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FactureRepository extends JpaRepository<Facture, Long> {
    List<Facture> findByStatus(String status);
    Long countByClientId(Long clientId);
    List<Facture> findByUser_Username(String username);
}
