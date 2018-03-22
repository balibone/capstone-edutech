import React, {Component} from 'react';
import {Button} from 'react-bootstrap';

class DownloadBtn extends Component {

	downloadFile(){
		let rowData = this.props.dependentValues;
		console.log("download this file", rowData.fileName)
	}

	render(){
		// console.log("getting row data", this.props.dependentValues)
		return(
			<div>
				<Button bsSize="xsmall" onClick={this.downloadFile.bind(this)}>Download</Button>
			</div>
		)
	}
}

export default DownloadBtn;