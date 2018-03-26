import {observable, action, computed, toJS} from 'mobx';
import axios from 'axios';
import moment from 'moment';
import {Announcement} from './Announcement';
import swal from 'sweetalert';

class AnnouncementStore {
	@observable announcementList = [];


	@action
	getAnnouncementsForUser(){
    const username = localStorage.getItem('username');
		axios.get(`/announcement/user/${username}`)
		.then((res) => {
			this.announcementList = res.data;
		})
		.catch((err) => {
			console.log(err);
		})
	}

	@action
	createAnnouncement(announcement){
		axios.post('announcement', announcement)
		.then((res) => {

		})
		.catch((err) => {
			console.log(err);
		})
	}

}


export default new AnnouncementStore;