package com.test.test.repository;

import com.test.test.entity.Devis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DevisRepository extends JpaRepository<Devis, Long> {
    Long countByClientId(Long clientId);
    List<Devis> findByStatus(String status);
    List<Devis> findByUser_Username(String username);
}
