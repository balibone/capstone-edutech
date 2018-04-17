var express = require('express');
var socket = require('socket.io');
var jsonfile = require('jsonfile');
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
const folderPath = './data/';

// Socket setup & pass server
var io = socket(server);
io.on('connection', (socket) => {
    // once a client has connected, we expect to get a ping from them saying what room they want to join
    socket.on('join', (data, callback) => {
        socket.join(data.room);
        users.removeUser(socket.id);
        users.addUser(socket.id, data.user, data.room);
        console.log(socket.id, 'in room', data.room)
        console.log('users.getUserList(data.room)', users.getUserList(data.room))
        io.to(data.room).emit('updateUserList', users.getUserList(data.room));

        var filepath = folderPath + data.room + '.json';
        jsonfile.readFile(filepath, function(err, obj) {
            console.log('read obj: ', obj)
            if(obj) {
                io.to(data.room).emit('populateChat', obj);
            }
        })
    });

    // Handle chat event
    socket.on('chat', function(data){
        console.log('chat in', data);

        var room = data.room;
        var user = data.name;
        var message = data.message;
        var dataObj = [];

        var filePath = folderPath + room + '.json';
        console.log('filepath:', filePath)

        jsonfile.readFile(filePath, function(err, obj) {
            console.log("read file: ", obj)
            if(obj===null) {
                console.log('file not found')
            } else if(obj) {
                obj.push(data);
                jsonfile.writeFile(filePath, obj, function (err) {
                  if(err) console.log(err)
                })
            } else {
                dataObj.push(data);
                jsonfile.writeFile(filePath, dataObj, function (err) {
                  if(err) console.log(err)
                })
            }
        }) 

        io.to(data.room).emit('chat', data);
    });

    // Handle typing event
    socket.on('typing', function(data){
        socket.broadcast.to(data.room).emit('typing in', data.name);
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
