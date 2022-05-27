import React from 'react';

import MenuItem from 'app/shared/layout/menus/menu-item';

const EntitiesMenu = () => {
  return (
    <>
      {/* prettier-ignore */}
      <MenuItem icon="asterisk" to="/info-adicional">
        Info Adicional
      </MenuItem>
      <MenuItem icon="asterisk" to="/bateria">
        Bateria
      </MenuItem>
      <MenuItem icon="asterisk" to="/equipamento">
        Equipamento
      </MenuItem>
      {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
    </>
  );
};

export default EntitiesMenu as React.ComponentType<any>;
