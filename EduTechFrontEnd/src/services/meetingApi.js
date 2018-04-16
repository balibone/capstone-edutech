import axios from 'axios';

const findGroupMeetingItems = async groupId => axios.get(`/scheduleitem/group/${groupId}`);

const createMeetingItem = async meetingItem => axios.post('/scheduleitem', meetingItem);

const editMeetingItem = async id => axios.delete(`/scheduleitem/${id}`);

const deleteMeetingItem = async id => axios.delete(`/scheduleitem/${id}`);

const createMeetingMinute = async minuteItem => axios.post('/meetingminute', minuteItem);

const createAgenda = async (minuteId, agenda) => axios.post(`/agenda/${minuteId}`, agenda);

const updateAgenda = async (minuteId, agenda) => axios.put(`/agenda/${minuteId}`, agenda);

const deleteAgenda = async agendaId => axios.delete(`/agenda/${agendaId}`);

const getOneMinute = async minuteId => axios.get(`/meetingMinute/${minuteId}`);

export {
  findGroupMeetingItems, createMeetingItem, editMeetingItem, deleteMeetingItem,
  createMeetingMinute, createAgenda, updateAgenda, deleteAgenda, getOneMinute,
 };
