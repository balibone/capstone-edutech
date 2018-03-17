import React, {Component} from 'react';
import { Modal,Button } from 'react-bootstrap';

class AddCalendarItemModal extends Component {

	render(){
		let show = this.props.show;


		return(
			<Modal show={show} onHide={this.props.handleCloseModal()}>
	          <Modal.Header closeButton>
	            <Modal.Title>Add New Calendar Item</Modal.Title>
	          </Modal.Header>
	          <Modal.Body>
	            <h4>Text in a modal</h4>
	            
	          </Modal.Body>
	          <Modal.Footer>
	            <Button onClick={this.props.handleCloseModal()}>Close</Button>
	          </Modal.Footer>
	        </Modal>
		)
	}
}

export default AddCalendarItemModal;