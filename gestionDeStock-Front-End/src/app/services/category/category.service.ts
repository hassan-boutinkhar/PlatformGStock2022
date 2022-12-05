import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable, Subscription} from 'rxjs';
import {categoryDTO} from '../../DTO/categoryDTO';
import {environment} from '../../../utils/envirments';

export const BASE_URI = 'http://localhost:8080/gestionDeStock/v1/api/category/addCategory';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }
  public addCategory(category: categoryDTO): Subscription {
    return this.http.post<categoryDTO>(`${this.apiServerUrl}/category/addCategory`, category).subscribe();
  }
  public deleteCategory(id: number): Subscription {
    return this.http.delete<void>(`${this.apiServerUrl}/category/delete/` + id ).subscribe();
  }
  public getCategoryByCodeCategoryAndIdEntreprise(codeCategory: string, IdEntreprise: string): Observable<categoryDTO> {
    return this.http.get<categoryDTO>(`${this.apiServerUrl}/category/getByCodeCategoryAndIdE/${codeCategory}/${IdEntreprise}`);
  }
  public getAllDataByEntreprise(idEntreprise:string,page:number): Observable<categoryDTO[]> {
    return this.http.get<categoryDTO[]>(`${this.apiServerUrl}/category/allByIdEntreprise/` + idEntreprise+ '?page=' +page);
  }
  public updateCategory(category: categoryDTO): Subscription {
    return this.http.put<categoryDTO>(`${this.apiServerUrl}/category/updateCategory`, category).subscribe();
  }
  public getNumberOfPages(idEntreprise: string): Observable<number> {
    return this.http.get<number>(`${this.apiServerUrl}/category/getNumberpage/`+idEntreprise );
  }
}
