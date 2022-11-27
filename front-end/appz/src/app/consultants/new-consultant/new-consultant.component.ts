import {Component, Inject, OnInit} from "@angular/core";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {UserService} from "../../services/user.service";
import {User} from "../../models/user";
import {Observable, Subject, takeUntil} from "rxjs";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {SnackbarService} from "../../services/snack-bar.service";

@Component({
  selector: 'app-new-consultant',
  templateUrl: './new-consultant.component.html',
  styleUrls: ['./new-consultant.component.css']
})
export class NewConsultantComponent implements OnInit {

  public consultant: User;

  public createForm: FormGroup;

  private destroy$ = new Subject();

  constructor(
    private formBuilder: FormBuilder,
    private userService: UserService,
    public dialogRef: MatDialogRef<NewConsultantComponent>,
    @Inject(MAT_DIALOG_DATA) public data: {adding: boolean, consultant: User, consultants: User[]},
    private snackBarService: SnackbarService
  ) { }

  public ngOnInit() {
    this.createForm = this.formBuilder.group({
      name: [this.data.adding ? "" : this.data.consultant.name, [Validators.required]],
      surname: [this.data.adding ? "" : this.data.consultant.surname, [Validators.required]],
      location: [this.data.adding ? "" : this.data.consultant.location, [Validators.required]],
      email: [this.data.adding ? "" : this.data.consultant.email, [Validators.required, Validators.email]],
      password: [this.data.adding ? "" : this.data.consultant.password, [Validators.required]],
      birthDate: [this.data.adding ? new Date() : this.data.consultant.birthDate, [Validators.required]]
    });
  }

  public ngOnDestroy(): void {
    this.destroy$.next(true);
    this.destroy$.complete();
  }

  public onSubmit(): void {
    this.consultant = this.createForm.value;
    this.consultant.id ? this.consultant.id = this.data.consultant.id : 0;

    if (this.data.adding && !this.checkEmailUnique(this.consultant.email)) {
      this.createForm.controls.email.setErrors({ ...(this.createForm.controls.email.errors || {}), 'exists': 'Email already exists' })
    }

    if (this.createForm.invalid)
    {
      return;
    }

    const result: Observable<User> = this.data.adding ? this.userService.createConsultant(this.consultant) : this.userService.updateUser(this.consultant);
    result
      .pipe(takeUntil(this.destroy$))
      .subscribe({
        next: () => {
          this.dialogRef.close(this.consultant);
        },
        error: (error) => {
          console.warn(error)
          this.snackBarService.error(error.error.message)
        }
      })
  }

  private checkEmailUnique(email: string): boolean {
    return !this.data.consultants.filter(consultant => consultant.email === email).length;
  }
}
