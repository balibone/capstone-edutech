import { observable, action, computed, toJS } from 'mobx';
import Assignment from './Assignment.js';
import axios from 'axios';
import moment from 'moment';
import _ from 'lodash';
import swal from 'sweetalert';

import GroupStore from '../GroupStore/GroupStore';

class AssignmentStore {
	@observable assignmentList = [];


	@action
	createAssignment(openDate, closeDate, username, moduleCode, title, numberOfStudents, groupSize, type){
		const createdBy = {username: username};
		const module = {moduleCode: moduleCode};
		openDate = moment(openDate).format('YYYY-MM-DDTHH:mm:ss');
		closeDate = moment(closeDate).format('YYYY-MM-DDTHH:mm:ss');
		const dataSet = new Assignment(title, openDate, closeDate, [], module, createdBy, [], []);
		
		if(type === "individual"){
			console.log("dataset Assignment: ", dataSet);
			axios.post('/assignment/individual', dataSet)
			.then((res) => {
				console.log(res.data);
				GroupStore.getCreatedGroups(res.data);
				swal("Success!", "Group assignment created successfully.", "success")
				this.populateModuleAssignments(moduleCode);
			})
			.catch((err) => {
				console.log(err);
			})
		}else {
			console.log("dataset Assignment: ", dataSet);
			console.log("num of students: ", numberOfStudents)
			console.log("group size: ", groupSize)
			var numOfGroups = numberOfStudents/groupSize;
			numOfGroups = _.ceil(numOfGroups);
			console.log("Number of GROUPS: ", numOfGroups)
			axios.post(`/assignment/group/${numOfGroups}/${groupSize}`, dataSet)
			.then((res) => {
				console.log(res.data);
				GroupStore.getCreatedGroups(res.data.groups);
				swal("Success!", "Group assignment created successfully.", "success");
				this.populateModuleAssignments(moduleCode);
			})
			.catch((err) => {
				console.log(err);
			})
		}
	}

	@action
	populateModuleAssignments(moduleCode){
		axios.get(`/assignment/module/${moduleCode}`)
		.then((res) => {
			console.log("assignmentList store", res.data);
			this.assignmentList = res.data
		})
		.catch((err) => {
			console.log(err)
		})
	}

	@action
	joinAssignmentGroup(username, groupId, assignmentId){
		axios.put(`/group/join/${groupId}/${username}`)
		.then((res) => {
			const joinedGroup = res.data;
			console.log("res data: ", joinedGroup)
			this.populateModuleAssignments("IS4103");

			// const assignmentIndex = _.findIndex(this.assignmentList, {id: assignmentId});
			// const groupIndex = _.findIndex(this.assignmentList[assignmentIndex].groups, {id: groupId});
			// this.assignmentList[assignmentIndex].groups.splice(groupIndex, 1, joinedGroup);
			// console.log("modified assignmentList", toJS(this.assignmentList))
		})
		.catch((err) => {
			console.log(err)
		})
	}

	@action
	leaveAssignmentGroup(username, groupId, assignmentId){
		axios.put(`/group/leave/${groupId}/${username}`)
		.then((res) => {
			const leaveGroup = res.data;
			console.log("res data: ", leaveGroup)
			this.populateModuleAssignments("IS4103")

			// const assignmentIndex = _.findIndex(this.assignmentList, {id: assignmentId});
			// const groupIndex = _.findIndex(this.assignmentList[assignmentIndex].groups, {id: groupId});
			// this.assignmentList[assignmentIndex].groups.splice(groupIndex, 1, leaveGroup);
			// console.log("modified assignmentList", toJS(this.assignmentList))
		})
		.catch((err) => {
			console.log(err)
		})
	}

}

export default new AssignmentStore;