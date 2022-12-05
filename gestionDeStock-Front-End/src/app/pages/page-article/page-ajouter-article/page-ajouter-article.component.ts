import {Component, HostListener, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {UntypedFormBuilder, UntypedFormGroup, Validators} from '@angular/forms';
import {CategoryService} from '../../../services/category/category.service';
import {Observable} from 'rxjs';
import {categoryDTO} from '../../../DTO/categoryDTO';
import {entrepriseId} from '../../../../utils/entrepriseId';
import {MatSnackBar} from '@angular/material/snack-bar';
import {Location} from '@angular/common';
import {articleDTO} from '../../../DTO/articleDTO';
import {ArticleServiceService} from '../../../services/article/article-service.service';
import {UploadFileService} from '../../../services/uploadFile/upload-file.service';
import {ImageUploadModule} from '../../../DTO/image-upload.module';
import {DomSanitizer} from '@angular/platform-browser';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';

@Component({
  selector: 'app-page-ajouter-article',
  templateUrl: './page-ajouter-article.component.html',
  styleUrls: ['./page-ajouter-article.component.scss']
})
export class PageAjouterArticleComponent implements OnInit {

  private entrepriseId = entrepriseId.entrepriseIdValus;
  object:any;
  myForm: UntypedFormGroup;
  listCotegory:Observable<categoryDTO[]>;
  testVar:boolean=false;
  screenHeight: number;
  screenWidth: number;
  selectedFiles?: FileList;
   fileName:ImageUploadModule={
    file:null,
    url:null
  }
   id:any;// TEST IF ARTICLE IS ALEARDY EXIST
   oBT:Observable<articleDTO>;//TO MAKE DATA SELECTED IN UPDATE
  articleObject:articleDTO=new articleDTO();
  constructor(
    private router: ActivatedRoute,
    private fb: UntypedFormBuilder,
    private categoryService: CategoryService,
    private uploadFileService:UploadFileService,
    private articleService: ArticleServiceService,
    private snackBar: MatSnackBar,
    private location: Location,
    private sanitizer: DomSanitizer,
    private http: HttpClient) {
    this.getScreenSize();
  }
  @HostListener('window:resize', ['$event'])
  getScreenSize(event?) {
    this.screenHeight = window.innerHeight;
    this.screenWidth = window.innerWidth;
  }
  ngOnInit(): void {
    this.listCotegory = this.categoryService.getAllDataByEntreprise(this.entrepriseId, 0);//TO GET GACTRGORY BY ID ENTREPRISE
    this.id = this.router.snapshot.paramMap.get('articleId');
    if(this.id){
      this.oBT=this.articleService.getArticleById(this.id);//TO MAKE DATA SELECTED IN UPDATE
      this.articleService.getArticleById(this.id).subscribe(
        (data) =>{this.object=data;
          this.fileName=this.object.articleImage;
          this.myForm = this.fb.group({
            id: [this.object?.id],
            codearticle: [this.object?.codeArticle, Validators.required],
            Designation: [this.object?.designation, [Validators.required]],
            Prix_unitaire: [this.object?.prixUnitaireHt, [Validators.required]],
            Taux_TVA: [this.object?.touxTva, [Validators.required]],
            Prix_unitaireTTC: [this.object?.prixUnitaireTtc, [Validators.required]],
            category: [this.object?.categoryId, [Validators.required]],
            photo: [this.object?.photo],
            quantite: [this.object?.quantite, [Validators.required]],
          });

        }
      );
    }
    this.myForm = this.fb.group({
      id: [this.object?.id],
      codearticle: [this.object?.codeArticle, Validators.required],
      Designation: [this.object?.designation, [Validators.required]],
      Prix_unitaire: [this.object?.prixUnitaireHt, [Validators.required]],
      Taux_TVA: [this.object?.touxTva, [Validators.required]],
      Prix_unitaireTTC: [this.object?.prixUnitaireTtc, [Validators.required]],
      category: [this.object?.categoryId, [Validators.required]],
      photo: [this.object?.photo],
      quantite: [this.object?.quantite, [Validators.required]]
    });
    }
  onSubmit(form: UntypedFormGroup) {
    console.log(form)
    this.articleObject.codeArticle=form.value.codearticle;
    this.articleObject.designation=form.value.Designation;
    this.articleObject.touxTva=form.value.Taux_TVA;
    this.articleObject.prixUnitaireTtc=form.value.Prix_unitaireTTC;
    this.articleObject.categoryId=form.value.category;
    this.articleObject.idEntreprise=entrepriseId.entrepriseIdValus;
    this.articleObject.prixUnitaireHt=form.value.Prix_unitaire;
    this.articleObject.quantite=form.value.quantite;
    form.value.idEntreprise=entrepriseId.entrepriseIdValus;
    if(this.id){
      this.articleObject.id=this.id;
      const formDataZ:FormData=this.prapreFormData(this.articleObject);
      this.testVar=true;
      this.articleService.updateArticle(formDataZ);
      setTimeout(()=>{
        this.snackBar.open('Operation avec succés !', '', {
          duration: 3000,
          verticalPosition: 'bottom', // 'top' | 'bottom'
          horizontalPosition: 'end',
          panelClass: ['blue-snackbar']
        });
        this.myForm.reset({});
        this.testVar=false;
      },3000);
    }else {
      const formDataZ:FormData=this.prapreFormData(this.articleObject);
      console.log(formDataZ)
      this.articleService.getArticleByCodeArticleAndIdEntreprise(form.value.codearticle, form.value.idEntreprise).subscribe(
        (data) => {
          this.testVar=true;
          if(data == null)
          {
            this.articleService.addArticle(formDataZ).subscribe(
              (response:articleDTO)=>{
                form.reset();
              },
              (error:HttpErrorResponse)=>{
                console.log(error);
              }
            );
            setTimeout(()=>{
              this.snackBar.open('Operation avec succés !', '', {
                duration: 3000,
                verticalPosition: 'bottom', // 'top' | 'bottom'
                horizontalPosition: 'end',
                panelClass: ['blue-snackbar']
              });
              this.testVar=false;
            },3000);
          }
          else
          {
            console.log(form)
            setTimeout(()=>{
              this.snackBar.open('Le categorie est deja exist !', '', {
                duration: 3000,
                verticalPosition: 'bottom', // 'top' | 'bottom'
                horizontalPosition: 'end',
                panelClass: ['red-snackbar']
              });
              this.testVar=false;
            },3000);

          }
        }
      );
    }

  }
  close() {
    this.location.back();
  }
  onSelected(event){
    if(event.target.files){
      const file=event.target.files[0];
      this.fileName={
        file:file,
        url:this.sanitizer.bypassSecurityTrustUrl(
          window.URL.createObjectURL(file)
        )
      }
      this.articleObject.photo=this.fileName;
    }
  }
  prapreFormData(article:articleDTO):FormData {
    const formData = new FormData();
    formData.append(
      'article',
      new Blob([JSON.stringify(article)], {type: 'application/json'})
    );
    if (this.fileName.file != null) {
      formData.append(
        'imageFile',
        article.photo.file,
        article.photo.file.name
      );
      this.fileName.file = null;
    }
    return formData;
  }
}
