import {Component, HostListener, OnInit} from '@angular/core';
import {entrepriseId} from '../../../../utils/entrepriseId';
import {map, Observable} from 'rxjs';
import {UploadFileService} from '../../../services/uploadFile/upload-file.service';
import {Router} from '@angular/router';
import {ArticleServiceService} from '../../../services/article/article-service.service';
import {DomSanitizer} from '@angular/platform-browser';
import {MatDialog} from '@angular/material/dialog';
import {MatSnackBar} from '@angular/material/snack-bar';
import {articleDTO} from '../../../DTO/articleDTO';
import {ConfirmDialogModel, ConfirmDialogueComponent} from '../../confirm-dialogue/confirm-dialogue.component';
import {DetailsComponent} from '../../mvstocks/details/details.component';
import {ProfilePageComponent} from '../../profile-page/profile-page.component';
import {UserServicesService} from '../../../services/userServices/user-services.service';

@Component({
  selector: 'app-list-user',
  templateUrl: './list-user.component.html',
  styleUrls: ['./list-user.component.scss']
})
export class ListUserComponent implements OnInit {

  imagePath: any="../assets/img/icons/User_icon_2.svg.png";
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
    private serviceArticle:UserServicesService,
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
        this.uploadFileServiceprivate.createImageUser(y))))
      .subscribe(
        (data)=> {
          this.aboutus = data;
          console.log(this.aboutus)
        });
    this.pagesTabes=this.serviceArticle.getNumberOfPages(this.idEntreprise);

  }
  noveaux() {
    this.testVar=true;
    setTimeout(this.codingCourse.bind(this),1000);
  }
  codingCourse(){
    this.router.navigate(['adduser']);
    this.testVar=false;
  }
  delete(id) {
    this.serviceArticle.deleteUtilisateurs(id);

  }

  upadate(userId:number) {
    this.router.navigate(['/adduser',{userId:userId}]);
  }

  Next() {
    this.serviceArticle.getAllDataByEntreprise(this.idEntreprise,++this.pages).pipe(
      map((x:any)=>x.map((y:any)=>
        this.uploadFileServiceprivate.createImageUser(y)
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
        this.uploadFileServiceprivate.createImageUser(y)
      ))
    ).subscribe(
      (data)=> {
        console.log(data);
        this.aboutus = data;
      }
    );

  }
  deleteDiague(id: number): void {
    const message = `ce utilisateur`;

    const dialogData = new ConfirmDialogModel("l'utilisateur", message);

    const dialogRef = this.dialog.open(ConfirmDialogueComponent, {
      maxWidth: "400px",
      data: dialogData
    });

    dialogRef.afterClosed().subscribe(dialogResult => {
      if(dialogResult){
        this.testVar=true;
        this.serviceArticle.deleteUtilisateurs(id);
        this.serviceArticle.getAllDataByEntreprise(this.idEntreprise,this.pages).pipe(
          map((x:any)=>x.map((y:any)=>
            this.uploadFileServiceprivate.createImageUser(y)
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


  openDialog(): void {
    const dialogRef = this.dialog.open(ProfilePageComponent, {
      width: '90%', height: '90%'});

    dialogRef.afterClosed().subscribe(result => {
    });
  }
}
