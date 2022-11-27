import {Component, Input, OnInit} from "@angular/core";
import {ActivatedRoute, Router} from "@angular/router";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {AuthenticationService} from "../services/authentication.service";
import {first} from "rxjs";

@Component({
  selector: 'app-chat',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  form: FormGroup;
  loading = false;
  submitted = false;

  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private authenticationService: AuthenticationService
  ) { }

  ngOnInit() {
    if (localStorage.getItem('token')) {
      this.router.navigate(['/dwellings']);
    }

    this.form = new FormGroup({
      email: new FormControl('', Validators.required),
      password: new FormControl('', Validators.required)
    });
  }

  // convenience getter for easy access to form fields
  get f() { return this.form.controls; }

  onSubmit() {
    this.submitted = true;

    // stop here if form is invalid
    if (this.form.invalid) {
      return;
    }

    this.loading = true;
    this.authenticationService.login(this.f.email.value, this.f.password.value)
      .subscribe({
        next: (response) => {
          this.authenticationService.setLoggedInValue(true);
          localStorage.setItem("email", this.f.email.value);
          const data = response.data;
          const dataArray = data.split(' ');
          localStorage.setItem("token", dataArray[0]);
          localStorage.setItem("userId", dataArray[1]);
          this.authenticationService.setIsConsultantValue(dataArray[2] == "true");
          this.router.navigate(['/dwellings'], {});
        },
        error: error => {
          this.loading = false;
        }
      });
  }
}
