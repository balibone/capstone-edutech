import React from 'react';
import { Paper } from 'material-ui';

import './styles.css';

const ProfilePanel = ({ img, primaryInfo, secondaryInfo }) => (
  <Paper className="profilePanel">
    <img src={`/img/${img}`} alt="profile" height="110" className="img-circle img-profile" />
    <h4><span className="capitalize">{primaryInfo}</span></h4>
    <p>{secondaryInfo}</p>
  </Paper>
);

export default ProfilePanel;
