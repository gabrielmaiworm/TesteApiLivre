import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, ValidatedField, ValidatedForm, ValidatedBlobField } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IUser } from 'app/shared/model/user.model';
import { getUsers } from 'app/modules/administration/user-management/user-management.reducer';
import { IInfoAdicional } from 'app/shared/model/info-adicional.model';
import { getEntity, updateEntity, createEntity, reset } from './info-adicional.reducer';

export const InfoAdicionalUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const users = useAppSelector(state => state.userManagement.users);
  const infoAdicionalEntity = useAppSelector(state => state.infoAdicional.entity);
  const loading = useAppSelector(state => state.infoAdicional.loading);
  const updating = useAppSelector(state => state.infoAdicional.updating);
  const updateSuccess = useAppSelector(state => state.infoAdicional.updateSuccess);
  const handleClose = () => {
    props.history.push('/info-adicional');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(props.match.params.id));
    }

    dispatch(getUsers({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...infoAdicionalEntity,
      ...values,
      user: users.find(it => it.id.toString() === values.user.toString()),
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {}
      : {
          ...infoAdicionalEntity,
          user: infoAdicionalEntity?.user?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="testeApiLivreApp.infoAdicional.home.createOrEditLabel" data-cy="InfoAdicionalCreateUpdateHeading">
            Create or edit a InfoAdicional
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? (
                <ValidatedField name="id" required readOnly id="info-adicional-id" label="ID" validate={{ required: true }} />
              ) : null}
              <ValidatedField label="Nome" id="info-adicional-nome" name="nome" data-cy="nome" type="text" />
              <ValidatedField label="Sobrenome" id="info-adicional-sobrenome" name="sobrenome" data-cy="sobrenome" type="text" />
              <ValidatedField label="Nascimento" id="info-adicional-nascimento" name="nascimento" data-cy="nascimento" type="text" />
              <ValidatedField
                label="Telefone Celular"
                id="info-adicional-telefoneCelular"
                name="telefoneCelular"
                data-cy="telefoneCelular"
                type="text"
              />
              <ValidatedField label="Doc" id="info-adicional-doc" name="doc" data-cy="doc" type="text" />
              <ValidatedField label="Cep" id="info-adicional-cep" name="cep" data-cy="cep" type="text" />
              <ValidatedField label="Logradouro" id="info-adicional-logradouro" name="logradouro" data-cy="logradouro" type="text" />
              <ValidatedField label="Numero" id="info-adicional-numero" name="numero" data-cy="numero" type="text" />
              <ValidatedField label="Complemento" id="info-adicional-complemento" name="complemento" data-cy="complemento" type="text" />
              <ValidatedField label="Bairro" id="info-adicional-bairro" name="bairro" data-cy="bairro" type="text" />
              <ValidatedField label="Cidade" id="info-adicional-cidade" name="cidade" data-cy="cidade" type="text" />
              <ValidatedField label="Estado" id="info-adicional-estado" name="estado" data-cy="estado" type="text" />
              <ValidatedField label="Situacao" id="info-adicional-situacao" name="situacao" data-cy="situacao" type="text" />
              <ValidatedField label="Tipo Lesao" id="info-adicional-tipoLesao" name="tipoLesao" data-cy="tipoLesao" type="text" />
              <ValidatedField label="Detalhes" id="info-adicional-detalhes" name="detalhes" data-cy="detalhes" type="text" />
              <ValidatedBlobField
                label="Imagem Perfil"
                id="info-adicional-imagemPerfil"
                name="imagemPerfil"
                data-cy="imagemPerfil"
                isImage
                accept="image/*"
              />
              <ValidatedBlobField
                label="Imagem Com Doc"
                id="info-adicional-imagemComDoc"
                name="imagemComDoc"
                data-cy="imagemComDoc"
                isImage
                accept="image/*"
              />
              <ValidatedBlobField
                label="Imagem Logo Parceiro"
                id="info-adicional-imagemLogoParceiro"
                name="imagemLogoParceiro"
                data-cy="imagemLogoParceiro"
                isImage
                accept="image/*"
              />
              <ValidatedField label="Area Empresa" id="info-adicional-areaEmpresa" name="areaEmpresa" data-cy="areaEmpresa" type="text" />
              <ValidatedField label="Cnpj" id="info-adicional-cnpj" name="cnpj" data-cy="cnpj" type="text" />
              <ValidatedField
                label="Inscricao Estadual"
                id="info-adicional-inscricaoEstadual"
                name="inscricaoEstadual"
                data-cy="inscricaoEstadual"
                type="text"
              />
              <ValidatedField label="Tipo Servico" id="info-adicional-tipoServico" name="tipoServico" data-cy="tipoServico" type="text" />
              <ValidatedField label="Razao Social" id="info-adicional-razaoSocial" name="razaoSocial" data-cy="razaoSocial" type="text" />
              <ValidatedField
                label="Nome Fantasia"
                id="info-adicional-nomeFantasia"
                name="nomeFantasia"
                data-cy="nomeFantasia"
                type="text"
              />
              <ValidatedField label="Banco" id="info-adicional-banco" name="banco" data-cy="banco" type="text" />
              <ValidatedField label="Banco Outro" id="info-adicional-bancoOutro" name="bancoOutro" data-cy="bancoOutro" type="text" />
              <ValidatedField label="Agencia" id="info-adicional-agencia" name="agencia" data-cy="agencia" type="text" />
              <ValidatedField label="Numero Conta" id="info-adicional-numeroConta" name="numeroConta" data-cy="numeroConta" type="text" />
              <ValidatedField
                label="Telefone Empresa"
                id="info-adicional-telefoneEmpresa"
                name="telefoneEmpresa"
                data-cy="telefoneEmpresa"
                type="text"
              />
              <ValidatedField
                label="Email Empresa"
                id="info-adicional-emailEmpresa"
                name="emailEmpresa"
                data-cy="emailEmpresa"
                type="text"
              />
              <ValidatedField id="info-adicional-user" name="user" data-cy="user" label="User" type="select">
                <option value="" key="0" />
                {users
                  ? users.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/info-adicional" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">Back</span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp; Save
              </Button>
            </ValidatedForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default InfoAdicionalUpdate;
