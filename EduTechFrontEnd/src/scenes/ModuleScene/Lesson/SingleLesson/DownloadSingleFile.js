import React, {Component} from 'react';
import {ListGroupItem} from 'react-bootstrap';
import {toJS} from 'mobx';
import {observer} from 'mobx-react';

import LessonStore from '../../../../stores/LessonStore/LessonStore';

import './styles.css';

@observer
class DownloadSingleFile extends Component {

	downloadFile(){
		const { id, fileName } = this.props.file;
		const { lessonId } = this.props;
		LessonStore.downloadOneFile(lessonId, id, fileName);
	}

	render(){
		const file = this.props.file;

		return(
			<ListGroupItem bsStyle="info">
				{file.fileName}
				<a className="pull-right downloadBtn" onClick={this.downloadFile.bind(this)}>download</a>
				<p className="smallText" style={{color: '#000000'}}>{file.title}</p>
		  		
		  	</ListGroupItem>
		)
	}
}

export default DownloadSingleFile;