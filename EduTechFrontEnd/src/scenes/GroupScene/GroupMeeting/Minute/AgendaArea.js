import React, {Component} from 'react';
import {Form, FormGroup, FormControl, Button, Panel, PanelGroup, Col, Row, ControlLabel} from 'react-bootstrap';


class AgendaArea extends Component {

	constructor(){
		super()
		this.state = {
			agendaList: [],
			agenda: ""
		}
	}

	addAgenda(event){
		event.preventDefault();
		let agenda = this.state.agenda;
		console.log("agenda value: ",agenda);
		let agendaList = this.state.agendaList;
		agendaList.push(agenda);

		this.setState({agenda: "", agendaList: agendaList})
	}

	handleAgendaChange(event){
		this.setState({agenda: event.target.value})
	}

	render(){
		// console.log("agendalist : ", this.state.agendaList)
		return(
			<div> 
				<hr />
				<Form inline>
				  <FormGroup>
				    <FormControl type="text" placeholder="Add agenda title" value={this.state.agenda} onChange={this.handleAgendaChange.bind(this)}/>
				  </FormGroup>{' '}
				  <Button type="submit" bsStyle="primary" onClick={this.addAgenda.bind(this)}>Add Agenda</Button>
				</Form>

				<PanelGroup accordion id="accordion-example">
				  {
				  	this.state.agendaList.map((agenda) => {
				  		console.log(agenda)
				  		// LessonStore.fetchFilesForLeson(lesson.id);
				  		return (

				  			<Panel eventKey={agenda} className="standardTopGap">
					          
					          <Panel.Heading>
					              <Panel.Title toggle>{agenda}</Panel.Title>
					          </Panel.Heading>

					          <Panel.Body collapsible>
					              	<Row className="show-grid">
						              <Col md={12}>
						                <FormGroup controlId="formControlsTextarea">
									      <ControlLabel>Discussion</ControlLabel>
									      <FormControl componentClass="textarea" placeholder="Add discussion" />
									    </FormGroup>

									    <FormGroup controlId="formControlsTextarea">
									      <ControlLabel>Conclusion</ControlLabel>
									      <FormControl componentClass="textarea" placeholder="Add conclusion" />
									    </FormGroup>
									    <Button bsStyle="primary">Submit</Button>
						              </Col>             
						            </Row>
					          </Panel.Body>
					       </Panel>
				  		)
				  	})
				  }
				</PanelGroup>
			</div>
		)
	}
}

export default AgendaArea;