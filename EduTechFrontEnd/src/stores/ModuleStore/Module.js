import { observable, computed } from 'mobx';

export default class Module {
	@observable moduleCode;
	@observable title;
	@observable description;
	@observable modularCredit;
	@observable members;
	@observable semester;

	constructor(moduleCode, title, description, modularCredit, members, semester){
		this.moduleCode = moduleCode;
		this.title = title;
		this.description = description;
		this.modularCredit = modularCredit;
		this.members = members;
		this.semester = semester;
	}

}