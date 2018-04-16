import {
  PERSONAL_ITEM_COLOR, MEETING_ITEM_COLOR, TIMETABLE_ITEM_COLOR,
  ASSESSMENT_ITEM_COLOR, TASK_ITEM_COLOR,
} from './constants';

const retrieveCookie = (name) => {
  const value = `; ${document.cookie}`;
  const parts = value.split(`; ${name}=`);
  if (parts.length === 2) return parts.pop().split(';').shift();
  return false;
};

const formatMergedCalendarEvents = (scheduleItems, groupId) => {
  let formattedEvents = [];
  if (scheduleItems && scheduleItems.length > 0) {
    for (let i = 0; i < scheduleItems.length; i += 1) {
      let eventOwners = scheduleItems[i].assignedTo.map(owner => owner.username);
      let color = TIMETABLE_ITEM_COLOR;
      let description = null;
      if (scheduleItems[i].groupId === groupId) {
        description = scheduleItems[i].description;
        switch (scheduleItems[i].itemType) {
          case 'meeting':
            eventOwners = [scheduleItems[i].title];
            color = MEETING_ITEM_COLOR;
            break;
          case 'task':
            eventOwners = [scheduleItems[i].title];
            color = TASK_ITEM_COLOR;
            break;
          default:
            color = TIMETABLE_ITEM_COLOR;
        }
      }
      formattedEvents = formattedEvents.concat({
        id: scheduleItems[i].id,
        title: eventOwners.join(', '),
        allDay: false,
        start: new Date(scheduleItems[i].startDate),
        end: new Date(scheduleItems[i].endDate),
        description,
        location: scheduleItems[i].location,
        type: scheduleItems[i].itemType,
        createdBy: scheduleItems[i].createdBy,
        hexColor: color,
        groupId: scheduleItems[i].groupId,
      });
    }
  }
  return formattedEvents;
  // var scheduleItemArr = [];
  // if(membersScheduleItems && membersScheduleItems.length>0){
  //   for(var i=0 ; i<membersScheduleItems.length ; i++){
  //     var namesArr =[];
  //     if(membersScheduleItems[i].itemType === "timetable"){
  //       for(var j=0 ; j<membersScheduleItems[i].assignedTo.length ; j++){
  //         namesArr.push(membersScheduleItems[i].assignedTo[j].username);
  //       }
  //       scheduleItemArr = scheduleItemArr.concat({
  //         id: membersScheduleItems[i].id,
  //         title: namesArr.join(", "),
  //         allDay: false,
  //         start: new Date(membersScheduleItems[i].startDate),
  //         end: new Date(membersScheduleItems[i].endDate),
  //         description: membersScheduleItems[i].description,
  //         location: membersScheduleItems[i].location,
  //         type: membersScheduleItems[i].itemType,
  //         createdBy: membersScheduleItems[i].createdBy
  //       })
  //     }
  //   }
  // }
  // console.log('membersScheduleItems', membersScheduleItems);
  // console.log('scheduleItemArr', scheduleItemArr);
  // return scheduleItemArr;

}

const setScheduleItemsColor = (scheduleItems) => {
  let scheduleItemsWithColor = [];
  if (scheduleItems && scheduleItems.length > 0) {
    for (let i = 0; i < scheduleItems.length; i += 1) {
      let color;
      switch (scheduleItems[i].itemType) {
        case 'personal':
          color = PERSONAL_ITEM_COLOR;
          break;
        case 'meeting':
          color = MEETING_ITEM_COLOR;
          break;
        case 'timetable':
          color = TIMETABLE_ITEM_COLOR;
          break;
        case 'assessment':
          color = ASSESSMENT_ITEM_COLOR;
          break;
        case 'task':
          color = TASK_ITEM_COLOR;
          break;
        default:
          color = PERSONAL_ITEM_COLOR;
      }
      scheduleItemsWithColor = scheduleItemsWithColor.concat({
        id: scheduleItems[i].id,
        title: scheduleItems[i].title,
        allDay: false,
        start: new Date(scheduleItems[i].startDate),
        end: new Date(scheduleItems[i].endDate),
        description: scheduleItems[i].description,
        location: scheduleItems[i].location,
        type: scheduleItems[i].itemType,
        createdBy: scheduleItems[i].createdBy,
        hexColor: color,
      });
    }
  }
  return scheduleItemsWithColor;
};

const getScheduleTypeColor = (type) => {
  switch (type) {
    case 'personal':
      return PERSONAL_ITEM_COLOR;
    case 'meeting':
      return MEETING_ITEM_COLOR;
    case 'timetable':
      return TIMETABLE_ITEM_COLOR;
    case 'assessment':
      return ASSESSMENT_ITEM_COLOR;
    case 'task':
      return TASK_ITEM_COLOR;
    default:
      return PERSONAL_ITEM_COLOR;
  }
};


export { retrieveCookie, formatMergedCalendarEvents, setScheduleItemsColor, getScheduleTypeColor };
