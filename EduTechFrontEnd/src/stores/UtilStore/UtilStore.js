import { observable, action } from 'mobx';


class UtilStore {
    @observable snackbarIsOpen = false;
    @observable snackbarMessage = '';

    @action openSnackbar(snackbarMessage) {
      this.snackbarMessage = snackbarMessage;
      this.snackbarIsOpen = true;
    }
    @action closeSnackbar() {
      this.snackbarMessage = '';
      this.snackbarIsOpen = false;
    }
}


export default new UtilStore();
