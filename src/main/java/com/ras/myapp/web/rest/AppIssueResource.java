package com.ras.myapp.web.rest;

import com.ras.myapp.domain.AppIssue;
import com.ras.myapp.repository.AppIssueRepository;
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
 * REST controller for managing {@link com.ras.myapp.domain.AppIssue}.
 */
@RestController
@RequestMapping("/api/app-issues")
@Transactional
public class AppIssueResource {

    private final Logger log = LoggerFactory.getLogger(AppIssueResource.class);

    private static final String ENTITY_NAME = "appIssue";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AppIssueRepository appIssueRepository;

    public AppIssueResource(AppIssueRepository appIssueRepository) {
        this.appIssueRepository = appIssueRepository;
    }

    /**
     * {@code POST  /app-issues} : Create a new appIssue.
     *
     * @param appIssue the appIssue to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new appIssue, or with status {@code 400 (Bad Request)} if the appIssue has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<AppIssue> createAppIssue(@RequestBody AppIssue appIssue) throws URISyntaxException {
        log.debug("REST request to save AppIssue : {}", appIssue);
        if (appIssue.getId() != null) {
            throw new BadRequestAlertException("A new appIssue cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AppIssue result = appIssueRepository.save(appIssue);
        return ResponseEntity
            .created(new URI("/api/app-issues/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /app-issues/:id} : Updates an existing appIssue.
     *
     * @param id the id of the appIssue to save.
     * @param appIssue the appIssue to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated appIssue,
     * or with status {@code 400 (Bad Request)} if the appIssue is not valid,
     * or with status {@code 500 (Internal Server Error)} if the appIssue couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<AppIssue> updateAppIssue(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody AppIssue appIssue
    ) throws URISyntaxException {
        log.debug("REST request to update AppIssue : {}, {}", id, appIssue);
        if (appIssue.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, appIssue.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!appIssueRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        AppIssue result = appIssueRepository.save(appIssue);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, appIssue.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /app-issues/:id} : Partial updates given fields of an existing appIssue, field will ignore if it is null
     *
     * @param id the id of the appIssue to save.
     * @param appIssue the appIssue to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated appIssue,
     * or with status {@code 400 (Bad Request)} if the appIssue is not valid,
     * or with status {@code 404 (Not Found)} if the appIssue is not found,
     * or with status {@code 500 (Internal Server Error)} if the appIssue couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<AppIssue> partialUpdateAppIssue(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody AppIssue appIssue
    ) throws URISyntaxException {
        log.debug("REST request to partial update AppIssue partially : {}, {}", id, appIssue);
        if (appIssue.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, appIssue.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!appIssueRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<AppIssue> result = appIssueRepository
            .findById(appIssue.getId())
            .map(existingAppIssue -> {
                if (appIssue.getAppId() != null) {
                    existingAppIssue.setAppId(appIssue.getAppId());
                }
                if (appIssue.getProgrammingLanguage() != null) {
                    existingAppIssue.setProgrammingLanguage(appIssue.getProgrammingLanguage());
                }
                if (appIssue.getSeverities() != null) {
                    existingAppIssue.setSeverities(appIssue.getSeverities());
                }
                if (appIssue.getStatusIssue() != null) {
                    existingAppIssue.setStatusIssue(appIssue.getStatusIssue());
                }
                if (appIssue.getIssueType() != null) {
                    existingAppIssue.setIssueType(appIssue.getIssueType());
                }
                if (appIssue.getLocationIssue() != null) {
                    existingAppIssue.setLocationIssue(appIssue.getLocationIssue());
                }
                if (appIssue.getDateCreated() != null) {
                    existingAppIssue.setDateCreated(appIssue.getDateCreated());
                }
                if (appIssue.getLastUpdated() != null) {
                    existingAppIssue.setLastUpdated(appIssue.getLastUpdated());
                }
                if (appIssue.getLastFound() != null) {
                    existingAppIssue.setLastFound(appIssue.getLastFound());
                }
                if (appIssue.getCallingMethod() != null) {
                    existingAppIssue.setCallingMethod(appIssue.getCallingMethod());
                }
                if (appIssue.getIsNewInScope() != null) {
                    existingAppIssue.setIsNewInScope(appIssue.getIsNewInScope());
                }
                if (appIssue.getLibraryName() != null) {
                    existingAppIssue.setLibraryName(appIssue.getLibraryName());
                }
                if (appIssue.getLibraryVersion() != null) {
                    existingAppIssue.setLibraryVersion(appIssue.getLibraryVersion());
                }
                if (appIssue.getScaTechnology() != null) {
                    existingAppIssue.setScaTechnology(appIssue.getScaTechnology());
                }
                if (appIssue.getfGStatus() != null) {
                    existingAppIssue.setfGStatus(appIssue.getfGStatus());
                }
                if (appIssue.getApplicationId() != null) {
                    existingAppIssue.setApplicationId(appIssue.getApplicationId());
                }
                if (appIssue.getFixGroupId() != null) {
                    existingAppIssue.setFixGroupId(appIssue.getFixGroupId());
                }
                if (appIssue.getApiIssue() != null) {
                    existingAppIssue.setApiIssue(appIssue.getApiIssue());
                }
                if (appIssue.getSourceIssue() != null) {
                    existingAppIssue.setSourceIssue(appIssue.getSourceIssue());
                }
                if (appIssue.getContextIssue() != null) {
                    existingAppIssue.setContextIssue(appIssue.getContextIssue());
                }
                if (appIssue.getAppscanVulnId() != null) {
                    existingAppIssue.setAppscanVulnId(appIssue.getAppscanVulnId());
                }
                if (appIssue.getCallingLine() != null) {
                    existingAppIssue.setCallingLine(appIssue.getCallingLine());
                }
                if (appIssue.getClassIssue() != null) {
                    existingAppIssue.setClassIssue(appIssue.getClassIssue());
                }
                if (appIssue.getCveIssue() != null) {
                    existingAppIssue.setCveIssue(appIssue.getCveIssue());
                }

                return existingAppIssue;
            })
            .map(appIssueRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, appIssue.getId().toString())
        );
    }

    /**
     * {@code GET  /app-issues} : get all the appIssues.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of appIssues in body.
     */
    @GetMapping("")
    public List<AppIssue> getAllAppIssues() {
        log.debug("REST request to get all AppIssues");
        return appIssueRepository.findAll();
    }

    /**
     * {@code GET  /app-issues/:id} : get the "id" appIssue.
     *
     * @param id the id of the appIssue to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the appIssue, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<AppIssue> getAppIssue(@PathVariable("id") Long id) {
        log.debug("REST request to get AppIssue : {}", id);
        Optional<AppIssue> appIssue = appIssueRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(appIssue);
    }

    /**
     * {@code DELETE  /app-issues/:id} : delete the "id" appIssue.
     *
     * @param id the id of the appIssue to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppIssue(@PathVariable("id") Long id) {
        log.debug("REST request to delete AppIssue : {}", id);
        appIssueRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
