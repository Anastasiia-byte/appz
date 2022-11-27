import {Lessor} from "./lessor";

export interface Dwelling {
  id: number;
  name: string;
  photo: string;
  location: string;
  numberOfRooms: number;
  arranged: boolean;
  balcony: boolean;
  description: string;
  lessor: Lessor;
}
