import React, { Component } from 'react';
import { observer } from 'mobx-react';
import moment from 'moment';
import './styles.css';

import ScheduleItemStore from '../../../stores/ScheduleItemStore/ScheduleItemStore';

@observer
class AssessmentListView extends Component {
	renderKeyDates(filteredKeyDates) {
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
		// const allAssessmentList = toJS(ScheduleItemStore.assessmentItems);
		const filteredKeyDates = ScheduleItemStore.getModuleKeyDates(this.props.moduleCode);
		return (
			<div>
				{this.renderKeyDates(filteredKeyDates)}
			</div>
		);
	}
}

export default AssessmentListView;
