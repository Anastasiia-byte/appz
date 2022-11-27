import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Agreement} from "../models/agreement";

@Injectable({
  providedIn: 'root'
})
export class AgreementService {
  private readonly serverUrl = 'http://localhost:8080/api/agreement';

  constructor(private http: HttpClient) {
  }

  public createAgreement(userEmail: string | null, dwellingId: number): Observable<void> {
    return this.http.post<void>(this.serverUrl, {userEmail: userEmail, dwellingId: dwellingId});
  }

  public getAllAgreementsByComplete(complete: boolean): Observable<Agreement[]> {
    return this.http.get<Agreement[]>(`${this.serverUrl}/complete?complete=${complete}`);
  }

  public updateAgreement(id: number): Observable<Agreement> {
    return this.http.put<Agreement>(`${this.serverUrl}/update/${id}`, {});
  }

  public deleteAgreement(id: number): Observable<void> {
    return this.http.delete<void>(`${this.serverUrl}/delete/${id}`);
  }
}
