import {Component, HostListener, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {ArticleServiceService} from '../../services/article/article-service.service';
import {articleDTO} from '../../DTO/articleDTO';
import {DomSanitizer} from '@angular/platform-browser';
import {UploadFileService} from '../../services/uploadFile/upload-file.service';
import {map, Observable} from 'rxjs';
import {entrepriseId} from '../../../utils/entrepriseId';
import {ConfirmDialogModel, ConfirmDialogueComponent} from '../confirm-dialogue/confirm-dialogue.component';
import {MatDialog} from '@angular/material/dialog';
import {MatSnackBar} from '@angular/material/snack-bar';


@Component({
  templateUrl: './page-article.component.html',
  selector: 'app-page-article',
  styleUrls: ['./page-article.component.scss']
})

// tslint:disable-next-line:class-name component-class-suffix

export class PageArticleComponent implements OnInit {

  imagePath: any="../assets/img/icons/22546762.jpg";
  idEntreprise=entrepriseId.entrepriseIdValus;
  pages:number=0;
  pagesTabes: Observable<number>;
  aboutus: any = null;
  screenHeight: number;
  screenWidth: number;
  testVar:boolean=false;
  constructor(
    private uploadFileServiceprivate:UploadFileService,
    private router: Router,
    private serviceArticle:ArticleServiceService,
    private sanitizer: DomSanitizer,
    public dialog: MatDialog,
    private snackBar: MatSnackBar) {
    this.getScreenSize();
  }

  @HostListener('window:resize', ['$event'])
  getScreenSize(event?) {
    this.screenHeight = window.innerHeight;
    this.screenWidth = window.innerWidth;
    console.log(this.screenHeight, this.screenWidth);
  }

  ngOnInit(): void {
    this.serviceArticle.getAllDataByEntreprise(this.idEntreprise,this.pages).pipe(
         map((x:any)=>x.map((y:any)=>
             this.uploadFileServiceprivate.createImage(y))))
             .subscribe(
              (data)=> {
               this.aboutus = data;
              });
    this.pagesTabes=this.serviceArticle.getNumberOfPages(this.idEntreprise);

  }
  noveaux() {
    this.testVar=true;
    setTimeout(this.codingCourse.bind(this),1000);
  }
  codingCourse(){
    this.router.navigate(['ajouterarticle']);
    this.testVar=false;
  }
  delete(id) {
    this.serviceArticle.deleteArticle(id);

  }

  upadate(articleId:articleDTO) {
    this.router.navigate(['/ajouterarticle',{articleId:articleId}]);
  }

  Next() {
    this.serviceArticle.getAllDataByEntreprise(this.idEntreprise,++this.pages).pipe(
      map((x:any)=>x.map((y:any)=>
        this.uploadFileServiceprivate.createImage(y)
      ))
    ).subscribe(
      (data)=> {
        console.log(data);
        this.aboutus = data;
      }
    );
  }

  Previous() {
    this.serviceArticle.getAllDataByEntreprise(this.idEntreprise,--this.pages).pipe(
      map((x:any)=>x.map((y:any)=>
        this.uploadFileServiceprivate.createImage(y)
      ))
    ).subscribe(
      (data)=> {
        console.log(data);
        this.aboutus = data;
      }
    );

  }
  deleteDiague(id: number): void {
    const message = `ce article`;

    const dialogData = new ConfirmDialogModel("l'article", message);

    const dialogRef = this.dialog.open(ConfirmDialogueComponent, {
      maxWidth: "400px",
      data: dialogData
    });

    dialogRef.afterClosed().subscribe(dialogResult => {
      if(dialogResult){
        this.testVar=true;
        this.serviceArticle.deleteArticle(id);
        this.serviceArticle.getAllDataByEntreprise(this.idEntreprise,this.pages).pipe(
          map((x:any)=>x.map((y:any)=>
            this.uploadFileServiceprivate.createImage(y)
          ))
        ).subscribe(
          (data)=> {
            this.aboutus = data;
          }
        );
        setTimeout(()=>{
          this.ngOnInit();
          this.snackBar.open('Operation avec succés !', '', {
            duration: 3000,
            verticalPosition: 'bottom', // 'top' | 'bottom'
            horizontalPosition: 'end',
            panelClass: ['blue-snackbar']
          });
          this.testVar=false;
        },3000);
      }
    });
  }

}
