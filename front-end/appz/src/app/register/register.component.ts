import {Component} from "@angular/core";
import {FormBuilder, Validators} from "@angular/forms";
import {formatDate} from "@angular/common";
import {AuthenticationService} from "../services/authentication.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  constructor(private formBuilder: FormBuilder, private authService: AuthenticationService, private router: Router) {
  }

  public registerForm = this.formBuilder.group({
    name: ["", [Validators.required]],
    surname: ["", [Validators.required]],
    location: ["", [Validators.required]],
    email: ["", [Validators.required, Validators.email]],
    password: ["", [Validators.required]],
    birthDate: [new Date(), [Validators.required]]
  });

  public register(): void {
    const user = {
      name: this.registerForm.get('name')?.value,
      surname: this.registerForm.get('surname')?.value,
      location: this.registerForm.get('location')?.value,
      email: this.registerForm.get('email')?.value,
      password: this.registerForm.get('password')?.value,
      birthDate: formatDate(this.registerForm.get('birthDate')?.value, "yyyy-MM-dd", "en-US")
    };

    this.authService.register(user).subscribe(() => {
      this.router.navigate(['/login'], {});
    });
  }
}
