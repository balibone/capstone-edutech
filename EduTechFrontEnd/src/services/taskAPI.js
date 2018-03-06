import axios from 'axios';

const findUserTasks = async username => axios.get(`/task/user/${username}`);

const createTask = task => axios.post('/task', task);

export { findUserTasks, createTask };
