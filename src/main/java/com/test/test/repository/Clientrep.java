package com.test.test.repository;

import com.test.test.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Clientrep  extends JpaRepository<Client,Long> {
}
