import React from 'react';
import { Row, Col } from 'react-bootstrap';
import { observer } from 'mobx-react';
import { Animated } from 'react-animated-css';

import MyCalendar from './MyCalendar';
import MyTasks from './MyTasks';
import './style.css';

const HomeScene = observer(() => (
  <Animated animationIn="fadeIn" animationOut="fadeOut" isVisible>
    <div>
      <Row>
        <Col md={8}>
          <MyCalendar step={60} expandViewButton />
        </Col>
        <Col md={4}>
          <MyTasks expandViewButton />
        </Col>
      </Row>
    </div>
  </Animated>
));

export default HomeScene;
