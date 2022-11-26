import {Role} from "./role";

export class User {
  public id: number;
  public name: string;
  public surname: string;
  public email: string;
  public password: string;
  public birthDate: Date;
  public location: string;
  public roles: Set<Role>;
}
