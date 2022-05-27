import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { openFile, byteSize, Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IInfoAdicional } from 'app/shared/model/info-adicional.model';
import { getEntities } from './info-adicional.reducer';

export const InfoAdicional = (props: RouteComponentProps<{ url: string }>) => {
  const dispatch = useAppDispatch();

  const infoAdicionalList = useAppSelector(state => state.infoAdicional.entities);
  const loading = useAppSelector(state => state.infoAdicional.loading);

  useEffect(() => {
    dispatch(getEntities({}));
  }, []);

  const handleSyncList = () => {
    dispatch(getEntities({}));
  };

  const { match } = props;

  return (
    <div>
      <h2 id="info-adicional-heading" data-cy="InfoAdicionalHeading">
        Info Adicionals
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} /> Refresh List
          </Button>
          <Link to="/info-adicional/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create new Info Adicional
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {infoAdicionalList && infoAdicionalList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>ID</th>
                <th>Nome</th>
                <th>Sobrenome</th>
                <th>Nascimento</th>
                <th>Telefone Celular</th>
                <th>Doc</th>
                <th>Cep</th>
                <th>Logradouro</th>
                <th>Numero</th>
                <th>Complemento</th>
                <th>Bairro</th>
                <th>Cidade</th>
                <th>Estado</th>
                <th>Situacao</th>
                <th>Tipo Lesao</th>
                <th>Detalhes</th>
                <th>Imagem Perfil</th>
                <th>Imagem Com Doc</th>
                <th>Imagem Logo Parceiro</th>
                <th>Area Empresa</th>
                <th>Cnpj</th>
                <th>Inscricao Estadual</th>
                <th>Tipo Servico</th>
                <th>Razao Social</th>
                <th>Nome Fantasia</th>
                <th>Banco</th>
                <th>Banco Outro</th>
                <th>Agencia</th>
                <th>Numero Conta</th>
                <th>Telefone Empresa</th>
                <th>Email Empresa</th>
                <th>User</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {infoAdicionalList.map((infoAdicional, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/info-adicional/${infoAdicional.id}`} color="link" size="sm">
                      {infoAdicional.id}
                    </Button>
                  </td>
                  <td>{infoAdicional.nome}</td>
                  <td>{infoAdicional.sobrenome}</td>
                  <td>{infoAdicional.nascimento}</td>
                  <td>{infoAdicional.telefoneCelular}</td>
                  <td>{infoAdicional.doc}</td>
                  <td>{infoAdicional.cep}</td>
                  <td>{infoAdicional.logradouro}</td>
                  <td>{infoAdicional.numero}</td>
                  <td>{infoAdicional.complemento}</td>
                  <td>{infoAdicional.bairro}</td>
                  <td>{infoAdicional.cidade}</td>
                  <td>{infoAdicional.estado}</td>
                  <td>{infoAdicional.situacao}</td>
                  <td>{infoAdicional.tipoLesao}</td>
                  <td>{infoAdicional.detalhes}</td>
                  <td>
                    {infoAdicional.imagemPerfil ? (
                      <div>
                        {infoAdicional.imagemPerfilContentType ? (
                          <a onClick={openFile(infoAdicional.imagemPerfilContentType, infoAdicional.imagemPerfil)}>
                            <img
                              src={`data:${infoAdicional.imagemPerfilContentType};base64,${infoAdicional.imagemPerfil}`}
                              style={{ maxHeight: '30px' }}
                            />
                            &nbsp;
                          </a>
                        ) : null}
                        <span>
                          {infoAdicional.imagemPerfilContentType}, {byteSize(infoAdicional.imagemPerfil)}
                        </span>
                      </div>
                    ) : null}
                  </td>
                  <td>
                    {infoAdicional.imagemComDoc ? (
                      <div>
                        {infoAdicional.imagemComDocContentType ? (
                          <a onClick={openFile(infoAdicional.imagemComDocContentType, infoAdicional.imagemComDoc)}>
                            <img
                              src={`data:${infoAdicional.imagemComDocContentType};base64,${infoAdicional.imagemComDoc}`}
                              style={{ maxHeight: '30px' }}
                            />
                            &nbsp;
                          </a>
                        ) : null}
                        <span>
                          {infoAdicional.imagemComDocContentType}, {byteSize(infoAdicional.imagemComDoc)}
                        </span>
                      </div>
                    ) : null}
                  </td>
                  <td>
                    {infoAdicional.imagemLogoParceiro ? (
                      <div>
                        {infoAdicional.imagemLogoParceiroContentType ? (
                          <a onClick={openFile(infoAdicional.imagemLogoParceiroContentType, infoAdicional.imagemLogoParceiro)}>
                            <img
                              src={`data:${infoAdicional.imagemLogoParceiroContentType};base64,${infoAdicional.imagemLogoParceiro}`}
                              style={{ maxHeight: '30px' }}
                            />
                            &nbsp;
                          </a>
                        ) : null}
                        <span>
                          {infoAdicional.imagemLogoParceiroContentType}, {byteSize(infoAdicional.imagemLogoParceiro)}
                        </span>
                      </div>
                    ) : null}
                  </td>
                  <td>{infoAdicional.areaEmpresa}</td>
                  <td>{infoAdicional.cnpj}</td>
                  <td>{infoAdicional.inscricaoEstadual}</td>
                  <td>{infoAdicional.tipoServico}</td>
                  <td>{infoAdicional.razaoSocial}</td>
                  <td>{infoAdicional.nomeFantasia}</td>
                  <td>{infoAdicional.banco}</td>
                  <td>{infoAdicional.bancoOutro}</td>
                  <td>{infoAdicional.agencia}</td>
                  <td>{infoAdicional.numeroConta}</td>
                  <td>{infoAdicional.telefoneEmpresa}</td>
                  <td>{infoAdicional.emailEmpresa}</td>
                  <td>{infoAdicional.user ? infoAdicional.user.id : ''}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/info-adicional/${infoAdicional.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/info-adicional/${infoAdicional.id}/edit`}
                        color="primary"
                        size="sm"
                        data-cy="entityEditButton"
                      >
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/info-adicional/${infoAdicional.id}/delete`}
                        color="danger"
                        size="sm"
                        data-cy="entityDeleteButton"
                      >
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && <div className="alert alert-warning">No Info Adicionals found</div>
        )}
      </div>
    </div>
  );
};

export default InfoAdicional;
