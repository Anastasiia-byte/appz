import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Dwelling} from "../models/dwelling";

@Injectable({
  providedIn: 'root'
})
export class DwellingService {
  private readonly serverUrl = 'http://localhost:8080/api/dwelling';

  constructor(private http: HttpClient) {
  }

  public getAllDwellings(): Observable<Dwelling[]> {
    return this.http.get<Dwelling[]>(this.serverUrl);
  }
}
