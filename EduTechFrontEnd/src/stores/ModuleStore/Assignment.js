import { observable, computed } from 'mobx';

class Assignment {
	@observable title;
	@observable openDate;
	@observable closeDate;
	@observable submissions;
	@observable module;
	@observable createdBy;
	@observable groups;
	@observable tasks;

	constructor(title, openDate, closeDate, submissions, module, createdBy, groups, tasks){
		this.title = title;
		this.openDate = openDate;
		this.closeDate = closeDate;
		this.submissions = submissions;
		this.module = module;
		this.createdBy = createdBy;
		this.groups = groups;
		this.tasks = tasks;
	}

}

export default Assignment;
