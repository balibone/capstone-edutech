import {observable, action, computed, toJS, runInAction} from 'mobx';
import axios from 'axios';
import swal from 'sweetalert';

class MergeGroupStore {
	@observable membersInGroup = [];
	@observable membersScheduleItems = [];

	@action
	getMembersInGroup(groupId){
		let membersArray = [];
		const username = "nanda";
		axios.get(`/group/user/${username}`)
		.then((res) => {
			this.membersInGroup = res.data;
			// console.log("Members in groupId: ", res.data);
		})
		.catch((err) => {
			console.log(err);
			swal("Error!", "Network error in merge meetings.", "error")
		})
	}	

	async fetchMergedCalendar(usersArray) {
		const mergedScheduleItems = [];
		for(let i=0; i < usersArray.length; i++) {
			const scheduleitems = await axios.get(`/scheduleitem/user/${usersArray[i].username}`);
			mergedScheduleItems.push(...scheduleitems.data);

		}
		console.log('mergedScheduleItems', mergedScheduleItems);
		runInAction(() => {
        	this.membersScheduleItems.push(...mergedScheduleItems);
        	console.log('this.membersScheduleItem', this.membersScheduleItem);

      	});
      			return mergedScheduleItems;


	}


}

export default new MergeGroupStore;
