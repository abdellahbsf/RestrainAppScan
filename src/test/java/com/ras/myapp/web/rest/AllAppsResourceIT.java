package com.ras.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.ras.myapp.IntegrationTest;
import com.ras.myapp.domain.AllApps;
import com.ras.myapp.repository.AllAppsRepository;
import jakarta.persistence.EntityManager;
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
 * Integration tests for the {@link AllAppsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AllAppsResourceIT {

    private static final String DEFAULT_APP_ID = "AAAAAAAAAA";
    private static final String UPDATED_APP_ID = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/all-apps";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private AllAppsRepository allAppsRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAllAppsMockMvc;

    private AllApps allApps;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AllApps createEntity(EntityManager em) {
        AllApps allApps = new AllApps().appId(DEFAULT_APP_ID).name(DEFAULT_NAME);
        return allApps;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AllApps createUpdatedEntity(EntityManager em) {
        AllApps allApps = new AllApps().appId(UPDATED_APP_ID).name(UPDATED_NAME);
        return allApps;
    }

    @BeforeEach
    public void initTest() {
        allApps = createEntity(em);
    }

    @Test
    @Transactional
    void createAllApps() throws Exception {
        int databaseSizeBeforeCreate = allAppsRepository.findAll().size();
        // Create the AllApps
        restAllAppsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(allApps)))
            .andExpect(status().isCreated());

        // Validate the AllApps in the database
        List<AllApps> allAppsList = allAppsRepository.findAll();
        assertThat(allAppsList).hasSize(databaseSizeBeforeCreate + 1);
        AllApps testAllApps = allAppsList.get(allAppsList.size() - 1);
        assertThat(testAllApps.getAppId()).isEqualTo(DEFAULT_APP_ID);
        assertThat(testAllApps.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    void createAllAppsWithExistingId() throws Exception {
        // Create the AllApps with an existing ID
        allApps.setId(1L);

        int databaseSizeBeforeCreate = allAppsRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restAllAppsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(allApps)))
            .andExpect(status().isBadRequest());

        // Validate the AllApps in the database
        List<AllApps> allAppsList = allAppsRepository.findAll();
        assertThat(allAppsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllAllApps() throws Exception {
        // Initialize the database
        allAppsRepository.saveAndFlush(allApps);

        // Get all the allAppsList
        restAllAppsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(allApps.getId().intValue())))
            .andExpect(jsonPath("$.[*].appId").value(hasItem(DEFAULT_APP_ID)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));
    }

    @Test
    @Transactional
    void getAllApps() throws Exception {
        // Initialize the database
        allAppsRepository.saveAndFlush(allApps);

        // Get the allApps
        restAllAppsMockMvc
            .perform(get(ENTITY_API_URL_ID, allApps.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(allApps.getId().intValue()))
            .andExpect(jsonPath("$.appId").value(DEFAULT_APP_ID))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME));
    }

    @Test
    @Transactional
    void getNonExistingAllApps() throws Exception {
        // Get the allApps
        restAllAppsMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingAllApps() throws Exception {
        // Initialize the database
        allAppsRepository.saveAndFlush(allApps);

        int databaseSizeBeforeUpdate = allAppsRepository.findAll().size();

        // Update the allApps
        AllApps updatedAllApps = allAppsRepository.findById(allApps.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedAllApps are not directly saved in db
        em.detach(updatedAllApps);
        updatedAllApps.appId(UPDATED_APP_ID).name(UPDATED_NAME);

        restAllAppsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedAllApps.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedAllApps))
            )
            .andExpect(status().isOk());

        // Validate the AllApps in the database
        List<AllApps> allAppsList = allAppsRepository.findAll();
        assertThat(allAppsList).hasSize(databaseSizeBeforeUpdate);
        AllApps testAllApps = allAppsList.get(allAppsList.size() - 1);
        assertThat(testAllApps.getAppId()).isEqualTo(UPDATED_APP_ID);
        assertThat(testAllApps.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    void putNonExistingAllApps() throws Exception {
        int databaseSizeBeforeUpdate = allAppsRepository.findAll().size();
        allApps.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAllAppsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, allApps.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(allApps))
            )
            .andExpect(status().isBadRequest());

        // Validate the AllApps in the database
        List<AllApps> allAppsList = allAppsRepository.findAll();
        assertThat(allAppsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchAllApps() throws Exception {
        int databaseSizeBeforeUpdate = allAppsRepository.findAll().size();
        allApps.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAllAppsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(allApps))
            )
            .andExpect(status().isBadRequest());

        // Validate the AllApps in the database
        List<AllApps> allAppsList = allAppsRepository.findAll();
        assertThat(allAppsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamAllApps() throws Exception {
        int databaseSizeBeforeUpdate = allAppsRepository.findAll().size();
        allApps.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAllAppsMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(allApps)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the AllApps in the database
        List<AllApps> allAppsList = allAppsRepository.findAll();
        assertThat(allAppsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateAllAppsWithPatch() throws Exception {
        // Initialize the database
        allAppsRepository.saveAndFlush(allApps);

        int databaseSizeBeforeUpdate = allAppsRepository.findAll().size();

        // Update the allApps using partial update
        AllApps partialUpdatedAllApps = new AllApps();
        partialUpdatedAllApps.setId(allApps.getId());

        partialUpdatedAllApps.appId(UPDATED_APP_ID).name(UPDATED_NAME);

        restAllAppsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAllApps.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAllApps))
            )
            .andExpect(status().isOk());

        // Validate the AllApps in the database
        List<AllApps> allAppsList = allAppsRepository.findAll();
        assertThat(allAppsList).hasSize(databaseSizeBeforeUpdate);
        AllApps testAllApps = allAppsList.get(allAppsList.size() - 1);
        assertThat(testAllApps.getAppId()).isEqualTo(UPDATED_APP_ID);
        assertThat(testAllApps.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    void fullUpdateAllAppsWithPatch() throws Exception {
        // Initialize the database
        allAppsRepository.saveAndFlush(allApps);

        int databaseSizeBeforeUpdate = allAppsRepository.findAll().size();

        // Update the allApps using partial update
        AllApps partialUpdatedAllApps = new AllApps();
        partialUpdatedAllApps.setId(allApps.getId());

        partialUpdatedAllApps.appId(UPDATED_APP_ID).name(UPDATED_NAME);

        restAllAppsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAllApps.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAllApps))
            )
            .andExpect(status().isOk());

        // Validate the AllApps in the database
        List<AllApps> allAppsList = allAppsRepository.findAll();
        assertThat(allAppsList).hasSize(databaseSizeBeforeUpdate);
        AllApps testAllApps = allAppsList.get(allAppsList.size() - 1);
        assertThat(testAllApps.getAppId()).isEqualTo(UPDATED_APP_ID);
        assertThat(testAllApps.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    void patchNonExistingAllApps() throws Exception {
        int databaseSizeBeforeUpdate = allAppsRepository.findAll().size();
        allApps.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAllAppsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, allApps.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(allApps))
            )
            .andExpect(status().isBadRequest());

        // Validate the AllApps in the database
        List<AllApps> allAppsList = allAppsRepository.findAll();
        assertThat(allAppsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchAllApps() throws Exception {
        int databaseSizeBeforeUpdate = allAppsRepository.findAll().size();
        allApps.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAllAppsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(allApps))
            )
            .andExpect(status().isBadRequest());

        // Validate the AllApps in the database
        List<AllApps> allAppsList = allAppsRepository.findAll();
        assertThat(allAppsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamAllApps() throws Exception {
        int databaseSizeBeforeUpdate = allAppsRepository.findAll().size();
        allApps.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAllAppsMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(allApps)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the AllApps in the database
        List<AllApps> allAppsList = allAppsRepository.findAll();
        assertThat(allAppsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteAllApps() throws Exception {
        // Initialize the database
        allAppsRepository.saveAndFlush(allApps);

        int databaseSizeBeforeDelete = allAppsRepository.findAll().size();

        // Delete the allApps
        restAllAppsMockMvc
            .perform(delete(ENTITY_API_URL_ID, allApps.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AllApps> allAppsList = allAppsRepository.findAll();
        assertThat(allAppsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
