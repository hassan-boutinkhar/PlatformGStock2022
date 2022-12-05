import { Injectable } from '@angular/core';
import {environment} from '../../../utils/envirments';
import {HttpClient} from '@angular/common/http';
import {Observable, Subscription} from 'rxjs';
import {articleDTO} from '../../DTO/articleDTO';
import {mvtDeStockDTO} from '../../DTO/mvtDeStockDTO';

@Injectable({
  providedIn: 'root'
})
export class MvtDeStockServiceService {

  private apiServerUrl = environment.apiBaseUrl;
  constructor(private http: HttpClient) { }

  public getAllDataByEntreprise(idEntreprise:string): Subscription {
    return this.http.get<mvtDeStockDTO[]>(`${this.apiServerUrl}/mvt/allMvt/` + idEntreprise).subscribe();
  }
  public addMvtDeStock(mvt: mvtDeStockDTO):Subscription {
    return this.http.post<mvtDeStockDTO>(`${this.apiServerUrl}/mvt/addMvt`,mvt).subscribe();
  }
  public getLastMvt(idEntreprise:string,idArticle:number): Observable<mvtDeStockDTO> {
    return this.http.get<mvtDeStockDTO>(`${this.apiServerUrl}/mvt/lastMvt/` + idEntreprise+`/`+idArticle);
  }
  public getArticleMvements(idEntreprise:string,idArticle:number): Observable<mvtDeStockDTO[]> {
    return this.http.get<mvtDeStockDTO[]>(`${this.apiServerUrl}/mvt/movementsArticle/` + idEntreprise+`/`+idArticle);
  }
  public mvtIsEmpty(idEntreprise:string,idArticle:number): Observable<boolean> {
    return this.http.get<boolean>(`${this.apiServerUrl}/mvt/movementsArticleEmpty/` + idEntreprise+`/`+idArticle);
  }
}
