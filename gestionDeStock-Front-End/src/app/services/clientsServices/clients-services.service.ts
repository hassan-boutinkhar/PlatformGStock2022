import { Injectable } from '@angular/core';
import {environment} from '../../../utils/envirments';
import {HttpClient} from '@angular/common/http';
import {Observable, Subscription} from 'rxjs';
import {clientsDTO} from '../../DTO/clientsDTO';

@Injectable({
  providedIn: 'root'
})
export class ClientsServicesService {

  private apiServerUrl = environment.apiBaseUrl;
  constructor(private http: HttpClient) { }
  public getAllDataByEntreprise(idEntreprise:string,page:number): Observable<clientsDTO[]> {
    return this.http.get<clientsDTO[]>(`${this.apiServerUrl}/client/allByIdEntreprise/` + idEntreprise+ '?page=' +page);
  }
  public addClient(client: FormData) {
    return this.http.post<clientsDTO>(`${this.apiServerUrl}/client/addNewClient`,client);
  }
  public deleteClient(id: number): Subscription {
    return this.http.delete<void>(`${this.apiServerUrl}/client/delete/` + id ).subscribe();
  }
  public getClientById(id: string): Observable<clientsDTO> {
    return this.http.get<clientsDTO>(`${this.apiServerUrl}/client/clientId/`+ id);
  }
  public updateClient(client: FormData): Subscription {
    return this.http.put<clientsDTO>(`${this.apiServerUrl}/client/updateclients`, client).subscribe();
  }
  public getClientByMailAndIdEntreprise(mail: string, IdEntreprise: string): Observable<clientsDTO> {
    return this.http.get<clientsDTO>(`${this.apiServerUrl}/client/getByMailclientsAndIdE/${mail}/${IdEntreprise}`);
  }
  public getNumberOfPages(idEntreprise: string): Observable<number> {
    return this.http.get<number>(`${this.apiServerUrl}/client/getNumberpage/`+idEntreprise );
  }
  public getNumberOfData(idEntreprise: string): Observable<number> {
    return this.http.get<number>(`${this.apiServerUrl}/client/getNumberData/`+idEntreprise );
  }
}
