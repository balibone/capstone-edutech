import React, { Component } from 'react';
import {Card, CardActions, CardHeader, CardMedia, CardTitle, CardText} from 'material-ui/Card';
import FlatButton from 'material-ui/FlatButton';
import moment from 'moment';
import axios from 'axios';
import {observer} from 'mobx-react';
import swal from 'sweetalert';

import MeetingStore from '../../../stores/MeetingStore/MeetingStore';

// const MeetingCard = (meeting,start,end) => (
//   <Card className="standardTopGap">
//     <CardTitle title={meeting.meeting.name} subtitle={`${start} - ${end}`} />
//     {console.log("start", start)}
//     <CardText>
//       Anything
//     </CardText>
//     <CardActions>
//       <FlatButton label="Add Meeting Minutes" />
//       <FlatButton label="Remove Meeting" />
//     </CardActions>
//   </Card>
// );

@observer
class MeetingCard extends Component {

	removeMeeting(){
		var meetingId = this.props.meeting._id;
		axios.post(`/v2/removeMeeting/${meetingId}`)
		.then((res) => {
			MeetingStore.removeMeeting(meetingId);
			swal("Success!", "Meeting removed successfully.", "success")
		})
		.catch((err) => {
			console.log(err);
			swal("Error!", "Unable to connect to the server.", "error");
		})
	}

	render(){
		let { name, description, startTime, endTime, createdBy } = this.props.meeting;
      	var start = moment(startTime).format("HH:mm")
        var end = moment(endTime).format("HH:mm")
		return(
			  <Card className="standardTopGap">
			    <CardTitle title={name} subtitle={`${start} - ${end}`} />
			    <CardText>
			      {description}
			    </CardText>
			    <CardActions>
			      <FlatButton label="Add Meeting Minutes" />
			      <FlatButton label="Remove Meeting" onClick={this.removeMeeting.bind(this)}/>
			    </CardActions>
			  </Card>
		)
	}
}

export default MeetingCard;