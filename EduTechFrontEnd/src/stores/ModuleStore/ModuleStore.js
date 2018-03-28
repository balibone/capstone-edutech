import { observable, action, computed, toJS } from 'mobx';
import axios from 'axios';

class ModuleStore {
	@observable moduleList = [];
	@observable selectedModule = null;

	constructor(){
		if (JSON.parse(localStorage.getItem('moduleList')) && JSON.parse(localStorage.getItem('moduleList')).length > 0) {
			this.populateModule();
		} else {
			this.moduleList = localStorage.getItem('moduleList');
		}
	}

	@action
	populateModule(){
		axios.get('http://localhost:8080/EduTechWebApp-war/api/module')
		.then((res) => {
			console.log('populate module', res.data)
			localStorage.setItem('moduleList',JSON.stringify(res.data));
			this.moduleList = res.data;
		})
		.catch((err) => {
			console.log(err);
		})
	}

	// @action
	// getSelectedGroup(moduleCode){
	// 	let moduleList = toJS(this.moduleList);
	// 	this.selectedModule = moduleList.find((module) => module.moduleCode === moduleCode)
	// 	return this.selectedModule;
	// }


}

export default new ModuleStore();