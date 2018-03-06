import { observable, action, computed, toJS } from 'mobx';
import axios from 'axios';

import Group from './Group';

class GroupStore {
	@observable groupList = [];
	@observable selectedGroup = null;
	@observable groupId;

	constructor() {
		if (JSON.parse(localStorage.getItem('groupList')) && JSON.parse(localStorage.getItem('groupList')).length > 0) {
			this.populateGroup();
		} else {
			this.groupList = localStorage.getItem('groupList')
		}
	}

	@action
	populateGroup(){
		// const username = "nanda";
		// axios.get(`/group/${username}`)

		const username = localStorage.getItem('username');
		axios.get(`/group/user/${username}`)
		.then((res) =>{
			console.log("GroupStore: ", res.data)
			localStorage.setItem('groupList', JSON.stringify(res.data));
			this.groupList = res.data;
		})
		.catch((err) => {
			console.log(err);
		})
	}

	async editGroupDetails(group: Group) {
		await axios.put(`/group/${group.id}`, group);
		this.populateGroup();
	}

	@computed
	get currentGroup() {
		console.log('WHERE', this.groupId);
		const whatThis = this.groupList.find(group => group.id === parseInt(this.groupId, 10))
		console.log('currentGroup whatThis', whatThis);
		return whatThis;
	}

	@action
	getSelectedGroup(id){
		console.log("search for this id", id)
		console.log(this.groupList)
		let groupList = toJS(this.groupList);
		console.log(groupList)
		this.selectedGroup = groupList.find((group) => group.id === parseInt(id))
		console.log("selectedGroup in store: ", this.selectedGroup);
		return this.selectedGroup;
	}


}

export default new GroupStore();
