package com.ras.myapp.repository;

import com.ras.myapp.domain.AppScans;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the AppScans entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AppScansRepository extends JpaRepository<AppScans, Long> {}
