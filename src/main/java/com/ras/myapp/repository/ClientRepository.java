package com.ras.myapp.repository;

import com.ras.myapp.domain.Client;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Client entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findTopByOrderByIdDesc();
}
