import { Injectable } from '@angular/core';
import {environment} from '../../../utils/envirments';
import {HttpClient} from '@angular/common/http';
import {Observable, Subscription} from 'rxjs';
import {commandeDTO} from '../../DTO/commandeDTO';

@Injectable({
  providedIn: 'root'
})
export class CommandeFournisseursServiceService {

  private apiServerUrl = environment.apiBaseUrl;
  constructor(private http: HttpClient) { }
  public getAllDataByEntreprise(idEntreprise:string,page:number): Observable<commandeDTO[]> {
    return this.http.get<commandeDTO[]>(`${this.apiServerUrl}/commandeFournisseur/allByIdEntreprise/` + idEntreprise+ '?page=' +page);
  }
  public addCommande(commandeFournisseur: commandeDTO) {
    return this.http.post<commandeDTO>(`${this.apiServerUrl}/commandeFournisseur/addCommandeFournisseurs`,commandeFournisseur);
  }
  public deleteCommande(id: number): Subscription {
    return this.http.delete<void>(`${this.apiServerUrl}/commandeFournisseur/delete/` + id ).subscribe();
  }
  public getCommandeById(id: number): Observable<commandeDTO> {
    return this.http.get<commandeDTO>(`${this.apiServerUrl}/commandeFournisseur/getCommandeById/`+ id);
  }
  public updateCommande(commandeFournisseur: commandeDTO): Subscription {
    return this.http.put<commandeDTO>(`${this.apiServerUrl}/commandeFournisseur/updateCommande`, commandeFournisseur).subscribe();
  }
  public getCommandeByCodeCommandeAndIdEntreprise(codeCommande: string, IdEntreprise: string): Observable<commandeDTO> {
    return this.http.get<commandeDTO>(`${this.apiServerUrl}/commandeFournisseur/getByCodeCommandeAndIdE/${codeCommande}/${IdEntreprise}`);
  }
  public getNumberOfPages(idEntreprise: string): Observable<number> {
    return this.http.get<number>(`${this.apiServerUrl}/commandeFournisseur/getNumberpage/`+idEntreprise );
  }
  public getNumberOfData(idEntreprise: string): Observable<number> {
    return this.http.get<number>(`${this.apiServerUrl}/commandeFournisseur/getNumberData/`+idEntreprise );
  }
}
