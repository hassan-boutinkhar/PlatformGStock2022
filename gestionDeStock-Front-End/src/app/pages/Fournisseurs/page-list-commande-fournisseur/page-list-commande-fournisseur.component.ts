import {Component, HostListener, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {MatDialog} from '@angular/material/dialog';
import {entrepriseId} from '../../../../utils/entrepriseId';
import {map, Observable} from 'rxjs';
import {UntypedFormBuilder, UntypedFormGroup, Validators} from '@angular/forms';
import {commandeDTO} from '../../../DTO/commandeDTO';
import {UploadFileService} from '../../../services/uploadFile/upload-file.service';
import {DomSanitizer} from '@angular/platform-browser';
import {MatSnackBar} from '@angular/material/snack-bar';
import {ConfirmDialogModel, ConfirmDialogueComponent} from '../../confirm-dialogue/confirm-dialogue.component';
import {ProfilePageComponent} from '../../profile-page/profile-page.component';
import {HttpErrorResponse} from '@angular/common/http';
import {CommandeFournisseursServiceService} from '../../../services/commandeFournisseurService/commande-fournisseurs-service.service';
import {FournisseurServicesService} from '../../../services/fournisseurServices/fournisseur-services.service';

@Component({
  selector: 'app-page-list-commande-fournisseur',
  templateUrl: './page-list-commande-fournisseur.component.html',
  styleUrls: ['./page-list-commande-fournisseur.component.scss']
})
export class PageListCommandeFournisseurComponent implements OnInit {
  imagePath: any="../assets/img/icons/User_icon_2.svg.png";
  idEntreprise=entrepriseId.entrepriseIdValus;
  pages:number=0;
  pagesTabes: Observable<number>;
  aboutus: any = null;
  screenHeight: number;
  screenWidth: number;
  testVar:boolean=false;
  display = 'none';
  myForm: UntypedFormGroup;
  commandeCliente:commandeDTO=new commandeDTO();
  constructor(
    private uploadFileServiceprivate:UploadFileService,
    private router: Router,
    private commandeFournisseur:CommandeFournisseursServiceService,
    private sanitizer: DomSanitizer,
    public dialog: MatDialog,
    private snackBar: MatSnackBar,
    private fb: UntypedFormBuilder,
    private fournisseurService:FournisseurServicesService) {
    this.getScreenSize();
  }

  @HostListener('window:resize', ['$event'])
  getScreenSize(event?) {
    this.screenHeight = window.innerHeight;
    this.screenWidth = window.innerWidth;
    console.log(this.screenHeight, this.screenWidth);
  }

  ngOnInit(): void {
    this.commandeFournisseur.getAllDataByEntreprise(this.idEntreprise,this.pages)
      .subscribe(
        (data)=> {
          this.aboutus = data;
          console.log(this.aboutus)
        });
    this.pagesTabes=this.commandeFournisseur.getNumberOfPages(this.idEntreprise);
    this.myForm = this.fb.group({
      etat: [this.commandeCliente.etatCommande, Validators.required]
    });
    setTimeout (()=>{
      for(let i=0;i<this.aboutus.length;i++){
        this.fournisseurService.getFournisseurById(this.aboutus[i].idClient).subscribe(data=>this.aboutus[i].fournisseur=data.nom+" "+data.prenom)
      }
    },50)
  }
  noveaux() {
    this.testVar=true;
    setTimeout(this.codingCourse.bind(this),1000);
  }
  codingCourse(){
    this.router.navigate(['noveaucommandefournisseur']);
    this.testVar=false;
  }
  delete(id) {
    //this.commandeFournisseur.deleteUtilisateurs(id);

  }

  upadate(userId:number) {
    this.router.navigate(['/adduser',{userId:userId}]);
  }

  Next() {
    this.commandeFournisseur.getAllDataByEntreprise(this.idEntreprise,++this.pages).subscribe(
      (data)=> {
        console.log(data);
        this.aboutus = data;
      }
    );
    setTimeout (()=>{
      for(let i=0;i<this.aboutus.length;i++){
        this.fournisseurService.getFournisseurById(this.aboutus[i].idClient).subscribe(data=>this.aboutus[i].fournisseur=data.nom+" "+data.prenom)
      }
    },50)
  }

  Previous() {
    this.commandeFournisseur.getAllDataByEntreprise(this.idEntreprise,--this.pages).subscribe(
      (data)=> {
        console.log(data);
        this.aboutus = data;
      }
    );
    setTimeout (()=>{
      for(let i=0;i<this.aboutus.length;i++){
        this.fournisseurService.getFournisseurById(this.aboutus[i].idClient).subscribe(data=>this.aboutus[i].fournisseur=data.nom+" "+data.prenom)
      }
    },50)
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
        //this.commandeFournisseur.deleteUtilisateurs(id);
        this.commandeFournisseur.getAllDataByEntreprise(this.idEntreprise,this.pages).pipe(
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



  onCloseHandled() {
    this.display = 'none';
  }


  openModal(id:number) {
    this.display = 'block';
    this.commandeFournisseur.getCommandeById(id).subscribe(
      (data)=>{
        this.commandeCliente=data;
        this.myForm = this.fb.group({
          etat: [this.commandeCliente.etatCommande, Validators.required]
        });
      }

    )

  }
  onSubmit(form: UntypedFormGroup) {
    this.commandeCliente.etatCommande=form.value.etat;
    this.commandeFournisseur.addCommande(this.commandeCliente).subscribe(
      (response:commandeDTO)=>{
        this.myForm.reset();

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
    setTimeout(()=>{
      location.reload();
    },3000)
  }
}
