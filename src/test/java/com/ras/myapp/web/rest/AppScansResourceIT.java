package com.ras.myapp.web.rest;

import static com.ras.myapp.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.ras.myapp.IntegrationTest;
import com.ras.myapp.domain.AppScans;
import com.ras.myapp.repository.AppScansRepository;
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
 * Integration tests for the {@link AppScansResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AppScansResourceIT {

    private static final String DEFAULT_APP_ID = "AAAAAAAAAA";
    private static final String UPDATED_APP_ID = "BBBBBBBBBB";

    private static final String DEFAULT_NAME_SCAN = "AAAAAAAAAA";
    private static final String UPDATED_NAME_SCAN = "BBBBBBBBBB";

    private static final String DEFAULT_TECHNOLOGY = "AAAAAAAAAA";
    private static final String UPDATED_TECHNOLOGY = "BBBBBBBBBB";

    private static final String DEFAULT_IAST_AGENT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_IAST_AGENT_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_IAST_AGENT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_IAST_AGENT_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_URL_SCAN = "AAAAAAAAAA";
    private static final String UPDATED_URL_SCAN = "BBBBBBBBBB";

    private static final String DEFAULT_APP_NAME = "AAAAAAAAAA";
    private static final String UPDATED_APP_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_TEST_OPTIMIZATION_LEVEL = "AAAAAAAAAA";
    private static final String UPDATED_TEST_OPTIMIZATION_LEVEL = "BBBBBBBBBB";

    private static final Integer DEFAULT_NUMBER_OF_EXECUTIONS = 1;
    private static final Integer UPDATED_NUMBER_OF_EXECUTIONS = 2;

    private static final ZonedDateTime DEFAULT_CREATED_AT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATED_AT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_LAST_MODIFIED = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_LAST_MODIFIED = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String ENTITY_API_URL = "/api/app-scans";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private AppScansRepository appScansRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAppScansMockMvc;

    private AppScans appScans;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AppScans createEntity(EntityManager em) {
        AppScans appScans = new AppScans()
            .appId(DEFAULT_APP_ID)
            .nameScan(DEFAULT_NAME_SCAN)
            .technology(DEFAULT_TECHNOLOGY)
            .iastAgentType(DEFAULT_IAST_AGENT_TYPE)
            .iastAgentStatus(DEFAULT_IAST_AGENT_STATUS)
            .urlScan(DEFAULT_URL_SCAN)
            .appName(DEFAULT_APP_NAME)
            .testOptimizationLevel(DEFAULT_TEST_OPTIMIZATION_LEVEL)
            .numberOfExecutions(DEFAULT_NUMBER_OF_EXECUTIONS)
            .createdAt(DEFAULT_CREATED_AT)
            .lastModified(DEFAULT_LAST_MODIFIED);
        return appScans;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AppScans createUpdatedEntity(EntityManager em) {
        AppScans appScans = new AppScans()
            .appId(UPDATED_APP_ID)
            .nameScan(UPDATED_NAME_SCAN)
            .technology(UPDATED_TECHNOLOGY)
            .iastAgentType(UPDATED_IAST_AGENT_TYPE)
            .iastAgentStatus(UPDATED_IAST_AGENT_STATUS)
            .urlScan(UPDATED_URL_SCAN)
            .appName(UPDATED_APP_NAME)
            .testOptimizationLevel(UPDATED_TEST_OPTIMIZATION_LEVEL)
            .numberOfExecutions(UPDATED_NUMBER_OF_EXECUTIONS)
            .createdAt(UPDATED_CREATED_AT)
            .lastModified(UPDATED_LAST_MODIFIED);
        return appScans;
    }

    @BeforeEach
    public void initTest() {
        appScans = createEntity(em);
    }

    @Test
    @Transactional
    void createAppScans() throws Exception {
        int databaseSizeBeforeCreate = appScansRepository.findAll().size();
        // Create the AppScans
        restAppScansMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(appScans)))
            .andExpect(status().isCreated());

        // Validate the AppScans in the database
        List<AppScans> appScansList = appScansRepository.findAll();
        assertThat(appScansList).hasSize(databaseSizeBeforeCreate + 1);
        AppScans testAppScans = appScansList.get(appScansList.size() - 1);
        assertThat(testAppScans.getAppId()).isEqualTo(DEFAULT_APP_ID);
        assertThat(testAppScans.getNameScan()).isEqualTo(DEFAULT_NAME_SCAN);
        assertThat(testAppScans.getTechnology()).isEqualTo(DEFAULT_TECHNOLOGY);
        assertThat(testAppScans.getIastAgentType()).isEqualTo(DEFAULT_IAST_AGENT_TYPE);
        assertThat(testAppScans.getIastAgentStatus()).isEqualTo(DEFAULT_IAST_AGENT_STATUS);
        assertThat(testAppScans.getUrlScan()).isEqualTo(DEFAULT_URL_SCAN);
        assertThat(testAppScans.getAppName()).isEqualTo(DEFAULT_APP_NAME);
        assertThat(testAppScans.getTestOptimizationLevel()).isEqualTo(DEFAULT_TEST_OPTIMIZATION_LEVEL);
        assertThat(testAppScans.getNumberOfExecutions()).isEqualTo(DEFAULT_NUMBER_OF_EXECUTIONS);
        assertThat(testAppScans.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testAppScans.getLastModified()).isEqualTo(DEFAULT_LAST_MODIFIED);
    }

    @Test
    @Transactional
    void createAppScansWithExistingId() throws Exception {
        // Create the AppScans with an existing ID
        appScans.setId(1L);

        int databaseSizeBeforeCreate = appScansRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restAppScansMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(appScans)))
            .andExpect(status().isBadRequest());

        // Validate the AppScans in the database
        List<AppScans> appScansList = appScansRepository.findAll();
        assertThat(appScansList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllAppScans() throws Exception {
        // Initialize the database
        appScansRepository.saveAndFlush(appScans);

        // Get all the appScansList
        restAppScansMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(appScans.getId().intValue())))
            .andExpect(jsonPath("$.[*].appId").value(hasItem(DEFAULT_APP_ID)))
            .andExpect(jsonPath("$.[*].nameScan").value(hasItem(DEFAULT_NAME_SCAN)))
            .andExpect(jsonPath("$.[*].technology").value(hasItem(DEFAULT_TECHNOLOGY)))
            .andExpect(jsonPath("$.[*].iastAgentType").value(hasItem(DEFAULT_IAST_AGENT_TYPE)))
            .andExpect(jsonPath("$.[*].iastAgentStatus").value(hasItem(DEFAULT_IAST_AGENT_STATUS)))
            .andExpect(jsonPath("$.[*].urlScan").value(hasItem(DEFAULT_URL_SCAN)))
            .andExpect(jsonPath("$.[*].appName").value(hasItem(DEFAULT_APP_NAME)))
            .andExpect(jsonPath("$.[*].testOptimizationLevel").value(hasItem(DEFAULT_TEST_OPTIMIZATION_LEVEL)))
            .andExpect(jsonPath("$.[*].numberOfExecutions").value(hasItem(DEFAULT_NUMBER_OF_EXECUTIONS)))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(sameInstant(DEFAULT_CREATED_AT))))
            .andExpect(jsonPath("$.[*].lastModified").value(hasItem(sameInstant(DEFAULT_LAST_MODIFIED))));
    }

    @Test
    @Transactional
    void getAppScans() throws Exception {
        // Initialize the database
        appScansRepository.saveAndFlush(appScans);

        // Get the appScans
        restAppScansMockMvc
            .perform(get(ENTITY_API_URL_ID, appScans.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(appScans.getId().intValue()))
            .andExpect(jsonPath("$.appId").value(DEFAULT_APP_ID))
            .andExpect(jsonPath("$.nameScan").value(DEFAULT_NAME_SCAN))
            .andExpect(jsonPath("$.technology").value(DEFAULT_TECHNOLOGY))
            .andExpect(jsonPath("$.iastAgentType").value(DEFAULT_IAST_AGENT_TYPE))
            .andExpect(jsonPath("$.iastAgentStatus").value(DEFAULT_IAST_AGENT_STATUS))
            .andExpect(jsonPath("$.urlScan").value(DEFAULT_URL_SCAN))
            .andExpect(jsonPath("$.appName").value(DEFAULT_APP_NAME))
            .andExpect(jsonPath("$.testOptimizationLevel").value(DEFAULT_TEST_OPTIMIZATION_LEVEL))
            .andExpect(jsonPath("$.numberOfExecutions").value(DEFAULT_NUMBER_OF_EXECUTIONS))
            .andExpect(jsonPath("$.createdAt").value(sameInstant(DEFAULT_CREATED_AT)))
            .andExpect(jsonPath("$.lastModified").value(sameInstant(DEFAULT_LAST_MODIFIED)));
    }

    @Test
    @Transactional
    void getNonExistingAppScans() throws Exception {
        // Get the appScans
        restAppScansMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingAppScans() throws Exception {
        // Initialize the database
        appScansRepository.saveAndFlush(appScans);

        int databaseSizeBeforeUpdate = appScansRepository.findAll().size();

        // Update the appScans
        AppScans updatedAppScans = appScansRepository.findById(appScans.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedAppScans are not directly saved in db
        em.detach(updatedAppScans);
        updatedAppScans
            .appId(UPDATED_APP_ID)
            .nameScan(UPDATED_NAME_SCAN)
            .technology(UPDATED_TECHNOLOGY)
            .iastAgentType(UPDATED_IAST_AGENT_TYPE)
            .iastAgentStatus(UPDATED_IAST_AGENT_STATUS)
            .urlScan(UPDATED_URL_SCAN)
            .appName(UPDATED_APP_NAME)
            .testOptimizationLevel(UPDATED_TEST_OPTIMIZATION_LEVEL)
            .numberOfExecutions(UPDATED_NUMBER_OF_EXECUTIONS)
            .createdAt(UPDATED_CREATED_AT)
            .lastModified(UPDATED_LAST_MODIFIED);

        restAppScansMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedAppScans.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedAppScans))
            )
            .andExpect(status().isOk());

        // Validate the AppScans in the database
        List<AppScans> appScansList = appScansRepository.findAll();
        assertThat(appScansList).hasSize(databaseSizeBeforeUpdate);
        AppScans testAppScans = appScansList.get(appScansList.size() - 1);
        assertThat(testAppScans.getAppId()).isEqualTo(UPDATED_APP_ID);
        assertThat(testAppScans.getNameScan()).isEqualTo(UPDATED_NAME_SCAN);
        assertThat(testAppScans.getTechnology()).isEqualTo(UPDATED_TECHNOLOGY);
        assertThat(testAppScans.getIastAgentType()).isEqualTo(UPDATED_IAST_AGENT_TYPE);
        assertThat(testAppScans.getIastAgentStatus()).isEqualTo(UPDATED_IAST_AGENT_STATUS);
        assertThat(testAppScans.getUrlScan()).isEqualTo(UPDATED_URL_SCAN);
        assertThat(testAppScans.getAppName()).isEqualTo(UPDATED_APP_NAME);
        assertThat(testAppScans.getTestOptimizationLevel()).isEqualTo(UPDATED_TEST_OPTIMIZATION_LEVEL);
        assertThat(testAppScans.getNumberOfExecutions()).isEqualTo(UPDATED_NUMBER_OF_EXECUTIONS);
        assertThat(testAppScans.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testAppScans.getLastModified()).isEqualTo(UPDATED_LAST_MODIFIED);
    }

    @Test
    @Transactional
    void putNonExistingAppScans() throws Exception {
        int databaseSizeBeforeUpdate = appScansRepository.findAll().size();
        appScans.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAppScansMockMvc
            .perform(
                put(ENTITY_API_URL_ID, appScans.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(appScans))
            )
            .andExpect(status().isBadRequest());

        // Validate the AppScans in the database
        List<AppScans> appScansList = appScansRepository.findAll();
        assertThat(appScansList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchAppScans() throws Exception {
        int databaseSizeBeforeUpdate = appScansRepository.findAll().size();
        appScans.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAppScansMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(appScans))
            )
            .andExpect(status().isBadRequest());

        // Validate the AppScans in the database
        List<AppScans> appScansList = appScansRepository.findAll();
        assertThat(appScansList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamAppScans() throws Exception {
        int databaseSizeBeforeUpdate = appScansRepository.findAll().size();
        appScans.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAppScansMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(appScans)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the AppScans in the database
        List<AppScans> appScansList = appScansRepository.findAll();
        assertThat(appScansList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateAppScansWithPatch() throws Exception {
        // Initialize the database
        appScansRepository.saveAndFlush(appScans);

        int databaseSizeBeforeUpdate = appScansRepository.findAll().size();

        // Update the appScans using partial update
        AppScans partialUpdatedAppScans = new AppScans();
        partialUpdatedAppScans.setId(appScans.getId());

        partialUpdatedAppScans
            .technology(UPDATED_TECHNOLOGY)
            .iastAgentStatus(UPDATED_IAST_AGENT_STATUS)
            .urlScan(UPDATED_URL_SCAN)
            .testOptimizationLevel(UPDATED_TEST_OPTIMIZATION_LEVEL)
            .createdAt(UPDATED_CREATED_AT)
            .lastModified(UPDATED_LAST_MODIFIED);

        restAppScansMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAppScans.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAppScans))
            )
            .andExpect(status().isOk());

        // Validate the AppScans in the database
        List<AppScans> appScansList = appScansRepository.findAll();
        assertThat(appScansList).hasSize(databaseSizeBeforeUpdate);
        AppScans testAppScans = appScansList.get(appScansList.size() - 1);
        assertThat(testAppScans.getAppId()).isEqualTo(DEFAULT_APP_ID);
        assertThat(testAppScans.getNameScan()).isEqualTo(DEFAULT_NAME_SCAN);
        assertThat(testAppScans.getTechnology()).isEqualTo(UPDATED_TECHNOLOGY);
        assertThat(testAppScans.getIastAgentType()).isEqualTo(DEFAULT_IAST_AGENT_TYPE);
        assertThat(testAppScans.getIastAgentStatus()).isEqualTo(UPDATED_IAST_AGENT_STATUS);
        assertThat(testAppScans.getUrlScan()).isEqualTo(UPDATED_URL_SCAN);
        assertThat(testAppScans.getAppName()).isEqualTo(DEFAULT_APP_NAME);
        assertThat(testAppScans.getTestOptimizationLevel()).isEqualTo(UPDATED_TEST_OPTIMIZATION_LEVEL);
        assertThat(testAppScans.getNumberOfExecutions()).isEqualTo(DEFAULT_NUMBER_OF_EXECUTIONS);
        assertThat(testAppScans.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testAppScans.getLastModified()).isEqualTo(UPDATED_LAST_MODIFIED);
    }

    @Test
    @Transactional
    void fullUpdateAppScansWithPatch() throws Exception {
        // Initialize the database
        appScansRepository.saveAndFlush(appScans);

        int databaseSizeBeforeUpdate = appScansRepository.findAll().size();

        // Update the appScans using partial update
        AppScans partialUpdatedAppScans = new AppScans();
        partialUpdatedAppScans.setId(appScans.getId());

        partialUpdatedAppScans
            .appId(UPDATED_APP_ID)
            .nameScan(UPDATED_NAME_SCAN)
            .technology(UPDATED_TECHNOLOGY)
            .iastAgentType(UPDATED_IAST_AGENT_TYPE)
            .iastAgentStatus(UPDATED_IAST_AGENT_STATUS)
            .urlScan(UPDATED_URL_SCAN)
            .appName(UPDATED_APP_NAME)
            .testOptimizationLevel(UPDATED_TEST_OPTIMIZATION_LEVEL)
            .numberOfExecutions(UPDATED_NUMBER_OF_EXECUTIONS)
            .createdAt(UPDATED_CREATED_AT)
            .lastModified(UPDATED_LAST_MODIFIED);

        restAppScansMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAppScans.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAppScans))
            )
            .andExpect(status().isOk());

        // Validate the AppScans in the database
        List<AppScans> appScansList = appScansRepository.findAll();
        assertThat(appScansList).hasSize(databaseSizeBeforeUpdate);
        AppScans testAppScans = appScansList.get(appScansList.size() - 1);
        assertThat(testAppScans.getAppId()).isEqualTo(UPDATED_APP_ID);
        assertThat(testAppScans.getNameScan()).isEqualTo(UPDATED_NAME_SCAN);
        assertThat(testAppScans.getTechnology()).isEqualTo(UPDATED_TECHNOLOGY);
        assertThat(testAppScans.getIastAgentType()).isEqualTo(UPDATED_IAST_AGENT_TYPE);
        assertThat(testAppScans.getIastAgentStatus()).isEqualTo(UPDATED_IAST_AGENT_STATUS);
        assertThat(testAppScans.getUrlScan()).isEqualTo(UPDATED_URL_SCAN);
        assertThat(testAppScans.getAppName()).isEqualTo(UPDATED_APP_NAME);
        assertThat(testAppScans.getTestOptimizationLevel()).isEqualTo(UPDATED_TEST_OPTIMIZATION_LEVEL);
        assertThat(testAppScans.getNumberOfExecutions()).isEqualTo(UPDATED_NUMBER_OF_EXECUTIONS);
        assertThat(testAppScans.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testAppScans.getLastModified()).isEqualTo(UPDATED_LAST_MODIFIED);
    }

    @Test
    @Transactional
    void patchNonExistingAppScans() throws Exception {
        int databaseSizeBeforeUpdate = appScansRepository.findAll().size();
        appScans.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAppScansMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, appScans.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(appScans))
            )
            .andExpect(status().isBadRequest());

        // Validate the AppScans in the database
        List<AppScans> appScansList = appScansRepository.findAll();
        assertThat(appScansList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchAppScans() throws Exception {
        int databaseSizeBeforeUpdate = appScansRepository.findAll().size();
        appScans.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAppScansMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(appScans))
            )
            .andExpect(status().isBadRequest());

        // Validate the AppScans in the database
        List<AppScans> appScansList = appScansRepository.findAll();
        assertThat(appScansList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamAppScans() throws Exception {
        int databaseSizeBeforeUpdate = appScansRepository.findAll().size();
        appScans.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAppScansMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(appScans)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the AppScans in the database
        List<AppScans> appScansList = appScansRepository.findAll();
        assertThat(appScansList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteAppScans() throws Exception {
        // Initialize the database
        appScansRepository.saveAndFlush(appScans);

        int databaseSizeBeforeDelete = appScansRepository.findAll().size();

        // Delete the appScans
        restAppScansMockMvc
            .perform(delete(ENTITY_API_URL_ID, appScans.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AppScans> appScansList = appScansRepository.findAll();
        assertThat(appScansList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
