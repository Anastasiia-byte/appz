import {MatSnackBar} from "@angular/material/snack-bar";
import {Injectable} from "@angular/core";

@Injectable({
  providedIn: 'root'
})
export class SnackbarService {

  constructor(
    private _snackBar: MatSnackBar) {
  }

  error(message: string) {
    return this._snackBar.open(message, undefined, {panelClass: ['snackbar-error'], duration: 5000});
  }

  success(message: string) {
    return this._snackBar.open(message, undefined, {panelClass: ['snackbar-success'], duration: 5000});
  }

  info(message: string) {
    return this._snackBar.open(message, undefined, {panelClass: ['snackbar-info'], duration: 5000});
  }
}
