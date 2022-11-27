import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Dwelling} from "../models/dwelling";
import {DwellingRequirements} from "../models/dwelling-requirements";

@Injectable({
  providedIn: 'root'
})
export class DwellingService {
  private readonly serverUrl = 'http://localhost:8080/api/dwelling';

  constructor(private http: HttpClient) {
  }

  public getAllDwellings(filter: boolean): Observable<DwellingRequirements> {
    return this.http.get<DwellingRequirements>(`${this.serverUrl}?filter=${filter}`);
  }
}
