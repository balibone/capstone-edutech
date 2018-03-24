import {observable, action, computed, toJS} from 'mobx';
import axios from 'axios';
import moment from 'moment';
import {Lesson} from './Lesson';
import swal from 'sweetalert';

class LessonStore {
	@observable lessonList = [];


	@action
	getLessonsForModule(moduleCode){
		axios.get(`/module/lessons/${moduleCode}`)
		.then((res) => {
			this.lessonList = res.data;
		})
		.catch((err) => {
			console.log(err);
		})
	}

	getNumberOfWeeks(){
        axios.get('/semester')
        .then((res) => {
          const startD = moment(res.data[0].startDate);
          const endD = moment(res.data[0].endDate);

          console.log("d1 d2", startD)
          // var result = this.getNumberOfWeeks(startD, endD);
          var result = endD.diff(startD, 'week') + 1;

          console.log("RESULT No of Weeks: ", result);
        })
        .catch((err) => {
          console.log(err)
        })
    }


}


export default new LessonStore;