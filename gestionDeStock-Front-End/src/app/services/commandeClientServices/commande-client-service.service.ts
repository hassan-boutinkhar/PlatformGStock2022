import { Injectable } from '@angular/core';
import {environment} from '../../../utils/envirments';
import {HttpClient} from '@angular/common/http';
import {Observable, Subscription} from 'rxjs';
import {commandeDTO} from '../../DTO/commandeDTO';

@Injectable({
  providedIn: 'root'
})
export class CommandeClientServiceService {

  private apiServerUrl = environment.apiBaseUrl;
  constructor(private http: HttpClient) { }
  public getAllDataByEntreprise(idEntreprise:string,page:number): Observable<commandeDTO[]> {
    return this.http.get<commandeDTO[]>(`${this.apiServerUrl}/commandeClient/allByIdEntreprise/` + idEntreprise+ '?page=' +page);
  }
  public addCommande(commandeClient: commandeDTO) {
    return this.http.post<commandeDTO>(`${this.apiServerUrl}/commandeClient/addCommandeClient`,commandeClient);
  }
  public deleteCommande(id: number): Subscription {
    return this.http.delete<void>(`${this.apiServerUrl}/commandeClient/delete/` + id ).subscribe();
  }
  public getCommandeById(id: number): Observable<commandeDTO> {
    return this.http.get<commandeDTO>(`${this.apiServerUrl}/commandeClient/getCommandeById/`+ id);
  }
  public updateCommande(commandeClient: commandeDTO): Subscription {
    return this.http.put<commandeDTO>(`${this.apiServerUrl}/commandeClient/updateCommande`, commandeClient).subscribe();
  }
  public getCommandeByCodeCommandeAndIdEntreprise(codeCommande: string, IdEntreprise: string): Observable<commandeDTO> {
    return this.http.get<commandeDTO>(`${this.apiServerUrl}/commandeClient/getByCodeCommandeAndIdE/${codeCommande}/${IdEntreprise}`);
  }
  public getNumberOfPages(idEntreprise: string): Observable<number> {
    return this.http.get<number>(`${this.apiServerUrl}/commandeClient/getNumberpage/`+idEntreprise );
  }
  public getNumberOfData(idEntreprise: string): Observable<number> {
    return this.http.get<number>(`${this.apiServerUrl}/commandeClient/getNumberData/`+idEntreprise );
  }
}
