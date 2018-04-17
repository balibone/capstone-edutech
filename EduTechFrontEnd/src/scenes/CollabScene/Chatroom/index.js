/* eslint-disable */
import React, { Component } from 'react';
import ChatContentArea from './ChatContentArea';
import ChatTypeField from './ChatTypeField';

const typingAreaStyle = {
  height: '20vh',
  position: 'absolute',
  bottom:'0',
  width: '100%',
  borderTop: '1px solid #1fbcd3',
}

const contentAreaStyle = {
  height: '70vh',
  width: '100%',
  overflow: 'scroll',
}

export default class Chatroom extends Component {
  render() {
    return (
      <div>
        <div style={contentAreaStyle}>
          <ChatContentArea messages={this.props.messages} />
        </div>
        <div style={typingAreaStyle}>
          <ChatTypeField />
        </div>
      </div>
    );
  }
}
