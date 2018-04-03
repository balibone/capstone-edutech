import { observable, action, computed, toJS } from 'mobx';
import axios from 'axios';

class ModuleStore {
	@observable moduleList = [];
	@observable selectedModule = [];

	constructor(){
		if (JSON.parse(localStorage.getItem('moduleList')) && JSON.parse(localStorage.getItem('moduleList')).length > 0) {
			this.populateModule();
		} else {
			this.moduleList = localStorage.getItem('moduleList');
		}
	}

	@action
	populateModule(){
		const username = localStorage.getItem('username');
		axios.get(`/module/user/${username}`)
		.then((res) => {
			// console.log('populate module', res.data)
			localStorage.setItem('moduleList',JSON.stringify(res.data));
			this.moduleList = res.data;
		})
		.catch((err) => {
			console.log(err);
		})
	}

	@action
	getOneModule(moduleCode){
		let moduleList = toJS(this.moduleList);
		this.selectedModule = moduleList.find((module) => module.moduleCode === moduleCode)
	}


}

export default new ModuleStore();