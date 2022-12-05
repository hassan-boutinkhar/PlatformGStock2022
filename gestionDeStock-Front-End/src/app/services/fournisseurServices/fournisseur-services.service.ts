import { Injectable } from '@angular/core';
import {environment} from '../../../utils/envirments';
import {HttpClient} from '@angular/common/http';
import {Observable, Subscription} from 'rxjs';
import {clientsDTO} from '../../DTO/clientsDTO';

@Injectable({
  providedIn: 'root'
})
export class FournisseurServicesService {

  private apiServerUrl = environment.apiBaseUrl;
  constructor(private http: HttpClient) { }
  public getAllDataByEntreprise(idEntreprise:string,page:number): Observable<clientsDTO[]> {
    return this.http.get<clientsDTO[]>(`${this.apiServerUrl}/fournisseur/allByIdEntreprise/` + idEntreprise+ '?page=' +page);
  }
  public addFournisseur(fournisseur: FormData) {
    return this.http.post<clientsDTO>(`${this.apiServerUrl}/fournisseur/addNewFournisseur`,fournisseur);
  }
  public deleteFournisseur(id: number): Subscription {
    return this.http.delete<void>(`${this.apiServerUrl}/fournisseur/delete/` + id ).subscribe();
  }
  public getFournisseurById(id: string): Observable<clientsDTO> {
    return this.http.get<clientsDTO>(`${this.apiServerUrl}/fournisseur/fournisseurId/`+ id);
  }
  public updateFournisseur(fournisseur: FormData): Subscription {
    return this.http.put<clientsDTO>(`${this.apiServerUrl}/fournisseur/updateFournisseur`, fournisseur).subscribe();
  }
  public getFournisseurByMailAndIdEntreprise(mail: string, IdEntreprise: string): Observable<clientsDTO> {
    return this.http.get<clientsDTO>(`${this.apiServerUrl}/fournisseur/getByMailFournisseurAndIdE/${mail}/${IdEntreprise}`);
  }
  public getNumberOfPages(idEntreprise: string): Observable<number> {
    return this.http.get<number>(`${this.apiServerUrl}/fournisseur/getNumberpage/`+idEntreprise );
  }
  public getNumberOfData(idEntreprise: string): Observable<number> {
    return this.http.get<number>(`${this.apiServerUrl}/fournisseur/getNumberData/`+idEntreprise );
  }
}
