import React from 'react';
import PropTypes from 'prop-types';
import { Paper } from 'material-ui';
import { Tabs, Tab } from 'react-bootstrap';

import GroupTask from './GroupTask';
import GroupMeeting from './GroupMeeting';
import GroupBrainstorm from './GroupBrainstorm';
import Feed from '../../components/Feed';

const GroupScene = ({ match, meetingStore }) => (
  <Paper className="standardTopGap standardBottomGap paperDefault">
    <Tabs defaultActiveKey={1} id="uncontrolled-tab-example">
      <Tab eventKey={1} title={`Group ${match.params.groupId}`}>
        <Feed pageId={match.params.groupId} />
      </Tab>
      <Tab eventKey={2} title="Tasks">
        <GroupTask groupId={match.params.groupId} />
      </Tab>
      <Tab eventKey={3} title="Meetings">
      {console.log(meetingStore)}
        <GroupMeeting meetingStore={meetingStore}/>
      
      </Tab>
      <Tab eventKey={4} title="Brainstorms">
        <GroupBrainstorm groupId={match.params.groupId} />
      </Tab>
    </Tabs>
  </Paper>
);

GroupScene.propTypes = {
  match: PropTypes.shape({
    params: PropTypes.shape({
      moduleCode: PropTypes.string,
    }),
  }).isRequired,
};

export default GroupScene;
