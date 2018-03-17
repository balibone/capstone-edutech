import React, { Component } from 'react';
import { Paper } from 'material-ui';
import { observer } from 'mobx-react';

import SingleMember from './SingleMember';
import GroupStore from '../../../stores/GroupStore/GroupStore';

@observer
class MembersPanel extends Component {

	getSelectedGroup(groupId) {
		const groupList = JSON.parse(localStorage.getItem('groupList'))
		return groupList.find((group) => group.id === parseInt(groupId))
	}

	getSelectedModule(moduleCode){
		const moduleList = JSON.parse(localStorage.getItem('moduleList'));
		return moduleList.find((module) => module.moduleCode === moduleCode)
	}


	render(){
		const { match } = this.props;
		let members = [];
		if(match.params.groupId){
			const { getSelectedGroup } = this.props;
			const { groupId } = match.params;
			const selectedGroup = getSelectedGroup(groupId)
			members = selectedGroup.members;
		}else{
			const { getSelectedModule } = this.props;
			const { moduleCode } = match.params;
			const selectedModule = getSelectedModule(moduleCode)
			console.log("selected module in members panel", selectedModule)
			members = selectedModule.members;
		}

		

		return(
			<Paper className="paperDefault standardTopGap">
		    	<h4> Members </h4>
		    	{
		    		(members.length>0) ? (
		    		members.map((member) => {
		    			return <SingleMember key={member.id} member={member}/>
		    		})
		    		) : (<p>No members exists.</p>)
		    	}
	    	</Paper>
		)
	}
}

export default MembersPanel;
