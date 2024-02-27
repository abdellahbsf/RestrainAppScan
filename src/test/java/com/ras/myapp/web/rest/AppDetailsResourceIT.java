package com.ras.myapp.web.rest;

import static com.ras.myapp.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.ras.myapp.IntegrationTest;
import com.ras.myapp.domain.AppDetails;
import com.ras.myapp.repository.AppDetailsRepository;
import jakarta.persistence.EntityManager;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link AppDetailsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AppDetailsResourceIT {

    private static final String DEFAULT_APP_ID = "AAAAAAAAAA";
    private static final String UPDATED_APP_ID = "BBBBBBBBBB";

    private static final String DEFAULT_RISK_RATING = "AAAAAAAAAA";
    private static final String UPDATED_RISK_RATING = "BBBBBBBBBB";

    private static final Integer DEFAULT_CRITICAL_ISSUES = 1;
    private static final Integer UPDATED_CRITICAL_ISSUES = 2;

    private static final Integer DEFAULT_HIGH_ISSUES = 1;
    private static final Integer UPDATED_HIGH_ISSUES = 2;

    private static final Integer DEFAULT_MEDIUM_ISSUES = 1;
    private static final Integer UPDATED_MEDIUM_ISSUES = 2;

    private static final Integer DEFAULT_LOW_ISSUES = 1;
    private static final Integer UPDATED_LOW_ISSUES = 2;

    private static final Integer DEFAULT_INFORMATIONAL_ISSUES = 1;
    private static final Integer UPDATED_INFORMATIONAL_ISSUES = 2;

    private static final Integer DEFAULT_ISSUES_IN_PROGRESS = 1;
    private static final Integer UPDATED_ISSUES_IN_PROGRESS = 2;

    private static final String DEFAULT_MAX_SEVERITY = "AAAAAAAAAA";
    private static final String UPDATED_MAX_SEVERITY = "BBBBBBBBBB";

    private static final String DEFAULT_CORRELATION_STATE = "AAAAAAAAAA";
    private static final String UPDATED_CORRELATION_STATE = "BBBBBBBBBB";

    private static final Integer DEFAULT_R_R_MAX_SEVERITY = 1;
    private static final Integer UPDATED_R_R_MAX_SEVERITY = 2;

    private static final String DEFAULT_ASSET_GROUP_ID = "AAAAAAAAAA";
    private static final String UPDATED_ASSET_GROUP_ID = "BBBBBBBBBB";

    private static final String DEFAULT_BUSINESS_IMPACT = "AAAAAAAAAA";
    private static final String UPDATED_BUSINESS_IMPACT = "BBBBBBBBBB";

    private static final String DEFAULT_URL = "AAAAAAAAAA";
    private static final String UPDATED_URL = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_BUSINESS_UNIT = "AAAAAAAAAA";
    private static final String UPDATED_BUSINESS_UNIT = "BBBBBBBBBB";

    private static final String DEFAULT_BUSINESS_UNIT_ID = "AAAAAAAAAA";
    private static final String UPDATED_BUSINESS_UNIT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_TYPES = "AAAAAAAAAA";
    private static final String UPDATED_TYPES = "BBBBBBBBBB";

    private static final String DEFAULT_TECHNOLOGY = "AAAAAAAAAA";
    private static final String UPDATED_TECHNOLOGY = "BBBBBBBBBB";

    private static final String DEFAULT_TESTING_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_TESTING_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_APP_HOSTS = "AAAAAAAAAA";
    private static final String UPDATED_APP_HOSTS = "BBBBBBBBBB";

    private static final String DEFAULT_COLLATERAL_DAMAGE_POTENTIAL = "AAAAAAAAAA";
    private static final String UPDATED_COLLATERAL_DAMAGE_POTENTIAL = "BBBBBBBBBB";

    private static final String DEFAULT_TARGET_DISTRIBUTION = "AAAAAAAAAA";
    private static final String UPDATED_TARGET_DISTRIBUTION = "BBBBBBBBBB";

    private static final String DEFAULT_CONFIDENTIALITY_REQUIREMENT = "AAAAAAAAAA";
    private static final String UPDATED_CONFIDENTIALITY_REQUIREMENT = "BBBBBBBBBB";

    private static final String DEFAULT_INTEGRITY_REQUIREMENT = "AAAAAAAAAA";
    private static final String UPDATED_INTEGRITY_REQUIREMENT = "BBBBBBBBBB";

    private static final String DEFAULT_AVAILABILITY_REQUIREMENT = "AAAAAAAAAA";
    private static final String UPDATED_AVAILABILITY_REQUIREMENT = "BBBBBBBBBB";

    private static final String DEFAULT_TESTER = "AAAAAAAAAA";
    private static final String UPDATED_TESTER = "BBBBBBBBBB";

    private static final String DEFAULT_BUSINESS_OWNER = "AAAAAAAAAA";
    private static final String UPDATED_BUSINESS_OWNER = "BBBBBBBBBB";

    private static final String DEFAULT_DEVELOPMENT_CONTACT = "AAAAAAAAAA";
    private static final String UPDATED_DEVELOPMENT_CONTACT = "BBBBBBBBBB";

    private static final String DEFAULT_PREFERRED_OFFERING_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_PREFERRED_OFFERING_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_ASSET_GROUP_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ASSET_GROUP_NAME = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_DATE_CREATED = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE_CREATED = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_LAST_UPDATED = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_LAST_UPDATED = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_LAST_COMMENT = "AAAAAAAAAA";
    private static final String UPDATED_LAST_COMMENT = "BBBBBBBBBB";

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final Integer DEFAULT_NEW_ISSUES = 1;
    private static final Integer UPDATED_NEW_ISSUES = 2;

    private static final Integer DEFAULT_OPEN_ISSUES = 1;
    private static final Integer UPDATED_OPEN_ISSUES = 2;

    private static final Integer DEFAULT_TOTAL_ISSUES = 1;
    private static final Integer UPDATED_TOTAL_ISSUES = 2;

    private static final Boolean DEFAULT_OVERALL_COMPLIANCE = false;
    private static final Boolean UPDATED_OVERALL_COMPLIANCE = true;

    private static final Boolean DEFAULT_CAN_BE_DELETED = false;
    private static final Boolean UPDATED_CAN_BE_DELETED = true;

    private static final Boolean DEFAULT_LOCKED_TO_SUBSCRIPTION = false;
    private static final Boolean UPDATED_LOCKED_TO_SUBSCRIPTION = true;

    private static final Integer DEFAULT_TOTAL_SCANS = 1;
    private static final Integer UPDATED_TOTAL_SCANS = 2;

    private static final Integer DEFAULT_N_SCAN_EXECUTIONS = 1;
    private static final Integer UPDATED_N_SCAN_EXECUTIONS = 2;

    private static final Boolean DEFAULT_HAS_EXCEEDING_ISSUES_NUMBER = false;
    private static final Boolean UPDATED_HAS_EXCEEDING_ISSUES_NUMBER = true;

    private static final Boolean DEFAULT_HAS_EXCEEDING_SCANS_NUMBER = false;
    private static final Boolean UPDATED_HAS_EXCEEDING_SCANS_NUMBER = true;

    private static final Boolean DEFAULT_AUTO_DELETE_EXCEEDED_SCANS = false;
    private static final Boolean UPDATED_AUTO_DELETE_EXCEEDED_SCANS = true;

    private static final String ENTITY_API_URL = "/api/app-details";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private AppDetailsRepository appDetailsRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAppDetailsMockMvc;

    private AppDetails appDetails;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AppDetails createEntity(EntityManager em) {
        AppDetails appDetails = new AppDetails()
            .appId(DEFAULT_APP_ID)
            .riskRating(DEFAULT_RISK_RATING)
            .criticalIssues(DEFAULT_CRITICAL_ISSUES)
            .highIssues(DEFAULT_HIGH_ISSUES)
            .mediumIssues(DEFAULT_MEDIUM_ISSUES)
            .lowIssues(DEFAULT_LOW_ISSUES)
            .informationalIssues(DEFAULT_INFORMATIONAL_ISSUES)
            .issuesInProgress(DEFAULT_ISSUES_IN_PROGRESS)
            .maxSeverity(DEFAULT_MAX_SEVERITY)
            .correlationState(DEFAULT_CORRELATION_STATE)
            .rRMaxSeverity(DEFAULT_R_R_MAX_SEVERITY)
            .assetGroupId(DEFAULT_ASSET_GROUP_ID)
            .businessImpact(DEFAULT_BUSINESS_IMPACT)
            .url(DEFAULT_URL)
            .description(DEFAULT_DESCRIPTION)
            .businessUnit(DEFAULT_BUSINESS_UNIT)
            .businessUnitId(DEFAULT_BUSINESS_UNIT_ID)
            .types(DEFAULT_TYPES)
            .technology(DEFAULT_TECHNOLOGY)
            .testingStatus(DEFAULT_TESTING_STATUS)
            .appHosts(DEFAULT_APP_HOSTS)
            .collateralDamagePotential(DEFAULT_COLLATERAL_DAMAGE_POTENTIAL)
            .targetDistribution(DEFAULT_TARGET_DISTRIBUTION)
            .confidentialityRequirement(DEFAULT_CONFIDENTIALITY_REQUIREMENT)
            .integrityRequirement(DEFAULT_INTEGRITY_REQUIREMENT)
            .availabilityRequirement(DEFAULT_AVAILABILITY_REQUIREMENT)
            .tester(DEFAULT_TESTER)
            .businessOwner(DEFAULT_BUSINESS_OWNER)
            .developmentContact(DEFAULT_DEVELOPMENT_CONTACT)
            .preferredOfferingType(DEFAULT_PREFERRED_OFFERING_TYPE)
            .assetGroupName(DEFAULT_ASSET_GROUP_NAME)
            .dateCreated(DEFAULT_DATE_CREATED)
            .lastUpdated(DEFAULT_LAST_UPDATED)
            .lastComment(DEFAULT_LAST_COMMENT)
            .createdBy(DEFAULT_CREATED_BY)
            .newIssues(DEFAULT_NEW_ISSUES)
            .openIssues(DEFAULT_OPEN_ISSUES)
            .totalIssues(DEFAULT_TOTAL_ISSUES)
            .overallCompliance(DEFAULT_OVERALL_COMPLIANCE)
            .canBeDeleted(DEFAULT_CAN_BE_DELETED)
            .lockedToSubscription(DEFAULT_LOCKED_TO_SUBSCRIPTION)
            .totalScans(DEFAULT_TOTAL_SCANS)
            .nScanExecutions(DEFAULT_N_SCAN_EXECUTIONS)
            .hasExceedingIssuesNumber(DEFAULT_HAS_EXCEEDING_ISSUES_NUMBER)
            .hasExceedingScansNumber(DEFAULT_HAS_EXCEEDING_SCANS_NUMBER)
            .autoDeleteExceededScans(DEFAULT_AUTO_DELETE_EXCEEDED_SCANS);
        return appDetails;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AppDetails createUpdatedEntity(EntityManager em) {
        AppDetails appDetails = new AppDetails()
            .appId(UPDATED_APP_ID)
            .riskRating(UPDATED_RISK_RATING)
            .criticalIssues(UPDATED_CRITICAL_ISSUES)
            .highIssues(UPDATED_HIGH_ISSUES)
            .mediumIssues(UPDATED_MEDIUM_ISSUES)
            .lowIssues(UPDATED_LOW_ISSUES)
            .informationalIssues(UPDATED_INFORMATIONAL_ISSUES)
            .issuesInProgress(UPDATED_ISSUES_IN_PROGRESS)
            .maxSeverity(UPDATED_MAX_SEVERITY)
            .correlationState(UPDATED_CORRELATION_STATE)
            .rRMaxSeverity(UPDATED_R_R_MAX_SEVERITY)
            .assetGroupId(UPDATED_ASSET_GROUP_ID)
            .businessImpact(UPDATED_BUSINESS_IMPACT)
            .url(UPDATED_URL)
            .description(UPDATED_DESCRIPTION)
            .businessUnit(UPDATED_BUSINESS_UNIT)
            .businessUnitId(UPDATED_BUSINESS_UNIT_ID)
            .types(UPDATED_TYPES)
            .technology(UPDATED_TECHNOLOGY)
            .testingStatus(UPDATED_TESTING_STATUS)
            .appHosts(UPDATED_APP_HOSTS)
            .collateralDamagePotential(UPDATED_COLLATERAL_DAMAGE_POTENTIAL)
            .targetDistribution(UPDATED_TARGET_DISTRIBUTION)
            .confidentialityRequirement(UPDATED_CONFIDENTIALITY_REQUIREMENT)
            .integrityRequirement(UPDATED_INTEGRITY_REQUIREMENT)
            .availabilityRequirement(UPDATED_AVAILABILITY_REQUIREMENT)
            .tester(UPDATED_TESTER)
            .businessOwner(UPDATED_BUSINESS_OWNER)
            .developmentContact(UPDATED_DEVELOPMENT_CONTACT)
            .preferredOfferingType(UPDATED_PREFERRED_OFFERING_TYPE)
            .assetGroupName(UPDATED_ASSET_GROUP_NAME)
            .dateCreated(UPDATED_DATE_CREATED)
            .lastUpdated(UPDATED_LAST_UPDATED)
            .lastComment(UPDATED_LAST_COMMENT)
            .createdBy(UPDATED_CREATED_BY)
            .newIssues(UPDATED_NEW_ISSUES)
            .openIssues(UPDATED_OPEN_ISSUES)
            .totalIssues(UPDATED_TOTAL_ISSUES)
            .overallCompliance(UPDATED_OVERALL_COMPLIANCE)
            .canBeDeleted(UPDATED_CAN_BE_DELETED)
            .lockedToSubscription(UPDATED_LOCKED_TO_SUBSCRIPTION)
            .totalScans(UPDATED_TOTAL_SCANS)
            .nScanExecutions(UPDATED_N_SCAN_EXECUTIONS)
            .hasExceedingIssuesNumber(UPDATED_HAS_EXCEEDING_ISSUES_NUMBER)
            .hasExceedingScansNumber(UPDATED_HAS_EXCEEDING_SCANS_NUMBER)
            .autoDeleteExceededScans(UPDATED_AUTO_DELETE_EXCEEDED_SCANS);
        return appDetails;
    }

    @BeforeEach
    public void initTest() {
        appDetails = createEntity(em);
    }

    @Test
    @Transactional
    void createAppDetails() throws Exception {
        int databaseSizeBeforeCreate = appDetailsRepository.findAll().size();
        // Create the AppDetails
        restAppDetailsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(appDetails)))
            .andExpect(status().isCreated());

        // Validate the AppDetails in the database
        List<AppDetails> appDetailsList = appDetailsRepository.findAll();
        assertThat(appDetailsList).hasSize(databaseSizeBeforeCreate + 1);
        AppDetails testAppDetails = appDetailsList.get(appDetailsList.size() - 1);
        assertThat(testAppDetails.getAppId()).isEqualTo(DEFAULT_APP_ID);
        assertThat(testAppDetails.getRiskRating()).isEqualTo(DEFAULT_RISK_RATING);
        assertThat(testAppDetails.getCriticalIssues()).isEqualTo(DEFAULT_CRITICAL_ISSUES);
        assertThat(testAppDetails.getHighIssues()).isEqualTo(DEFAULT_HIGH_ISSUES);
        assertThat(testAppDetails.getMediumIssues()).isEqualTo(DEFAULT_MEDIUM_ISSUES);
        assertThat(testAppDetails.getLowIssues()).isEqualTo(DEFAULT_LOW_ISSUES);
        assertThat(testAppDetails.getInformationalIssues()).isEqualTo(DEFAULT_INFORMATIONAL_ISSUES);
        assertThat(testAppDetails.getIssuesInProgress()).isEqualTo(DEFAULT_ISSUES_IN_PROGRESS);
        assertThat(testAppDetails.getMaxSeverity()).isEqualTo(DEFAULT_MAX_SEVERITY);
        assertThat(testAppDetails.getCorrelationState()).isEqualTo(DEFAULT_CORRELATION_STATE);
        assertThat(testAppDetails.getrRMaxSeverity()).isEqualTo(DEFAULT_R_R_MAX_SEVERITY);
        assertThat(testAppDetails.getAssetGroupId()).isEqualTo(DEFAULT_ASSET_GROUP_ID);
        assertThat(testAppDetails.getBusinessImpact()).isEqualTo(DEFAULT_BUSINESS_IMPACT);
        assertThat(testAppDetails.getUrl()).isEqualTo(DEFAULT_URL);
        assertThat(testAppDetails.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testAppDetails.getBusinessUnit()).isEqualTo(DEFAULT_BUSINESS_UNIT);
        assertThat(testAppDetails.getBusinessUnitId()).isEqualTo(DEFAULT_BUSINESS_UNIT_ID);
        assertThat(testAppDetails.getTypes()).isEqualTo(DEFAULT_TYPES);
        assertThat(testAppDetails.getTechnology()).isEqualTo(DEFAULT_TECHNOLOGY);
        assertThat(testAppDetails.getTestingStatus()).isEqualTo(DEFAULT_TESTING_STATUS);
        assertThat(testAppDetails.getAppHosts()).isEqualTo(DEFAULT_APP_HOSTS);
        assertThat(testAppDetails.getCollateralDamagePotential()).isEqualTo(DEFAULT_COLLATERAL_DAMAGE_POTENTIAL);
        assertThat(testAppDetails.getTargetDistribution()).isEqualTo(DEFAULT_TARGET_DISTRIBUTION);
        assertThat(testAppDetails.getConfidentialityRequirement()).isEqualTo(DEFAULT_CONFIDENTIALITY_REQUIREMENT);
        assertThat(testAppDetails.getIntegrityRequirement()).isEqualTo(DEFAULT_INTEGRITY_REQUIREMENT);
        assertThat(testAppDetails.getAvailabilityRequirement()).isEqualTo(DEFAULT_AVAILABILITY_REQUIREMENT);
        assertThat(testAppDetails.getTester()).isEqualTo(DEFAULT_TESTER);
        assertThat(testAppDetails.getBusinessOwner()).isEqualTo(DEFAULT_BUSINESS_OWNER);
        assertThat(testAppDetails.getDevelopmentContact()).isEqualTo(DEFAULT_DEVELOPMENT_CONTACT);
        assertThat(testAppDetails.getPreferredOfferingType()).isEqualTo(DEFAULT_PREFERRED_OFFERING_TYPE);
        assertThat(testAppDetails.getAssetGroupName()).isEqualTo(DEFAULT_ASSET_GROUP_NAME);
        assertThat(testAppDetails.getDateCreated()).isEqualTo(DEFAULT_DATE_CREATED);
        assertThat(testAppDetails.getLastUpdated()).isEqualTo(DEFAULT_LAST_UPDATED);
        assertThat(testAppDetails.getLastComment()).isEqualTo(DEFAULT_LAST_COMMENT);
        assertThat(testAppDetails.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testAppDetails.getNewIssues()).isEqualTo(DEFAULT_NEW_ISSUES);
        assertThat(testAppDetails.getOpenIssues()).isEqualTo(DEFAULT_OPEN_ISSUES);
        assertThat(testAppDetails.getTotalIssues()).isEqualTo(DEFAULT_TOTAL_ISSUES);
        assertThat(testAppDetails.getOverallCompliance()).isEqualTo(DEFAULT_OVERALL_COMPLIANCE);
        assertThat(testAppDetails.getCanBeDeleted()).isEqualTo(DEFAULT_CAN_BE_DELETED);
        assertThat(testAppDetails.getLockedToSubscription()).isEqualTo(DEFAULT_LOCKED_TO_SUBSCRIPTION);
        assertThat(testAppDetails.getTotalScans()).isEqualTo(DEFAULT_TOTAL_SCANS);
        assertThat(testAppDetails.getnScanExecutions()).isEqualTo(DEFAULT_N_SCAN_EXECUTIONS);
        assertThat(testAppDetails.getHasExceedingIssuesNumber()).isEqualTo(DEFAULT_HAS_EXCEEDING_ISSUES_NUMBER);
        assertThat(testAppDetails.getHasExceedingScansNumber()).isEqualTo(DEFAULT_HAS_EXCEEDING_SCANS_NUMBER);
        assertThat(testAppDetails.getAutoDeleteExceededScans()).isEqualTo(DEFAULT_AUTO_DELETE_EXCEEDED_SCANS);
    }

    @Test
    @Transactional
    void createAppDetailsWithExistingId() throws Exception {
        // Create the AppDetails with an existing ID
        appDetails.setId(1L);

        int databaseSizeBeforeCreate = appDetailsRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restAppDetailsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(appDetails)))
            .andExpect(status().isBadRequest());

        // Validate the AppDetails in the database
        List<AppDetails> appDetailsList = appDetailsRepository.findAll();
        assertThat(appDetailsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllAppDetails() throws Exception {
        // Initialize the database
        appDetailsRepository.saveAndFlush(appDetails);

        // Get all the appDetailsList
        restAppDetailsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(appDetails.getId().intValue())))
            .andExpect(jsonPath("$.[*].appId").value(hasItem(DEFAULT_APP_ID)))
            .andExpect(jsonPath("$.[*].riskRating").value(hasItem(DEFAULT_RISK_RATING)))
            .andExpect(jsonPath("$.[*].criticalIssues").value(hasItem(DEFAULT_CRITICAL_ISSUES)))
            .andExpect(jsonPath("$.[*].highIssues").value(hasItem(DEFAULT_HIGH_ISSUES)))
            .andExpect(jsonPath("$.[*].mediumIssues").value(hasItem(DEFAULT_MEDIUM_ISSUES)))
            .andExpect(jsonPath("$.[*].lowIssues").value(hasItem(DEFAULT_LOW_ISSUES)))
            .andExpect(jsonPath("$.[*].informationalIssues").value(hasItem(DEFAULT_INFORMATIONAL_ISSUES)))
            .andExpect(jsonPath("$.[*].issuesInProgress").value(hasItem(DEFAULT_ISSUES_IN_PROGRESS)))
            .andExpect(jsonPath("$.[*].maxSeverity").value(hasItem(DEFAULT_MAX_SEVERITY)))
            .andExpect(jsonPath("$.[*].correlationState").value(hasItem(DEFAULT_CORRELATION_STATE)))
            .andExpect(jsonPath("$.[*].rRMaxSeverity").value(hasItem(DEFAULT_R_R_MAX_SEVERITY)))
            .andExpect(jsonPath("$.[*].assetGroupId").value(hasItem(DEFAULT_ASSET_GROUP_ID)))
            .andExpect(jsonPath("$.[*].businessImpact").value(hasItem(DEFAULT_BUSINESS_IMPACT)))
            .andExpect(jsonPath("$.[*].url").value(hasItem(DEFAULT_URL)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].businessUnit").value(hasItem(DEFAULT_BUSINESS_UNIT)))
            .andExpect(jsonPath("$.[*].businessUnitId").value(hasItem(DEFAULT_BUSINESS_UNIT_ID)))
            .andExpect(jsonPath("$.[*].types").value(hasItem(DEFAULT_TYPES)))
            .andExpect(jsonPath("$.[*].technology").value(hasItem(DEFAULT_TECHNOLOGY)))
            .andExpect(jsonPath("$.[*].testingStatus").value(hasItem(DEFAULT_TESTING_STATUS)))
            .andExpect(jsonPath("$.[*].appHosts").value(hasItem(DEFAULT_APP_HOSTS)))
            .andExpect(jsonPath("$.[*].collateralDamagePotential").value(hasItem(DEFAULT_COLLATERAL_DAMAGE_POTENTIAL)))
            .andExpect(jsonPath("$.[*].targetDistribution").value(hasItem(DEFAULT_TARGET_DISTRIBUTION)))
            .andExpect(jsonPath("$.[*].confidentialityRequirement").value(hasItem(DEFAULT_CONFIDENTIALITY_REQUIREMENT)))
            .andExpect(jsonPath("$.[*].integrityRequirement").value(hasItem(DEFAULT_INTEGRITY_REQUIREMENT)))
            .andExpect(jsonPath("$.[*].availabilityRequirement").value(hasItem(DEFAULT_AVAILABILITY_REQUIREMENT)))
            .andExpect(jsonPath("$.[*].tester").value(hasItem(DEFAULT_TESTER)))
            .andExpect(jsonPath("$.[*].businessOwner").value(hasItem(DEFAULT_BUSINESS_OWNER)))
            .andExpect(jsonPath("$.[*].developmentContact").value(hasItem(DEFAULT_DEVELOPMENT_CONTACT)))
            .andExpect(jsonPath("$.[*].preferredOfferingType").value(hasItem(DEFAULT_PREFERRED_OFFERING_TYPE)))
            .andExpect(jsonPath("$.[*].assetGroupName").value(hasItem(DEFAULT_ASSET_GROUP_NAME)))
            .andExpect(jsonPath("$.[*].dateCreated").value(hasItem(sameInstant(DEFAULT_DATE_CREATED))))
            .andExpect(jsonPath("$.[*].lastUpdated").value(hasItem(sameInstant(DEFAULT_LAST_UPDATED))))
            .andExpect(jsonPath("$.[*].lastComment").value(hasItem(DEFAULT_LAST_COMMENT)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].newIssues").value(hasItem(DEFAULT_NEW_ISSUES)))
            .andExpect(jsonPath("$.[*].openIssues").value(hasItem(DEFAULT_OPEN_ISSUES)))
            .andExpect(jsonPath("$.[*].totalIssues").value(hasItem(DEFAULT_TOTAL_ISSUES)))
            .andExpect(jsonPath("$.[*].overallCompliance").value(hasItem(DEFAULT_OVERALL_COMPLIANCE.booleanValue())))
            .andExpect(jsonPath("$.[*].canBeDeleted").value(hasItem(DEFAULT_CAN_BE_DELETED.booleanValue())))
            .andExpect(jsonPath("$.[*].lockedToSubscription").value(hasItem(DEFAULT_LOCKED_TO_SUBSCRIPTION.booleanValue())))
            .andExpect(jsonPath("$.[*].totalScans").value(hasItem(DEFAULT_TOTAL_SCANS)))
            .andExpect(jsonPath("$.[*].nScanExecutions").value(hasItem(DEFAULT_N_SCAN_EXECUTIONS)))
            .andExpect(jsonPath("$.[*].hasExceedingIssuesNumber").value(hasItem(DEFAULT_HAS_EXCEEDING_ISSUES_NUMBER.booleanValue())))
            .andExpect(jsonPath("$.[*].hasExceedingScansNumber").value(hasItem(DEFAULT_HAS_EXCEEDING_SCANS_NUMBER.booleanValue())))
            .andExpect(jsonPath("$.[*].autoDeleteExceededScans").value(hasItem(DEFAULT_AUTO_DELETE_EXCEEDED_SCANS.booleanValue())));
    }

    @Test
    @Transactional
    void getAppDetails() throws Exception {
        // Initialize the database
        appDetailsRepository.saveAndFlush(appDetails);

        // Get the appDetails
        restAppDetailsMockMvc
            .perform(get(ENTITY_API_URL_ID, appDetails.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(appDetails.getId().intValue()))
            .andExpect(jsonPath("$.appId").value(DEFAULT_APP_ID))
            .andExpect(jsonPath("$.riskRating").value(DEFAULT_RISK_RATING))
            .andExpect(jsonPath("$.criticalIssues").value(DEFAULT_CRITICAL_ISSUES))
            .andExpect(jsonPath("$.highIssues").value(DEFAULT_HIGH_ISSUES))
            .andExpect(jsonPath("$.mediumIssues").value(DEFAULT_MEDIUM_ISSUES))
            .andExpect(jsonPath("$.lowIssues").value(DEFAULT_LOW_ISSUES))
            .andExpect(jsonPath("$.informationalIssues").value(DEFAULT_INFORMATIONAL_ISSUES))
            .andExpect(jsonPath("$.issuesInProgress").value(DEFAULT_ISSUES_IN_PROGRESS))
            .andExpect(jsonPath("$.maxSeverity").value(DEFAULT_MAX_SEVERITY))
            .andExpect(jsonPath("$.correlationState").value(DEFAULT_CORRELATION_STATE))
            .andExpect(jsonPath("$.rRMaxSeverity").value(DEFAULT_R_R_MAX_SEVERITY))
            .andExpect(jsonPath("$.assetGroupId").value(DEFAULT_ASSET_GROUP_ID))
            .andExpect(jsonPath("$.businessImpact").value(DEFAULT_BUSINESS_IMPACT))
            .andExpect(jsonPath("$.url").value(DEFAULT_URL))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.businessUnit").value(DEFAULT_BUSINESS_UNIT))
            .andExpect(jsonPath("$.businessUnitId").value(DEFAULT_BUSINESS_UNIT_ID))
            .andExpect(jsonPath("$.types").value(DEFAULT_TYPES))
            .andExpect(jsonPath("$.technology").value(DEFAULT_TECHNOLOGY))
            .andExpect(jsonPath("$.testingStatus").value(DEFAULT_TESTING_STATUS))
            .andExpect(jsonPath("$.appHosts").value(DEFAULT_APP_HOSTS))
            .andExpect(jsonPath("$.collateralDamagePotential").value(DEFAULT_COLLATERAL_DAMAGE_POTENTIAL))
            .andExpect(jsonPath("$.targetDistribution").value(DEFAULT_TARGET_DISTRIBUTION))
            .andExpect(jsonPath("$.confidentialityRequirement").value(DEFAULT_CONFIDENTIALITY_REQUIREMENT))
            .andExpect(jsonPath("$.integrityRequirement").value(DEFAULT_INTEGRITY_REQUIREMENT))
            .andExpect(jsonPath("$.availabilityRequirement").value(DEFAULT_AVAILABILITY_REQUIREMENT))
            .andExpect(jsonPath("$.tester").value(DEFAULT_TESTER))
            .andExpect(jsonPath("$.businessOwner").value(DEFAULT_BUSINESS_OWNER))
            .andExpect(jsonPath("$.developmentContact").value(DEFAULT_DEVELOPMENT_CONTACT))
            .andExpect(jsonPath("$.preferredOfferingType").value(DEFAULT_PREFERRED_OFFERING_TYPE))
            .andExpect(jsonPath("$.assetGroupName").value(DEFAULT_ASSET_GROUP_NAME))
            .andExpect(jsonPath("$.dateCreated").value(sameInstant(DEFAULT_DATE_CREATED)))
            .andExpect(jsonPath("$.lastUpdated").value(sameInstant(DEFAULT_LAST_UPDATED)))
            .andExpect(jsonPath("$.lastComment").value(DEFAULT_LAST_COMMENT))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.newIssues").value(DEFAULT_NEW_ISSUES))
            .andExpect(jsonPath("$.openIssues").value(DEFAULT_OPEN_ISSUES))
            .andExpect(jsonPath("$.totalIssues").value(DEFAULT_TOTAL_ISSUES))
            .andExpect(jsonPath("$.overallCompliance").value(DEFAULT_OVERALL_COMPLIANCE.booleanValue()))
            .andExpect(jsonPath("$.canBeDeleted").value(DEFAULT_CAN_BE_DELETED.booleanValue()))
            .andExpect(jsonPath("$.lockedToSubscription").value(DEFAULT_LOCKED_TO_SUBSCRIPTION.booleanValue()))
            .andExpect(jsonPath("$.totalScans").value(DEFAULT_TOTAL_SCANS))
            .andExpect(jsonPath("$.nScanExecutions").value(DEFAULT_N_SCAN_EXECUTIONS))
            .andExpect(jsonPath("$.hasExceedingIssuesNumber").value(DEFAULT_HAS_EXCEEDING_ISSUES_NUMBER.booleanValue()))
            .andExpect(jsonPath("$.hasExceedingScansNumber").value(DEFAULT_HAS_EXCEEDING_SCANS_NUMBER.booleanValue()))
            .andExpect(jsonPath("$.autoDeleteExceededScans").value(DEFAULT_AUTO_DELETE_EXCEEDED_SCANS.booleanValue()));
    }

    @Test
    @Transactional
    void getNonExistingAppDetails() throws Exception {
        // Get the appDetails
        restAppDetailsMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingAppDetails() throws Exception {
        // Initialize the database
        appDetailsRepository.saveAndFlush(appDetails);

        int databaseSizeBeforeUpdate = appDetailsRepository.findAll().size();

        // Update the appDetails
        AppDetails updatedAppDetails = appDetailsRepository.findById(appDetails.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedAppDetails are not directly saved in db
        em.detach(updatedAppDetails);
        updatedAppDetails
            .appId(UPDATED_APP_ID)
            .riskRating(UPDATED_RISK_RATING)
            .criticalIssues(UPDATED_CRITICAL_ISSUES)
            .highIssues(UPDATED_HIGH_ISSUES)
            .mediumIssues(UPDATED_MEDIUM_ISSUES)
            .lowIssues(UPDATED_LOW_ISSUES)
            .informationalIssues(UPDATED_INFORMATIONAL_ISSUES)
            .issuesInProgress(UPDATED_ISSUES_IN_PROGRESS)
            .maxSeverity(UPDATED_MAX_SEVERITY)
            .correlationState(UPDATED_CORRELATION_STATE)
            .rRMaxSeverity(UPDATED_R_R_MAX_SEVERITY)
            .assetGroupId(UPDATED_ASSET_GROUP_ID)
            .businessImpact(UPDATED_BUSINESS_IMPACT)
            .url(UPDATED_URL)
            .description(UPDATED_DESCRIPTION)
            .businessUnit(UPDATED_BUSINESS_UNIT)
            .businessUnitId(UPDATED_BUSINESS_UNIT_ID)
            .types(UPDATED_TYPES)
            .technology(UPDATED_TECHNOLOGY)
            .testingStatus(UPDATED_TESTING_STATUS)
            .appHosts(UPDATED_APP_HOSTS)
            .collateralDamagePotential(UPDATED_COLLATERAL_DAMAGE_POTENTIAL)
            .targetDistribution(UPDATED_TARGET_DISTRIBUTION)
            .confidentialityRequirement(UPDATED_CONFIDENTIALITY_REQUIREMENT)
            .integrityRequirement(UPDATED_INTEGRITY_REQUIREMENT)
            .availabilityRequirement(UPDATED_AVAILABILITY_REQUIREMENT)
            .tester(UPDATED_TESTER)
            .businessOwner(UPDATED_BUSINESS_OWNER)
            .developmentContact(UPDATED_DEVELOPMENT_CONTACT)
            .preferredOfferingType(UPDATED_PREFERRED_OFFERING_TYPE)
            .assetGroupName(UPDATED_ASSET_GROUP_NAME)
            .dateCreated(UPDATED_DATE_CREATED)
            .lastUpdated(UPDATED_LAST_UPDATED)
            .lastComment(UPDATED_LAST_COMMENT)
            .createdBy(UPDATED_CREATED_BY)
            .newIssues(UPDATED_NEW_ISSUES)
            .openIssues(UPDATED_OPEN_ISSUES)
            .totalIssues(UPDATED_TOTAL_ISSUES)
            .overallCompliance(UPDATED_OVERALL_COMPLIANCE)
            .canBeDeleted(UPDATED_CAN_BE_DELETED)
            .lockedToSubscription(UPDATED_LOCKED_TO_SUBSCRIPTION)
            .totalScans(UPDATED_TOTAL_SCANS)
            .nScanExecutions(UPDATED_N_SCAN_EXECUTIONS)
            .hasExceedingIssuesNumber(UPDATED_HAS_EXCEEDING_ISSUES_NUMBER)
            .hasExceedingScansNumber(UPDATED_HAS_EXCEEDING_SCANS_NUMBER)
            .autoDeleteExceededScans(UPDATED_AUTO_DELETE_EXCEEDED_SCANS);

        restAppDetailsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedAppDetails.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedAppDetails))
            )
            .andExpect(status().isOk());

        // Validate the AppDetails in the database
        List<AppDetails> appDetailsList = appDetailsRepository.findAll();
        assertThat(appDetailsList).hasSize(databaseSizeBeforeUpdate);
        AppDetails testAppDetails = appDetailsList.get(appDetailsList.size() - 1);
        assertThat(testAppDetails.getAppId()).isEqualTo(UPDATED_APP_ID);
        assertThat(testAppDetails.getRiskRating()).isEqualTo(UPDATED_RISK_RATING);
        assertThat(testAppDetails.getCriticalIssues()).isEqualTo(UPDATED_CRITICAL_ISSUES);
        assertThat(testAppDetails.getHighIssues()).isEqualTo(UPDATED_HIGH_ISSUES);
        assertThat(testAppDetails.getMediumIssues()).isEqualTo(UPDATED_MEDIUM_ISSUES);
        assertThat(testAppDetails.getLowIssues()).isEqualTo(UPDATED_LOW_ISSUES);
        assertThat(testAppDetails.getInformationalIssues()).isEqualTo(UPDATED_INFORMATIONAL_ISSUES);
        assertThat(testAppDetails.getIssuesInProgress()).isEqualTo(UPDATED_ISSUES_IN_PROGRESS);
        assertThat(testAppDetails.getMaxSeverity()).isEqualTo(UPDATED_MAX_SEVERITY);
        assertThat(testAppDetails.getCorrelationState()).isEqualTo(UPDATED_CORRELATION_STATE);
        assertThat(testAppDetails.getrRMaxSeverity()).isEqualTo(UPDATED_R_R_MAX_SEVERITY);
        assertThat(testAppDetails.getAssetGroupId()).isEqualTo(UPDATED_ASSET_GROUP_ID);
        assertThat(testAppDetails.getBusinessImpact()).isEqualTo(UPDATED_BUSINESS_IMPACT);
        assertThat(testAppDetails.getUrl()).isEqualTo(UPDATED_URL);
        assertThat(testAppDetails.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testAppDetails.getBusinessUnit()).isEqualTo(UPDATED_BUSINESS_UNIT);
        assertThat(testAppDetails.getBusinessUnitId()).isEqualTo(UPDATED_BUSINESS_UNIT_ID);
        assertThat(testAppDetails.getTypes()).isEqualTo(UPDATED_TYPES);
        assertThat(testAppDetails.getTechnology()).isEqualTo(UPDATED_TECHNOLOGY);
        assertThat(testAppDetails.getTestingStatus()).isEqualTo(UPDATED_TESTING_STATUS);
        assertThat(testAppDetails.getAppHosts()).isEqualTo(UPDATED_APP_HOSTS);
        assertThat(testAppDetails.getCollateralDamagePotential()).isEqualTo(UPDATED_COLLATERAL_DAMAGE_POTENTIAL);
        assertThat(testAppDetails.getTargetDistribution()).isEqualTo(UPDATED_TARGET_DISTRIBUTION);
        assertThat(testAppDetails.getConfidentialityRequirement()).isEqualTo(UPDATED_CONFIDENTIALITY_REQUIREMENT);
        assertThat(testAppDetails.getIntegrityRequirement()).isEqualTo(UPDATED_INTEGRITY_REQUIREMENT);
        assertThat(testAppDetails.getAvailabilityRequirement()).isEqualTo(UPDATED_AVAILABILITY_REQUIREMENT);
        assertThat(testAppDetails.getTester()).isEqualTo(UPDATED_TESTER);
        assertThat(testAppDetails.getBusinessOwner()).isEqualTo(UPDATED_BUSINESS_OWNER);
        assertThat(testAppDetails.getDevelopmentContact()).isEqualTo(UPDATED_DEVELOPMENT_CONTACT);
        assertThat(testAppDetails.getPreferredOfferingType()).isEqualTo(UPDATED_PREFERRED_OFFERING_TYPE);
        assertThat(testAppDetails.getAssetGroupName()).isEqualTo(UPDATED_ASSET_GROUP_NAME);
        assertThat(testAppDetails.getDateCreated()).isEqualTo(UPDATED_DATE_CREATED);
        assertThat(testAppDetails.getLastUpdated()).isEqualTo(UPDATED_LAST_UPDATED);
        assertThat(testAppDetails.getLastComment()).isEqualTo(UPDATED_LAST_COMMENT);
        assertThat(testAppDetails.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testAppDetails.getNewIssues()).isEqualTo(UPDATED_NEW_ISSUES);
        assertThat(testAppDetails.getOpenIssues()).isEqualTo(UPDATED_OPEN_ISSUES);
        assertThat(testAppDetails.getTotalIssues()).isEqualTo(UPDATED_TOTAL_ISSUES);
        assertThat(testAppDetails.getOverallCompliance()).isEqualTo(UPDATED_OVERALL_COMPLIANCE);
        assertThat(testAppDetails.getCanBeDeleted()).isEqualTo(UPDATED_CAN_BE_DELETED);
        assertThat(testAppDetails.getLockedToSubscription()).isEqualTo(UPDATED_LOCKED_TO_SUBSCRIPTION);
        assertThat(testAppDetails.getTotalScans()).isEqualTo(UPDATED_TOTAL_SCANS);
        assertThat(testAppDetails.getnScanExecutions()).isEqualTo(UPDATED_N_SCAN_EXECUTIONS);
        assertThat(testAppDetails.getHasExceedingIssuesNumber()).isEqualTo(UPDATED_HAS_EXCEEDING_ISSUES_NUMBER);
        assertThat(testAppDetails.getHasExceedingScansNumber()).isEqualTo(UPDATED_HAS_EXCEEDING_SCANS_NUMBER);
        assertThat(testAppDetails.getAutoDeleteExceededScans()).isEqualTo(UPDATED_AUTO_DELETE_EXCEEDED_SCANS);
    }

    @Test
    @Transactional
    void putNonExistingAppDetails() throws Exception {
        int databaseSizeBeforeUpdate = appDetailsRepository.findAll().size();
        appDetails.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAppDetailsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, appDetails.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(appDetails))
            )
            .andExpect(status().isBadRequest());

        // Validate the AppDetails in the database
        List<AppDetails> appDetailsList = appDetailsRepository.findAll();
        assertThat(appDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchAppDetails() throws Exception {
        int databaseSizeBeforeUpdate = appDetailsRepository.findAll().size();
        appDetails.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAppDetailsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(appDetails))
            )
            .andExpect(status().isBadRequest());

        // Validate the AppDetails in the database
        List<AppDetails> appDetailsList = appDetailsRepository.findAll();
        assertThat(appDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamAppDetails() throws Exception {
        int databaseSizeBeforeUpdate = appDetailsRepository.findAll().size();
        appDetails.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAppDetailsMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(appDetails)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the AppDetails in the database
        List<AppDetails> appDetailsList = appDetailsRepository.findAll();
        assertThat(appDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateAppDetailsWithPatch() throws Exception {
        // Initialize the database
        appDetailsRepository.saveAndFlush(appDetails);

        int databaseSizeBeforeUpdate = appDetailsRepository.findAll().size();

        // Update the appDetails using partial update
        AppDetails partialUpdatedAppDetails = new AppDetails();
        partialUpdatedAppDetails.setId(appDetails.getId());

        partialUpdatedAppDetails
            .riskRating(UPDATED_RISK_RATING)
            .criticalIssues(UPDATED_CRITICAL_ISSUES)
            .highIssues(UPDATED_HIGH_ISSUES)
            .mediumIssues(UPDATED_MEDIUM_ISSUES)
            .informationalIssues(UPDATED_INFORMATIONAL_ISSUES)
            .issuesInProgress(UPDATED_ISSUES_IN_PROGRESS)
            .correlationState(UPDATED_CORRELATION_STATE)
            .rRMaxSeverity(UPDATED_R_R_MAX_SEVERITY)
            .url(UPDATED_URL)
            .businessUnit(UPDATED_BUSINESS_UNIT)
            .technology(UPDATED_TECHNOLOGY)
            .collateralDamagePotential(UPDATED_COLLATERAL_DAMAGE_POTENTIAL)
            .targetDistribution(UPDATED_TARGET_DISTRIBUTION)
            .confidentialityRequirement(UPDATED_CONFIDENTIALITY_REQUIREMENT)
            .availabilityRequirement(UPDATED_AVAILABILITY_REQUIREMENT)
            .tester(UPDATED_TESTER)
            .preferredOfferingType(UPDATED_PREFERRED_OFFERING_TYPE)
            .assetGroupName(UPDATED_ASSET_GROUP_NAME)
            .lastUpdated(UPDATED_LAST_UPDATED)
            .lastComment(UPDATED_LAST_COMMENT)
            .createdBy(UPDATED_CREATED_BY)
            .openIssues(UPDATED_OPEN_ISSUES)
            .totalScans(UPDATED_TOTAL_SCANS)
            .nScanExecutions(UPDATED_N_SCAN_EXECUTIONS)
            .autoDeleteExceededScans(UPDATED_AUTO_DELETE_EXCEEDED_SCANS);

        restAppDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAppDetails.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAppDetails))
            )
            .andExpect(status().isOk());

        // Validate the AppDetails in the database
        List<AppDetails> appDetailsList = appDetailsRepository.findAll();
        assertThat(appDetailsList).hasSize(databaseSizeBeforeUpdate);
        AppDetails testAppDetails = appDetailsList.get(appDetailsList.size() - 1);
        assertThat(testAppDetails.getAppId()).isEqualTo(DEFAULT_APP_ID);
        assertThat(testAppDetails.getRiskRating()).isEqualTo(UPDATED_RISK_RATING);
        assertThat(testAppDetails.getCriticalIssues()).isEqualTo(UPDATED_CRITICAL_ISSUES);
        assertThat(testAppDetails.getHighIssues()).isEqualTo(UPDATED_HIGH_ISSUES);
        assertThat(testAppDetails.getMediumIssues()).isEqualTo(UPDATED_MEDIUM_ISSUES);
        assertThat(testAppDetails.getLowIssues()).isEqualTo(DEFAULT_LOW_ISSUES);
        assertThat(testAppDetails.getInformationalIssues()).isEqualTo(UPDATED_INFORMATIONAL_ISSUES);
        assertThat(testAppDetails.getIssuesInProgress()).isEqualTo(UPDATED_ISSUES_IN_PROGRESS);
        assertThat(testAppDetails.getMaxSeverity()).isEqualTo(DEFAULT_MAX_SEVERITY);
        assertThat(testAppDetails.getCorrelationState()).isEqualTo(UPDATED_CORRELATION_STATE);
        assertThat(testAppDetails.getrRMaxSeverity()).isEqualTo(UPDATED_R_R_MAX_SEVERITY);
        assertThat(testAppDetails.getAssetGroupId()).isEqualTo(DEFAULT_ASSET_GROUP_ID);
        assertThat(testAppDetails.getBusinessImpact()).isEqualTo(DEFAULT_BUSINESS_IMPACT);
        assertThat(testAppDetails.getUrl()).isEqualTo(UPDATED_URL);
        assertThat(testAppDetails.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testAppDetails.getBusinessUnit()).isEqualTo(UPDATED_BUSINESS_UNIT);
        assertThat(testAppDetails.getBusinessUnitId()).isEqualTo(DEFAULT_BUSINESS_UNIT_ID);
        assertThat(testAppDetails.getTypes()).isEqualTo(DEFAULT_TYPES);
        assertThat(testAppDetails.getTechnology()).isEqualTo(UPDATED_TECHNOLOGY);
        assertThat(testAppDetails.getTestingStatus()).isEqualTo(DEFAULT_TESTING_STATUS);
        assertThat(testAppDetails.getAppHosts()).isEqualTo(DEFAULT_APP_HOSTS);
        assertThat(testAppDetails.getCollateralDamagePotential()).isEqualTo(UPDATED_COLLATERAL_DAMAGE_POTENTIAL);
        assertThat(testAppDetails.getTargetDistribution()).isEqualTo(UPDATED_TARGET_DISTRIBUTION);
        assertThat(testAppDetails.getConfidentialityRequirement()).isEqualTo(UPDATED_CONFIDENTIALITY_REQUIREMENT);
        assertThat(testAppDetails.getIntegrityRequirement()).isEqualTo(DEFAULT_INTEGRITY_REQUIREMENT);
        assertThat(testAppDetails.getAvailabilityRequirement()).isEqualTo(UPDATED_AVAILABILITY_REQUIREMENT);
        assertThat(testAppDetails.getTester()).isEqualTo(UPDATED_TESTER);
        assertThat(testAppDetails.getBusinessOwner()).isEqualTo(DEFAULT_BUSINESS_OWNER);
        assertThat(testAppDetails.getDevelopmentContact()).isEqualTo(DEFAULT_DEVELOPMENT_CONTACT);
        assertThat(testAppDetails.getPreferredOfferingType()).isEqualTo(UPDATED_PREFERRED_OFFERING_TYPE);
        assertThat(testAppDetails.getAssetGroupName()).isEqualTo(UPDATED_ASSET_GROUP_NAME);
        assertThat(testAppDetails.getDateCreated()).isEqualTo(DEFAULT_DATE_CREATED);
        assertThat(testAppDetails.getLastUpdated()).isEqualTo(UPDATED_LAST_UPDATED);
        assertThat(testAppDetails.getLastComment()).isEqualTo(UPDATED_LAST_COMMENT);
        assertThat(testAppDetails.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testAppDetails.getNewIssues()).isEqualTo(DEFAULT_NEW_ISSUES);
        assertThat(testAppDetails.getOpenIssues()).isEqualTo(UPDATED_OPEN_ISSUES);
        assertThat(testAppDetails.getTotalIssues()).isEqualTo(DEFAULT_TOTAL_ISSUES);
        assertThat(testAppDetails.getOverallCompliance()).isEqualTo(DEFAULT_OVERALL_COMPLIANCE);
        assertThat(testAppDetails.getCanBeDeleted()).isEqualTo(DEFAULT_CAN_BE_DELETED);
        assertThat(testAppDetails.getLockedToSubscription()).isEqualTo(DEFAULT_LOCKED_TO_SUBSCRIPTION);
        assertThat(testAppDetails.getTotalScans()).isEqualTo(UPDATED_TOTAL_SCANS);
        assertThat(testAppDetails.getnScanExecutions()).isEqualTo(UPDATED_N_SCAN_EXECUTIONS);
        assertThat(testAppDetails.getHasExceedingIssuesNumber()).isEqualTo(DEFAULT_HAS_EXCEEDING_ISSUES_NUMBER);
        assertThat(testAppDetails.getHasExceedingScansNumber()).isEqualTo(DEFAULT_HAS_EXCEEDING_SCANS_NUMBER);
        assertThat(testAppDetails.getAutoDeleteExceededScans()).isEqualTo(UPDATED_AUTO_DELETE_EXCEEDED_SCANS);
    }

    @Test
    @Transactional
    void fullUpdateAppDetailsWithPatch() throws Exception {
        // Initialize the database
        appDetailsRepository.saveAndFlush(appDetails);

        int databaseSizeBeforeUpdate = appDetailsRepository.findAll().size();

        // Update the appDetails using partial update
        AppDetails partialUpdatedAppDetails = new AppDetails();
        partialUpdatedAppDetails.setId(appDetails.getId());

        partialUpdatedAppDetails
            .appId(UPDATED_APP_ID)
            .riskRating(UPDATED_RISK_RATING)
            .criticalIssues(UPDATED_CRITICAL_ISSUES)
            .highIssues(UPDATED_HIGH_ISSUES)
            .mediumIssues(UPDATED_MEDIUM_ISSUES)
            .lowIssues(UPDATED_LOW_ISSUES)
            .informationalIssues(UPDATED_INFORMATIONAL_ISSUES)
            .issuesInProgress(UPDATED_ISSUES_IN_PROGRESS)
            .maxSeverity(UPDATED_MAX_SEVERITY)
            .correlationState(UPDATED_CORRELATION_STATE)
            .rRMaxSeverity(UPDATED_R_R_MAX_SEVERITY)
            .assetGroupId(UPDATED_ASSET_GROUP_ID)
            .businessImpact(UPDATED_BUSINESS_IMPACT)
            .url(UPDATED_URL)
            .description(UPDATED_DESCRIPTION)
            .businessUnit(UPDATED_BUSINESS_UNIT)
            .businessUnitId(UPDATED_BUSINESS_UNIT_ID)
            .types(UPDATED_TYPES)
            .technology(UPDATED_TECHNOLOGY)
            .testingStatus(UPDATED_TESTING_STATUS)
            .appHosts(UPDATED_APP_HOSTS)
            .collateralDamagePotential(UPDATED_COLLATERAL_DAMAGE_POTENTIAL)
            .targetDistribution(UPDATED_TARGET_DISTRIBUTION)
            .confidentialityRequirement(UPDATED_CONFIDENTIALITY_REQUIREMENT)
            .integrityRequirement(UPDATED_INTEGRITY_REQUIREMENT)
            .availabilityRequirement(UPDATED_AVAILABILITY_REQUIREMENT)
            .tester(UPDATED_TESTER)
            .businessOwner(UPDATED_BUSINESS_OWNER)
            .developmentContact(UPDATED_DEVELOPMENT_CONTACT)
            .preferredOfferingType(UPDATED_PREFERRED_OFFERING_TYPE)
            .assetGroupName(UPDATED_ASSET_GROUP_NAME)
            .dateCreated(UPDATED_DATE_CREATED)
            .lastUpdated(UPDATED_LAST_UPDATED)
            .lastComment(UPDATED_LAST_COMMENT)
            .createdBy(UPDATED_CREATED_BY)
            .newIssues(UPDATED_NEW_ISSUES)
            .openIssues(UPDATED_OPEN_ISSUES)
            .totalIssues(UPDATED_TOTAL_ISSUES)
            .overallCompliance(UPDATED_OVERALL_COMPLIANCE)
            .canBeDeleted(UPDATED_CAN_BE_DELETED)
            .lockedToSubscription(UPDATED_LOCKED_TO_SUBSCRIPTION)
            .totalScans(UPDATED_TOTAL_SCANS)
            .nScanExecutions(UPDATED_N_SCAN_EXECUTIONS)
            .hasExceedingIssuesNumber(UPDATED_HAS_EXCEEDING_ISSUES_NUMBER)
            .hasExceedingScansNumber(UPDATED_HAS_EXCEEDING_SCANS_NUMBER)
            .autoDeleteExceededScans(UPDATED_AUTO_DELETE_EXCEEDED_SCANS);

        restAppDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAppDetails.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAppDetails))
            )
            .andExpect(status().isOk());

        // Validate the AppDetails in the database
        List<AppDetails> appDetailsList = appDetailsRepository.findAll();
        assertThat(appDetailsList).hasSize(databaseSizeBeforeUpdate);
        AppDetails testAppDetails = appDetailsList.get(appDetailsList.size() - 1);
        assertThat(testAppDetails.getAppId()).isEqualTo(UPDATED_APP_ID);
        assertThat(testAppDetails.getRiskRating()).isEqualTo(UPDATED_RISK_RATING);
        assertThat(testAppDetails.getCriticalIssues()).isEqualTo(UPDATED_CRITICAL_ISSUES);
        assertThat(testAppDetails.getHighIssues()).isEqualTo(UPDATED_HIGH_ISSUES);
        assertThat(testAppDetails.getMediumIssues()).isEqualTo(UPDATED_MEDIUM_ISSUES);
        assertThat(testAppDetails.getLowIssues()).isEqualTo(UPDATED_LOW_ISSUES);
        assertThat(testAppDetails.getInformationalIssues()).isEqualTo(UPDATED_INFORMATIONAL_ISSUES);
        assertThat(testAppDetails.getIssuesInProgress()).isEqualTo(UPDATED_ISSUES_IN_PROGRESS);
        assertThat(testAppDetails.getMaxSeverity()).isEqualTo(UPDATED_MAX_SEVERITY);
        assertThat(testAppDetails.getCorrelationState()).isEqualTo(UPDATED_CORRELATION_STATE);
        assertThat(testAppDetails.getrRMaxSeverity()).isEqualTo(UPDATED_R_R_MAX_SEVERITY);
        assertThat(testAppDetails.getAssetGroupId()).isEqualTo(UPDATED_ASSET_GROUP_ID);
        assertThat(testAppDetails.getBusinessImpact()).isEqualTo(UPDATED_BUSINESS_IMPACT);
        assertThat(testAppDetails.getUrl()).isEqualTo(UPDATED_URL);
        assertThat(testAppDetails.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testAppDetails.getBusinessUnit()).isEqualTo(UPDATED_BUSINESS_UNIT);
        assertThat(testAppDetails.getBusinessUnitId()).isEqualTo(UPDATED_BUSINESS_UNIT_ID);
        assertThat(testAppDetails.getTypes()).isEqualTo(UPDATED_TYPES);
        assertThat(testAppDetails.getTechnology()).isEqualTo(UPDATED_TECHNOLOGY);
        assertThat(testAppDetails.getTestingStatus()).isEqualTo(UPDATED_TESTING_STATUS);
        assertThat(testAppDetails.getAppHosts()).isEqualTo(UPDATED_APP_HOSTS);
        assertThat(testAppDetails.getCollateralDamagePotential()).isEqualTo(UPDATED_COLLATERAL_DAMAGE_POTENTIAL);
        assertThat(testAppDetails.getTargetDistribution()).isEqualTo(UPDATED_TARGET_DISTRIBUTION);
        assertThat(testAppDetails.getConfidentialityRequirement()).isEqualTo(UPDATED_CONFIDENTIALITY_REQUIREMENT);
        assertThat(testAppDetails.getIntegrityRequirement()).isEqualTo(UPDATED_INTEGRITY_REQUIREMENT);
        assertThat(testAppDetails.getAvailabilityRequirement()).isEqualTo(UPDATED_AVAILABILITY_REQUIREMENT);
        assertThat(testAppDetails.getTester()).isEqualTo(UPDATED_TESTER);
        assertThat(testAppDetails.getBusinessOwner()).isEqualTo(UPDATED_BUSINESS_OWNER);
        assertThat(testAppDetails.getDevelopmentContact()).isEqualTo(UPDATED_DEVELOPMENT_CONTACT);
        assertThat(testAppDetails.getPreferredOfferingType()).isEqualTo(UPDATED_PREFERRED_OFFERING_TYPE);
        assertThat(testAppDetails.getAssetGroupName()).isEqualTo(UPDATED_ASSET_GROUP_NAME);
        assertThat(testAppDetails.getDateCreated()).isEqualTo(UPDATED_DATE_CREATED);
        assertThat(testAppDetails.getLastUpdated()).isEqualTo(UPDATED_LAST_UPDATED);
        assertThat(testAppDetails.getLastComment()).isEqualTo(UPDATED_LAST_COMMENT);
        assertThat(testAppDetails.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testAppDetails.getNewIssues()).isEqualTo(UPDATED_NEW_ISSUES);
        assertThat(testAppDetails.getOpenIssues()).isEqualTo(UPDATED_OPEN_ISSUES);
        assertThat(testAppDetails.getTotalIssues()).isEqualTo(UPDATED_TOTAL_ISSUES);
        assertThat(testAppDetails.getOverallCompliance()).isEqualTo(UPDATED_OVERALL_COMPLIANCE);
        assertThat(testAppDetails.getCanBeDeleted()).isEqualTo(UPDATED_CAN_BE_DELETED);
        assertThat(testAppDetails.getLockedToSubscription()).isEqualTo(UPDATED_LOCKED_TO_SUBSCRIPTION);
        assertThat(testAppDetails.getTotalScans()).isEqualTo(UPDATED_TOTAL_SCANS);
        assertThat(testAppDetails.getnScanExecutions()).isEqualTo(UPDATED_N_SCAN_EXECUTIONS);
        assertThat(testAppDetails.getHasExceedingIssuesNumber()).isEqualTo(UPDATED_HAS_EXCEEDING_ISSUES_NUMBER);
        assertThat(testAppDetails.getHasExceedingScansNumber()).isEqualTo(UPDATED_HAS_EXCEEDING_SCANS_NUMBER);
        assertThat(testAppDetails.getAutoDeleteExceededScans()).isEqualTo(UPDATED_AUTO_DELETE_EXCEEDED_SCANS);
    }

    @Test
    @Transactional
    void patchNonExistingAppDetails() throws Exception {
        int databaseSizeBeforeUpdate = appDetailsRepository.findAll().size();
        appDetails.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAppDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, appDetails.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(appDetails))
            )
            .andExpect(status().isBadRequest());

        // Validate the AppDetails in the database
        List<AppDetails> appDetailsList = appDetailsRepository.findAll();
        assertThat(appDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchAppDetails() throws Exception {
        int databaseSizeBeforeUpdate = appDetailsRepository.findAll().size();
        appDetails.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAppDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(appDetails))
            )
            .andExpect(status().isBadRequest());

        // Validate the AppDetails in the database
        List<AppDetails> appDetailsList = appDetailsRepository.findAll();
        assertThat(appDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamAppDetails() throws Exception {
        int databaseSizeBeforeUpdate = appDetailsRepository.findAll().size();
        appDetails.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAppDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(appDetails))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the AppDetails in the database
        List<AppDetails> appDetailsList = appDetailsRepository.findAll();
        assertThat(appDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteAppDetails() throws Exception {
        // Initialize the database
        appDetailsRepository.saveAndFlush(appDetails);

        int databaseSizeBeforeDelete = appDetailsRepository.findAll().size();

        // Delete the appDetails
        restAppDetailsMockMvc
            .perform(delete(ENTITY_API_URL_ID, appDetails.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AppDetails> appDetailsList = appDetailsRepository.findAll();
        assertThat(appDetailsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
