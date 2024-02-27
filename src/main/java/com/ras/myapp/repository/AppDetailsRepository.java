package com.ras.myapp.repository;

import com.ras.myapp.domain.AppDetails;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the AppDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AppDetailsRepository extends JpaRepository<AppDetails, Long> {}
