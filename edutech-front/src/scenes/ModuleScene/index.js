import React from 'react';
import PropTypes from 'prop-types';
import { Paper } from 'material-ui';
import { Tabs, Tab } from 'react-bootstrap';

import Feed from '../../components/Feed';

const ModuleScene = ({ match }) => (
  <Paper className="standardTopGap standardBottomGap paperDefault">
    <Tabs defaultActiveKey={1} id="uncontrolled-tab-example">
      <Tab eventKey={1} title={match.params.moduleCode}>
        <Feed pageId={match.params.moduleCode} />
      </Tab>
      <Tab eventKey={2} title="Resources">
        Tab 2 content
      </Tab>
      <Tab eventKey={3} title="Submissions">
        Tab 3 content
      </Tab>
      <Tab eventKey={4} title="Groupings">
        Tab 4 content
      </Tab>
    </Tabs>
  </Paper>
);

ModuleScene.propTypes = {
  match: PropTypes.shape({
    params: PropTypes.shape({
      moduleCode: PropTypes.string,
    }),
  }).isRequired,
};


export default ModuleScene;
