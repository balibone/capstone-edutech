import axios from 'axios';

const findUserTasks = async username => axios.get(`/task/user/${username}`);

const createTask = async task => axios.post('/task', task);

const deleteTask = async id => axios.delete(`/task/${id}`);

const editTask = async task => axios.put(`/task/${task.id}`, task);

const updateTaskProgress = async (id, progressCode) => axios.put(`/task/${id}/${progressCode}`);

export { findUserTasks, createTask, deleteTask, editTask, updateTaskProgress };
