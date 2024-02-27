package com.ras.myapp.web.rest;

import com.ras.myapp.domain.AppDetails;
import com.ras.myapp.repository.AppDetailsRepository;
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
 * REST controller for managing {@link com.ras.myapp.domain.AppDetails}.
 */
@RestController
@RequestMapping("/api/app-details")
@Transactional
public class AppDetailsResource {

    private final Logger log = LoggerFactory.getLogger(AppDetailsResource.class);

    private static final String ENTITY_NAME = "appDetails";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AppDetailsRepository appDetailsRepository;

    public AppDetailsResource(AppDetailsRepository appDetailsRepository) {
        this.appDetailsRepository = appDetailsRepository;
    }

    /**
     * {@code POST  /app-details} : Create a new appDetails.
     *
     * @param appDetails the appDetails to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new appDetails, or with status {@code 400 (Bad Request)} if the appDetails has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<AppDetails> createAppDetails(@RequestBody AppDetails appDetails) throws URISyntaxException {
        log.debug("REST request to save AppDetails : {}", appDetails);
        if (appDetails.getId() != null) {
            throw new BadRequestAlertException("A new appDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AppDetails result = appDetailsRepository.save(appDetails);
        return ResponseEntity
            .created(new URI("/api/app-details/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /app-details/:id} : Updates an existing appDetails.
     *
     * @param id the id of the appDetails to save.
     * @param appDetails the appDetails to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated appDetails,
     * or with status {@code 400 (Bad Request)} if the appDetails is not valid,
     * or with status {@code 500 (Internal Server Error)} if the appDetails couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<AppDetails> updateAppDetails(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody AppDetails appDetails
    ) throws URISyntaxException {
        log.debug("REST request to update AppDetails : {}, {}", id, appDetails);
        if (appDetails.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, appDetails.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!appDetailsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        AppDetails result = appDetailsRepository.save(appDetails);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, appDetails.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /app-details/:id} : Partial updates given fields of an existing appDetails, field will ignore if it is null
     *
     * @param id the id of the appDetails to save.
     * @param appDetails the appDetails to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated appDetails,
     * or with status {@code 400 (Bad Request)} if the appDetails is not valid,
     * or with status {@code 404 (Not Found)} if the appDetails is not found,
     * or with status {@code 500 (Internal Server Error)} if the appDetails couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<AppDetails> partialUpdateAppDetails(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody AppDetails appDetails
    ) throws URISyntaxException {
        log.debug("REST request to partial update AppDetails partially : {}, {}", id, appDetails);
        if (appDetails.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, appDetails.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!appDetailsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<AppDetails> result = appDetailsRepository
            .findById(appDetails.getId())
            .map(existingAppDetails -> {
                if (appDetails.getAppId() != null) {
                    existingAppDetails.setAppId(appDetails.getAppId());
                }
                if (appDetails.getRiskRating() != null) {
                    existingAppDetails.setRiskRating(appDetails.getRiskRating());
                }
                if (appDetails.getCriticalIssues() != null) {
                    existingAppDetails.setCriticalIssues(appDetails.getCriticalIssues());
                }
                if (appDetails.getHighIssues() != null) {
                    existingAppDetails.setHighIssues(appDetails.getHighIssues());
                }
                if (appDetails.getMediumIssues() != null) {
                    existingAppDetails.setMediumIssues(appDetails.getMediumIssues());
                }
                if (appDetails.getLowIssues() != null) {
                    existingAppDetails.setLowIssues(appDetails.getLowIssues());
                }
                if (appDetails.getInformationalIssues() != null) {
                    existingAppDetails.setInformationalIssues(appDetails.getInformationalIssues());
                }
                if (appDetails.getIssuesInProgress() != null) {
                    existingAppDetails.setIssuesInProgress(appDetails.getIssuesInProgress());
                }
                if (appDetails.getMaxSeverity() != null) {
                    existingAppDetails.setMaxSeverity(appDetails.getMaxSeverity());
                }
                if (appDetails.getCorrelationState() != null) {
                    existingAppDetails.setCorrelationState(appDetails.getCorrelationState());
                }
                if (appDetails.getrRMaxSeverity() != null) {
                    existingAppDetails.setrRMaxSeverity(appDetails.getrRMaxSeverity());
                }
                if (appDetails.getAssetGroupId() != null) {
                    existingAppDetails.setAssetGroupId(appDetails.getAssetGroupId());
                }
                if (appDetails.getBusinessImpact() != null) {
                    existingAppDetails.setBusinessImpact(appDetails.getBusinessImpact());
                }
                if (appDetails.getUrl() != null) {
                    existingAppDetails.setUrl(appDetails.getUrl());
                }
                if (appDetails.getDescription() != null) {
                    existingAppDetails.setDescription(appDetails.getDescription());
                }
                if (appDetails.getBusinessUnit() != null) {
                    existingAppDetails.setBusinessUnit(appDetails.getBusinessUnit());
                }
                if (appDetails.getBusinessUnitId() != null) {
                    existingAppDetails.setBusinessUnitId(appDetails.getBusinessUnitId());
                }
                if (appDetails.getTypes() != null) {
                    existingAppDetails.setTypes(appDetails.getTypes());
                }
                if (appDetails.getTechnology() != null) {
                    existingAppDetails.setTechnology(appDetails.getTechnology());
                }
                if (appDetails.getTestingStatus() != null) {
                    existingAppDetails.setTestingStatus(appDetails.getTestingStatus());
                }
                if (appDetails.getAppHosts() != null) {
                    existingAppDetails.setAppHosts(appDetails.getAppHosts());
                }
                if (appDetails.getCollateralDamagePotential() != null) {
                    existingAppDetails.setCollateralDamagePotential(appDetails.getCollateralDamagePotential());
                }
                if (appDetails.getTargetDistribution() != null) {
                    existingAppDetails.setTargetDistribution(appDetails.getTargetDistribution());
                }
                if (appDetails.getConfidentialityRequirement() != null) {
                    existingAppDetails.setConfidentialityRequirement(appDetails.getConfidentialityRequirement());
                }
                if (appDetails.getIntegrityRequirement() != null) {
                    existingAppDetails.setIntegrityRequirement(appDetails.getIntegrityRequirement());
                }
                if (appDetails.getAvailabilityRequirement() != null) {
                    existingAppDetails.setAvailabilityRequirement(appDetails.getAvailabilityRequirement());
                }
                if (appDetails.getTester() != null) {
                    existingAppDetails.setTester(appDetails.getTester());
                }
                if (appDetails.getBusinessOwner() != null) {
                    existingAppDetails.setBusinessOwner(appDetails.getBusinessOwner());
                }
                if (appDetails.getDevelopmentContact() != null) {
                    existingAppDetails.setDevelopmentContact(appDetails.getDevelopmentContact());
                }
                if (appDetails.getPreferredOfferingType() != null) {
                    existingAppDetails.setPreferredOfferingType(appDetails.getPreferredOfferingType());
                }
                if (appDetails.getAssetGroupName() != null) {
                    existingAppDetails.setAssetGroupName(appDetails.getAssetGroupName());
                }
                if (appDetails.getDateCreated() != null) {
                    existingAppDetails.setDateCreated(appDetails.getDateCreated());
                }
                if (appDetails.getLastUpdated() != null) {
                    existingAppDetails.setLastUpdated(appDetails.getLastUpdated());
                }
                if (appDetails.getLastComment() != null) {
                    existingAppDetails.setLastComment(appDetails.getLastComment());
                }
                if (appDetails.getCreatedBy() != null) {
                    existingAppDetails.setCreatedBy(appDetails.getCreatedBy());
                }
                if (appDetails.getNewIssues() != null) {
                    existingAppDetails.setNewIssues(appDetails.getNewIssues());
                }
                if (appDetails.getOpenIssues() != null) {
                    existingAppDetails.setOpenIssues(appDetails.getOpenIssues());
                }
                if (appDetails.getTotalIssues() != null) {
                    existingAppDetails.setTotalIssues(appDetails.getTotalIssues());
                }
                if (appDetails.getOverallCompliance() != null) {
                    existingAppDetails.setOverallCompliance(appDetails.getOverallCompliance());
                }
                if (appDetails.getCanBeDeleted() != null) {
                    existingAppDetails.setCanBeDeleted(appDetails.getCanBeDeleted());
                }
                if (appDetails.getLockedToSubscription() != null) {
                    existingAppDetails.setLockedToSubscription(appDetails.getLockedToSubscription());
                }
                if (appDetails.getTotalScans() != null) {
                    existingAppDetails.setTotalScans(appDetails.getTotalScans());
                }
                if (appDetails.getnScanExecutions() != null) {
                    existingAppDetails.setnScanExecutions(appDetails.getnScanExecutions());
                }
                if (appDetails.getHasExceedingIssuesNumber() != null) {
                    existingAppDetails.setHasExceedingIssuesNumber(appDetails.getHasExceedingIssuesNumber());
                }
                if (appDetails.getHasExceedingScansNumber() != null) {
                    existingAppDetails.setHasExceedingScansNumber(appDetails.getHasExceedingScansNumber());
                }
                if (appDetails.getAutoDeleteExceededScans() != null) {
                    existingAppDetails.setAutoDeleteExceededScans(appDetails.getAutoDeleteExceededScans());
                }

                return existingAppDetails;
            })
            .map(appDetailsRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, appDetails.getId().toString())
        );
    }

    /**
     * {@code GET  /app-details} : get all the appDetails.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of appDetails in body.
     */
    @GetMapping("")
    public List<AppDetails> getAllAppDetails() {
        log.debug("REST request to get all AppDetails");
        return appDetailsRepository.findAll();
    }

    /**
     * {@code GET  /app-details/:id} : get the "id" appDetails.
     *
     * @param id the id of the appDetails to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the appDetails, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<AppDetails> getAppDetails(@PathVariable("id") Long id) {
        log.debug("REST request to get AppDetails : {}", id);
        Optional<AppDetails> appDetails = appDetailsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(appDetails);
    }

    /**
     * {@code DELETE  /app-details/:id} : delete the "id" appDetails.
     *
     * @param id the id of the appDetails to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppDetails(@PathVariable("id") Long id) {
        log.debug("REST request to delete AppDetails : {}", id);
        appDetailsRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
