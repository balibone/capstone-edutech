import React, { Component } from 'react';

const bubbleStyle = {
	marginTop: '8px',
	padding: '8px',
	backgroundColor:'#eee',
	borderRadius: '8px'
}

const nameStyle = {
	fontWeight: 'bold'
}

export default class SingleChatBubble extends Component {

	render() {
		const { message } = this.props;
		return (
		  <div style={bubbleStyle}>
		    <span style={nameStyle}> {message.name}: </span>
		    <span> {message.message} </span>
		  </div>
		);
	}
}
