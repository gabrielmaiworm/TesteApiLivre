import React from 'react';
import { Switch } from 'react-router-dom';
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import InfoAdicional from './info-adicional';
import Bateria from './bateria';
import Equipamento from './equipamento';
/* jhipster-needle-add-route-import - JHipster will add routes here */

export default ({ match }) => {
  return (
    <div>
      <Switch>
        {/* prettier-ignore */}
        <ErrorBoundaryRoute path={`${match.url}info-adicional`} component={InfoAdicional} />
        <ErrorBoundaryRoute path={`${match.url}bateria`} component={Bateria} />
        <ErrorBoundaryRoute path={`${match.url}equipamento`} component={Equipamento} />
        {/* jhipster-needle-add-route-path - JHipster will add routes here */}
      </Switch>
    </div>
  );
};
