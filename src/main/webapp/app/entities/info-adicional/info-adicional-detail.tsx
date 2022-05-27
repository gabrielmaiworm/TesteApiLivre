import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { openFile, byteSize } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './info-adicional.reducer';

export const InfoAdicionalDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const infoAdicionalEntity = useAppSelector(state => state.infoAdicional.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="infoAdicionalDetailsHeading">InfoAdicional</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{infoAdicionalEntity.id}</dd>
          <dt>
            <span id="nome">Nome</span>
          </dt>
          <dd>{infoAdicionalEntity.nome}</dd>
          <dt>
            <span id="sobrenome">Sobrenome</span>
          </dt>
          <dd>{infoAdicionalEntity.sobrenome}</dd>
          <dt>
            <span id="nascimento">Nascimento</span>
          </dt>
          <dd>{infoAdicionalEntity.nascimento}</dd>
          <dt>
            <span id="telefoneCelular">Telefone Celular</span>
          </dt>
          <dd>{infoAdicionalEntity.telefoneCelular}</dd>
          <dt>
            <span id="doc">Doc</span>
          </dt>
          <dd>{infoAdicionalEntity.doc}</dd>
          <dt>
            <span id="cep">Cep</span>
          </dt>
          <dd>{infoAdicionalEntity.cep}</dd>
          <dt>
            <span id="logradouro">Logradouro</span>
          </dt>
          <dd>{infoAdicionalEntity.logradouro}</dd>
          <dt>
            <span id="numero">Numero</span>
          </dt>
          <dd>{infoAdicionalEntity.numero}</dd>
          <dt>
            <span id="complemento">Complemento</span>
          </dt>
          <dd>{infoAdicionalEntity.complemento}</dd>
          <dt>
            <span id="bairro">Bairro</span>
          </dt>
          <dd>{infoAdicionalEntity.bairro}</dd>
          <dt>
            <span id="cidade">Cidade</span>
          </dt>
          <dd>{infoAdicionalEntity.cidade}</dd>
          <dt>
            <span id="estado">Estado</span>
          </dt>
          <dd>{infoAdicionalEntity.estado}</dd>
          <dt>
            <span id="situacao">Situacao</span>
          </dt>
          <dd>{infoAdicionalEntity.situacao}</dd>
          <dt>
            <span id="tipoLesao">Tipo Lesao</span>
          </dt>
          <dd>{infoAdicionalEntity.tipoLesao}</dd>
          <dt>
            <span id="detalhes">Detalhes</span>
          </dt>
          <dd>{infoAdicionalEntity.detalhes}</dd>
          <dt>
            <span id="imagemPerfil">Imagem Perfil</span>
          </dt>
          <dd>
            {infoAdicionalEntity.imagemPerfil ? (
              <div>
                {infoAdicionalEntity.imagemPerfilContentType ? (
                  <a onClick={openFile(infoAdicionalEntity.imagemPerfilContentType, infoAdicionalEntity.imagemPerfil)}>
                    <img
                      src={`data:${infoAdicionalEntity.imagemPerfilContentType};base64,${infoAdicionalEntity.imagemPerfil}`}
                      style={{ maxHeight: '30px' }}
                    />
                  </a>
                ) : null}
                <span>
                  {infoAdicionalEntity.imagemPerfilContentType}, {byteSize(infoAdicionalEntity.imagemPerfil)}
                </span>
              </div>
            ) : null}
          </dd>
          <dt>
            <span id="imagemComDoc">Imagem Com Doc</span>
          </dt>
          <dd>
            {infoAdicionalEntity.imagemComDoc ? (
              <div>
                {infoAdicionalEntity.imagemComDocContentType ? (
                  <a onClick={openFile(infoAdicionalEntity.imagemComDocContentType, infoAdicionalEntity.imagemComDoc)}>
                    <img
                      src={`data:${infoAdicionalEntity.imagemComDocContentType};base64,${infoAdicionalEntity.imagemComDoc}`}
                      style={{ maxHeight: '30px' }}
                    />
                  </a>
                ) : null}
                <span>
                  {infoAdicionalEntity.imagemComDocContentType}, {byteSize(infoAdicionalEntity.imagemComDoc)}
                </span>
              </div>
            ) : null}
          </dd>
          <dt>
            <span id="imagemLogoParceiro">Imagem Logo Parceiro</span>
          </dt>
          <dd>
            {infoAdicionalEntity.imagemLogoParceiro ? (
              <div>
                {infoAdicionalEntity.imagemLogoParceiroContentType ? (
                  <a onClick={openFile(infoAdicionalEntity.imagemLogoParceiroContentType, infoAdicionalEntity.imagemLogoParceiro)}>
                    <img
                      src={`data:${infoAdicionalEntity.imagemLogoParceiroContentType};base64,${infoAdicionalEntity.imagemLogoParceiro}`}
                      style={{ maxHeight: '30px' }}
                    />
                  </a>
                ) : null}
                <span>
                  {infoAdicionalEntity.imagemLogoParceiroContentType}, {byteSize(infoAdicionalEntity.imagemLogoParceiro)}
                </span>
              </div>
            ) : null}
          </dd>
          <dt>
            <span id="areaEmpresa">Area Empresa</span>
          </dt>
          <dd>{infoAdicionalEntity.areaEmpresa}</dd>
          <dt>
            <span id="cnpj">Cnpj</span>
          </dt>
          <dd>{infoAdicionalEntity.cnpj}</dd>
          <dt>
            <span id="inscricaoEstadual">Inscricao Estadual</span>
          </dt>
          <dd>{infoAdicionalEntity.inscricaoEstadual}</dd>
          <dt>
            <span id="tipoServico">Tipo Servico</span>
          </dt>
          <dd>{infoAdicionalEntity.tipoServico}</dd>
          <dt>
            <span id="razaoSocial">Razao Social</span>
          </dt>
          <dd>{infoAdicionalEntity.razaoSocial}</dd>
          <dt>
            <span id="nomeFantasia">Nome Fantasia</span>
          </dt>
          <dd>{infoAdicionalEntity.nomeFantasia}</dd>
          <dt>
            <span id="banco">Banco</span>
          </dt>
          <dd>{infoAdicionalEntity.banco}</dd>
          <dt>
            <span id="bancoOutro">Banco Outro</span>
          </dt>
          <dd>{infoAdicionalEntity.bancoOutro}</dd>
          <dt>
            <span id="agencia">Agencia</span>
          </dt>
          <dd>{infoAdicionalEntity.agencia}</dd>
          <dt>
            <span id="numeroConta">Numero Conta</span>
          </dt>
          <dd>{infoAdicionalEntity.numeroConta}</dd>
          <dt>
            <span id="telefoneEmpresa">Telefone Empresa</span>
          </dt>
          <dd>{infoAdicionalEntity.telefoneEmpresa}</dd>
          <dt>
            <span id="emailEmpresa">Email Empresa</span>
          </dt>
          <dd>{infoAdicionalEntity.emailEmpresa}</dd>
          <dt>User</dt>
          <dd>{infoAdicionalEntity.user ? infoAdicionalEntity.user.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/info-adicional" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/info-adicional/${infoAdicionalEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

export default InfoAdicionalDetail;
