
import {observable, action, computed, toJS} from 'mobx';
import axios from 'axios';
import moment from 'moment';
import {Announcement} from './Announcement';
import swal from 'sweetalert';

class MinuteStore {
	@observable minutes = [];


	@action
	getMinutesForMeeting(){
		axios.get(`announcement/user/${username}`)
		.then((res) => {
			this.announcementList = res.data;
		})
		.catch((err) => {
			console.log(err);
		})
	}

}


export default new MinuteStore;