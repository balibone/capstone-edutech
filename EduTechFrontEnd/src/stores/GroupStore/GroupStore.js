import { observable, action, computed, toJS } from 'mobx';
import axios from 'axios';

import Group from './Group';

class GroupStore {
	@observable groupList = [];
	@observable selectedGroup = null;
	@observable groupId;
	@observable newGroups = [];
	@observable moduleGrouping = [];

	constructor() {
		if (JSON.parse(localStorage.getItem('groupList')) && JSON.parse(localStorage.getItem('groupList')).length > 0) {
			this.populateGroup();
		} else {
			this.groupList = localStorage.getItem('groupList')
		}
	}

	@action
	populateGroup(){
		const username = localStorage.getItem('username');
		axios.get(`/group/user/${username}`)
		.then((res) =>{
			localStorage.setItem('groupList', JSON.stringify(res.data));
			this.groupList = res.data;
		})
		.catch((err) => {
			console.log(err);
		})
	}

	@action
	populateModuleGroup(moduleCode){
		console.log("in populate module group")
		axios.get(`/group/module/${moduleCode}`)
		.then((res) => {
			console.log("populateModuleGroup", res.data);
			this.moduleGrouping = res.data;
		})
		.catch((err) => {
			console.log(err)
		})
		console.log("populateModuleGroup2", this.moduleGrouping);

	}

	async editGroupDetails(group: Group) {
		await axios.put(`/group/${group.id}`, group);
		this.populateGroup();
	}

	@computed
	get currentGroup() {
		const whatThis = this.groupList.find(group => group.id === parseInt(this.groupId, 10))
		return whatThis;
	}

	@action
	getSelectedGroup(id){
		let groupList = toJS(this.groupList);
		this.selectedGroup = groupList.find((group) => group.id === parseInt(id))
		return this.selectedGroup;
	}

	@action
	getCreatedGroups(newGroups){
		console.log("new created groups", newGroups)
		this.newGroups = newGroups;
	}

}

export default new GroupStore();
