import { observable, action, runInAction, computed } from 'mobx';
import axios from 'axios';
import swal from 'sweetalert';

import UtilStore from '../UtilStore/UtilStore';
import { findUserModules } from '../../services/moduleApi';


class ModuleStore {
	@observable moduleList = [];
	@observable donePopulating = false;
	@observable selectedModule;

	async populateModuleList(username) {
		const modules = await findUserModules(username);
		runInAction(() => {
			this.moduleList = modules.data;
			this.donePopulating = true;
		});
	}

	@action
	getModule(moduleCode) {
		this.selectedModule = this.moduleList.find(module => module.moduleCode === moduleCode);
		return this.selectedModule;
	}

	@action
	setSelectedModule(moduleCode) {
		this.selectedModule = this.moduleList.find(module => module.moduleCode === moduleCode);
		return this.selectedModule;
	}

	@computed
	get students() {
		return this.selectedModule.members.filter(member => member.userType === 'student');
	}

	@computed
	get instructors() {
		return this.selectedModule.members.filter(member => member.userType === 'instructor');
	}

	@action
	async editModuleDescription(newDescription) {
		try {
			this.selectedModule.description = newDescription;
			await axios.put(`/module/${this.selectedModule.moduleCode}`, this.selectedModule);
			UtilStore.openSnackbar('Module decription updated')
		} catch (e) {
			swal('Error', 'Error updating group description', 'error')
		}
	}
}

export default new ModuleStore();
