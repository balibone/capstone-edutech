import axios from 'axios';

const nusVenuesInstance = axios.create({ baseURL: 'https://nusmods.com/api/2017-2018/1/venues.json' });

const fetchVenueSuggestions = async () => nusVenuesInstance.get();

export { fetchVenueSuggestions }; // eslint-disable-line
