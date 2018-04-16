import axios from 'axios';

const findUserGroups = async username => axios.get(`/group/user/${username}`);

const findGroup = async id => axios.get(`/group/${id}`);

const getMembersWithoutGroup = async assignmentId => axios.get(`/group/memberswithoutgroup/${assignmentId}`);

const autoAssignMembers = async assignmentId => axios.put(`/group/autoAssign/${assignmentId}`);

export { findUserGroups, getMembersWithoutGroup, autoAssignMembers, findGroup }; // eslint-disable-line
