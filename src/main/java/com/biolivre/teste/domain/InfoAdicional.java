package com.biolivre.teste.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A InfoAdicional.
 */
@Entity
@Table(name = "info_adicional")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class InfoAdicional implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "sobrenome")
    private String sobrenome;

    @Column(name = "nascimento")
    private String nascimento;

    @Column(name = "telefone_celular")
    private String telefoneCelular;

    @Column(name = "doc")
    private String doc;

    @Column(name = "cep")
    private String cep;

    @Column(name = "logradouro")
    private String logradouro;

    @Column(name = "numero")
    private String numero;

    @Column(name = "complemento")
    private String complemento;

    @Column(name = "bairro")
    private String bairro;

    @Column(name = "cidade")
    private String cidade;

    @Column(name = "estado")
    private String estado;

    @Column(name = "situacao")
    private String situacao;

    @Column(name = "tipo_lesao")
    private String tipoLesao;

    @Column(name = "detalhes")
    private String detalhes;

    @Lob
    @Column(name = "imagem_perfil")
    private byte[] imagemPerfil;

    @Column(name = "imagem_perfil_content_type")
    private String imagemPerfilContentType;

    @Lob
    @Column(name = "imagem_com_doc")
    private byte[] imagemComDoc;

    @Column(name = "imagem_com_doc_content_type")
    private String imagemComDocContentType;

    @Lob
    @Column(name = "imagem_logo_parceiro")
    private byte[] imagemLogoParceiro;

    @Column(name = "imagem_logo_parceiro_content_type")
    private String imagemLogoParceiroContentType;

    @Column(name = "area_empresa")
    private String areaEmpresa;

    @Column(name = "cnpj")
    private String cnpj;

    @Column(name = "inscricao_estadual")
    private String inscricaoEstadual;

    @Column(name = "tipo_servico")
    private String tipoServico;

    @Column(name = "razao_social")
    private String razaoSocial;

    @Column(name = "nome_fantasia")
    private String nomeFantasia;

    @Column(name = "banco")
    private String banco;

    @Column(name = "banco_outro")
    private String bancoOutro;

    @Column(name = "agencia")
    private String agencia;

    @Column(name = "numero_conta")
    private String numeroConta;

    @Column(name = "telefone_empresa")
    private String telefoneEmpresa;

    @Column(name = "email_empresa")
    private String emailEmpresa;

    @ManyToOne
    private User user;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public InfoAdicional id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public InfoAdicional nome(String nome) {
        this.setNome(nome);
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return this.sobrenome;
    }

    public InfoAdicional sobrenome(String sobrenome) {
        this.setSobrenome(sobrenome);
        return this;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getNascimento() {
        return this.nascimento;
    }

    public InfoAdicional nascimento(String nascimento) {
        this.setNascimento(nascimento);
        return this;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

    public String getTelefoneCelular() {
        return this.telefoneCelular;
    }

    public InfoAdicional telefoneCelular(String telefoneCelular) {
        this.setTelefoneCelular(telefoneCelular);
        return this;
    }

    public void setTelefoneCelular(String telefoneCelular) {
        this.telefoneCelular = telefoneCelular;
    }

    public String getDoc() {
        return this.doc;
    }

    public InfoAdicional doc(String doc) {
        this.setDoc(doc);
        return this;
    }

    public void setDoc(String doc) {
        this.doc = doc;
    }

    public String getCep() {
        return this.cep;
    }

    public InfoAdicional cep(String cep) {
        this.setCep(cep);
        return this;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return this.logradouro;
    }

    public InfoAdicional logradouro(String logradouro) {
        this.setLogradouro(logradouro);
        return this;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return this.numero;
    }

    public InfoAdicional numero(String numero) {
        this.setNumero(numero);
        return this;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return this.complemento;
    }

    public InfoAdicional complemento(String complemento) {
        this.setComplemento(complemento);
        return this;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return this.bairro;
    }

    public InfoAdicional bairro(String bairro) {
        this.setBairro(bairro);
        return this;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return this.cidade;
    }

    public InfoAdicional cidade(String cidade) {
        this.setCidade(cidade);
        return this;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return this.estado;
    }

    public InfoAdicional estado(String estado) {
        this.setEstado(estado);
        return this;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getSituacao() {
        return this.situacao;
    }

    public InfoAdicional situacao(String situacao) {
        this.setSituacao(situacao);
        return this;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getTipoLesao() {
        return this.tipoLesao;
    }

    public InfoAdicional tipoLesao(String tipoLesao) {
        this.setTipoLesao(tipoLesao);
        return this;
    }

    public void setTipoLesao(String tipoLesao) {
        this.tipoLesao = tipoLesao;
    }

    public String getDetalhes() {
        return this.detalhes;
    }

    public InfoAdicional detalhes(String detalhes) {
        this.setDetalhes(detalhes);
        return this;
    }

    public void setDetalhes(String detalhes) {
        this.detalhes = detalhes;
    }

    public byte[] getImagemPerfil() {
        return this.imagemPerfil;
    }

    public InfoAdicional imagemPerfil(byte[] imagemPerfil) {
        this.setImagemPerfil(imagemPerfil);
        return this;
    }

    public void setImagemPerfil(byte[] imagemPerfil) {
        this.imagemPerfil = imagemPerfil;
    }

    public String getImagemPerfilContentType() {
        return this.imagemPerfilContentType;
    }

    public InfoAdicional imagemPerfilContentType(String imagemPerfilContentType) {
        this.imagemPerfilContentType = imagemPerfilContentType;
        return this;
    }

    public void setImagemPerfilContentType(String imagemPerfilContentType) {
        this.imagemPerfilContentType = imagemPerfilContentType;
    }

    public byte[] getImagemComDoc() {
        return this.imagemComDoc;
    }

    public InfoAdicional imagemComDoc(byte[] imagemComDoc) {
        this.setImagemComDoc(imagemComDoc);
        return this;
    }

    public void setImagemComDoc(byte[] imagemComDoc) {
        this.imagemComDoc = imagemComDoc;
    }

    public String getImagemComDocContentType() {
        return this.imagemComDocContentType;
    }

    public InfoAdicional imagemComDocContentType(String imagemComDocContentType) {
        this.imagemComDocContentType = imagemComDocContentType;
        return this;
    }

    public void setImagemComDocContentType(String imagemComDocContentType) {
        this.imagemComDocContentType = imagemComDocContentType;
    }

    public byte[] getImagemLogoParceiro() {
        return this.imagemLogoParceiro;
    }

    public InfoAdicional imagemLogoParceiro(byte[] imagemLogoParceiro) {
        this.setImagemLogoParceiro(imagemLogoParceiro);
        return this;
    }

    public void setImagemLogoParceiro(byte[] imagemLogoParceiro) {
        this.imagemLogoParceiro = imagemLogoParceiro;
    }

    public String getImagemLogoParceiroContentType() {
        return this.imagemLogoParceiroContentType;
    }

    public InfoAdicional imagemLogoParceiroContentType(String imagemLogoParceiroContentType) {
        this.imagemLogoParceiroContentType = imagemLogoParceiroContentType;
        return this;
    }

    public void setImagemLogoParceiroContentType(String imagemLogoParceiroContentType) {
        this.imagemLogoParceiroContentType = imagemLogoParceiroContentType;
    }

    public String getAreaEmpresa() {
        return this.areaEmpresa;
    }

    public InfoAdicional areaEmpresa(String areaEmpresa) {
        this.setAreaEmpresa(areaEmpresa);
        return this;
    }

    public void setAreaEmpresa(String areaEmpresa) {
        this.areaEmpresa = areaEmpresa;
    }

    public String getCnpj() {
        return this.cnpj;
    }

    public InfoAdicional cnpj(String cnpj) {
        this.setCnpj(cnpj);
        return this;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getInscricaoEstadual() {
        return this.inscricaoEstadual;
    }

    public InfoAdicional inscricaoEstadual(String inscricaoEstadual) {
        this.setInscricaoEstadual(inscricaoEstadual);
        return this;
    }

    public void setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }

    public String getTipoServico() {
        return this.tipoServico;
    }

    public InfoAdicional tipoServico(String tipoServico) {
        this.setTipoServico(tipoServico);
        return this;
    }

    public void setTipoServico(String tipoServico) {
        this.tipoServico = tipoServico;
    }

    public String getRazaoSocial() {
        return this.razaoSocial;
    }

    public InfoAdicional razaoSocial(String razaoSocial) {
        this.setRazaoSocial(razaoSocial);
        return this;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getNomeFantasia() {
        return this.nomeFantasia;
    }

    public InfoAdicional nomeFantasia(String nomeFantasia) {
        this.setNomeFantasia(nomeFantasia);
        return this;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getBanco() {
        return this.banco;
    }

    public InfoAdicional banco(String banco) {
        this.setBanco(banco);
        return this;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getBancoOutro() {
        return this.bancoOutro;
    }

    public InfoAdicional bancoOutro(String bancoOutro) {
        this.setBancoOutro(bancoOutro);
        return this;
    }

    public void setBancoOutro(String bancoOutro) {
        this.bancoOutro = bancoOutro;
    }

    public String getAgencia() {
        return this.agencia;
    }

    public InfoAdicional agencia(String agencia) {
        this.setAgencia(agencia);
        return this;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getNumeroConta() {
        return this.numeroConta;
    }

    public InfoAdicional numeroConta(String numeroConta) {
        this.setNumeroConta(numeroConta);
        return this;
    }

    public void setNumeroConta(String numeroConta) {
        this.numeroConta = numeroConta;
    }

    public String getTelefoneEmpresa() {
        return this.telefoneEmpresa;
    }

    public InfoAdicional telefoneEmpresa(String telefoneEmpresa) {
        this.setTelefoneEmpresa(telefoneEmpresa);
        return this;
    }

    public void setTelefoneEmpresa(String telefoneEmpresa) {
        this.telefoneEmpresa = telefoneEmpresa;
    }

    public String getEmailEmpresa() {
        return this.emailEmpresa;
    }

    public InfoAdicional emailEmpresa(String emailEmpresa) {
        this.setEmailEmpresa(emailEmpresa);
        return this;
    }

    public void setEmailEmpresa(String emailEmpresa) {
        this.emailEmpresa = emailEmpresa;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public InfoAdicional user(User user) {
        this.setUser(user);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InfoAdicional)) {
            return false;
        }
        return id != null && id.equals(((InfoAdicional) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InfoAdicional{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", sobrenome='" + getSobrenome() + "'" +
            ", nascimento='" + getNascimento() + "'" +
            ", telefoneCelular='" + getTelefoneCelular() + "'" +
            ", doc='" + getDoc() + "'" +
            ", cep='" + getCep() + "'" +
            ", logradouro='" + getLogradouro() + "'" +
            ", numero='" + getNumero() + "'" +
            ", complemento='" + getComplemento() + "'" +
            ", bairro='" + getBairro() + "'" +
            ", cidade='" + getCidade() + "'" +
            ", estado='" + getEstado() + "'" +
            ", situacao='" + getSituacao() + "'" +
            ", tipoLesao='" + getTipoLesao() + "'" +
            ", detalhes='" + getDetalhes() + "'" +
            ", imagemPerfil='" + getImagemPerfil() + "'" +
            ", imagemPerfilContentType='" + getImagemPerfilContentType() + "'" +
            ", imagemComDoc='" + getImagemComDoc() + "'" +
            ", imagemComDocContentType='" + getImagemComDocContentType() + "'" +
            ", imagemLogoParceiro='" + getImagemLogoParceiro() + "'" +
            ", imagemLogoParceiroContentType='" + getImagemLogoParceiroContentType() + "'" +
            ", areaEmpresa='" + getAreaEmpresa() + "'" +
            ", cnpj='" + getCnpj() + "'" +
            ", inscricaoEstadual='" + getInscricaoEstadual() + "'" +
            ", tipoServico='" + getTipoServico() + "'" +
            ", razaoSocial='" + getRazaoSocial() + "'" +
            ", nomeFantasia='" + getNomeFantasia() + "'" +
            ", banco='" + getBanco() + "'" +
            ", bancoOutro='" + getBancoOutro() + "'" +
            ", agencia='" + getAgencia() + "'" +
            ", numeroConta='" + getNumeroConta() + "'" +
            ", telefoneEmpresa='" + getTelefoneEmpresa() + "'" +
            ", emailEmpresa='" + getEmailEmpresa() + "'" +
            "}";
    }
}
