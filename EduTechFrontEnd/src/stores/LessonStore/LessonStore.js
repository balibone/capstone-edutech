import {observable, action, computed, toJS} from 'mobx';
import axios from 'axios';
import moment from 'moment';
import {Lesson} from './Lesson';
import swal from 'sweetalert';
import FileSaver from 'file-saver';
import _ from 'lodash';

class LessonStore {
	@observable lessonList = [];
  @observable uploadedFile = [];

	@action
	getLessonsForModule(moduleCode){
    const username = localStorage.getItem('username');
    var unsoredLessonList = [];
		axios.get(`/module/lessons/${moduleCode}`) // to replace with get modules for user
		.then((res) => {
      unsoredLessonList = res.data;
      unsoredLessonList.sort(function(a,b) {return (a.startDate > b.startDate) ? 1 : ((b.startDate > a.startDate) ? -1 : 0);} );
			this.lessonList = unsoredLessonList;
		})
		.catch((err) => {
			console.log(err);
		})
	}

  @action
  uploadAttachment(title, file, lessonId){
    const formData = new FormData();
    formData.append('title', title)
    formData.append('file', file)

    axios.post(`/lesson/uploadAttachment/${lessonId}`,formData)
    .then((res) => {
      this.uploadedFile = res.data;
      this.uploadedFile[0].lessonId = lessonId;
      console.log("uploadedFile with lessonId:" , this.uploadedFile)
      swal("Success !", `${file.name} is successfully uploaded.`, "success");
    })
    .catch((err) => {
      console.log(err);
    })
  }

  @action
  downloadAllFiles(lessonId, title, dateTimeFormat){
    axios.get(`/lesson/downloadAllAttachments/${lessonId}`,{responseType: 'blob'})
    .then((res) => {
      const downloadedZip = res.data;
      var zipFileName = title.concat("_" + dateTimeFormat + ".zip");
      FileSaver.saveAs(downloadedZip, zipFileName);
    })
    .catch((err) => {
      console.log(err);
    })
  }

  @action
  downloadOneFile(lessonId, attachmentId, fileName){
    console.log("DOWNLOADING ONE FILE", lessonId, attachmentId, fileName)
    axios.get(`/lesson/downloadAttachment/${lessonId}/${attachmentId}`,{responseType: 'blob'})
    .then((res) => {
      const downloadedFile = res.data;
      FileSaver.saveAs(downloadedFile, fileName);
    })
    .catch((err) => {
      console.log(err);
    })
  }

  @action
  removeOneFile(lessonId, attachmentId, fileName){
    axios.delete(`/lesson/deleteAttachment/${lessonId}/${attachmentId}`)
    .then((res) => {
      console.log("delete file success!", res.data);
      swal(`${fileName} has been deleted!`, {icon: "success"});

      // swal("Sucess !", "File removed.", "success");
    })
    .catch((err) => {
      console.log(err);
    })
  }

  // @action
  // getFilesForLesson(lessonId){
  //   var attachmentArr = [];
  //   var index = _.findIndex(this.lessonList, (item) => {return item.id === lessonId});
  //   axios.get(`/lesson/allAttachments/${lessonId}`)
  //   .then((res) => {
  //     this.lessonList[index].files = res.data;
  //     console.log("lesson", this.lessonList[index])
  //   })
  //   .catch((err) => {
  //     console.log(err);
  //   })
  // }


  @action
  fetchFilesForLesson(lessonId) {
      var attachmentArr = [];
      var index = _.findIndex(this.lessonList, (item) => {return item.id === lessonId});
      
      axios.get(`/lesson/allAttachments/${lessonId}`)
      .then(action("fetchSuccess", res => {
                // const filteredProjects = somePreprocessing(projects)
                // this.githubProjects = filteredProjects
                // this.state = "done"

                this.lessonList[index].files = res.data;
                console.log("lesson", this.lessonList[index])
            }),
            // inline created action
            action("fetchError", error => {
                console.log(error)
            }) 
      )
    }  

}


export default new LessonStore;