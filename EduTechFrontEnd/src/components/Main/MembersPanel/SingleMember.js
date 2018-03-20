import React, {Component} from 'react';

class SingleMember extends Component {

	render(){
		var member = this.props.member;

		return(
			<div style={{ 'marginBottom': '10px' }}>
				<img src={`http://localhost:8080/EduTechWebApp-war/uploads/commoninfrastructure/admin/images/${member.imgFileName}`} alt="avatar" width="40" className="img-circle" id="avatarImg"/>
		    	<label htmlFor="avatarImg" className="col-2 col-form-label"> &nbsp; {member.username}</label>
			</div>

		)
	}
}

export default SingleMember;
