package com.ras.myapp.web.rest;

import com.ras.myapp.domain.AppScans;
import com.ras.myapp.repository.AppScansRepository;
import com.ras.myapp.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.ras.myapp.domain.AppScans}.
 */
@RestController
@RequestMapping("/api/app-scans")
@Transactional
public class AppScansResource {

    private final Logger log = LoggerFactory.getLogger(AppScansResource.class);

    private static final String ENTITY_NAME = "appScans";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AppScansRepository appScansRepository;

    public AppScansResource(AppScansRepository appScansRepository) {
        this.appScansRepository = appScansRepository;
    }

    /**
     * {@code POST  /app-scans} : Create a new appScans.
     *
     * @param appScans the appScans to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new appScans, or with status {@code 400 (Bad Request)} if the appScans has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<AppScans> createAppScans(@RequestBody AppScans appScans) throws URISyntaxException {
        log.debug("REST request to save AppScans : {}", appScans);
        if (appScans.getId() != null) {
            throw new BadRequestAlertException("A new appScans cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AppScans result = appScansRepository.save(appScans);
        return ResponseEntity
            .created(new URI("/api/app-scans/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /app-scans/:id} : Updates an existing appScans.
     *
     * @param id the id of the appScans to save.
     * @param appScans the appScans to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated appScans,
     * or with status {@code 400 (Bad Request)} if the appScans is not valid,
     * or with status {@code 500 (Internal Server Error)} if the appScans couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<AppScans> updateAppScans(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody AppScans appScans
    ) throws URISyntaxException {
        log.debug("REST request to update AppScans : {}, {}", id, appScans);
        if (appScans.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, appScans.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!appScansRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        AppScans result = appScansRepository.save(appScans);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, appScans.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /app-scans/:id} : Partial updates given fields of an existing appScans, field will ignore if it is null
     *
     * @param id the id of the appScans to save.
     * @param appScans the appScans to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated appScans,
     * or with status {@code 400 (Bad Request)} if the appScans is not valid,
     * or with status {@code 404 (Not Found)} if the appScans is not found,
     * or with status {@code 500 (Internal Server Error)} if the appScans couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<AppScans> partialUpdateAppScans(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody AppScans appScans
    ) throws URISyntaxException {
        log.debug("REST request to partial update AppScans partially : {}, {}", id, appScans);
        if (appScans.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, appScans.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!appScansRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<AppScans> result = appScansRepository
            .findById(appScans.getId())
            .map(existingAppScans -> {
                if (appScans.getAppId() != null) {
                    existingAppScans.setAppId(appScans.getAppId());
                }
                if (appScans.getNameScan() != null) {
                    existingAppScans.setNameScan(appScans.getNameScan());
                }
                if (appScans.getTechnology() != null) {
                    existingAppScans.setTechnology(appScans.getTechnology());
                }
                if (appScans.getIastAgentType() != null) {
                    existingAppScans.setIastAgentType(appScans.getIastAgentType());
                }
                if (appScans.getIastAgentStatus() != null) {
                    existingAppScans.setIastAgentStatus(appScans.getIastAgentStatus());
                }
                if (appScans.getUrlScan() != null) {
                    existingAppScans.setUrlScan(appScans.getUrlScan());
                }
                if (appScans.getAppName() != null) {
                    existingAppScans.setAppName(appScans.getAppName());
                }
                if (appScans.getTestOptimizationLevel() != null) {
                    existingAppScans.setTestOptimizationLevel(appScans.getTestOptimizationLevel());
                }
                if (appScans.getNumberOfExecutions() != null) {
                    existingAppScans.setNumberOfExecutions(appScans.getNumberOfExecutions());
                }
                if (appScans.getCreatedAt() != null) {
                    existingAppScans.setCreatedAt(appScans.getCreatedAt());
                }
                if (appScans.getLastModified() != null) {
                    existingAppScans.setLastModified(appScans.getLastModified());
                }

                return existingAppScans;
            })
            .map(appScansRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, appScans.getId().toString())
        );
    }

    /**
     * {@code GET  /app-scans} : get all the appScans.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of appScans in body.
     */
    @GetMapping("")
    public List<AppScans> getAllAppScans() {
        log.debug("REST request to get all AppScans");
        return appScansRepository.findAll();
    }

    /**
     * {@code GET  /app-scans/:id} : get the "id" appScans.
     *
     * @param id the id of the appScans to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the appScans, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<AppScans> getAppScans(@PathVariable("id") Long id) {
        log.debug("REST request to get AppScans : {}", id);
        Optional<AppScans> appScans = appScansRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(appScans);
    }

    /**
     * {@code DELETE  /app-scans/:id} : delete the "id" appScans.
     *
     * @param id the id of the appScans to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppScans(@PathVariable("id") Long id) {
        log.debug("REST request to delete AppScans : {}", id);
        appScansRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
