import { Injectable } from '@angular/core';
import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot} from '@angular/router';
import {articleDTO} from '../../DTO/articleDTO';
import {ArticleServiceService} from '../article/article-service.service';
import {map, Observable} from 'rxjs';
import {ImageUploadModule} from '../../DTO/image-upload.module';
import {categoryDTO} from '../../DTO/categoryDTO';
import {UploadFileService} from '../uploadFile/upload-file.service';

@Injectable({
  providedIn: 'root'
})
export class ArticleResolvServiceService {

  /*
  constructor(private articleService:ArticleServiceService,private uploadFileService:UploadFileService) { }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<articleDTO> | Promise<articleDTO> | articleDTO {
    const id = route.paramMap.get('articleId');
    if (id) {
      return this.articleService.getArticleById(id).pipe(
        map(
          p=> this.uploadFileService.createImage(p)
        )
      );
    }
  }
  getArticleDetails(){
    return{
      codeArticle: "",
      designation:"",
      prixUnitaireHt: 0,
      touxTva: 0,
      photo:null,
      prixUnitaireTtc: 0,
      categoryId: null,
      idEntreprise:""
    }

  }
*/


}
