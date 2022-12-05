import { Injectable } from '@angular/core';
import {environment} from '../../../utils/envirments';
import {HttpClient} from '@angular/common/http';
import {Observable, Subscription} from 'rxjs';
import {userDTO} from '../../DTO/userDTO';
import {LoginData} from '../../DTO/LoginData';

@Injectable({
  providedIn: 'root'
})
export class UserServicesService {

  private apiServerUrl = environment.apiBaseUrl;
  constructor(private http: HttpClient) { }
  public getAllDataByEntreprise(idEntreprise:string,page:number): Observable<userDTO[]> {
    return this.http.get<userDTO[]>(`${this.apiServerUrl}/utilisateur/allByIdEntreprise/` + idEntreprise+ '?page=' +page);
  }
  public addUtilisateurs(utilisateur: FormData){
    console.log(utilisateur.get('article'))
    console.log(utilisateur.get('imageFile'))
    return this.http.post<userDTO>(`${this.apiServerUrl}/utilisateur/addNewUtilisateurs`,utilisateur);
  }
  public deleteUtilisateurs(id: number): Subscription {
    return this.http.delete<void>(`${this.apiServerUrl}/utilisateur/delete/` + id ).subscribe();
  }
  public getUtilisateursById(id: string): Observable<userDTO> {
    return this.http.get<userDTO>(`${this.apiServerUrl}/utilisateur/utilisateurId/`+ id);
  }
  public updateUtilisateurs(utilisateur: FormData): Subscription {
    return this.http.put<userDTO>(`${this.apiServerUrl}/utilisateur/updateUtilisateurs`, utilisateur).subscribe();
  }
  public getUtilisateursByMailAndIdEntreprise(mail: string, IdEntreprise: string): Observable<userDTO> {
    return this.http.get<userDTO>(`${this.apiServerUrl}/utilisateur/getByMailUtilisateursAndIdE/${mail}/${IdEntreprise}`);
  }
  public getNumberOfPages(idEntreprise: string): Observable<number> {
    return this.http.get<number>(`${this.apiServerUrl}/utilisateur/getNumberpage/`+idEntreprise );
  }

  public findUserLogin(mail:string,password:string):Observable<userDTO[]>{
     //let userLogin: LoginData = new LoginData(mail, password);
    return this.http.get<userDTO[]>(`${this.apiServerUrl}/utilisateur/findUserLogin/${mail}/${password}`);
  }
}
