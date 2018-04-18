import React, { Component } from 'react';
import { observer } from 'mobx-react';
import moment from 'moment';
import swal from 'sweetalert';
import './styles.css';

import ScheduleItemStore from '../../../stores/ScheduleItemStore/ScheduleItemStore';

@observer
class AssessmentListView extends Component {
	constructor(props) {
		super(props)
		const filteredKeyAssessmentDates = ScheduleItemStore.getModuleKeyDates(this.props.moduleCode);
		this.state = {
			filteredKeyAssessmentDates
		}
	}
	deleteAssessment(id) {
		console.log('id to be removed', id)
		swal({
      title: 'Are you sure?',
      text: 'You will not be able to recover this assessment!',
      icon: 'warning',
      buttons: true,
      dangerMode: true,
    })
    .then((willDelete) => {
      if (willDelete) {
        ScheduleItemStore.removeScheduleItem(id);
				this.setState({
					filteredKeyAssessmentDates: ScheduleItemStore.getModuleKeyDates(this.props.moduleCode)
				})
      }
    });
	}
	renderDeleteIcon(itemType, id) {
		const userType = localStorage.getItem('userType');
		if (userType === 'instructor' && itemType === 'assessment') {
			return (<i className="fas fa-trash-alt pull-right" onClick={() => this.deleteAssessment(id)} />)
		}
		return '';
	}
	renderKeyDates() {
		const filteredKeyDates = this.state.filteredKeyAssessmentDates;
		if (filteredKeyDates.length > 0) {
			return filteredKeyDates.map((keyDate) => {
				console.log(keyDate)
				let time = '';
				let location = '';
				const { title } = keyDate;
				if (keyDate.itemType === 'assessment') {
					time = moment(keyDate.startDate).format('dddd, Do MMMM h:mm a') + ' - ' + moment(keyDate.endDate).format('h:mm a');
					location = 'Location: ' + keyDate.location;
				} else {
					time = 'Deadline: ' + moment(keyDate.startDate).format('dddd, Do MMMM h:mm a');
				}
				return (
				<div className="cardInfo standardTopGap">
					<div className="cardContainer">
						<h4><b>{title}</b></h4>
						{this.renderDeleteIcon(keyDate.itemType, keyDate.id)}
						<p>
							{time}
						</p>
						<p>{location}</p>
					</div>
				</div>
				)}
			)
		}
		return (<p className="lead">No Upcoming Assessment and Assignment</p>)
	}
	render() {
		// const filteredKeyDates = ScheduleItemStore.getModuleKeyDates(this.props.moduleCode);
		return (
			<div>
				{this.renderKeyDates()}
			</div>
		);
	}
}

export default AssessmentListView;
