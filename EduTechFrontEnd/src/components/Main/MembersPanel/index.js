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


	render(){
		const { match, getSelectedGroup } = this.props;
		const { groupId } = match.params
		const selectedGroup = getSelectedGroup(groupId)


		return(
			<Paper className="paperDefault standardTopGap">
		    	<h4> Members </h4>
		    	{
		    		selectedGroup ? (
		    		selectedGroup.members.map((member) => {
		    			return <SingleMember key={member.id} member={member}/>
		    		})
		    		) : (<p>No members exists.</p>)
		    	}
	    	</Paper>
		)
	}
}

export default MembersPanel;
