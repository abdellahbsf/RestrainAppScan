package com.ras.myapp.web.rest;

import static com.ras.myapp.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.ras.myapp.IntegrationTest;
import com.ras.myapp.domain.AppIssue;
import com.ras.myapp.repository.AppIssueRepository;
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
 * Integration tests for the {@link AppIssueResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AppIssueResourceIT {

    private static final String DEFAULT_APP_ID = "AAAAAAAAAA";
    private static final String UPDATED_APP_ID = "BBBBBBBBBB";

    private static final String DEFAULT_PROGRAMMING_LANGUAGE = "AAAAAAAAAA";
    private static final String UPDATED_PROGRAMMING_LANGUAGE = "BBBBBBBBBB";

    private static final String DEFAULT_SEVERITIES = "AAAAAAAAAA";
    private static final String UPDATED_SEVERITIES = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS_ISSUE = "AAAAAAAAAA";
    private static final String UPDATED_STATUS_ISSUE = "BBBBBBBBBB";

    private static final String DEFAULT_ISSUE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_ISSUE_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_LOCATION_ISSUE = "AAAAAAAAAA";
    private static final String UPDATED_LOCATION_ISSUE = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_DATE_CREATED = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE_CREATED = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_LAST_UPDATED = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_LAST_UPDATED = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_LAST_FOUND = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_LAST_FOUND = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_CALLING_METHOD = "AAAAAAAAAA";
    private static final String UPDATED_CALLING_METHOD = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_NEW_IN_SCOPE = false;
    private static final Boolean UPDATED_IS_NEW_IN_SCOPE = true;

    private static final String DEFAULT_LIBRARY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LIBRARY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LIBRARY_VERSION = "AAAAAAAAAA";
    private static final String UPDATED_LIBRARY_VERSION = "BBBBBBBBBB";

    private static final String DEFAULT_SCA_TECHNOLOGY = "AAAAAAAAAA";
    private static final String UPDATED_SCA_TECHNOLOGY = "BBBBBBBBBB";

    private static final String DEFAULT_F_G_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_F_G_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_APPLICATION_ID = "AAAAAAAAAA";
    private static final String UPDATED_APPLICATION_ID = "BBBBBBBBBB";

    private static final String DEFAULT_FIX_GROUP_ID = "AAAAAAAAAA";
    private static final String UPDATED_FIX_GROUP_ID = "BBBBBBBBBB";

    private static final String DEFAULT_API_ISSUE = "AAAAAAAAAA";
    private static final String UPDATED_API_ISSUE = "BBBBBBBBBB";

    private static final String DEFAULT_SOURCE_ISSUE = "AAAAAAAAAA";
    private static final String UPDATED_SOURCE_ISSUE = "BBBBBBBBBB";

    private static final String DEFAULT_CONTEXT_ISSUE = "AAAAAAAAAA";
    private static final String UPDATED_CONTEXT_ISSUE = "BBBBBBBBBB";

    private static final String DEFAULT_APPSCAN_VULN_ID = "AAAAAAAAAA";
    private static final String UPDATED_APPSCAN_VULN_ID = "BBBBBBBBBB";

    private static final String DEFAULT_CALLING_LINE = "AAAAAAAAAA";
    private static final String UPDATED_CALLING_LINE = "BBBBBBBBBB";

    private static final String DEFAULT_CLASS_ISSUE = "AAAAAAAAAA";
    private static final String UPDATED_CLASS_ISSUE = "BBBBBBBBBB";

    private static final String DEFAULT_CVE_ISSUE = "AAAAAAAAAA";
    private static final String UPDATED_CVE_ISSUE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/app-issues";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private AppIssueRepository appIssueRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAppIssueMockMvc;

    private AppIssue appIssue;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AppIssue createEntity(EntityManager em) {
        AppIssue appIssue = new AppIssue()
            .appId(DEFAULT_APP_ID)
            .programmingLanguage(DEFAULT_PROGRAMMING_LANGUAGE)
            .severities(DEFAULT_SEVERITIES)
            .statusIssue(DEFAULT_STATUS_ISSUE)
            .issueType(DEFAULT_ISSUE_TYPE)
            .locationIssue(DEFAULT_LOCATION_ISSUE)
            .dateCreated(DEFAULT_DATE_CREATED)
            .lastUpdated(DEFAULT_LAST_UPDATED)
            .lastFound(DEFAULT_LAST_FOUND)
            .callingMethod(DEFAULT_CALLING_METHOD)
            .isNewInScope(DEFAULT_IS_NEW_IN_SCOPE)
            .libraryName(DEFAULT_LIBRARY_NAME)
            .libraryVersion(DEFAULT_LIBRARY_VERSION)
            .scaTechnology(DEFAULT_SCA_TECHNOLOGY)
            .fGStatus(DEFAULT_F_G_STATUS)
            .applicationId(DEFAULT_APPLICATION_ID)
            .fixGroupId(DEFAULT_FIX_GROUP_ID)
            .apiIssue(DEFAULT_API_ISSUE)
            .sourceIssue(DEFAULT_SOURCE_ISSUE)
            .contextIssue(DEFAULT_CONTEXT_ISSUE)
            .appscanVulnId(DEFAULT_APPSCAN_VULN_ID)
            .callingLine(DEFAULT_CALLING_LINE)
            .classIssue(DEFAULT_CLASS_ISSUE)
            .cveIssue(DEFAULT_CVE_ISSUE);
        return appIssue;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AppIssue createUpdatedEntity(EntityManager em) {
        AppIssue appIssue = new AppIssue()
            .appId(UPDATED_APP_ID)
            .programmingLanguage(UPDATED_PROGRAMMING_LANGUAGE)
            .severities(UPDATED_SEVERITIES)
            .statusIssue(UPDATED_STATUS_ISSUE)
            .issueType(UPDATED_ISSUE_TYPE)
            .locationIssue(UPDATED_LOCATION_ISSUE)
            .dateCreated(UPDATED_DATE_CREATED)
            .lastUpdated(UPDATED_LAST_UPDATED)
            .lastFound(UPDATED_LAST_FOUND)
            .callingMethod(UPDATED_CALLING_METHOD)
            .isNewInScope(UPDATED_IS_NEW_IN_SCOPE)
            .libraryName(UPDATED_LIBRARY_NAME)
            .libraryVersion(UPDATED_LIBRARY_VERSION)
            .scaTechnology(UPDATED_SCA_TECHNOLOGY)
            .fGStatus(UPDATED_F_G_STATUS)
            .applicationId(UPDATED_APPLICATION_ID)
            .fixGroupId(UPDATED_FIX_GROUP_ID)
            .apiIssue(UPDATED_API_ISSUE)
            .sourceIssue(UPDATED_SOURCE_ISSUE)
            .contextIssue(UPDATED_CONTEXT_ISSUE)
            .appscanVulnId(UPDATED_APPSCAN_VULN_ID)
            .callingLine(UPDATED_CALLING_LINE)
            .classIssue(UPDATED_CLASS_ISSUE)
            .cveIssue(UPDATED_CVE_ISSUE);
        return appIssue;
    }

    @BeforeEach
    public void initTest() {
        appIssue = createEntity(em);
    }

    @Test
    @Transactional
    void createAppIssue() throws Exception {
        int databaseSizeBeforeCreate = appIssueRepository.findAll().size();
        // Create the AppIssue
        restAppIssueMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(appIssue)))
            .andExpect(status().isCreated());

        // Validate the AppIssue in the database
        List<AppIssue> appIssueList = appIssueRepository.findAll();
        assertThat(appIssueList).hasSize(databaseSizeBeforeCreate + 1);
        AppIssue testAppIssue = appIssueList.get(appIssueList.size() - 1);
        assertThat(testAppIssue.getAppId()).isEqualTo(DEFAULT_APP_ID);
        assertThat(testAppIssue.getProgrammingLanguage()).isEqualTo(DEFAULT_PROGRAMMING_LANGUAGE);
        assertThat(testAppIssue.getSeverities()).isEqualTo(DEFAULT_SEVERITIES);
        assertThat(testAppIssue.getStatusIssue()).isEqualTo(DEFAULT_STATUS_ISSUE);
        assertThat(testAppIssue.getIssueType()).isEqualTo(DEFAULT_ISSUE_TYPE);
        assertThat(testAppIssue.getLocationIssue()).isEqualTo(DEFAULT_LOCATION_ISSUE);
        assertThat(testAppIssue.getDateCreated()).isEqualTo(DEFAULT_DATE_CREATED);
        assertThat(testAppIssue.getLastUpdated()).isEqualTo(DEFAULT_LAST_UPDATED);
        assertThat(testAppIssue.getLastFound()).isEqualTo(DEFAULT_LAST_FOUND);
        assertThat(testAppIssue.getCallingMethod()).isEqualTo(DEFAULT_CALLING_METHOD);
        assertThat(testAppIssue.getIsNewInScope()).isEqualTo(DEFAULT_IS_NEW_IN_SCOPE);
        assertThat(testAppIssue.getLibraryName()).isEqualTo(DEFAULT_LIBRARY_NAME);
        assertThat(testAppIssue.getLibraryVersion()).isEqualTo(DEFAULT_LIBRARY_VERSION);
        assertThat(testAppIssue.getScaTechnology()).isEqualTo(DEFAULT_SCA_TECHNOLOGY);
        assertThat(testAppIssue.getfGStatus()).isEqualTo(DEFAULT_F_G_STATUS);
        assertThat(testAppIssue.getApplicationId()).isEqualTo(DEFAULT_APPLICATION_ID);
        assertThat(testAppIssue.getFixGroupId()).isEqualTo(DEFAULT_FIX_GROUP_ID);
        assertThat(testAppIssue.getApiIssue()).isEqualTo(DEFAULT_API_ISSUE);
        assertThat(testAppIssue.getSourceIssue()).isEqualTo(DEFAULT_SOURCE_ISSUE);
        assertThat(testAppIssue.getContextIssue()).isEqualTo(DEFAULT_CONTEXT_ISSUE);
        assertThat(testAppIssue.getAppscanVulnId()).isEqualTo(DEFAULT_APPSCAN_VULN_ID);
        assertThat(testAppIssue.getCallingLine()).isEqualTo(DEFAULT_CALLING_LINE);
        assertThat(testAppIssue.getClassIssue()).isEqualTo(DEFAULT_CLASS_ISSUE);
        assertThat(testAppIssue.getCveIssue()).isEqualTo(DEFAULT_CVE_ISSUE);
    }

    @Test
    @Transactional
    void createAppIssueWithExistingId() throws Exception {
        // Create the AppIssue with an existing ID
        appIssue.setId(1L);

        int databaseSizeBeforeCreate = appIssueRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restAppIssueMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(appIssue)))
            .andExpect(status().isBadRequest());

        // Validate the AppIssue in the database
        List<AppIssue> appIssueList = appIssueRepository.findAll();
        assertThat(appIssueList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllAppIssues() throws Exception {
        // Initialize the database
        appIssueRepository.saveAndFlush(appIssue);

        // Get all the appIssueList
        restAppIssueMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(appIssue.getId().intValue())))
            .andExpect(jsonPath("$.[*].appId").value(hasItem(DEFAULT_APP_ID)))
            .andExpect(jsonPath("$.[*].programmingLanguage").value(hasItem(DEFAULT_PROGRAMMING_LANGUAGE)))
            .andExpect(jsonPath("$.[*].severities").value(hasItem(DEFAULT_SEVERITIES)))
            .andExpect(jsonPath("$.[*].statusIssue").value(hasItem(DEFAULT_STATUS_ISSUE)))
            .andExpect(jsonPath("$.[*].issueType").value(hasItem(DEFAULT_ISSUE_TYPE)))
            .andExpect(jsonPath("$.[*].locationIssue").value(hasItem(DEFAULT_LOCATION_ISSUE)))
            .andExpect(jsonPath("$.[*].dateCreated").value(hasItem(sameInstant(DEFAULT_DATE_CREATED))))
            .andExpect(jsonPath("$.[*].lastUpdated").value(hasItem(sameInstant(DEFAULT_LAST_UPDATED))))
            .andExpect(jsonPath("$.[*].lastFound").value(hasItem(sameInstant(DEFAULT_LAST_FOUND))))
            .andExpect(jsonPath("$.[*].callingMethod").value(hasItem(DEFAULT_CALLING_METHOD)))
            .andExpect(jsonPath("$.[*].isNewInScope").value(hasItem(DEFAULT_IS_NEW_IN_SCOPE.booleanValue())))
            .andExpect(jsonPath("$.[*].libraryName").value(hasItem(DEFAULT_LIBRARY_NAME)))
            .andExpect(jsonPath("$.[*].libraryVersion").value(hasItem(DEFAULT_LIBRARY_VERSION)))
            .andExpect(jsonPath("$.[*].scaTechnology").value(hasItem(DEFAULT_SCA_TECHNOLOGY)))
            .andExpect(jsonPath("$.[*].fGStatus").value(hasItem(DEFAULT_F_G_STATUS)))
            .andExpect(jsonPath("$.[*].applicationId").value(hasItem(DEFAULT_APPLICATION_ID)))
            .andExpect(jsonPath("$.[*].fixGroupId").value(hasItem(DEFAULT_FIX_GROUP_ID)))
            .andExpect(jsonPath("$.[*].apiIssue").value(hasItem(DEFAULT_API_ISSUE)))
            .andExpect(jsonPath("$.[*].sourceIssue").value(hasItem(DEFAULT_SOURCE_ISSUE)))
            .andExpect(jsonPath("$.[*].contextIssue").value(hasItem(DEFAULT_CONTEXT_ISSUE)))
            .andExpect(jsonPath("$.[*].appscanVulnId").value(hasItem(DEFAULT_APPSCAN_VULN_ID)))
            .andExpect(jsonPath("$.[*].callingLine").value(hasItem(DEFAULT_CALLING_LINE)))
            .andExpect(jsonPath("$.[*].classIssue").value(hasItem(DEFAULT_CLASS_ISSUE)))
            .andExpect(jsonPath("$.[*].cveIssue").value(hasItem(DEFAULT_CVE_ISSUE)));
    }

    @Test
    @Transactional
    void getAppIssue() throws Exception {
        // Initialize the database
        appIssueRepository.saveAndFlush(appIssue);

        // Get the appIssue
        restAppIssueMockMvc
            .perform(get(ENTITY_API_URL_ID, appIssue.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(appIssue.getId().intValue()))
            .andExpect(jsonPath("$.appId").value(DEFAULT_APP_ID))
            .andExpect(jsonPath("$.programmingLanguage").value(DEFAULT_PROGRAMMING_LANGUAGE))
            .andExpect(jsonPath("$.severities").value(DEFAULT_SEVERITIES))
            .andExpect(jsonPath("$.statusIssue").value(DEFAULT_STATUS_ISSUE))
            .andExpect(jsonPath("$.issueType").value(DEFAULT_ISSUE_TYPE))
            .andExpect(jsonPath("$.locationIssue").value(DEFAULT_LOCATION_ISSUE))
            .andExpect(jsonPath("$.dateCreated").value(sameInstant(DEFAULT_DATE_CREATED)))
            .andExpect(jsonPath("$.lastUpdated").value(sameInstant(DEFAULT_LAST_UPDATED)))
            .andExpect(jsonPath("$.lastFound").value(sameInstant(DEFAULT_LAST_FOUND)))
            .andExpect(jsonPath("$.callingMethod").value(DEFAULT_CALLING_METHOD))
            .andExpect(jsonPath("$.isNewInScope").value(DEFAULT_IS_NEW_IN_SCOPE.booleanValue()))
            .andExpect(jsonPath("$.libraryName").value(DEFAULT_LIBRARY_NAME))
            .andExpect(jsonPath("$.libraryVersion").value(DEFAULT_LIBRARY_VERSION))
            .andExpect(jsonPath("$.scaTechnology").value(DEFAULT_SCA_TECHNOLOGY))
            .andExpect(jsonPath("$.fGStatus").value(DEFAULT_F_G_STATUS))
            .andExpect(jsonPath("$.applicationId").value(DEFAULT_APPLICATION_ID))
            .andExpect(jsonPath("$.fixGroupId").value(DEFAULT_FIX_GROUP_ID))
            .andExpect(jsonPath("$.apiIssue").value(DEFAULT_API_ISSUE))
            .andExpect(jsonPath("$.sourceIssue").value(DEFAULT_SOURCE_ISSUE))
            .andExpect(jsonPath("$.contextIssue").value(DEFAULT_CONTEXT_ISSUE))
            .andExpect(jsonPath("$.appscanVulnId").value(DEFAULT_APPSCAN_VULN_ID))
            .andExpect(jsonPath("$.callingLine").value(DEFAULT_CALLING_LINE))
            .andExpect(jsonPath("$.classIssue").value(DEFAULT_CLASS_ISSUE))
            .andExpect(jsonPath("$.cveIssue").value(DEFAULT_CVE_ISSUE));
    }

    @Test
    @Transactional
    void getNonExistingAppIssue() throws Exception {
        // Get the appIssue
        restAppIssueMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingAppIssue() throws Exception {
        // Initialize the database
        appIssueRepository.saveAndFlush(appIssue);

        int databaseSizeBeforeUpdate = appIssueRepository.findAll().size();

        // Update the appIssue
        AppIssue updatedAppIssue = appIssueRepository.findById(appIssue.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedAppIssue are not directly saved in db
        em.detach(updatedAppIssue);
        updatedAppIssue
            .appId(UPDATED_APP_ID)
            .programmingLanguage(UPDATED_PROGRAMMING_LANGUAGE)
            .severities(UPDATED_SEVERITIES)
            .statusIssue(UPDATED_STATUS_ISSUE)
            .issueType(UPDATED_ISSUE_TYPE)
            .locationIssue(UPDATED_LOCATION_ISSUE)
            .dateCreated(UPDATED_DATE_CREATED)
            .lastUpdated(UPDATED_LAST_UPDATED)
            .lastFound(UPDATED_LAST_FOUND)
            .callingMethod(UPDATED_CALLING_METHOD)
            .isNewInScope(UPDATED_IS_NEW_IN_SCOPE)
            .libraryName(UPDATED_LIBRARY_NAME)
            .libraryVersion(UPDATED_LIBRARY_VERSION)
            .scaTechnology(UPDATED_SCA_TECHNOLOGY)
            .fGStatus(UPDATED_F_G_STATUS)
            .applicationId(UPDATED_APPLICATION_ID)
            .fixGroupId(UPDATED_FIX_GROUP_ID)
            .apiIssue(UPDATED_API_ISSUE)
            .sourceIssue(UPDATED_SOURCE_ISSUE)
            .contextIssue(UPDATED_CONTEXT_ISSUE)
            .appscanVulnId(UPDATED_APPSCAN_VULN_ID)
            .callingLine(UPDATED_CALLING_LINE)
            .classIssue(UPDATED_CLASS_ISSUE)
            .cveIssue(UPDATED_CVE_ISSUE);

        restAppIssueMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedAppIssue.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedAppIssue))
            )
            .andExpect(status().isOk());

        // Validate the AppIssue in the database
        List<AppIssue> appIssueList = appIssueRepository.findAll();
        assertThat(appIssueList).hasSize(databaseSizeBeforeUpdate);
        AppIssue testAppIssue = appIssueList.get(appIssueList.size() - 1);
        assertThat(testAppIssue.getAppId()).isEqualTo(UPDATED_APP_ID);
        assertThat(testAppIssue.getProgrammingLanguage()).isEqualTo(UPDATED_PROGRAMMING_LANGUAGE);
        assertThat(testAppIssue.getSeverities()).isEqualTo(UPDATED_SEVERITIES);
        assertThat(testAppIssue.getStatusIssue()).isEqualTo(UPDATED_STATUS_ISSUE);
        assertThat(testAppIssue.getIssueType()).isEqualTo(UPDATED_ISSUE_TYPE);
        assertThat(testAppIssue.getLocationIssue()).isEqualTo(UPDATED_LOCATION_ISSUE);
        assertThat(testAppIssue.getDateCreated()).isEqualTo(UPDATED_DATE_CREATED);
        assertThat(testAppIssue.getLastUpdated()).isEqualTo(UPDATED_LAST_UPDATED);
        assertThat(testAppIssue.getLastFound()).isEqualTo(UPDATED_LAST_FOUND);
        assertThat(testAppIssue.getCallingMethod()).isEqualTo(UPDATED_CALLING_METHOD);
        assertThat(testAppIssue.getIsNewInScope()).isEqualTo(UPDATED_IS_NEW_IN_SCOPE);
        assertThat(testAppIssue.getLibraryName()).isEqualTo(UPDATED_LIBRARY_NAME);
        assertThat(testAppIssue.getLibraryVersion()).isEqualTo(UPDATED_LIBRARY_VERSION);
        assertThat(testAppIssue.getScaTechnology()).isEqualTo(UPDATED_SCA_TECHNOLOGY);
        assertThat(testAppIssue.getfGStatus()).isEqualTo(UPDATED_F_G_STATUS);
        assertThat(testAppIssue.getApplicationId()).isEqualTo(UPDATED_APPLICATION_ID);
        assertThat(testAppIssue.getFixGroupId()).isEqualTo(UPDATED_FIX_GROUP_ID);
        assertThat(testAppIssue.getApiIssue()).isEqualTo(UPDATED_API_ISSUE);
        assertThat(testAppIssue.getSourceIssue()).isEqualTo(UPDATED_SOURCE_ISSUE);
        assertThat(testAppIssue.getContextIssue()).isEqualTo(UPDATED_CONTEXT_ISSUE);
        assertThat(testAppIssue.getAppscanVulnId()).isEqualTo(UPDATED_APPSCAN_VULN_ID);
        assertThat(testAppIssue.getCallingLine()).isEqualTo(UPDATED_CALLING_LINE);
        assertThat(testAppIssue.getClassIssue()).isEqualTo(UPDATED_CLASS_ISSUE);
        assertThat(testAppIssue.getCveIssue()).isEqualTo(UPDATED_CVE_ISSUE);
    }

    @Test
    @Transactional
    void putNonExistingAppIssue() throws Exception {
        int databaseSizeBeforeUpdate = appIssueRepository.findAll().size();
        appIssue.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAppIssueMockMvc
            .perform(
                put(ENTITY_API_URL_ID, appIssue.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(appIssue))
            )
            .andExpect(status().isBadRequest());

        // Validate the AppIssue in the database
        List<AppIssue> appIssueList = appIssueRepository.findAll();
        assertThat(appIssueList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchAppIssue() throws Exception {
        int databaseSizeBeforeUpdate = appIssueRepository.findAll().size();
        appIssue.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAppIssueMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(appIssue))
            )
            .andExpect(status().isBadRequest());

        // Validate the AppIssue in the database
        List<AppIssue> appIssueList = appIssueRepository.findAll();
        assertThat(appIssueList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamAppIssue() throws Exception {
        int databaseSizeBeforeUpdate = appIssueRepository.findAll().size();
        appIssue.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAppIssueMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(appIssue)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the AppIssue in the database
        List<AppIssue> appIssueList = appIssueRepository.findAll();
        assertThat(appIssueList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateAppIssueWithPatch() throws Exception {
        // Initialize the database
        appIssueRepository.saveAndFlush(appIssue);

        int databaseSizeBeforeUpdate = appIssueRepository.findAll().size();

        // Update the appIssue using partial update
        AppIssue partialUpdatedAppIssue = new AppIssue();
        partialUpdatedAppIssue.setId(appIssue.getId());

        partialUpdatedAppIssue
            .appId(UPDATED_APP_ID)
            .locationIssue(UPDATED_LOCATION_ISSUE)
            .dateCreated(UPDATED_DATE_CREATED)
            .lastUpdated(UPDATED_LAST_UPDATED)
            .lastFound(UPDATED_LAST_FOUND)
            .isNewInScope(UPDATED_IS_NEW_IN_SCOPE)
            .scaTechnology(UPDATED_SCA_TECHNOLOGY)
            .fGStatus(UPDATED_F_G_STATUS)
            .applicationId(UPDATED_APPLICATION_ID)
            .fixGroupId(UPDATED_FIX_GROUP_ID)
            .apiIssue(UPDATED_API_ISSUE)
            .cveIssue(UPDATED_CVE_ISSUE);

        restAppIssueMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAppIssue.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAppIssue))
            )
            .andExpect(status().isOk());

        // Validate the AppIssue in the database
        List<AppIssue> appIssueList = appIssueRepository.findAll();
        assertThat(appIssueList).hasSize(databaseSizeBeforeUpdate);
        AppIssue testAppIssue = appIssueList.get(appIssueList.size() - 1);
        assertThat(testAppIssue.getAppId()).isEqualTo(UPDATED_APP_ID);
        assertThat(testAppIssue.getProgrammingLanguage()).isEqualTo(DEFAULT_PROGRAMMING_LANGUAGE);
        assertThat(testAppIssue.getSeverities()).isEqualTo(DEFAULT_SEVERITIES);
        assertThat(testAppIssue.getStatusIssue()).isEqualTo(DEFAULT_STATUS_ISSUE);
        assertThat(testAppIssue.getIssueType()).isEqualTo(DEFAULT_ISSUE_TYPE);
        assertThat(testAppIssue.getLocationIssue()).isEqualTo(UPDATED_LOCATION_ISSUE);
        assertThat(testAppIssue.getDateCreated()).isEqualTo(UPDATED_DATE_CREATED);
        assertThat(testAppIssue.getLastUpdated()).isEqualTo(UPDATED_LAST_UPDATED);
        assertThat(testAppIssue.getLastFound()).isEqualTo(UPDATED_LAST_FOUND);
        assertThat(testAppIssue.getCallingMethod()).isEqualTo(DEFAULT_CALLING_METHOD);
        assertThat(testAppIssue.getIsNewInScope()).isEqualTo(UPDATED_IS_NEW_IN_SCOPE);
        assertThat(testAppIssue.getLibraryName()).isEqualTo(DEFAULT_LIBRARY_NAME);
        assertThat(testAppIssue.getLibraryVersion()).isEqualTo(DEFAULT_LIBRARY_VERSION);
        assertThat(testAppIssue.getScaTechnology()).isEqualTo(UPDATED_SCA_TECHNOLOGY);
        assertThat(testAppIssue.getfGStatus()).isEqualTo(UPDATED_F_G_STATUS);
        assertThat(testAppIssue.getApplicationId()).isEqualTo(UPDATED_APPLICATION_ID);
        assertThat(testAppIssue.getFixGroupId()).isEqualTo(UPDATED_FIX_GROUP_ID);
        assertThat(testAppIssue.getApiIssue()).isEqualTo(UPDATED_API_ISSUE);
        assertThat(testAppIssue.getSourceIssue()).isEqualTo(DEFAULT_SOURCE_ISSUE);
        assertThat(testAppIssue.getContextIssue()).isEqualTo(DEFAULT_CONTEXT_ISSUE);
        assertThat(testAppIssue.getAppscanVulnId()).isEqualTo(DEFAULT_APPSCAN_VULN_ID);
        assertThat(testAppIssue.getCallingLine()).isEqualTo(DEFAULT_CALLING_LINE);
        assertThat(testAppIssue.getClassIssue()).isEqualTo(DEFAULT_CLASS_ISSUE);
        assertThat(testAppIssue.getCveIssue()).isEqualTo(UPDATED_CVE_ISSUE);
    }

    @Test
    @Transactional
    void fullUpdateAppIssueWithPatch() throws Exception {
        // Initialize the database
        appIssueRepository.saveAndFlush(appIssue);

        int databaseSizeBeforeUpdate = appIssueRepository.findAll().size();

        // Update the appIssue using partial update
        AppIssue partialUpdatedAppIssue = new AppIssue();
        partialUpdatedAppIssue.setId(appIssue.getId());

        partialUpdatedAppIssue
            .appId(UPDATED_APP_ID)
            .programmingLanguage(UPDATED_PROGRAMMING_LANGUAGE)
            .severities(UPDATED_SEVERITIES)
            .statusIssue(UPDATED_STATUS_ISSUE)
            .issueType(UPDATED_ISSUE_TYPE)
            .locationIssue(UPDATED_LOCATION_ISSUE)
            .dateCreated(UPDATED_DATE_CREATED)
            .lastUpdated(UPDATED_LAST_UPDATED)
            .lastFound(UPDATED_LAST_FOUND)
            .callingMethod(UPDATED_CALLING_METHOD)
            .isNewInScope(UPDATED_IS_NEW_IN_SCOPE)
            .libraryName(UPDATED_LIBRARY_NAME)
            .libraryVersion(UPDATED_LIBRARY_VERSION)
            .scaTechnology(UPDATED_SCA_TECHNOLOGY)
            .fGStatus(UPDATED_F_G_STATUS)
            .applicationId(UPDATED_APPLICATION_ID)
            .fixGroupId(UPDATED_FIX_GROUP_ID)
            .apiIssue(UPDATED_API_ISSUE)
            .sourceIssue(UPDATED_SOURCE_ISSUE)
            .contextIssue(UPDATED_CONTEXT_ISSUE)
            .appscanVulnId(UPDATED_APPSCAN_VULN_ID)
            .callingLine(UPDATED_CALLING_LINE)
            .classIssue(UPDATED_CLASS_ISSUE)
            .cveIssue(UPDATED_CVE_ISSUE);

        restAppIssueMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAppIssue.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAppIssue))
            )
            .andExpect(status().isOk());

        // Validate the AppIssue in the database
        List<AppIssue> appIssueList = appIssueRepository.findAll();
        assertThat(appIssueList).hasSize(databaseSizeBeforeUpdate);
        AppIssue testAppIssue = appIssueList.get(appIssueList.size() - 1);
        assertThat(testAppIssue.getAppId()).isEqualTo(UPDATED_APP_ID);
        assertThat(testAppIssue.getProgrammingLanguage()).isEqualTo(UPDATED_PROGRAMMING_LANGUAGE);
        assertThat(testAppIssue.getSeverities()).isEqualTo(UPDATED_SEVERITIES);
        assertThat(testAppIssue.getStatusIssue()).isEqualTo(UPDATED_STATUS_ISSUE);
        assertThat(testAppIssue.getIssueType()).isEqualTo(UPDATED_ISSUE_TYPE);
        assertThat(testAppIssue.getLocationIssue()).isEqualTo(UPDATED_LOCATION_ISSUE);
        assertThat(testAppIssue.getDateCreated()).isEqualTo(UPDATED_DATE_CREATED);
        assertThat(testAppIssue.getLastUpdated()).isEqualTo(UPDATED_LAST_UPDATED);
        assertThat(testAppIssue.getLastFound()).isEqualTo(UPDATED_LAST_FOUND);
        assertThat(testAppIssue.getCallingMethod()).isEqualTo(UPDATED_CALLING_METHOD);
        assertThat(testAppIssue.getIsNewInScope()).isEqualTo(UPDATED_IS_NEW_IN_SCOPE);
        assertThat(testAppIssue.getLibraryName()).isEqualTo(UPDATED_LIBRARY_NAME);
        assertThat(testAppIssue.getLibraryVersion()).isEqualTo(UPDATED_LIBRARY_VERSION);
        assertThat(testAppIssue.getScaTechnology()).isEqualTo(UPDATED_SCA_TECHNOLOGY);
        assertThat(testAppIssue.getfGStatus()).isEqualTo(UPDATED_F_G_STATUS);
        assertThat(testAppIssue.getApplicationId()).isEqualTo(UPDATED_APPLICATION_ID);
        assertThat(testAppIssue.getFixGroupId()).isEqualTo(UPDATED_FIX_GROUP_ID);
        assertThat(testAppIssue.getApiIssue()).isEqualTo(UPDATED_API_ISSUE);
        assertThat(testAppIssue.getSourceIssue()).isEqualTo(UPDATED_SOURCE_ISSUE);
        assertThat(testAppIssue.getContextIssue()).isEqualTo(UPDATED_CONTEXT_ISSUE);
        assertThat(testAppIssue.getAppscanVulnId()).isEqualTo(UPDATED_APPSCAN_VULN_ID);
        assertThat(testAppIssue.getCallingLine()).isEqualTo(UPDATED_CALLING_LINE);
        assertThat(testAppIssue.getClassIssue()).isEqualTo(UPDATED_CLASS_ISSUE);
        assertThat(testAppIssue.getCveIssue()).isEqualTo(UPDATED_CVE_ISSUE);
    }

    @Test
    @Transactional
    void patchNonExistingAppIssue() throws Exception {
        int databaseSizeBeforeUpdate = appIssueRepository.findAll().size();
        appIssue.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAppIssueMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, appIssue.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(appIssue))
            )
            .andExpect(status().isBadRequest());

        // Validate the AppIssue in the database
        List<AppIssue> appIssueList = appIssueRepository.findAll();
        assertThat(appIssueList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchAppIssue() throws Exception {
        int databaseSizeBeforeUpdate = appIssueRepository.findAll().size();
        appIssue.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAppIssueMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(appIssue))
            )
            .andExpect(status().isBadRequest());

        // Validate the AppIssue in the database
        List<AppIssue> appIssueList = appIssueRepository.findAll();
        assertThat(appIssueList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamAppIssue() throws Exception {
        int databaseSizeBeforeUpdate = appIssueRepository.findAll().size();
        appIssue.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAppIssueMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(appIssue)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the AppIssue in the database
        List<AppIssue> appIssueList = appIssueRepository.findAll();
        assertThat(appIssueList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteAppIssue() throws Exception {
        // Initialize the database
        appIssueRepository.saveAndFlush(appIssue);

        int databaseSizeBeforeDelete = appIssueRepository.findAll().size();

        // Delete the appIssue
        restAppIssueMockMvc
            .perform(delete(ENTITY_API_URL_ID, appIssue.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AppIssue> appIssueList = appIssueRepository.findAll();
        assertThat(appIssueList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
