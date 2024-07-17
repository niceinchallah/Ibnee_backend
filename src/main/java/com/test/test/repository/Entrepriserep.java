package com.test.test.repository;

import com.test.test.entity.Client;
import com.test.test.entity.Entreprise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Entrepriserep  extends JpaRepository<Entreprise,Long> {
}
