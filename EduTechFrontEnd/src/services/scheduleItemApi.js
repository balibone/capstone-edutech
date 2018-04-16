import axios from 'axios';

const findSemester = async () => axios.get('/semester');

const findUserScheduleItems = async username => axios.get(`/scheduleitem/user/${username}`);

const createScheduleItem = async scheduleItem => axios.post('/scheduleitem', scheduleItem);

const deleteScheduleItem = async id => axios.delete(`/scheduleitem/${id}`);

const editScheduleItem = async (id, scheduleItem) => axios.put(`/scheduleitem/${id}`, scheduleItem);

const findGroupScheduleItems = async groupId => axios.get(`/scheduleitem/members/${groupId}`);

const findScheduleItem = async id => axios.get(`/scheduleitem/${id}`);

export {
  findSemester, findUserScheduleItems, createScheduleItem,
  deleteScheduleItem, editScheduleItem, findGroupScheduleItems,
  findScheduleItem,
 };
