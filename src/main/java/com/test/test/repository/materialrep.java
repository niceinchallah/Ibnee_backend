package com.test.test.repository;
import com.test.test.entity.materialsent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface materialrep extends JpaRepository<materialsent,Long> {
}
