package com.ras.myapp.web.rest;

import com.ras.myapp.domain.AllApps;
import com.ras.myapp.repository.AllAppsRepository;
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
 * REST controller for managing {@link com.ras.myapp.domain.AllApps}.
 */
@RestController
@RequestMapping("/api/all-apps")
@Transactional
public class AllAppsResource {

    private final Logger log = LoggerFactory.getLogger(AllAppsResource.class);

    private static final String ENTITY_NAME = "allApps";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AllAppsRepository allAppsRepository;

    public AllAppsResource(AllAppsRepository allAppsRepository) {
        this.allAppsRepository = allAppsRepository;
    }

    /**
     * {@code POST  /all-apps} : Create a new allApps.
     *
     * @param allApps the allApps to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new allApps, or with status {@code 400 (Bad Request)} if the allApps has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<AllApps> createAllApps(@RequestBody AllApps allApps) throws URISyntaxException {
        log.debug("REST request to save AllApps : {}", allApps);
        if (allApps.getId() != null) {
            throw new BadRequestAlertException("A new allApps cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AllApps result = allAppsRepository.save(allApps);
        return ResponseEntity
            .created(new URI("/api/all-apps/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /all-apps/:id} : Updates an existing allApps.
     *
     * @param id the id of the allApps to save.
     * @param allApps the allApps to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated allApps,
     * or with status {@code 400 (Bad Request)} if the allApps is not valid,
     * or with status {@code 500 (Internal Server Error)} if the allApps couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<AllApps> updateAllApps(@PathVariable(value = "id", required = false) final Long id, @RequestBody AllApps allApps)
        throws URISyntaxException {
        log.debug("REST request to update AllApps : {}, {}", id, allApps);
        if (allApps.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, allApps.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!allAppsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        AllApps result = allAppsRepository.save(allApps);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, allApps.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /all-apps/:id} : Partial updates given fields of an existing allApps, field will ignore if it is null
     *
     * @param id the id of the allApps to save.
     * @param allApps the allApps to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated allApps,
     * or with status {@code 400 (Bad Request)} if the allApps is not valid,
     * or with status {@code 404 (Not Found)} if the allApps is not found,
     * or with status {@code 500 (Internal Server Error)} if the allApps couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<AllApps> partialUpdateAllApps(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody AllApps allApps
    ) throws URISyntaxException {
        log.debug("REST request to partial update AllApps partially : {}, {}", id, allApps);
        if (allApps.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, allApps.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!allAppsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<AllApps> result = allAppsRepository
            .findById(allApps.getId())
            .map(existingAllApps -> {
                if (allApps.getAppId() != null) {
                    existingAllApps.setAppId(allApps.getAppId());
                }
                if (allApps.getName() != null) {
                    existingAllApps.setName(allApps.getName());
                }

                return existingAllApps;
            })
            .map(allAppsRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, allApps.getId().toString())
        );
    }

    /**
     * {@code GET  /all-apps} : get all the allApps.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of allApps in body.
     */
    @GetMapping("")
    public List<AllApps> getAllAllApps() {
        log.debug("REST request to get all AllApps");
        return allAppsRepository.findAll();
    }

    /**
     * {@code GET  /all-apps/:id} : get the "id" allApps.
     *
     * @param id the id of the allApps to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the allApps, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<AllApps> getAllApps(@PathVariable("id") Long id) {
        log.debug("REST request to get AllApps : {}", id);
        Optional<AllApps> allApps = allAppsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(allApps);
    }

    /**
     * {@code DELETE  /all-apps/:id} : delete the "id" allApps.
     *
     * @param id the id of the allApps to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAllApps(@PathVariable("id") Long id) {
        log.debug("REST request to delete AllApps : {}", id);
        allAppsRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
