import io from 'socket.io-client';

const socket = io('http://localhost:4000');

const sendMessage = (message) => {
	console.log('msg LOL', message)
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
			window.location.replace('http://localhost:3000')
		} else {
			console.log('success');
		}
	});
};

export { socket, captureTyping, sendMessage, joinRoom };
