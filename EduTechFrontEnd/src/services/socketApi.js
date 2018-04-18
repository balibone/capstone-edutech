import io from 'socket.io-client';
import { HOST_NAME } from '../utils/constants';

const socket = io(`http://${HOST_NAME}:4000`);

const sendMessage = (message) => {
	socket.emit('chat', message);
};

const captureTyping = (data) => {
	socket.emit('typing', data);
};

const joinRoom = (groupId) => {
	const params = {
		room: groupId,
		user: JSON.parse(localStorage.getItem('currentUser')),
	};
	socket.emit('join', params, (err) => {
		if (err) {
			window.location.replace(`http://${HOST_NAME}:3000`)
		} else {
			console.log('success');
		}
	});
};

export { socket, captureTyping, sendMessage, joinRoom };
