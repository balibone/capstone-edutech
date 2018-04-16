import axios from 'axios';

const findUserAnnouncements = async username => axios.get(`/announcement/user/${username}`);

const postAnnouncement = async announcement => axios.post('/announcement', announcement);

export { findUserAnnouncements, postAnnouncement };
