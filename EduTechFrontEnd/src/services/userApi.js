import axios from 'axios';

const findUser = async username => axios.get(`/user/${username}`);

export { findUser }; // eslint-disable-line
