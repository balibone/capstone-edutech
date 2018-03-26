import React, {Component} from 'react';
import {ListGroupItem} from 'react-bootstrap';
import {toJS} from 'mobx';
import {observer} from 'mobx-react';

import LessonStore from '../../../../stores/LessonStore/LessonStore';

import './styles.css';

@observer
class DownloadSingleFile extends Component {

	downloadFile(){
		const { id } = this.props.file;
		const { lessonId } = this.props;
		LessonStore.downloadOneFile(lessonId, id);
	}

	render(){
		const file = this.props.file;

		return(
			<ListGroupItem bsStyle="info">
				{file.fileName}
		  		<a className="pull-right downloadBtn" onClick={this.downloadFile.bind(this)}>download</a>
		  	</ListGroupItem>
		)
	}
}

export default DownloadSingleFile;