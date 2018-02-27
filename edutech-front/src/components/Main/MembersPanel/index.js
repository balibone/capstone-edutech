'use strict'
import React, { Component } from 'react';
import { Paper } from 'material-ui';
import SingleMember from './SingleMember';

class MembersPanel extends Component {
	
	constructor(){
		super()
		this.state ={
			members: [
				{
					id: '1',
					name: 'Nan Da',
					image: 'avatar.png'
				},
				{
					id: '2',
					name: 'Hafidz',
					image: 'steveJob.png'
				},
				{
					id:'3',
					name: 'Derian',
					image: 'cartoonAvatar.jpg'
				}
			]
		}
	}

	render(){
		
		return(
			<Paper className="paperDefault standardTopGap">
		    	<h4> Members </h4>
		    	{
		    		this.state.members ? (
		    		this.state.members.map((member) => {
		    			return <SingleMember key={member.id} member={member}/>
		    		})
		    		) : (<p>No members exists.</p>)
		    	}
	    	</Paper>
		)
	}
}

export default MembersPanel;
