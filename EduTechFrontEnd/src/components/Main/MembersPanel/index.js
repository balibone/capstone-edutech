import React, { Component } from 'react';
import { Paper } from 'material-ui';
import { observer } from 'mobx-react';

import SingleMember from './SingleMember';
import GroupStore from '../../../stores/GroupStore/GroupStore';

@observer
class MembersPanel extends Component {
	//
	// constructor(){
	// 	super()
	// 	this.state ={
	// 		members: [
	// 			{
	// 				id: '1',
	// 				name: 'Nan Da',
	// 				image: 'avatar.png'
	// 			},
	// 			{
	// 				id: '2',
	// 				name: 'Hafidz',
	// 				image: 'steveJob.png'
	// 			},
	// 			{
	// 				id:'3',
	// 				name: 'Derian',
	// 				image: 'cartoonAvatar.jpg'
	// 			}
	// 		]
	// 	}
	// }
	// componentWillMount() {
	// 	console.log('YAaaaaz')
	// 	const { match, groupStore, getSelectedGroup } = this.props;
	// 	const { groupId } = match.params
	// 	const selectedGroup = getSelectedGroup(groupId)
	// 	console.log('componentWillMount', selectedGroup);
	// 	GroupStore.selectedGroup = selectedGroup;
	// 	console.log('GroupStore.selectedGroup', GroupStore.selectedGroup)
  // }
  // componentWillReceiveProps(newProps) {
	// 	console.log('NUUUU')
	// 	const { match, groupStore, getSelectedGroup } = newProps;
	// 	const { groupId } = match.params
	// 	const selectedGroup = getSelectedGroup(groupId)
	// 	console.log('componentWillReceiveProps', selectedGroup);
	//
	// 	GroupStore.selectedGroup = selectedGroup;
	// 	console.log('GroupStore.selectedGroup', GroupStore.selectedGroup)
	//
  // }

	getSelectedGroup(groupId) {
		const groupList = JSON.parse(localStorage.getItem('groupList'))
		console.log('groupListgroupList', groupList[0].id, "rwgwrg", groupId)
		return groupList.find((group) => group.id === parseInt(groupId))
	}


	render(){
		const { match, groupStore, getSelectedGroup } = this.props;
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
