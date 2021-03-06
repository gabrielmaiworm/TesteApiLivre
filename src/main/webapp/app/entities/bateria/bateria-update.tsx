import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IBateria } from 'app/shared/model/bateria.model';
import { getEntity, updateEntity, createEntity, reset } from './bateria.reducer';

export const BateriaUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const bateriaEntity = useAppSelector(state => state.bateria.entity);
  const loading = useAppSelector(state => state.bateria.loading);
  const updating = useAppSelector(state => state.bateria.updating);
  const updateSuccess = useAppSelector(state => state.bateria.updateSuccess);
  const handleClose = () => {
    props.history.push('/bateria');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(props.match.params.id));
    }
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...bateriaEntity,
      ...values,
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
          ...bateriaEntity,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="testeApiLivreApp.bateria.home.createOrEditLabel" data-cy="BateriaCreateUpdateHeading">
            Create or edit a Bateria
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? <ValidatedField name="id" required readOnly id="bateria-id" label="ID" validate={{ required: true }} /> : null}
              <ValidatedField label="Numero Serie" id="bateria-numeroSerie" name="numeroSerie" data-cy="numeroSerie" type="text" />
              <ValidatedField label="Status" id="bateria-status" name="status" data-cy="status" type="text" />
              <ValidatedField label="Carga" id="bateria-carga" name="carga" data-cy="carga" type="text" />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/bateria" replace color="info">
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

export default BateriaUpdate;
