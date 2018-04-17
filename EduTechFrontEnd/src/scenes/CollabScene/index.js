import React, { Component } from 'react';
import { observer } from 'mobx-react';
import { Grid, Row, Col } from 'react-bootstrap';
import { Avatar, Divider, Tabs, Tab } from 'material-ui';

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
      messages: []
		}
	}

	componentWillMount() {
    joinRoom(this.props.match.params.groupId);

	  socket.on('updateUserList', data => {
      this.setState({onlineUsers: data});
    });

    socket.on('populateChat', data => {
        if (data) this.setState({messages: data});
    });
  }

  renderOnlineUsers() {
    return this.state.onlineUsers.map(user => (
      <div className="singleOnlineUser" key={user.username}>
        <img src={USER_IMAGE_PATH + user.imgFileName} className="onlineUserImg" width="50"/>
        <p>{user.username}</p>
      </div>
    ));
  }

  render() {
    if (!GroupStore.doneFetchingCollabGroup) {
      return '';
    }

    console.log('curuser', JSON.parse(localStorage.getItem('currentUser')));
    console.log('members', GroupStore.collabGroup.members);

    const currentUser = JSON.parse(localStorage.getItem('currentUser'));
    const index = GroupStore.collabGroup.members.findIndex(member => member.username === currentUser.username);

    if (index === -1) {
      window.location.replace('http://localhost:3000');
    }


    return (
        <Row className="mainAreaCollab">
          <Col md={1} style={{ backgroundColor: STUDENT_PRIMARY1_COLOR }} className="onlineUsersSection">
            <h5 className="onlineLabel">Online</h5>
            {this.renderOnlineUsers()}
          </Col>
          <Col md={4} className="section collabChat">
            <Chatroom messages={this.state.messages} />
          </Col>
          <Col md={7} className="section collabDrawArea">
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
    )
  }
}
