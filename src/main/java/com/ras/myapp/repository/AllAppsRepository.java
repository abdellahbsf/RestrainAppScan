package com.ras.myapp.repository;

import com.ras.myapp.domain.AllApps;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the AllApps entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AllAppsRepository extends JpaRepository<AllApps, Long> {}
