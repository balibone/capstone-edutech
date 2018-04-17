import React, { Component } from 'react';
import { observer } from 'mobx-react';
import { Animated } from 'react-animated-css';
import { Breadcrumb, Row, Col } from 'react-bootstrap';
import { Paper, Divider } from 'material-ui';
import { Link } from 'react-router-dom';
import { Doughnut } from 'react-chartjs-2';

import {
  PERSONAL_ITEM_COLOR, MEETING_ITEM_COLOR,
  TASK_ITEM_COLOR,
} from '../../utils/constants';

import TaskStore from '../../stores/TaskStore/TaskStore';
import MyTasks from '../HomeScene/MyTasks';
import './styles.css';

@observer
export default class MyTasksScene extends Component {
  getTaskData() {
    const data = {
    	labels: [
    		'To Do',
    		'Doing',
    		'Done',
    	],
    	datasets: [{
    		data: TaskStore.taskProgressData,
    		backgroundColor: [
    		PERSONAL_ITEM_COLOR,
    		MEETING_ITEM_COLOR,
    		TASK_ITEM_COLOR,
    		],
    		hoverBackgroundColor: [
    		'#FF6384',
    		'#36A2EB',
    		'#FFCE56',
      ],
    }],
    };
    return data;
  }

  render() {
    const tasks = TaskStore.taskDeadlineInAWeek;

    return (
      <Animated animationIn="zoomIn" animationOut="fadeOut" isVisible>
        <div className="mainContent">
          <Row>
            <Col md={12}>
              <Breadcrumb>
                <Breadcrumb.Item><Link to="/">Home</Link></Breadcrumb.Item> {/* eslint-disable-line */}
                <Breadcrumb.Item active>My Tasks</Breadcrumb.Item>
              </Breadcrumb>
            </Col>
          </Row>
          <Row>
            <Col md={8}>
              <MyTasks />
            </Col>
            <Col md={4}>
              <Paper className="tasksChart">
                <h3> Key Summary </h3>
                <Row className="taskChartItem">
                  <h1 className="statNumberTask"> {tasks.length} </h1>
                  <p className="lead">deadline in 7 days</p>
                </Row>
                <Divider />
                <Row className="taskChartItem">
                  <Doughnut data={this.getTaskData()} height={220} />
                </Row>
              </Paper>
            </Col>
          </Row>
        </div>
      </Animated>
    );
  }
}
