import React, { Component } from 'react';
import { observer } from 'mobx-react';
import { Row, Col } from 'react-bootstrap';
import { Avatar, Divider, Tabs, Tab } from 'material-ui';

import GroupScheduleItemStore from '../../stores/ScheduleItemStore/GroupScheduleItemStore';
import GroupStore from '../../stores/GroupStore/GroupStore';
import {USER_IMAGE_PATH, STUDENT_PRIMARY1_COLOR} from '../../utils/constants';

import { joinRoom, socket } from '../../services/socketApi';
import Chatroom from './Chatroom';
import Whiteboard from './Whiteboard';
import Brainstorm from './Brainstorm';
import './styles.css';

@observer
export default class CollabScene extends Component {
  constructor() {
		super();
		this.state = {
			onlineUsers: [],
		}
	}

	componentWillMount() {
    joinRoom(this.props.match.params.meetingId);
	  socket.on('updateUserList', data => {
      this.setState({onlineUsers: data});
    });
  }

  renderOnlineUsers() {
    return this.state.onlineUsers.map(user => (
      <div className="singleOnlineUser">
        <img src={USER_IMAGE_PATH + user.imgFileName} className="onlineUserImg" width="50"/>
        <p>{user.username}</p>
      </div>
    ));
  }

  render() {
    if (!GroupScheduleItemStore.doneFetchingCollabMeeting || !GroupStore.doneFetchingCollabGroup) {
      return '';
    }
    return (
      <div className="collabScene">
          <Row>
            <Col md={1} className="onlineUsersSection collabMembers  text-center" style={{ backgroundColor: STUDENT_PRIMARY1_COLOR }}>
              <h5 className="onlineLabel">Online</h5>
              {this.renderOnlineUsers()}
            </Col>
            <Col md={4} className="section collabChat">
              <Chatroom />
            </Col>
            <Col md={7} className="section collabDraw">
              <Tabs>
                <Tab label="Sketch">
                  <Whiteboard />
                </Tab>
                <Tab label="Brainstorm">
                  <Brainstorm />
                </Tab>
              </Tabs>
            </Col>
          </Row>
      </div>
    );
  }
}
