package com.biolivre.teste.web.rest;

import com.biolivre.teste.domain.InfoAdicional;
import com.biolivre.teste.repository.InfoAdicionalRepository;
import com.biolivre.teste.web.rest.errors.BadRequestAlertException;
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
 * REST controller for managing {@link com.biolivre.teste.domain.InfoAdicional}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class InfoAdicionalResource {

    private final Logger log = LoggerFactory.getLogger(InfoAdicionalResource.class);

    private static final String ENTITY_NAME = "infoAdicional";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InfoAdicionalRepository infoAdicionalRepository;

    public InfoAdicionalResource(InfoAdicionalRepository infoAdicionalRepository) {
        this.infoAdicionalRepository = infoAdicionalRepository;
    }

    /**
     * {@code POST  /info-adicionals} : Create a new infoAdicional.
     *
     * @param infoAdicional the infoAdicional to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new infoAdicional, or with status {@code 400 (Bad Request)} if the infoAdicional has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/info-adicionals")
    public ResponseEntity<InfoAdicional> createInfoAdicional(@RequestBody InfoAdicional infoAdicional) throws URISyntaxException {
        log.debug("REST request to save InfoAdicional : {}", infoAdicional);
        if (infoAdicional.getId() != null) {
            throw new BadRequestAlertException("A new infoAdicional cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InfoAdicional result = infoAdicionalRepository.save(infoAdicional);
        return ResponseEntity
            .created(new URI("/api/info-adicionals/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /info-adicionals/:id} : Updates an existing infoAdicional.
     *
     * @param id the id of the infoAdicional to save.
     * @param infoAdicional the infoAdicional to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated infoAdicional,
     * or with status {@code 400 (Bad Request)} if the infoAdicional is not valid,
     * or with status {@code 500 (Internal Server Error)} if the infoAdicional couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/info-adicionals/{id}")
    public ResponseEntity<InfoAdicional> updateInfoAdicional(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody InfoAdicional infoAdicional
    ) throws URISyntaxException {
        log.debug("REST request to update InfoAdicional : {}, {}", id, infoAdicional);
        if (infoAdicional.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, infoAdicional.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!infoAdicionalRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        InfoAdicional result = infoAdicionalRepository.save(infoAdicional);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, infoAdicional.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /info-adicionals/:id} : Partial updates given fields of an existing infoAdicional, field will ignore if it is null
     *
     * @param id the id of the infoAdicional to save.
     * @param infoAdicional the infoAdicional to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated infoAdicional,
     * or with status {@code 400 (Bad Request)} if the infoAdicional is not valid,
     * or with status {@code 404 (Not Found)} if the infoAdicional is not found,
     * or with status {@code 500 (Internal Server Error)} if the infoAdicional couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/info-adicionals/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<InfoAdicional> partialUpdateInfoAdicional(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody InfoAdicional infoAdicional
    ) throws URISyntaxException {
        log.debug("REST request to partial update InfoAdicional partially : {}, {}", id, infoAdicional);
        if (infoAdicional.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, infoAdicional.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!infoAdicionalRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<InfoAdicional> result = infoAdicionalRepository
            .findById(infoAdicional.getId())
            .map(existingInfoAdicional -> {
                if (infoAdicional.getNome() != null) {
                    existingInfoAdicional.setNome(infoAdicional.getNome());
                }
                if (infoAdicional.getSobrenome() != null) {
                    existingInfoAdicional.setSobrenome(infoAdicional.getSobrenome());
                }
                if (infoAdicional.getNascimento() != null) {
                    existingInfoAdicional.setNascimento(infoAdicional.getNascimento());
                }
                if (infoAdicional.getTelefoneCelular() != null) {
                    existingInfoAdicional.setTelefoneCelular(infoAdicional.getTelefoneCelular());
                }
                if (infoAdicional.getDoc() != null) {
                    existingInfoAdicional.setDoc(infoAdicional.getDoc());
                }
                if (infoAdicional.getCep() != null) {
                    existingInfoAdicional.setCep(infoAdicional.getCep());
                }
                if (infoAdicional.getLogradouro() != null) {
                    existingInfoAdicional.setLogradouro(infoAdicional.getLogradouro());
                }
                if (infoAdicional.getNumero() != null) {
                    existingInfoAdicional.setNumero(infoAdicional.getNumero());
                }
                if (infoAdicional.getComplemento() != null) {
                    existingInfoAdicional.setComplemento(infoAdicional.getComplemento());
                }
                if (infoAdicional.getBairro() != null) {
                    existingInfoAdicional.setBairro(infoAdicional.getBairro());
                }
                if (infoAdicional.getCidade() != null) {
                    existingInfoAdicional.setCidade(infoAdicional.getCidade());
                }
                if (infoAdicional.getEstado() != null) {
                    existingInfoAdicional.setEstado(infoAdicional.getEstado());
                }
                if (infoAdicional.getSituacao() != null) {
                    existingInfoAdicional.setSituacao(infoAdicional.getSituacao());
                }
                if (infoAdicional.getTipoLesao() != null) {
                    existingInfoAdicional.setTipoLesao(infoAdicional.getTipoLesao());
                }
                if (infoAdicional.getDetalhes() != null) {
                    existingInfoAdicional.setDetalhes(infoAdicional.getDetalhes());
                }
                if (infoAdicional.getImagemPerfil() != null) {
                    existingInfoAdicional.setImagemPerfil(infoAdicional.getImagemPerfil());
                }
                if (infoAdicional.getImagemPerfilContentType() != null) {
                    existingInfoAdicional.setImagemPerfilContentType(infoAdicional.getImagemPerfilContentType());
                }
                if (infoAdicional.getImagemComDoc() != null) {
                    existingInfoAdicional.setImagemComDoc(infoAdicional.getImagemComDoc());
                }
                if (infoAdicional.getImagemComDocContentType() != null) {
                    existingInfoAdicional.setImagemComDocContentType(infoAdicional.getImagemComDocContentType());
                }
                if (infoAdicional.getImagemLogoParceiro() != null) {
                    existingInfoAdicional.setImagemLogoParceiro(infoAdicional.getImagemLogoParceiro());
                }
                if (infoAdicional.getImagemLogoParceiroContentType() != null) {
                    existingInfoAdicional.setImagemLogoParceiroContentType(infoAdicional.getImagemLogoParceiroContentType());
                }
                if (infoAdicional.getAreaEmpresa() != null) {
                    existingInfoAdicional.setAreaEmpresa(infoAdicional.getAreaEmpresa());
                }
                if (infoAdicional.getCnpj() != null) {
                    existingInfoAdicional.setCnpj(infoAdicional.getCnpj());
                }
                if (infoAdicional.getInscricaoEstadual() != null) {
                    existingInfoAdicional.setInscricaoEstadual(infoAdicional.getInscricaoEstadual());
                }
                if (infoAdicional.getTipoServico() != null) {
                    existingInfoAdicional.setTipoServico(infoAdicional.getTipoServico());
                }
                if (infoAdicional.getRazaoSocial() != null) {
                    existingInfoAdicional.setRazaoSocial(infoAdicional.getRazaoSocial());
                }
                if (infoAdicional.getNomeFantasia() != null) {
                    existingInfoAdicional.setNomeFantasia(infoAdicional.getNomeFantasia());
                }
                if (infoAdicional.getBanco() != null) {
                    existingInfoAdicional.setBanco(infoAdicional.getBanco());
                }
                if (infoAdicional.getBancoOutro() != null) {
                    existingInfoAdicional.setBancoOutro(infoAdicional.getBancoOutro());
                }
                if (infoAdicional.getAgencia() != null) {
                    existingInfoAdicional.setAgencia(infoAdicional.getAgencia());
                }
                if (infoAdicional.getNumeroConta() != null) {
                    existingInfoAdicional.setNumeroConta(infoAdicional.getNumeroConta());
                }
                if (infoAdicional.getTelefoneEmpresa() != null) {
                    existingInfoAdicional.setTelefoneEmpresa(infoAdicional.getTelefoneEmpresa());
                }
                if (infoAdicional.getEmailEmpresa() != null) {
                    existingInfoAdicional.setEmailEmpresa(infoAdicional.getEmailEmpresa());
                }

                return existingInfoAdicional;
            })
            .map(infoAdicionalRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, infoAdicional.getId().toString())
        );
    }

    /**
     * {@code GET  /info-adicionals} : get all the infoAdicionals.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of infoAdicionals in body.
     */
    @GetMapping("/info-adicionals")
    public List<InfoAdicional> getAllInfoAdicionals() {
        log.debug("REST request to get all InfoAdicionals");
        return infoAdicionalRepository.findAll();
    }

    /**
     * {@code GET  /info-adicionals/:id} : get the "id" infoAdicional.
     *
     * @param id the id of the infoAdicional to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the infoAdicional, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/info-adicionals/{id}")
    public ResponseEntity<InfoAdicional> getInfoAdicional(@PathVariable Long id) {
        log.debug("REST request to get InfoAdicional : {}", id);
        Optional<InfoAdicional> infoAdicional = infoAdicionalRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(infoAdicional);
    }

    /**
     * {@code DELETE  /info-adicionals/:id} : delete the "id" infoAdicional.
     *
     * @param id the id of the infoAdicional to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/info-adicionals/{id}")
    public ResponseEntity<Void> deleteInfoAdicional(@PathVariable Long id) {
        log.debug("REST request to delete InfoAdicional : {}", id);
        infoAdicionalRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
