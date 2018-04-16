import axios from 'axios';

const findUserModules = async username => axios.get(`/module/user/${username}`);

export { findUserModules }; // eslint-disable-line
