import {Component, HostListener, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {entrepriseId} from '../../../../utils/entrepriseId';
import {map, Observable} from 'rxjs';
import {UploadFileService} from '../../../services/uploadFile/upload-file.service';
import {UserServicesService} from '../../../services/userServices/user-services.service';
import {DomSanitizer} from '@angular/platform-browser';
import {MatDialog} from '@angular/material/dialog';
import {MatSnackBar} from '@angular/material/snack-bar';
import {ConfirmDialogModel, ConfirmDialogueComponent} from '../../confirm-dialogue/confirm-dialogue.component';
import {ProfilePageComponent} from '../../profile-page/profile-page.component';
import {FournisseurServicesService} from '../../../services/fournisseurServices/fournisseur-services.service';

@Component({
  selector: 'app-page-list-fournisseurs',
  templateUrl: './page-list-fournisseurs.component.html',
  styleUrls: ['./page-list-fournisseurs.component.scss']
})
export class PageListFournisseursComponent implements OnInit {

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
    private serviceFournisseur:FournisseurServicesService,
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
    this.serviceFournisseur.getAllDataByEntreprise(this.idEntreprise,this.pages).pipe(
      map((x:any)=>x.map((y:any)=>
        this.uploadFileServiceprivate.createImageFournisseur(y))))
      .subscribe(
        (data)=> {
          this.aboutus = data;
          console.log(this.aboutus)
        });
    this.pagesTabes=this.serviceFournisseur.getNumberOfPages(this.idEntreprise);

  }
  noveaux() {
    this.testVar=true;
    setTimeout(this.codingCourse.bind(this),1000);
  }
  codingCourse(){
    this.router.navigate(['addfr']);
    this.testVar=false;
  }
  delete(id) {
    this.serviceFournisseur.deleteFournisseur(id);

  }

  upadate(fournisseurId:number) {
    this.router.navigate(['/addfr',{fournisseurId:fournisseurId}]);
  }

  Next() {
    this.serviceFournisseur.getAllDataByEntreprise(this.idEntreprise,++this.pages).pipe(
      map((x:any)=>x.map((y:any)=>
        this.uploadFileServiceprivate.createImageFournisseur(y)
      ))
    ).subscribe(
      (data)=> {
        console.log(data);
        this.aboutus = data;
      }
    );
  }

  Previous() {
    this.serviceFournisseur.getAllDataByEntreprise(this.idEntreprise,--this.pages).pipe(
      map((x:any)=>x.map((y:any)=>
        this.uploadFileServiceprivate.createImageFournisseur(y)
      ))
    ).subscribe(
      (data)=> {
        console.log(data);
        this.aboutus = data;
      }
    );

  }
  deleteDiague(id: number): void {
    const message = `ce fournisseur`;

    const dialogData = new ConfirmDialogModel("le fournisseur", message);

    const dialogRef = this.dialog.open(ConfirmDialogueComponent, {
      maxWidth: "400px",
      data: dialogData
    });

    dialogRef.afterClosed().subscribe(dialogResult => {
      if(dialogResult){
        this.testVar=true;
        this.serviceFournisseur.deleteFournisseur(id);
        this.serviceFournisseur.getAllDataByEntreprise(this.idEntreprise,this.pages).pipe(
          map((x:any)=>x.map((y:any)=>
            this.uploadFileServiceprivate.createImageFournisseur(y)
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
