package com.biolivre.teste.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.biolivre.teste.IntegrationTest;
import com.biolivre.teste.domain.InfoAdicional;
import com.biolivre.teste.repository.InfoAdicionalRepository;
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
import org.springframework.util.Base64Utils;

/**
 * Integration tests for the {@link InfoAdicionalResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class InfoAdicionalResourceIT {

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final String DEFAULT_SOBRENOME = "AAAAAAAAAA";
    private static final String UPDATED_SOBRENOME = "BBBBBBBBBB";

    private static final String DEFAULT_NASCIMENTO = "AAAAAAAAAA";
    private static final String UPDATED_NASCIMENTO = "BBBBBBBBBB";

    private static final String DEFAULT_TELEFONE_CELULAR = "AAAAAAAAAA";
    private static final String UPDATED_TELEFONE_CELULAR = "BBBBBBBBBB";

    private static final String DEFAULT_DOC = "AAAAAAAAAA";
    private static final String UPDATED_DOC = "BBBBBBBBBB";

    private static final String DEFAULT_CEP = "AAAAAAAAAA";
    private static final String UPDATED_CEP = "BBBBBBBBBB";

    private static final String DEFAULT_LOGRADOURO = "AAAAAAAAAA";
    private static final String UPDATED_LOGRADOURO = "BBBBBBBBBB";

    private static final String DEFAULT_NUMERO = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO = "BBBBBBBBBB";

    private static final String DEFAULT_COMPLEMENTO = "AAAAAAAAAA";
    private static final String UPDATED_COMPLEMENTO = "BBBBBBBBBB";

    private static final String DEFAULT_BAIRRO = "AAAAAAAAAA";
    private static final String UPDATED_BAIRRO = "BBBBBBBBBB";

    private static final String DEFAULT_CIDADE = "AAAAAAAAAA";
    private static final String UPDATED_CIDADE = "BBBBBBBBBB";

    private static final String DEFAULT_ESTADO = "AAAAAAAAAA";
    private static final String UPDATED_ESTADO = "BBBBBBBBBB";

    private static final String DEFAULT_SITUACAO = "AAAAAAAAAA";
    private static final String UPDATED_SITUACAO = "BBBBBBBBBB";

    private static final String DEFAULT_TIPO_LESAO = "AAAAAAAAAA";
    private static final String UPDATED_TIPO_LESAO = "BBBBBBBBBB";

    private static final String DEFAULT_DETALHES = "AAAAAAAAAA";
    private static final String UPDATED_DETALHES = "BBBBBBBBBB";

    private static final byte[] DEFAULT_IMAGEM_PERFIL = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_IMAGEM_PERFIL = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_IMAGEM_PERFIL_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_IMAGEM_PERFIL_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_IMAGEM_COM_DOC = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_IMAGEM_COM_DOC = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_IMAGEM_COM_DOC_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_IMAGEM_COM_DOC_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_IMAGEM_LOGO_PARCEIRO = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_IMAGEM_LOGO_PARCEIRO = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_IMAGEM_LOGO_PARCEIRO_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_IMAGEM_LOGO_PARCEIRO_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_AREA_EMPRESA = "AAAAAAAAAA";
    private static final String UPDATED_AREA_EMPRESA = "BBBBBBBBBB";

    private static final String DEFAULT_CNPJ = "AAAAAAAAAA";
    private static final String UPDATED_CNPJ = "BBBBBBBBBB";

    private static final String DEFAULT_INSCRICAO_ESTADUAL = "AAAAAAAAAA";
    private static final String UPDATED_INSCRICAO_ESTADUAL = "BBBBBBBBBB";

    private static final String DEFAULT_TIPO_SERVICO = "AAAAAAAAAA";
    private static final String UPDATED_TIPO_SERVICO = "BBBBBBBBBB";

    private static final String DEFAULT_RAZAO_SOCIAL = "AAAAAAAAAA";
    private static final String UPDATED_RAZAO_SOCIAL = "BBBBBBBBBB";

    private static final String DEFAULT_NOME_FANTASIA = "AAAAAAAAAA";
    private static final String UPDATED_NOME_FANTASIA = "BBBBBBBBBB";

    private static final String DEFAULT_BANCO = "AAAAAAAAAA";
    private static final String UPDATED_BANCO = "BBBBBBBBBB";

    private static final String DEFAULT_BANCO_OUTRO = "AAAAAAAAAA";
    private static final String UPDATED_BANCO_OUTRO = "BBBBBBBBBB";

    private static final String DEFAULT_AGENCIA = "AAAAAAAAAA";
    private static final String UPDATED_AGENCIA = "BBBBBBBBBB";

    private static final String DEFAULT_NUMERO_CONTA = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO_CONTA = "BBBBBBBBBB";

    private static final String DEFAULT_TELEFONE_EMPRESA = "AAAAAAAAAA";
    private static final String UPDATED_TELEFONE_EMPRESA = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL_EMPRESA = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL_EMPRESA = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/info-adicionals";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private InfoAdicionalRepository infoAdicionalRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restInfoAdicionalMockMvc;

    private InfoAdicional infoAdicional;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InfoAdicional createEntity(EntityManager em) {
        InfoAdicional infoAdicional = new InfoAdicional()
            .nome(DEFAULT_NOME)
            .sobrenome(DEFAULT_SOBRENOME)
            .nascimento(DEFAULT_NASCIMENTO)
            .telefoneCelular(DEFAULT_TELEFONE_CELULAR)
            .doc(DEFAULT_DOC)
            .cep(DEFAULT_CEP)
            .logradouro(DEFAULT_LOGRADOURO)
            .numero(DEFAULT_NUMERO)
            .complemento(DEFAULT_COMPLEMENTO)
            .bairro(DEFAULT_BAIRRO)
            .cidade(DEFAULT_CIDADE)
            .estado(DEFAULT_ESTADO)
            .situacao(DEFAULT_SITUACAO)
            .tipoLesao(DEFAULT_TIPO_LESAO)
            .detalhes(DEFAULT_DETALHES)
            .imagemPerfil(DEFAULT_IMAGEM_PERFIL)
            .imagemPerfilContentType(DEFAULT_IMAGEM_PERFIL_CONTENT_TYPE)
            .imagemComDoc(DEFAULT_IMAGEM_COM_DOC)
            .imagemComDocContentType(DEFAULT_IMAGEM_COM_DOC_CONTENT_TYPE)
            .imagemLogoParceiro(DEFAULT_IMAGEM_LOGO_PARCEIRO)
            .imagemLogoParceiroContentType(DEFAULT_IMAGEM_LOGO_PARCEIRO_CONTENT_TYPE)
            .areaEmpresa(DEFAULT_AREA_EMPRESA)
            .cnpj(DEFAULT_CNPJ)
            .inscricaoEstadual(DEFAULT_INSCRICAO_ESTADUAL)
            .tipoServico(DEFAULT_TIPO_SERVICO)
            .razaoSocial(DEFAULT_RAZAO_SOCIAL)
            .nomeFantasia(DEFAULT_NOME_FANTASIA)
            .banco(DEFAULT_BANCO)
            .bancoOutro(DEFAULT_BANCO_OUTRO)
            .agencia(DEFAULT_AGENCIA)
            .numeroConta(DEFAULT_NUMERO_CONTA)
            .telefoneEmpresa(DEFAULT_TELEFONE_EMPRESA)
            .emailEmpresa(DEFAULT_EMAIL_EMPRESA);
        return infoAdicional;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InfoAdicional createUpdatedEntity(EntityManager em) {
        InfoAdicional infoAdicional = new InfoAdicional()
            .nome(UPDATED_NOME)
            .sobrenome(UPDATED_SOBRENOME)
            .nascimento(UPDATED_NASCIMENTO)
            .telefoneCelular(UPDATED_TELEFONE_CELULAR)
            .doc(UPDATED_DOC)
            .cep(UPDATED_CEP)
            .logradouro(UPDATED_LOGRADOURO)
            .numero(UPDATED_NUMERO)
            .complemento(UPDATED_COMPLEMENTO)
            .bairro(UPDATED_BAIRRO)
            .cidade(UPDATED_CIDADE)
            .estado(UPDATED_ESTADO)
            .situacao(UPDATED_SITUACAO)
            .tipoLesao(UPDATED_TIPO_LESAO)
            .detalhes(UPDATED_DETALHES)
            .imagemPerfil(UPDATED_IMAGEM_PERFIL)
            .imagemPerfilContentType(UPDATED_IMAGEM_PERFIL_CONTENT_TYPE)
            .imagemComDoc(UPDATED_IMAGEM_COM_DOC)
            .imagemComDocContentType(UPDATED_IMAGEM_COM_DOC_CONTENT_TYPE)
            .imagemLogoParceiro(UPDATED_IMAGEM_LOGO_PARCEIRO)
            .imagemLogoParceiroContentType(UPDATED_IMAGEM_LOGO_PARCEIRO_CONTENT_TYPE)
            .areaEmpresa(UPDATED_AREA_EMPRESA)
            .cnpj(UPDATED_CNPJ)
            .inscricaoEstadual(UPDATED_INSCRICAO_ESTADUAL)
            .tipoServico(UPDATED_TIPO_SERVICO)
            .razaoSocial(UPDATED_RAZAO_SOCIAL)
            .nomeFantasia(UPDATED_NOME_FANTASIA)
            .banco(UPDATED_BANCO)
            .bancoOutro(UPDATED_BANCO_OUTRO)
            .agencia(UPDATED_AGENCIA)
            .numeroConta(UPDATED_NUMERO_CONTA)
            .telefoneEmpresa(UPDATED_TELEFONE_EMPRESA)
            .emailEmpresa(UPDATED_EMAIL_EMPRESA);
        return infoAdicional;
    }

    @BeforeEach
    public void initTest() {
        infoAdicional = createEntity(em);
    }

    @Test
    @Transactional
    void createInfoAdicional() throws Exception {
        int databaseSizeBeforeCreate = infoAdicionalRepository.findAll().size();
        // Create the InfoAdicional
        restInfoAdicionalMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(infoAdicional)))
            .andExpect(status().isCreated());

        // Validate the InfoAdicional in the database
        List<InfoAdicional> infoAdicionalList = infoAdicionalRepository.findAll();
        assertThat(infoAdicionalList).hasSize(databaseSizeBeforeCreate + 1);
        InfoAdicional testInfoAdicional = infoAdicionalList.get(infoAdicionalList.size() - 1);
        assertThat(testInfoAdicional.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testInfoAdicional.getSobrenome()).isEqualTo(DEFAULT_SOBRENOME);
        assertThat(testInfoAdicional.getNascimento()).isEqualTo(DEFAULT_NASCIMENTO);
        assertThat(testInfoAdicional.getTelefoneCelular()).isEqualTo(DEFAULT_TELEFONE_CELULAR);
        assertThat(testInfoAdicional.getDoc()).isEqualTo(DEFAULT_DOC);
        assertThat(testInfoAdicional.getCep()).isEqualTo(DEFAULT_CEP);
        assertThat(testInfoAdicional.getLogradouro()).isEqualTo(DEFAULT_LOGRADOURO);
        assertThat(testInfoAdicional.getNumero()).isEqualTo(DEFAULT_NUMERO);
        assertThat(testInfoAdicional.getComplemento()).isEqualTo(DEFAULT_COMPLEMENTO);
        assertThat(testInfoAdicional.getBairro()).isEqualTo(DEFAULT_BAIRRO);
        assertThat(testInfoAdicional.getCidade()).isEqualTo(DEFAULT_CIDADE);
        assertThat(testInfoAdicional.getEstado()).isEqualTo(DEFAULT_ESTADO);
        assertThat(testInfoAdicional.getSituacao()).isEqualTo(DEFAULT_SITUACAO);
        assertThat(testInfoAdicional.getTipoLesao()).isEqualTo(DEFAULT_TIPO_LESAO);
        assertThat(testInfoAdicional.getDetalhes()).isEqualTo(DEFAULT_DETALHES);
        assertThat(testInfoAdicional.getImagemPerfil()).isEqualTo(DEFAULT_IMAGEM_PERFIL);
        assertThat(testInfoAdicional.getImagemPerfilContentType()).isEqualTo(DEFAULT_IMAGEM_PERFIL_CONTENT_TYPE);
        assertThat(testInfoAdicional.getImagemComDoc()).isEqualTo(DEFAULT_IMAGEM_COM_DOC);
        assertThat(testInfoAdicional.getImagemComDocContentType()).isEqualTo(DEFAULT_IMAGEM_COM_DOC_CONTENT_TYPE);
        assertThat(testInfoAdicional.getImagemLogoParceiro()).isEqualTo(DEFAULT_IMAGEM_LOGO_PARCEIRO);
        assertThat(testInfoAdicional.getImagemLogoParceiroContentType()).isEqualTo(DEFAULT_IMAGEM_LOGO_PARCEIRO_CONTENT_TYPE);
        assertThat(testInfoAdicional.getAreaEmpresa()).isEqualTo(DEFAULT_AREA_EMPRESA);
        assertThat(testInfoAdicional.getCnpj()).isEqualTo(DEFAULT_CNPJ);
        assertThat(testInfoAdicional.getInscricaoEstadual()).isEqualTo(DEFAULT_INSCRICAO_ESTADUAL);
        assertThat(testInfoAdicional.getTipoServico()).isEqualTo(DEFAULT_TIPO_SERVICO);
        assertThat(testInfoAdicional.getRazaoSocial()).isEqualTo(DEFAULT_RAZAO_SOCIAL);
        assertThat(testInfoAdicional.getNomeFantasia()).isEqualTo(DEFAULT_NOME_FANTASIA);
        assertThat(testInfoAdicional.getBanco()).isEqualTo(DEFAULT_BANCO);
        assertThat(testInfoAdicional.getBancoOutro()).isEqualTo(DEFAULT_BANCO_OUTRO);
        assertThat(testInfoAdicional.getAgencia()).isEqualTo(DEFAULT_AGENCIA);
        assertThat(testInfoAdicional.getNumeroConta()).isEqualTo(DEFAULT_NUMERO_CONTA);
        assertThat(testInfoAdicional.getTelefoneEmpresa()).isEqualTo(DEFAULT_TELEFONE_EMPRESA);
        assertThat(testInfoAdicional.getEmailEmpresa()).isEqualTo(DEFAULT_EMAIL_EMPRESA);
    }

    @Test
    @Transactional
    void createInfoAdicionalWithExistingId() throws Exception {
        // Create the InfoAdicional with an existing ID
        infoAdicional.setId(1L);

        int databaseSizeBeforeCreate = infoAdicionalRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restInfoAdicionalMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(infoAdicional)))
            .andExpect(status().isBadRequest());

        // Validate the InfoAdicional in the database
        List<InfoAdicional> infoAdicionalList = infoAdicionalRepository.findAll();
        assertThat(infoAdicionalList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllInfoAdicionals() throws Exception {
        // Initialize the database
        infoAdicionalRepository.saveAndFlush(infoAdicional);

        // Get all the infoAdicionalList
        restInfoAdicionalMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(infoAdicional.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME)))
            .andExpect(jsonPath("$.[*].sobrenome").value(hasItem(DEFAULT_SOBRENOME)))
            .andExpect(jsonPath("$.[*].nascimento").value(hasItem(DEFAULT_NASCIMENTO)))
            .andExpect(jsonPath("$.[*].telefoneCelular").value(hasItem(DEFAULT_TELEFONE_CELULAR)))
            .andExpect(jsonPath("$.[*].doc").value(hasItem(DEFAULT_DOC)))
            .andExpect(jsonPath("$.[*].cep").value(hasItem(DEFAULT_CEP)))
            .andExpect(jsonPath("$.[*].logradouro").value(hasItem(DEFAULT_LOGRADOURO)))
            .andExpect(jsonPath("$.[*].numero").value(hasItem(DEFAULT_NUMERO)))
            .andExpect(jsonPath("$.[*].complemento").value(hasItem(DEFAULT_COMPLEMENTO)))
            .andExpect(jsonPath("$.[*].bairro").value(hasItem(DEFAULT_BAIRRO)))
            .andExpect(jsonPath("$.[*].cidade").value(hasItem(DEFAULT_CIDADE)))
            .andExpect(jsonPath("$.[*].estado").value(hasItem(DEFAULT_ESTADO)))
            .andExpect(jsonPath("$.[*].situacao").value(hasItem(DEFAULT_SITUACAO)))
            .andExpect(jsonPath("$.[*].tipoLesao").value(hasItem(DEFAULT_TIPO_LESAO)))
            .andExpect(jsonPath("$.[*].detalhes").value(hasItem(DEFAULT_DETALHES)))
            .andExpect(jsonPath("$.[*].imagemPerfilContentType").value(hasItem(DEFAULT_IMAGEM_PERFIL_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].imagemPerfil").value(hasItem(Base64Utils.encodeToString(DEFAULT_IMAGEM_PERFIL))))
            .andExpect(jsonPath("$.[*].imagemComDocContentType").value(hasItem(DEFAULT_IMAGEM_COM_DOC_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].imagemComDoc").value(hasItem(Base64Utils.encodeToString(DEFAULT_IMAGEM_COM_DOC))))
            .andExpect(jsonPath("$.[*].imagemLogoParceiroContentType").value(hasItem(DEFAULT_IMAGEM_LOGO_PARCEIRO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].imagemLogoParceiro").value(hasItem(Base64Utils.encodeToString(DEFAULT_IMAGEM_LOGO_PARCEIRO))))
            .andExpect(jsonPath("$.[*].areaEmpresa").value(hasItem(DEFAULT_AREA_EMPRESA)))
            .andExpect(jsonPath("$.[*].cnpj").value(hasItem(DEFAULT_CNPJ)))
            .andExpect(jsonPath("$.[*].inscricaoEstadual").value(hasItem(DEFAULT_INSCRICAO_ESTADUAL)))
            .andExpect(jsonPath("$.[*].tipoServico").value(hasItem(DEFAULT_TIPO_SERVICO)))
            .andExpect(jsonPath("$.[*].razaoSocial").value(hasItem(DEFAULT_RAZAO_SOCIAL)))
            .andExpect(jsonPath("$.[*].nomeFantasia").value(hasItem(DEFAULT_NOME_FANTASIA)))
            .andExpect(jsonPath("$.[*].banco").value(hasItem(DEFAULT_BANCO)))
            .andExpect(jsonPath("$.[*].bancoOutro").value(hasItem(DEFAULT_BANCO_OUTRO)))
            .andExpect(jsonPath("$.[*].agencia").value(hasItem(DEFAULT_AGENCIA)))
            .andExpect(jsonPath("$.[*].numeroConta").value(hasItem(DEFAULT_NUMERO_CONTA)))
            .andExpect(jsonPath("$.[*].telefoneEmpresa").value(hasItem(DEFAULT_TELEFONE_EMPRESA)))
            .andExpect(jsonPath("$.[*].emailEmpresa").value(hasItem(DEFAULT_EMAIL_EMPRESA)));
    }

    @Test
    @Transactional
    void getInfoAdicional() throws Exception {
        // Initialize the database
        infoAdicionalRepository.saveAndFlush(infoAdicional);

        // Get the infoAdicional
        restInfoAdicionalMockMvc
            .perform(get(ENTITY_API_URL_ID, infoAdicional.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(infoAdicional.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME))
            .andExpect(jsonPath("$.sobrenome").value(DEFAULT_SOBRENOME))
            .andExpect(jsonPath("$.nascimento").value(DEFAULT_NASCIMENTO))
            .andExpect(jsonPath("$.telefoneCelular").value(DEFAULT_TELEFONE_CELULAR))
            .andExpect(jsonPath("$.doc").value(DEFAULT_DOC))
            .andExpect(jsonPath("$.cep").value(DEFAULT_CEP))
            .andExpect(jsonPath("$.logradouro").value(DEFAULT_LOGRADOURO))
            .andExpect(jsonPath("$.numero").value(DEFAULT_NUMERO))
            .andExpect(jsonPath("$.complemento").value(DEFAULT_COMPLEMENTO))
            .andExpect(jsonPath("$.bairro").value(DEFAULT_BAIRRO))
            .andExpect(jsonPath("$.cidade").value(DEFAULT_CIDADE))
            .andExpect(jsonPath("$.estado").value(DEFAULT_ESTADO))
            .andExpect(jsonPath("$.situacao").value(DEFAULT_SITUACAO))
            .andExpect(jsonPath("$.tipoLesao").value(DEFAULT_TIPO_LESAO))
            .andExpect(jsonPath("$.detalhes").value(DEFAULT_DETALHES))
            .andExpect(jsonPath("$.imagemPerfilContentType").value(DEFAULT_IMAGEM_PERFIL_CONTENT_TYPE))
            .andExpect(jsonPath("$.imagemPerfil").value(Base64Utils.encodeToString(DEFAULT_IMAGEM_PERFIL)))
            .andExpect(jsonPath("$.imagemComDocContentType").value(DEFAULT_IMAGEM_COM_DOC_CONTENT_TYPE))
            .andExpect(jsonPath("$.imagemComDoc").value(Base64Utils.encodeToString(DEFAULT_IMAGEM_COM_DOC)))
            .andExpect(jsonPath("$.imagemLogoParceiroContentType").value(DEFAULT_IMAGEM_LOGO_PARCEIRO_CONTENT_TYPE))
            .andExpect(jsonPath("$.imagemLogoParceiro").value(Base64Utils.encodeToString(DEFAULT_IMAGEM_LOGO_PARCEIRO)))
            .andExpect(jsonPath("$.areaEmpresa").value(DEFAULT_AREA_EMPRESA))
            .andExpect(jsonPath("$.cnpj").value(DEFAULT_CNPJ))
            .andExpect(jsonPath("$.inscricaoEstadual").value(DEFAULT_INSCRICAO_ESTADUAL))
            .andExpect(jsonPath("$.tipoServico").value(DEFAULT_TIPO_SERVICO))
            .andExpect(jsonPath("$.razaoSocial").value(DEFAULT_RAZAO_SOCIAL))
            .andExpect(jsonPath("$.nomeFantasia").value(DEFAULT_NOME_FANTASIA))
            .andExpect(jsonPath("$.banco").value(DEFAULT_BANCO))
            .andExpect(jsonPath("$.bancoOutro").value(DEFAULT_BANCO_OUTRO))
            .andExpect(jsonPath("$.agencia").value(DEFAULT_AGENCIA))
            .andExpect(jsonPath("$.numeroConta").value(DEFAULT_NUMERO_CONTA))
            .andExpect(jsonPath("$.telefoneEmpresa").value(DEFAULT_TELEFONE_EMPRESA))
            .andExpect(jsonPath("$.emailEmpresa").value(DEFAULT_EMAIL_EMPRESA));
    }

    @Test
    @Transactional
    void getNonExistingInfoAdicional() throws Exception {
        // Get the infoAdicional
        restInfoAdicionalMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewInfoAdicional() throws Exception {
        // Initialize the database
        infoAdicionalRepository.saveAndFlush(infoAdicional);

        int databaseSizeBeforeUpdate = infoAdicionalRepository.findAll().size();

        // Update the infoAdicional
        InfoAdicional updatedInfoAdicional = infoAdicionalRepository.findById(infoAdicional.getId()).get();
        // Disconnect from session so that the updates on updatedInfoAdicional are not directly saved in db
        em.detach(updatedInfoAdicional);
        updatedInfoAdicional
            .nome(UPDATED_NOME)
            .sobrenome(UPDATED_SOBRENOME)
            .nascimento(UPDATED_NASCIMENTO)
            .telefoneCelular(UPDATED_TELEFONE_CELULAR)
            .doc(UPDATED_DOC)
            .cep(UPDATED_CEP)
            .logradouro(UPDATED_LOGRADOURO)
            .numero(UPDATED_NUMERO)
            .complemento(UPDATED_COMPLEMENTO)
            .bairro(UPDATED_BAIRRO)
            .cidade(UPDATED_CIDADE)
            .estado(UPDATED_ESTADO)
            .situacao(UPDATED_SITUACAO)
            .tipoLesao(UPDATED_TIPO_LESAO)
            .detalhes(UPDATED_DETALHES)
            .imagemPerfil(UPDATED_IMAGEM_PERFIL)
            .imagemPerfilContentType(UPDATED_IMAGEM_PERFIL_CONTENT_TYPE)
            .imagemComDoc(UPDATED_IMAGEM_COM_DOC)
            .imagemComDocContentType(UPDATED_IMAGEM_COM_DOC_CONTENT_TYPE)
            .imagemLogoParceiro(UPDATED_IMAGEM_LOGO_PARCEIRO)
            .imagemLogoParceiroContentType(UPDATED_IMAGEM_LOGO_PARCEIRO_CONTENT_TYPE)
            .areaEmpresa(UPDATED_AREA_EMPRESA)
            .cnpj(UPDATED_CNPJ)
            .inscricaoEstadual(UPDATED_INSCRICAO_ESTADUAL)
            .tipoServico(UPDATED_TIPO_SERVICO)
            .razaoSocial(UPDATED_RAZAO_SOCIAL)
            .nomeFantasia(UPDATED_NOME_FANTASIA)
            .banco(UPDATED_BANCO)
            .bancoOutro(UPDATED_BANCO_OUTRO)
            .agencia(UPDATED_AGENCIA)
            .numeroConta(UPDATED_NUMERO_CONTA)
            .telefoneEmpresa(UPDATED_TELEFONE_EMPRESA)
            .emailEmpresa(UPDATED_EMAIL_EMPRESA);

        restInfoAdicionalMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedInfoAdicional.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedInfoAdicional))
            )
            .andExpect(status().isOk());

        // Validate the InfoAdicional in the database
        List<InfoAdicional> infoAdicionalList = infoAdicionalRepository.findAll();
        assertThat(infoAdicionalList).hasSize(databaseSizeBeforeUpdate);
        InfoAdicional testInfoAdicional = infoAdicionalList.get(infoAdicionalList.size() - 1);
        assertThat(testInfoAdicional.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testInfoAdicional.getSobrenome()).isEqualTo(UPDATED_SOBRENOME);
        assertThat(testInfoAdicional.getNascimento()).isEqualTo(UPDATED_NASCIMENTO);
        assertThat(testInfoAdicional.getTelefoneCelular()).isEqualTo(UPDATED_TELEFONE_CELULAR);
        assertThat(testInfoAdicional.getDoc()).isEqualTo(UPDATED_DOC);
        assertThat(testInfoAdicional.getCep()).isEqualTo(UPDATED_CEP);
        assertThat(testInfoAdicional.getLogradouro()).isEqualTo(UPDATED_LOGRADOURO);
        assertThat(testInfoAdicional.getNumero()).isEqualTo(UPDATED_NUMERO);
        assertThat(testInfoAdicional.getComplemento()).isEqualTo(UPDATED_COMPLEMENTO);
        assertThat(testInfoAdicional.getBairro()).isEqualTo(UPDATED_BAIRRO);
        assertThat(testInfoAdicional.getCidade()).isEqualTo(UPDATED_CIDADE);
        assertThat(testInfoAdicional.getEstado()).isEqualTo(UPDATED_ESTADO);
        assertThat(testInfoAdicional.getSituacao()).isEqualTo(UPDATED_SITUACAO);
        assertThat(testInfoAdicional.getTipoLesao()).isEqualTo(UPDATED_TIPO_LESAO);
        assertThat(testInfoAdicional.getDetalhes()).isEqualTo(UPDATED_DETALHES);
        assertThat(testInfoAdicional.getImagemPerfil()).isEqualTo(UPDATED_IMAGEM_PERFIL);
        assertThat(testInfoAdicional.getImagemPerfilContentType()).isEqualTo(UPDATED_IMAGEM_PERFIL_CONTENT_TYPE);
        assertThat(testInfoAdicional.getImagemComDoc()).isEqualTo(UPDATED_IMAGEM_COM_DOC);
        assertThat(testInfoAdicional.getImagemComDocContentType()).isEqualTo(UPDATED_IMAGEM_COM_DOC_CONTENT_TYPE);
        assertThat(testInfoAdicional.getImagemLogoParceiro()).isEqualTo(UPDATED_IMAGEM_LOGO_PARCEIRO);
        assertThat(testInfoAdicional.getImagemLogoParceiroContentType()).isEqualTo(UPDATED_IMAGEM_LOGO_PARCEIRO_CONTENT_TYPE);
        assertThat(testInfoAdicional.getAreaEmpresa()).isEqualTo(UPDATED_AREA_EMPRESA);
        assertThat(testInfoAdicional.getCnpj()).isEqualTo(UPDATED_CNPJ);
        assertThat(testInfoAdicional.getInscricaoEstadual()).isEqualTo(UPDATED_INSCRICAO_ESTADUAL);
        assertThat(testInfoAdicional.getTipoServico()).isEqualTo(UPDATED_TIPO_SERVICO);
        assertThat(testInfoAdicional.getRazaoSocial()).isEqualTo(UPDATED_RAZAO_SOCIAL);
        assertThat(testInfoAdicional.getNomeFantasia()).isEqualTo(UPDATED_NOME_FANTASIA);
        assertThat(testInfoAdicional.getBanco()).isEqualTo(UPDATED_BANCO);
        assertThat(testInfoAdicional.getBancoOutro()).isEqualTo(UPDATED_BANCO_OUTRO);
        assertThat(testInfoAdicional.getAgencia()).isEqualTo(UPDATED_AGENCIA);
        assertThat(testInfoAdicional.getNumeroConta()).isEqualTo(UPDATED_NUMERO_CONTA);
        assertThat(testInfoAdicional.getTelefoneEmpresa()).isEqualTo(UPDATED_TELEFONE_EMPRESA);
        assertThat(testInfoAdicional.getEmailEmpresa()).isEqualTo(UPDATED_EMAIL_EMPRESA);
    }

    @Test
    @Transactional
    void putNonExistingInfoAdicional() throws Exception {
        int databaseSizeBeforeUpdate = infoAdicionalRepository.findAll().size();
        infoAdicional.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInfoAdicionalMockMvc
            .perform(
                put(ENTITY_API_URL_ID, infoAdicional.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(infoAdicional))
            )
            .andExpect(status().isBadRequest());

        // Validate the InfoAdicional in the database
        List<InfoAdicional> infoAdicionalList = infoAdicionalRepository.findAll();
        assertThat(infoAdicionalList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchInfoAdicional() throws Exception {
        int databaseSizeBeforeUpdate = infoAdicionalRepository.findAll().size();
        infoAdicional.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInfoAdicionalMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(infoAdicional))
            )
            .andExpect(status().isBadRequest());

        // Validate the InfoAdicional in the database
        List<InfoAdicional> infoAdicionalList = infoAdicionalRepository.findAll();
        assertThat(infoAdicionalList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamInfoAdicional() throws Exception {
        int databaseSizeBeforeUpdate = infoAdicionalRepository.findAll().size();
        infoAdicional.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInfoAdicionalMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(infoAdicional)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the InfoAdicional in the database
        List<InfoAdicional> infoAdicionalList = infoAdicionalRepository.findAll();
        assertThat(infoAdicionalList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateInfoAdicionalWithPatch() throws Exception {
        // Initialize the database
        infoAdicionalRepository.saveAndFlush(infoAdicional);

        int databaseSizeBeforeUpdate = infoAdicionalRepository.findAll().size();

        // Update the infoAdicional using partial update
        InfoAdicional partialUpdatedInfoAdicional = new InfoAdicional();
        partialUpdatedInfoAdicional.setId(infoAdicional.getId());

        partialUpdatedInfoAdicional
            .nome(UPDATED_NOME)
            .telefoneCelular(UPDATED_TELEFONE_CELULAR)
            .doc(UPDATED_DOC)
            .cep(UPDATED_CEP)
            .logradouro(UPDATED_LOGRADOURO)
            .numero(UPDATED_NUMERO)
            .complemento(UPDATED_COMPLEMENTO)
            .bairro(UPDATED_BAIRRO)
            .imagemPerfil(UPDATED_IMAGEM_PERFIL)
            .imagemPerfilContentType(UPDATED_IMAGEM_PERFIL_CONTENT_TYPE)
            .imagemComDoc(UPDATED_IMAGEM_COM_DOC)
            .imagemComDocContentType(UPDATED_IMAGEM_COM_DOC_CONTENT_TYPE)
            .imagemLogoParceiro(UPDATED_IMAGEM_LOGO_PARCEIRO)
            .imagemLogoParceiroContentType(UPDATED_IMAGEM_LOGO_PARCEIRO_CONTENT_TYPE)
            .areaEmpresa(UPDATED_AREA_EMPRESA)
            .inscricaoEstadual(UPDATED_INSCRICAO_ESTADUAL)
            .tipoServico(UPDATED_TIPO_SERVICO)
            .nomeFantasia(UPDATED_NOME_FANTASIA)
            .numeroConta(UPDATED_NUMERO_CONTA);

        restInfoAdicionalMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedInfoAdicional.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedInfoAdicional))
            )
            .andExpect(status().isOk());

        // Validate the InfoAdicional in the database
        List<InfoAdicional> infoAdicionalList = infoAdicionalRepository.findAll();
        assertThat(infoAdicionalList).hasSize(databaseSizeBeforeUpdate);
        InfoAdicional testInfoAdicional = infoAdicionalList.get(infoAdicionalList.size() - 1);
        assertThat(testInfoAdicional.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testInfoAdicional.getSobrenome()).isEqualTo(DEFAULT_SOBRENOME);
        assertThat(testInfoAdicional.getNascimento()).isEqualTo(DEFAULT_NASCIMENTO);
        assertThat(testInfoAdicional.getTelefoneCelular()).isEqualTo(UPDATED_TELEFONE_CELULAR);
        assertThat(testInfoAdicional.getDoc()).isEqualTo(UPDATED_DOC);
        assertThat(testInfoAdicional.getCep()).isEqualTo(UPDATED_CEP);
        assertThat(testInfoAdicional.getLogradouro()).isEqualTo(UPDATED_LOGRADOURO);
        assertThat(testInfoAdicional.getNumero()).isEqualTo(UPDATED_NUMERO);
        assertThat(testInfoAdicional.getComplemento()).isEqualTo(UPDATED_COMPLEMENTO);
        assertThat(testInfoAdicional.getBairro()).isEqualTo(UPDATED_BAIRRO);
        assertThat(testInfoAdicional.getCidade()).isEqualTo(DEFAULT_CIDADE);
        assertThat(testInfoAdicional.getEstado()).isEqualTo(DEFAULT_ESTADO);
        assertThat(testInfoAdicional.getSituacao()).isEqualTo(DEFAULT_SITUACAO);
        assertThat(testInfoAdicional.getTipoLesao()).isEqualTo(DEFAULT_TIPO_LESAO);
        assertThat(testInfoAdicional.getDetalhes()).isEqualTo(DEFAULT_DETALHES);
        assertThat(testInfoAdicional.getImagemPerfil()).isEqualTo(UPDATED_IMAGEM_PERFIL);
        assertThat(testInfoAdicional.getImagemPerfilContentType()).isEqualTo(UPDATED_IMAGEM_PERFIL_CONTENT_TYPE);
        assertThat(testInfoAdicional.getImagemComDoc()).isEqualTo(UPDATED_IMAGEM_COM_DOC);
        assertThat(testInfoAdicional.getImagemComDocContentType()).isEqualTo(UPDATED_IMAGEM_COM_DOC_CONTENT_TYPE);
        assertThat(testInfoAdicional.getImagemLogoParceiro()).isEqualTo(UPDATED_IMAGEM_LOGO_PARCEIRO);
        assertThat(testInfoAdicional.getImagemLogoParceiroContentType()).isEqualTo(UPDATED_IMAGEM_LOGO_PARCEIRO_CONTENT_TYPE);
        assertThat(testInfoAdicional.getAreaEmpresa()).isEqualTo(UPDATED_AREA_EMPRESA);
        assertThat(testInfoAdicional.getCnpj()).isEqualTo(DEFAULT_CNPJ);
        assertThat(testInfoAdicional.getInscricaoEstadual()).isEqualTo(UPDATED_INSCRICAO_ESTADUAL);
        assertThat(testInfoAdicional.getTipoServico()).isEqualTo(UPDATED_TIPO_SERVICO);
        assertThat(testInfoAdicional.getRazaoSocial()).isEqualTo(DEFAULT_RAZAO_SOCIAL);
        assertThat(testInfoAdicional.getNomeFantasia()).isEqualTo(UPDATED_NOME_FANTASIA);
        assertThat(testInfoAdicional.getBanco()).isEqualTo(DEFAULT_BANCO);
        assertThat(testInfoAdicional.getBancoOutro()).isEqualTo(DEFAULT_BANCO_OUTRO);
        assertThat(testInfoAdicional.getAgencia()).isEqualTo(DEFAULT_AGENCIA);
        assertThat(testInfoAdicional.getNumeroConta()).isEqualTo(UPDATED_NUMERO_CONTA);
        assertThat(testInfoAdicional.getTelefoneEmpresa()).isEqualTo(DEFAULT_TELEFONE_EMPRESA);
        assertThat(testInfoAdicional.getEmailEmpresa()).isEqualTo(DEFAULT_EMAIL_EMPRESA);
    }

    @Test
    @Transactional
    void fullUpdateInfoAdicionalWithPatch() throws Exception {
        // Initialize the database
        infoAdicionalRepository.saveAndFlush(infoAdicional);

        int databaseSizeBeforeUpdate = infoAdicionalRepository.findAll().size();

        // Update the infoAdicional using partial update
        InfoAdicional partialUpdatedInfoAdicional = new InfoAdicional();
        partialUpdatedInfoAdicional.setId(infoAdicional.getId());

        partialUpdatedInfoAdicional
            .nome(UPDATED_NOME)
            .sobrenome(UPDATED_SOBRENOME)
            .nascimento(UPDATED_NASCIMENTO)
            .telefoneCelular(UPDATED_TELEFONE_CELULAR)
            .doc(UPDATED_DOC)
            .cep(UPDATED_CEP)
            .logradouro(UPDATED_LOGRADOURO)
            .numero(UPDATED_NUMERO)
            .complemento(UPDATED_COMPLEMENTO)
            .bairro(UPDATED_BAIRRO)
            .cidade(UPDATED_CIDADE)
            .estado(UPDATED_ESTADO)
            .situacao(UPDATED_SITUACAO)
            .tipoLesao(UPDATED_TIPO_LESAO)
            .detalhes(UPDATED_DETALHES)
            .imagemPerfil(UPDATED_IMAGEM_PERFIL)
            .imagemPerfilContentType(UPDATED_IMAGEM_PERFIL_CONTENT_TYPE)
            .imagemComDoc(UPDATED_IMAGEM_COM_DOC)
            .imagemComDocContentType(UPDATED_IMAGEM_COM_DOC_CONTENT_TYPE)
            .imagemLogoParceiro(UPDATED_IMAGEM_LOGO_PARCEIRO)
            .imagemLogoParceiroContentType(UPDATED_IMAGEM_LOGO_PARCEIRO_CONTENT_TYPE)
            .areaEmpresa(UPDATED_AREA_EMPRESA)
            .cnpj(UPDATED_CNPJ)
            .inscricaoEstadual(UPDATED_INSCRICAO_ESTADUAL)
            .tipoServico(UPDATED_TIPO_SERVICO)
            .razaoSocial(UPDATED_RAZAO_SOCIAL)
            .nomeFantasia(UPDATED_NOME_FANTASIA)
            .banco(UPDATED_BANCO)
            .bancoOutro(UPDATED_BANCO_OUTRO)
            .agencia(UPDATED_AGENCIA)
            .numeroConta(UPDATED_NUMERO_CONTA)
            .telefoneEmpresa(UPDATED_TELEFONE_EMPRESA)
            .emailEmpresa(UPDATED_EMAIL_EMPRESA);

        restInfoAdicionalMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedInfoAdicional.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedInfoAdicional))
            )
            .andExpect(status().isOk());

        // Validate the InfoAdicional in the database
        List<InfoAdicional> infoAdicionalList = infoAdicionalRepository.findAll();
        assertThat(infoAdicionalList).hasSize(databaseSizeBeforeUpdate);
        InfoAdicional testInfoAdicional = infoAdicionalList.get(infoAdicionalList.size() - 1);
        assertThat(testInfoAdicional.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testInfoAdicional.getSobrenome()).isEqualTo(UPDATED_SOBRENOME);
        assertThat(testInfoAdicional.getNascimento()).isEqualTo(UPDATED_NASCIMENTO);
        assertThat(testInfoAdicional.getTelefoneCelular()).isEqualTo(UPDATED_TELEFONE_CELULAR);
        assertThat(testInfoAdicional.getDoc()).isEqualTo(UPDATED_DOC);
        assertThat(testInfoAdicional.getCep()).isEqualTo(UPDATED_CEP);
        assertThat(testInfoAdicional.getLogradouro()).isEqualTo(UPDATED_LOGRADOURO);
        assertThat(testInfoAdicional.getNumero()).isEqualTo(UPDATED_NUMERO);
        assertThat(testInfoAdicional.getComplemento()).isEqualTo(UPDATED_COMPLEMENTO);
        assertThat(testInfoAdicional.getBairro()).isEqualTo(UPDATED_BAIRRO);
        assertThat(testInfoAdicional.getCidade()).isEqualTo(UPDATED_CIDADE);
        assertThat(testInfoAdicional.getEstado()).isEqualTo(UPDATED_ESTADO);
        assertThat(testInfoAdicional.getSituacao()).isEqualTo(UPDATED_SITUACAO);
        assertThat(testInfoAdicional.getTipoLesao()).isEqualTo(UPDATED_TIPO_LESAO);
        assertThat(testInfoAdicional.getDetalhes()).isEqualTo(UPDATED_DETALHES);
        assertThat(testInfoAdicional.getImagemPerfil()).isEqualTo(UPDATED_IMAGEM_PERFIL);
        assertThat(testInfoAdicional.getImagemPerfilContentType()).isEqualTo(UPDATED_IMAGEM_PERFIL_CONTENT_TYPE);
        assertThat(testInfoAdicional.getImagemComDoc()).isEqualTo(UPDATED_IMAGEM_COM_DOC);
        assertThat(testInfoAdicional.getImagemComDocContentType()).isEqualTo(UPDATED_IMAGEM_COM_DOC_CONTENT_TYPE);
        assertThat(testInfoAdicional.getImagemLogoParceiro()).isEqualTo(UPDATED_IMAGEM_LOGO_PARCEIRO);
        assertThat(testInfoAdicional.getImagemLogoParceiroContentType()).isEqualTo(UPDATED_IMAGEM_LOGO_PARCEIRO_CONTENT_TYPE);
        assertThat(testInfoAdicional.getAreaEmpresa()).isEqualTo(UPDATED_AREA_EMPRESA);
        assertThat(testInfoAdicional.getCnpj()).isEqualTo(UPDATED_CNPJ);
        assertThat(testInfoAdicional.getInscricaoEstadual()).isEqualTo(UPDATED_INSCRICAO_ESTADUAL);
        assertThat(testInfoAdicional.getTipoServico()).isEqualTo(UPDATED_TIPO_SERVICO);
        assertThat(testInfoAdicional.getRazaoSocial()).isEqualTo(UPDATED_RAZAO_SOCIAL);
        assertThat(testInfoAdicional.getNomeFantasia()).isEqualTo(UPDATED_NOME_FANTASIA);
        assertThat(testInfoAdicional.getBanco()).isEqualTo(UPDATED_BANCO);
        assertThat(testInfoAdicional.getBancoOutro()).isEqualTo(UPDATED_BANCO_OUTRO);
        assertThat(testInfoAdicional.getAgencia()).isEqualTo(UPDATED_AGENCIA);
        assertThat(testInfoAdicional.getNumeroConta()).isEqualTo(UPDATED_NUMERO_CONTA);
        assertThat(testInfoAdicional.getTelefoneEmpresa()).isEqualTo(UPDATED_TELEFONE_EMPRESA);
        assertThat(testInfoAdicional.getEmailEmpresa()).isEqualTo(UPDATED_EMAIL_EMPRESA);
    }

    @Test
    @Transactional
    void patchNonExistingInfoAdicional() throws Exception {
        int databaseSizeBeforeUpdate = infoAdicionalRepository.findAll().size();
        infoAdicional.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInfoAdicionalMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, infoAdicional.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(infoAdicional))
            )
            .andExpect(status().isBadRequest());

        // Validate the InfoAdicional in the database
        List<InfoAdicional> infoAdicionalList = infoAdicionalRepository.findAll();
        assertThat(infoAdicionalList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchInfoAdicional() throws Exception {
        int databaseSizeBeforeUpdate = infoAdicionalRepository.findAll().size();
        infoAdicional.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInfoAdicionalMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(infoAdicional))
            )
            .andExpect(status().isBadRequest());

        // Validate the InfoAdicional in the database
        List<InfoAdicional> infoAdicionalList = infoAdicionalRepository.findAll();
        assertThat(infoAdicionalList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamInfoAdicional() throws Exception {
        int databaseSizeBeforeUpdate = infoAdicionalRepository.findAll().size();
        infoAdicional.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInfoAdicionalMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(infoAdicional))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the InfoAdicional in the database
        List<InfoAdicional> infoAdicionalList = infoAdicionalRepository.findAll();
        assertThat(infoAdicionalList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteInfoAdicional() throws Exception {
        // Initialize the database
        infoAdicionalRepository.saveAndFlush(infoAdicional);

        int databaseSizeBeforeDelete = infoAdicionalRepository.findAll().size();

        // Delete the infoAdicional
        restInfoAdicionalMockMvc
            .perform(delete(ENTITY_API_URL_ID, infoAdicional.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<InfoAdicional> infoAdicionalList = infoAdicionalRepository.findAll();
        assertThat(infoAdicionalList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
