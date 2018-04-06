
import {observable, action, computed, toJS} from 'mobx';
import {Minute} from './Minute';
import axios from 'axios';
import moment from 'moment';
import swal from 'sweetalert';

class MinuteStore {
	@observable minuteList = [];
	@observable agendaList = []

	@action
	addMeetingMinute(meetingId, startTime, endTime, attendees){

		let attendeesObj = [];
		const meeting = {id: meetingId}
		const createdAt = new Date();
		attendees.forEach((attendee, index) => {
			console.log("index at: ", index)
			attendeesObj[index] = {username: attendee};
			console.log("attendeesObj: ", attendeesObj)
		})

		const meetingminute = new Minute(meeting, startTime, endTime, attendeesObj, [], createdAt, [])

		console.log("dataset meetingminute", toJS(meetingminute))
		

		axios.post('/meetingminute', meetingminute)
		.then((res) => {
			console.log("meetingminute success",res.data)
			this.minuteList = res.data;
		})
		.catch((err) => {
			console.log(err);
		})
	}

	addAgenda(meetingMinuteId){
		axios.post(`/agenda/${meetingMinuteId}`)
		.then((res) => {
			this.agendaList =  res.data;
		})	
		.catch((err) => {
			console.log(err)
		})
	}

}


export default new MinuteStore;