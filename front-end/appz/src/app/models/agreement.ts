import {Dwelling} from "./dwelling";
import {User} from "./user";

export interface Agreement {
  id: number;
  date: Date;
  dwelling: Dwelling;
  user: User;
  complete: boolean;
}
