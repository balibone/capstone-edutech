import React, { Component } from 'react';
import SingleChatBubble from './SingleChatBubble';
import { socket } from '../../../../services/socketApi';


const containerStyle = {
	width: '90%',
	margin: '8px auto',
};

const isTypingStyle = {
	fontStyle: 'italic',
	color: 'gray',
	display: 'table',
	margin: '8px auto'
}

export default class ChatContentArea extends Component {

	constructor(props) {
		super(props);
		this.state = {
			messages: [],
			personTyping: null
		}
	}

	componentWillMount() {
		this.setState({ messages: this.props.messages })

		socket.on('chat', data => {
			console.log('inside', data)
            this.setState({personTyping: null});
            this.setState({messages: [...this.state.messages, data]});
        });

    	socket.on('typing', data => {
    		this.setState({personTyping: data});
		});
	}

	renderChats() {
		return this.state.messages.map((message, i) => <SingleChatBubble key={i} message={message} />)
	}

	render() {
		const chatBubbleArea = this.renderChats();
		const personTyping = this.state.personTyping ? <span style={isTypingStyle}> {this.state.personTyping} is typing... </span> : <span></span>;
		return (
		  <div style={containerStyle} id="chatContentDiv">
		    {chatBubbleArea}
		    {personTyping}
		  </div>
		);
	}
}
