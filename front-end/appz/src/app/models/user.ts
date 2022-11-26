import {Role} from "./role";

export class User {
  private name: string;
  private surname: string;
  private email: string;
  private password: string;
  private birthDate: Date;
  private roles: Set<Role>;
}
