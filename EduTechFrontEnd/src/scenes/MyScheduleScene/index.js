import React, { Component } from 'react';
import { Tabs, Tab } from 'material-ui';
import { observer } from 'mobx-react';
import { Animated } from 'react-animated-css';
import { Breadcrumb } from 'react-bootstrap';
import { Link } from 'react-router-dom';

import MyCalendar from '../HomeScene/MyCalendar';
import ScheduleItemsChart from './ScheduleItemsChart';

@observer
export default class MyScheduleScene extends Component {
  handleLinking = (evt) => {
    evt.preventDefault();
    const { router } = this.context;
    router.transitionTo(evt.target.href.substring(window.location.origin.length));
  }

  render() {
    return (
      <Animated animationIn="zoomIn" animationOut="fadeOut" isVisible>
        <div>
          <Breadcrumb>
            <Breadcrumb.Item><Link to="/">Home</Link></Breadcrumb.Item> {/* eslint-disable-line */}
            <Breadcrumb.Item active>My Schedule</Breadcrumb.Item>
          </Breadcrumb>
          <Tabs>
            <Tab label="Calendar" >
              <div>
                <MyCalendar step={60} />
              </div>
            </Tab>
            <Tab label="Weekly Chart" >
              <div>
                <ScheduleItemsChart />
              </div>
            </Tab>
          </Tabs>

        </div>
      </Animated>
    );
  }
}
