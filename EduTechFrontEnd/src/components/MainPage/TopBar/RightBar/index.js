import React, { Component } from 'react';
import { Drawer, Tabs, Tab } from 'material-ui';

import KeyActivities from './KeyActivities';
import KeyDates from './KeyDates';
import './styles.css';

/*eslint-disable */
export default class RightBar extends Component {
  render() {
    const { openRightBar, handleToggleRightBar } = this.props;
    return (
      <Drawer
        docked={false}
        width={300}
        open={openRightBar}
        openSecondary={true}
        onRequestChange={() => handleToggleRightBar()}
      >
      <Tabs>
        <Tab label="Key Dates">
          <KeyDates/>
        </Tab>
        <Tab label="Key Activities">
          <div className="keyActivities">
            <KeyActivities />
          </div>
        </Tab>
      </Tabs>
      </Drawer>
    );
  }
}
