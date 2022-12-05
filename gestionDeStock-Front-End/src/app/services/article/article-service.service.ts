import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable, Subscription} from 'rxjs';
import {articleDTO} from '../../DTO/articleDTO';
import {environment} from '../../../utils/envirments';
import {error} from 'protractor';

@Injectable({
  providedIn: 'root'
})
export class ArticleServiceService {
  private apiServerUrl = environment.apiBaseUrl;
  constructor(private http: HttpClient) { }
  public getAllDataByEntreprise(idEntreprise:string,page:number): Observable<articleDTO[]> {
    return this.http.get<articleDTO[]>(`${this.apiServerUrl}/article/allByIdEntreprise/` + idEntreprise+ '?page=' +page);
  }
  public addArticle(article: FormData) {
    console.log(article.get('article'))
    console.log(article.get('imageFile'))
    return this.http.post<articleDTO>(`${this.apiServerUrl}/article/addNewArticle`,article);
  }
  public deleteArticle(id: number): Subscription {
    return this.http.delete<void>(`${this.apiServerUrl}/article/delete/` + id ).subscribe();
  }
  public getArticleById(id: string): Observable<articleDTO> {
    return this.http.get<articleDTO>(`${this.apiServerUrl}/article/articleId/`+ id);
  }
  public updateArticle(article: FormData): Subscription {
    return this.http.put<articleDTO>(`${this.apiServerUrl}/article/updateArticle`, article).subscribe();
  }
  public getArticleByCodeArticleAndIdEntreprise(codeArticle: string, IdEntreprise: string): Observable<articleDTO> {
    return this.http.get<articleDTO>(`${this.apiServerUrl}/article/getByCodeArticleAndIdE/${codeArticle}/${IdEntreprise}`);
  }
  public getNumberOfPages(idEntreprise: string): Observable<number> {
    return this.http.get<number>(`${this.apiServerUrl}/article/getNumberpage/`+idEntreprise );
  }
}
