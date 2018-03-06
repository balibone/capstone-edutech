import React from 'react';
import { Paper } from 'material-ui';
import { FormControl, ButtonToolbar, Button, Col, Row } from 'react-bootstrap';
import { DateTimePicker, DropdownList } from 'react-widgets';
import moment from 'moment';
import momentLocalizer from 'react-widgets-moment';
import './styles.css';

moment.locale('en');
momentLocalizer();

const EditSingleGroupTask = ({
  edittedTitle, edittedDate, edittedAssignee,
  task, handleChangeTitleEdit, handleChangeDateEdit,
  handleChangeAssigneeEdit, handleSaveEdit, handleCancelEdit,
  removeTask, groupMemberNames,
}) => {
  const edittedDateValue = edittedDate ? new Date(edittedDate) : null;
  const edittedAssigneeValue =
    edittedAssignee[0] ? edittedAssignee[0].username : null;
  console.log(edittedAssigneeValue);
  return (
    <Paper className="taskItem paperDefault">
      <div>
        <Row>
          <Col md={12}>
            <FormControl
              type="text"
              placeholder="Add group task"
              value={edittedTitle}
              onChange={e => handleChangeTitleEdit(e)}
            />
          </Col>
        </Row>
        <Row className="smallTopGap">
          <Col md={6}>
            <DropdownList
              data={groupMemberNames}
              placeholder="Assign a member"
              onChange={assignedTo => handleChangeAssigneeEdit(assignedTo)}
              value={edittedAssigneeValue}
            />
          </Col>
          <Col md={6}>
            <DateTimePicker
              time={false}
              min={new Date()}
              placeholder="Set deadline"
              onChange={selectedDate => handleChangeDateEdit(selectedDate)}
              value={edittedDateValue}
            />
          </Col>
        </Row>
      </div>
      <ButtonToolbar>
        <Button bsStyle="primary" bsSize="xsmall" onClick={() => handleSaveEdit(task)}>
          Save
        </Button>
        <Button bsStyle="danger" bsSize="xsmall" onClick={() => removeTask(task)}>
          delete
        </Button>
        <Button bsSize="xsmall" onClick={() => handleCancelEdit(task)}>
          cancel
        </Button>
      </ButtonToolbar>
    </Paper>
  );
}

export default EditSingleGroupTask;
