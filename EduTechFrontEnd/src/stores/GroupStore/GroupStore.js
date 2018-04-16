import { observable, action, runInAction, computed } from 'mobx';
import axios from 'axios';
import swal from 'sweetalert';

import UtilStore from '../UtilStore/UtilStore';

import AssignmentStore from '../ModuleStore/AssignmentStore';
import { findUserGroups, autoAssignMembers, findGroup } from '../../services/groupApi';

class GroupStore {
  @observable groupList = [];
  @observable donePopulating = false;
  @observable selectedGroup;
  @observable collabGroup;
  @observable doneFetchingCollabGroup = false;


  async populateGroupList(username) {
    const groups = await findUserGroups(username);
    runInAction(() => {
      this.groupList = groups.data;
      this.donePopulating = true;
    });
  }

  async fetchCollabGroup(id) {
    const group = await findGroup(id);
    runInAction(() => {
      this.collabGroup = group.data;
      this.doneFetchingCollabGroup = true;
    });
  }

  @action
  async doAutoAssignMembers(assignmentId, moduleCode) {
    console.log('autoAssign members in ', assignmentId)
    try {
      const res = await autoAssignMembers(assignmentId);
      console.log('res data auto assign: ', res.data);
      AssignmentStore.populateModuleAssignments(moduleCode)
      // this.groupList = res.data;
      // this.donePopulating = true;
    } catch(e) {
      console.log(e);
    }
  }

	@action
	getGroup(groupId) {
		return this.groupList.find(group => group.id === parseInt(groupId, 10));
	}

  @action
  setSelectedGroup(groupId) {
    this.selectedGroup = this.groupList.find(group => group.id === parseInt(groupId, 10));
  }

  @computed
  get students() {
    return this.selectedGroup.members.filter(member => member.userType === 'student');
  }

  @computed
  get instructors() {
    return this.selectedGroup.members.filter(member => member.userType === 'instructor');
  }

  @action
  async editGroupDescription(newDescription) {
    try {
      this.selectedGroup.description = newDescription;
      await axios.put(`/group/${this.selectedGroup.id}`, this.selectedGroup);
      UtilStore.openSnackbar('Group decription updated');
    } catch (e) {
      swal('Error', 'Error updating group description', 'error');
    }
  }
}

export default new GroupStore();
