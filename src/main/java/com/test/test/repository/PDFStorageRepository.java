package com.test.test.repository;

import com.test.test.entity.PDFStorage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PDFStorageRepository extends JpaRepository<PDFStorage, Long> {
}
