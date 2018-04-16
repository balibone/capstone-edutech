import React, { Component } from 'react';
import { toJS } from 'mobx';
import { observer } from 'mobx-react';
import { Paper, Tabs, Tab } from 'material-ui';
import BigCalendar from 'react-big-calendar';
import moment from 'moment';

import 'react-widgets/dist/css/react-widgets.css';
import 'react-big-calendar/lib/css/react-big-calendar.css';

import GroupScheduleItemStore from '../../../stores/ScheduleItemStore/GroupScheduleItemStore';
import ScheduleItemStore from '../../../stores/ScheduleItemStore/ScheduleItemStore';
import MeetingStore from '../../../stores/MeetingStore/MeetingStore';
import GroupStore from '../../../stores/GroupStore/GroupStore';
import MergeGroupStore from '../../../stores/GroupStore/MergeGroupStore';

import ProjectGroupCalendar from './ProjectGroupCalendar';
import './styles.css';

moment.locale('en')
BigCalendar.momentLocalizer(moment);


@observer
class MergeCalendar extends Component {

	componentDidMount() {
		ScheduleItemStore.populateMergedScheduleItemsForGroup(this.props.groupId);
	}

	getEventArray(membersScheduleItems) {
		let scheduleItemArr = [];
		if (membersScheduleItems && membersScheduleItems.length > 0) {
			for (let i = 0; i < membersScheduleItems.length; i++) {
				const namesArr = [];
				if (membersScheduleItems[i].itemType === 'timetable') {
					for (let j = 0; j < membersScheduleItems[i].assignedTo.length; j++) {
						namesArr.push(membersScheduleItems[i].assignedTo[j].username);
					}
					scheduleItemArr = scheduleItemArr.concat({
						id: membersScheduleItems[i].id,
						title: namesArr.join(', '),
						allDay: false,
						start: new Date(membersScheduleItems[i].startDate),
						end: new Date(membersScheduleItems[i].endDate),
						description: membersScheduleItems[i].description,
						location: membersScheduleItems[i].location,
						type: membersScheduleItems[i].itemType,
						createdBy: membersScheduleItems[i].createdBy
					})
				}
			}
		}
		console.log('membersScheduleItems', membersScheduleItems);
		console.log('scheduleItemArr', scheduleItemArr);
		return scheduleItemArr;
	}


	render() {
		const membersScheduleItems = toJS(GroupScheduleItemStore.scheduleItems);
		const eventsArray = this.getEventArray(membersScheduleItems);
		console.log('eventsArray', eventsArray)
		return (
			<div className="groupCalendar">
			      <ProjectGroupCalendar eventsArray={membersScheduleItems} />
			</div>
		)
	}
}

export default MergeCalendar;
