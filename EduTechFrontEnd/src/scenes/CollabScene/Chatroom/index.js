/* eslint-disable */
import React, { Component } from 'react';
import ChatContentArea from './ChatContentArea';
import ChatTypeField from './ChatTypeField';

const typingAreaStyle = {
  height: '18vh',
  position: 'absolute',
  bottom:'0',
  width: '100%',
  border: '1px solid #eee',
}

const contentAreaStyle = {
  height: '82vh',
  width: '100%',
  border: '1px solid #eee',
  overflow: 'hidden',
}

export default class Chatroom extends Component {
  render() {
    return (
      <div>
        <ChatContentArea/>
        <div style={typingAreaStyle}>
          <ChatTypeField />
        </div>
      </div>
    );
  }
}
