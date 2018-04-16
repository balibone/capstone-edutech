import React from 'react';
import { Paper } from 'material-ui';
import { FormControl, ButtonToolbar, Button } from 'react-bootstrap';
import { DateTimePicker } from 'react-widgets';
import moment from 'moment';
import momentLocalizer from 'react-widgets-moment';

import './styles.css';
// setup react-widgets moment localisation
moment.locale('en');
momentLocalizer();

const EditSingleTask = ({
  edittedTitle, edittedDate, task,
  handleChangeTitleEdit, handleChangeDateEdit,
  handleSaveEdit, handleCancelEdit, removeTask,
}) => {
  const edittedDateValue = edittedDate ? new Date(edittedDate) : null;
  return (
    <Paper className="editTaskItem">
      <FormControl
        type="text"
        placeholder="Add task"
        value={edittedTitle}
        onChange={(e) => { handleChangeTitleEdit(e); }}
        className="inputField"
      />
      <DateTimePicker
        time
        min={new Date()}
        placeholder="Set deadline"
        onChange={selectedDate => handleChangeDateEdit(selectedDate)}
        value={edittedDateValue}
      />
      <ButtonToolbar className="pull-right editActionButtons">
        <Button bsSize="xsmall" onClick={() => handleCancelEdit(task)}>
          cancel
        </Button>
        <Button bsStyle="danger" bsSize="xsmall" onClick={() => removeTask(task)}>
          delete
        </Button>
        <Button bsStyle="primary" bsSize="xsmall" onClick={() => handleSaveEdit(task)}>
          Save
        </Button>
      </ButtonToolbar>
    </Paper>
  );
};

export default EditSingleTask;
