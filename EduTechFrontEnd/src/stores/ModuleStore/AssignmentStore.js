import { observable, action } from 'mobx';
import Assignment from './Assignment.js';
import { fetchModuleAssignment, createGroupAssignment, createIndividualAssignment, deleteAssignment,
joinAssignmentGroup, leaveAssignmentGroup, submitAssignmentFile } from '../../services/assignmentApi';
import { getMembersWithoutGroup } from '../../services/groupApi';
import axios from 'axios';
import moment from 'moment';
import _ from 'lodash';
import swal from 'sweetalert';
import FileSaver from 'file-saver';
import UtilStore from '../UtilStore/UtilStore';

import GroupStore from '../GroupStore/GroupStore';
import ModuleStore from '../ModuleStore/ModuleStore';
import AnnouncementStore from '../AnnouncementStore/AnnouncementStore';


class AssignmentStore {
	@observable assignmentList = [];
	@observable membersWithoutGroup = [];

	@action
	async createAssignment(openDate, closeDate, username, moduleCode, title, numberOfStudents, groupSize, type) {
		const createdBy = { username };
		const module = { moduleCode };
		openDate = moment(openDate).format('YYYY-MM-DDTHH:mm:ss');
		closeDate = moment(closeDate).format('YYYY-MM-DDTHH:mm:ss');
		const dataSet = new Assignment(title, openDate, closeDate, [], module, createdBy, [], []);

		if (type === 'individual') {
			try {
				const assignmentItem = await createIndividualAssignment(dataSet)
				AnnouncementStore.postAnnouncement(
					ModuleStore.selectedModule.moduleCode,
					`Individual Assignment: ${title} published`,
					 ModuleStore.selectedModule.members,
					 `module/${ModuleStore.selectedModule.moduleCode}?tabKey/Assignments`,
				);
				UtilStore.openSnackbar("Individual Assignment created successfully");
				this.populateModuleAssignments(moduleCode);
			} catch (e) {
				swal('Error!','Unable to create individual assignment', 'error')
			}
		} else {
			let numOfGroups = numberOfStudents / groupSize;
			numOfGroups = _.ceil(numOfGroups);
			try {
				const assignmentItem = await createGroupAssignment(numOfGroups, groupSize, dataSet)
				console.log('assignment item: ', assignmentItem.data);
				this.populateModuleAssignments(moduleCode);
				UtilStore.openSnackbar("Group Assignment created successfully");
				AnnouncementStore.postAnnouncement(
					ModuleStore.selectedModule.moduleCode,
					`Group Assignment: ${title} published`,
					 ModuleStore.selectedModule.members,
					 `/module/${ModuleStore.selectedModule.moduleCode}`,
				);
			} catch (e) {
				swal('Error!', 'Unable to create group assignment', 'error')
			}
		}
	}

	@action
	async deleteAssignment(assignmentId) {
		try {
			const remainingAssignments = await deleteAssignment(assignmentId);
			this.assignmentList = remainingAssignments.data;
			UtilStore.openSnackbar('Assignment deleted successfully');
		} catch (e) {
			swal('Network Error!', 'Unable to delete assignment.', 'error')
		}
	}
// submissions
	@action
	async submitAssignment(title, file, username, assignmentId) {
		const formData = new FormData();
		formData.append('title', title)
		formData.append('file', file)
		formData.append('createdBy', username)
		try {
				const assignment = await submitAssignmentFile(formData, assignmentId)
				const index = this.getIndex(assignmentId, this.assignmentList, 'id')
				console.log('return assignment: ', index, assignment.data)
				this.assignmentList[index] = assignment.data;
				swal('Success!', `${file.name} uploaded successfully.`, 'success');
		} catch (e) {
			swal('Error', 'Unable to submit assignment', 'error');
		}
	}

	@action
	downloadAssignment(assignmentId, attachmentId, fileName) {
		console.log("DOWNLOADING ONE Assignment FILE", assignmentId, attachmentId, fileName)
		axios.get(`/assignment/download/${assignmentId}/${attachmentId}`, { responseType: 'blob' })
		.then((res) => {
			const downloadedFile = res.data;
			FileSaver.saveAs(downloadedFile, fileName);
		})
		.catch((err) => {
			console.log(err);
		})
	}

	@action
	async getMembersWithoutGroup(assignmentId) {
		try {
			const res = await getMembersWithoutGroup(assignmentId);
			console.log('members without group', res.data);
			this.membersWithoutGroup = res.data;
		} catch (e) {
			console.log(e)
		}
	}

	@action
	populateModuleAssignments(moduleCode) {
		axios.get(`/assignment/module/${moduleCode}`)
		.then((res) => {
			console.log('assignmentList store', res.data);
			this.assignmentList = res.data
		})
		.catch((err) => {
			console.log(err)
		})
	}

	@action
	joinAssignmentGroup(username, groupId, assignmentId, moduleCode) {
		axios.put(`/group/join/${groupId}/${username}`)
		.then((res) => {
			const joinedGroup = res.data;
			this.populateModuleAssignments(moduleCode);
			GroupStore.populateGroupList(username);
		})
		.catch((err) => {
			console.log(err)
		})
	}

	@action
	leaveAssignmentGroup(username, groupId, assignmentId, moduleCode) {
		axios.put(`/group/leave/${groupId}/${username}`)
		.then((res) => {
			const leaveGroup = res.data;
			console.log("res data: ", leaveGroup);
			this.populateModuleAssignments(moduleCode);
			GroupStore.populateGroupList(username);
		})
		.catch((err) => {
			console.log(err)
		})
	}

	getIndex(value, arr, prop) {
		for (let i = 0; i < arr.length; i++) {
				if (arr[i][prop] === value) {
						return i;
				}
		}
		return -1;
	}
}

export default new AssignmentStore;
