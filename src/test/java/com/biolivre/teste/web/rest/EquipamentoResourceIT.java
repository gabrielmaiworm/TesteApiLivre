package com.biolivre.teste.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.biolivre.teste.IntegrationTest;
import com.biolivre.teste.domain.Equipamento;
import com.biolivre.teste.repository.EquipamentoRepository;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link EquipamentoResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class EquipamentoResourceIT {

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_NUMERO_SERIE = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO_SERIE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/equipamentos";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private EquipamentoRepository equipamentoRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEquipamentoMockMvc;

    private Equipamento equipamento;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Equipamento createEntity(EntityManager em) {
        Equipamento equipamento = new Equipamento().nome(DEFAULT_NOME).status(DEFAULT_STATUS).numeroSerie(DEFAULT_NUMERO_SERIE);
        return equipamento;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Equipamento createUpdatedEntity(EntityManager em) {
        Equipamento equipamento = new Equipamento().nome(UPDATED_NOME).status(UPDATED_STATUS).numeroSerie(UPDATED_NUMERO_SERIE);
        return equipamento;
    }

    @BeforeEach
    public void initTest() {
        equipamento = createEntity(em);
    }

    @Test
    @Transactional
    void createEquipamento() throws Exception {
        int databaseSizeBeforeCreate = equipamentoRepository.findAll().size();
        // Create the Equipamento
        restEquipamentoMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(equipamento)))
            .andExpect(status().isCreated());

        // Validate the Equipamento in the database
        List<Equipamento> equipamentoList = equipamentoRepository.findAll();
        assertThat(equipamentoList).hasSize(databaseSizeBeforeCreate + 1);
        Equipamento testEquipamento = equipamentoList.get(equipamentoList.size() - 1);
        assertThat(testEquipamento.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testEquipamento.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testEquipamento.getNumeroSerie()).isEqualTo(DEFAULT_NUMERO_SERIE);
    }

    @Test
    @Transactional
    void createEquipamentoWithExistingId() throws Exception {
        // Create the Equipamento with an existing ID
        equipamento.setId(1L);

        int databaseSizeBeforeCreate = equipamentoRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restEquipamentoMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(equipamento)))
            .andExpect(status().isBadRequest());

        // Validate the Equipamento in the database
        List<Equipamento> equipamentoList = equipamentoRepository.findAll();
        assertThat(equipamentoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllEquipamentos() throws Exception {
        // Initialize the database
        equipamentoRepository.saveAndFlush(equipamento);

        // Get all the equipamentoList
        restEquipamentoMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(equipamento.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].numeroSerie").value(hasItem(DEFAULT_NUMERO_SERIE)));
    }

    @Test
    @Transactional
    void getEquipamento() throws Exception {
        // Initialize the database
        equipamentoRepository.saveAndFlush(equipamento);

        // Get the equipamento
        restEquipamentoMockMvc
            .perform(get(ENTITY_API_URL_ID, equipamento.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(equipamento.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.numeroSerie").value(DEFAULT_NUMERO_SERIE));
    }

    @Test
    @Transactional
    void getNonExistingEquipamento() throws Exception {
        // Get the equipamento
        restEquipamentoMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewEquipamento() throws Exception {
        // Initialize the database
        equipamentoRepository.saveAndFlush(equipamento);

        int databaseSizeBeforeUpdate = equipamentoRepository.findAll().size();

        // Update the equipamento
        Equipamento updatedEquipamento = equipamentoRepository.findById(equipamento.getId()).get();
        // Disconnect from session so that the updates on updatedEquipamento are not directly saved in db
        em.detach(updatedEquipamento);
        updatedEquipamento.nome(UPDATED_NOME).status(UPDATED_STATUS).numeroSerie(UPDATED_NUMERO_SERIE);

        restEquipamentoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedEquipamento.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedEquipamento))
            )
            .andExpect(status().isOk());

        // Validate the Equipamento in the database
        List<Equipamento> equipamentoList = equipamentoRepository.findAll();
        assertThat(equipamentoList).hasSize(databaseSizeBeforeUpdate);
        Equipamento testEquipamento = equipamentoList.get(equipamentoList.size() - 1);
        assertThat(testEquipamento.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testEquipamento.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testEquipamento.getNumeroSerie()).isEqualTo(UPDATED_NUMERO_SERIE);
    }

    @Test
    @Transactional
    void putNonExistingEquipamento() throws Exception {
        int databaseSizeBeforeUpdate = equipamentoRepository.findAll().size();
        equipamento.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEquipamentoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, equipamento.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(equipamento))
            )
            .andExpect(status().isBadRequest());

        // Validate the Equipamento in the database
        List<Equipamento> equipamentoList = equipamentoRepository.findAll();
        assertThat(equipamentoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchEquipamento() throws Exception {
        int databaseSizeBeforeUpdate = equipamentoRepository.findAll().size();
        equipamento.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEquipamentoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(equipamento))
            )
            .andExpect(status().isBadRequest());

        // Validate the Equipamento in the database
        List<Equipamento> equipamentoList = equipamentoRepository.findAll();
        assertThat(equipamentoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamEquipamento() throws Exception {
        int databaseSizeBeforeUpdate = equipamentoRepository.findAll().size();
        equipamento.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEquipamentoMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(equipamento)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Equipamento in the database
        List<Equipamento> equipamentoList = equipamentoRepository.findAll();
        assertThat(equipamentoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateEquipamentoWithPatch() throws Exception {
        // Initialize the database
        equipamentoRepository.saveAndFlush(equipamento);

        int databaseSizeBeforeUpdate = equipamentoRepository.findAll().size();

        // Update the equipamento using partial update
        Equipamento partialUpdatedEquipamento = new Equipamento();
        partialUpdatedEquipamento.setId(equipamento.getId());

        restEquipamentoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedEquipamento.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedEquipamento))
            )
            .andExpect(status().isOk());

        // Validate the Equipamento in the database
        List<Equipamento> equipamentoList = equipamentoRepository.findAll();
        assertThat(equipamentoList).hasSize(databaseSizeBeforeUpdate);
        Equipamento testEquipamento = equipamentoList.get(equipamentoList.size() - 1);
        assertThat(testEquipamento.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testEquipamento.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testEquipamento.getNumeroSerie()).isEqualTo(DEFAULT_NUMERO_SERIE);
    }

    @Test
    @Transactional
    void fullUpdateEquipamentoWithPatch() throws Exception {
        // Initialize the database
        equipamentoRepository.saveAndFlush(equipamento);

        int databaseSizeBeforeUpdate = equipamentoRepository.findAll().size();

        // Update the equipamento using partial update
        Equipamento partialUpdatedEquipamento = new Equipamento();
        partialUpdatedEquipamento.setId(equipamento.getId());

        partialUpdatedEquipamento.nome(UPDATED_NOME).status(UPDATED_STATUS).numeroSerie(UPDATED_NUMERO_SERIE);

        restEquipamentoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedEquipamento.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedEquipamento))
            )
            .andExpect(status().isOk());

        // Validate the Equipamento in the database
        List<Equipamento> equipamentoList = equipamentoRepository.findAll();
        assertThat(equipamentoList).hasSize(databaseSizeBeforeUpdate);
        Equipamento testEquipamento = equipamentoList.get(equipamentoList.size() - 1);
        assertThat(testEquipamento.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testEquipamento.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testEquipamento.getNumeroSerie()).isEqualTo(UPDATED_NUMERO_SERIE);
    }

    @Test
    @Transactional
    void patchNonExistingEquipamento() throws Exception {
        int databaseSizeBeforeUpdate = equipamentoRepository.findAll().size();
        equipamento.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEquipamentoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, equipamento.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(equipamento))
            )
            .andExpect(status().isBadRequest());

        // Validate the Equipamento in the database
        List<Equipamento> equipamentoList = equipamentoRepository.findAll();
        assertThat(equipamentoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchEquipamento() throws Exception {
        int databaseSizeBeforeUpdate = equipamentoRepository.findAll().size();
        equipamento.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEquipamentoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(equipamento))
            )
            .andExpect(status().isBadRequest());

        // Validate the Equipamento in the database
        List<Equipamento> equipamentoList = equipamentoRepository.findAll();
        assertThat(equipamentoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamEquipamento() throws Exception {
        int databaseSizeBeforeUpdate = equipamentoRepository.findAll().size();
        equipamento.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEquipamentoMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(equipamento))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Equipamento in the database
        List<Equipamento> equipamentoList = equipamentoRepository.findAll();
        assertThat(equipamentoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteEquipamento() throws Exception {
        // Initialize the database
        equipamentoRepository.saveAndFlush(equipamento);

        int databaseSizeBeforeDelete = equipamentoRepository.findAll().size();

        // Delete the equipamento
        restEquipamentoMockMvc
            .perform(delete(ENTITY_API_URL_ID, equipamento.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Equipamento> equipamentoList = equipamentoRepository.findAll();
        assertThat(equipamentoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
