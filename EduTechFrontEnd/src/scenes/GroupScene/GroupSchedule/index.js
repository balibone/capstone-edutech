import React, { Component } from 'react';
import FontIcon from 'material-ui/FontIcon';
import Paper from 'material-ui/Paper';
import { FlatButton } from 'material-ui';
import { ButtonToolbar, ToggleButton, ToggleButtonGroup } from 'react-bootstrap';

import GroupCalendar from '../GroupCalendar';
import GroupScheduleItemsChart from '../GroupScheduleItemsChart';

/**
 * A simple example of `BottomNavigation`, with three labels and icons
 * provided. The selected `BottomNavigationItem` is determined by application
 * state (for instance, by the URL).
 */
class GroupSchedule extends Component {
  state = {
    selectedIndex: 1,
  };

  renderSchedule() {
    return this.state.selectedIndex === 1 ?
      <GroupCalendar step={60} /> : <GroupScheduleItemsChart />;
  }

  render() {
    return (
      <div>
        <ButtonToolbar>
          <ToggleButtonGroup type="radio" name="options" defaultValue={1}>
            <ToggleButton value={1} onClick={() => this.setState({ selectedIndex: 1 })}>
              Calendar View
            </ToggleButton>
            <ToggleButton value={2} onClick={() => this.setState({ selectedIndex: 2 })}>
              Chart View
            </ToggleButton>
          </ToggleButtonGroup>
        </ButtonToolbar>
        { this.renderSchedule() }
      </div>
    );
  }
}

export default GroupSchedule;
