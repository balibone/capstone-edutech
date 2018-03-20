import {observable, action, computed, toJS} from 'mobx';
import axios from 'axios';
import moment from 'moment';
import {Lesson} from './Lesson';
import swal from 'sweetalert';

class LessonStore {
	@observable lessons = [];

}


export default new LessonStore;