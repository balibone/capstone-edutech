import axios from 'axios';

const fetchScheduleItems = () => {
	axios.get('/v2/calendarItems.json')
    .then((res) => {
      console.log("Calendar item in api", res.data);
      return res.data;
    })
    .catch((error)=>{
      console.log(error);
      return null;
    });
}

export {fetchScheduleItems};