var express = require('express');
var socket = require('socket.io');
const {Users} = require('./utils/users');
var users = new Users();


// App setup
var app = express();
var server = app.listen(4000, function(){
    console.log('listening for requests on port 4000,');
});

// Static files
app.use(express.static('public'));

const sessions = []

// Socket setup & pass server
var io = socket(server);
io.on('connection', (socket) => {
    // once a client has connected, we expect to get a ping from them saying what room they want to join
    socket.on('join', (data, callback) => {
        socket.join(data.room);
        users.removeUser(socket.id);
        users.addUser(socket.id, data.user, data.room);
        console.log('users.getUserList(data.room)', users.getUserList(data.room))
        io.to(data.room).emit('updateUserList', users.getUserList(data.room));

        console.log(socket.id, 'in room', data.room)
    });

    // Handle chat event
    socket.on('chat', function(data){
        // console.log(data);
        io.to(data.room).emit('chat', data);
    });

    // Handle typing event
    socket.on('typing', function(data){
        console.log(' typing data', data)
        socket.broadcast.to(data.room).emit('typing', data.name);
    });

    //handle whiteboard
    socket.on('addItem', (data, room) => {
        console.log('room', room);
        console.log('data', data);
        socket.broadcast.to(room).emit('addItem', data);
    });

    socket.on('disconnect', () => {
         var user = users.removeUser(socket.id);
         if (user) {
          io.to(user.room).emit('updateUserList', users.getUserList(user.room));
        }
        console.log(socket.id, ' left room')
    });

});