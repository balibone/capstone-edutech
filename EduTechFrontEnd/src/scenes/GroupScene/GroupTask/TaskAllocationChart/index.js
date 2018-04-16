import React, { Component } from 'react';
import { Bar } from 'react-chartjs-2';
import { observer } from 'mobx-react';
import { Paper } from 'material-ui';
import moment from 'moment';

import GroupTaskStore from '../../../../stores/TaskStore/GroupTaskStore';
import GroupStore from '../../../../stores/GroupStore/GroupStore';

import {
  PERSONAL_ITEM_COLOR, MEETING_ITEM_COLOR,
  TASK_ITEM_COLOR,
} from '../../../../utils/constants';

@observer
export default class TaskAllocationChart extends Component {
  getTasksCount() {
    const {members} = GroupStore.selectedGroup;

    const backlogTasksCounts = [];
    const currentTasksCounts = [];
    const completedCounts = [];


    for (let i = 0; i < members.length; i += 1) {
      backlogTasksCounts
        .push(this.getMemberTasksCount(members[i], GroupTaskStore.backlogTasks));
      currentTasksCounts
        .push(this.getMemberTasksCount(members[i], GroupTaskStore.currentTasks));
      completedCounts
        .push(this.getMemberTasksCount(members[i], GroupTaskStore.completedTasks));
  }
  return {
    backlogTasksCounts,
    currentTasksCounts,
    completedCounts,
  };
}

  getMemberTasksCount(member, tasks) {
    console.log('taskstasks', tasks)
    if (tasks) {
      const countedTasks =
        tasks.filter(task => (task.assignedTo[0] && task.assignedTo[0].username === member.username));
        return countedTasks.length;
    }
    return '';
  }

  getMemberLabels() {
    const {members} = GroupStore.selectedGroup;
    return members.map(member => member.username);
  }

  getTasksDataset() {
    console.log('1.getTasksDataset')

    const {
      backlogTasksCounts, currentTasksCounts, completedCounts,
    } = this.getTasksCount();
    console.log('backlogTasksCounts', backlogTasksCounts, 'currentTasksCounts', currentTasksCounts)
    const memberLabels = this.getMemberLabels();
    const data = {
	        labels: memberLabels,
	        datasets: [
	          {
	              	label: 'To Do',
	              	backgroundColor: PERSONAL_ITEM_COLOR,
	              	data: backlogTasksCounts,
	          },
	          {
	              	label: 'Doing',
	              	backgroundColor: MEETING_ITEM_COLOR,
	              	data: currentTasksCounts,
	          },
	          {
	              	label: 'Completed',
	              	backgroundColor: TASK_ITEM_COLOR,
	              	data: completedCounts,
	          },
	        ],
	      };
      return data;
    }

  render() {
    console.log('this.getTasksDataset()', this.getTasksDataset())
    return (
      <Paper className="weeklyChart">
        <h3>Task Allocation Chart</h3>
        <Bar
          data={this.getTasksDataset()}
          options={{
          		barValueSpacing: 5,
              scales: {
            xAxes: [{
                stacked: true
            }],
            yAxes: [{
                stacked: true
            }]
        },
        	title: {
          		display: this.props.displayTitle,
          		text: 'Semester view in weeks ',
          		fontSize: 20,
        	},
        	legend: {
          		display: true,
          		position: 'right',
        	},
      	}}
          height={100}
        />
      </Paper>
    );
  }
}
