import {Component, HostListener, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {MatDialog} from '@angular/material/dialog';
import {PageDetailsClientsComponent} from '../page-details-clients/page-details-clients.component';
import {entrepriseId} from '../../../../utils/entrepriseId';
import {map, Observable} from 'rxjs';
import {UploadFileService} from '../../../services/uploadFile/upload-file.service';
import {UserServicesService} from '../../../services/userServices/user-services.service';
import {DomSanitizer} from '@angular/platform-browser';
import {MatSnackBar} from '@angular/material/snack-bar';
import {ConfirmDialogModel, ConfirmDialogueComponent} from '../../confirm-dialogue/confirm-dialogue.component';
import {ProfilePageComponent} from '../../profile-page/profile-page.component';
import {UntypedFormBuilder, UntypedFormGroup, Validators} from '@angular/forms';
import {commandeDTO} from '../../../DTO/commandeDTO';
import {CommandeClientServiceService} from '../../../services/commandeClientServices/commande-client-service.service';
import {HttpErrorResponse} from '@angular/common/http';
import {ClientsServicesService} from '../../../services/clientsServices/clients-services.service';

@Component({
  selector: 'app-page-list-commande',
  templateUrl: './page-list-commande.component.html',
  styleUrls: ['./page-list-commande.component.scss']
})
export class PageListCommandeComponent implements OnInit {
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
    private commandeServiceClient:CommandeClientServiceService,
    private sanitizer: DomSanitizer,
    public dialog: MatDialog,
    private snackBar: MatSnackBar,
    private fb: UntypedFormBuilder,
    private clientService:ClientsServicesService) {
    this.getScreenSize();
  }

  @HostListener('window:resize', ['$event'])
  getScreenSize(event?) {
    this.screenHeight = window.innerHeight;
    this.screenWidth = window.innerWidth;
    console.log(this.screenHeight, this.screenWidth);
  }

  ngOnInit(): void {
    this.commandeServiceClient.getAllDataByEntreprise(this.idEntreprise,this.pages)
      .subscribe(
        (data)=> {
          this.aboutus = data;
          console.log(this.aboutus)
        });
    this.pagesTabes=this.commandeServiceClient.getNumberOfPages(this.idEntreprise);
    this.myForm = this.fb.group({
      etat: [this.commandeCliente.etatCommande, Validators.required]
    });
    setTimeout (()=>{
      for(let i=0;i<this.aboutus.length;i++){
        this.clientService.getClientById(this.aboutus[i].idClient).subscribe(data=>this.aboutus[i].client=data.nom+" "+data.prenom)
      }
    },50)
  }
  noveaux() {
    this.testVar=true;
    setTimeout(this.codingCourse.bind(this),1000);
  }
  codingCourse(){
    this.router.navigate(['nvcommandeclients']);
    this.testVar=false;
  }
  delete(id) {
    //this.commandeServiceClient.deleteUtilisateurs(id);

  }

  upadate(userId:number) {
    this.router.navigate(['/adduser',{userId:userId}]);
  }

  Next() {
    this.commandeServiceClient.getAllDataByEntreprise(this.idEntreprise,++this.pages).subscribe(
      (data)=> {
        console.log(data);
        this.aboutus = data;
      }
    );
    setTimeout (()=>{
      for(let i=0;i<this.aboutus.length;i++){
        this.clientService.getClientById(this.aboutus[i].idClient).subscribe(data=>this.aboutus[i].client=data.nom+" "+data.prenom)
      }
    },50)
  }

  Previous() {
    this.commandeServiceClient.getAllDataByEntreprise(this.idEntreprise,--this.pages).subscribe(
      (data)=> {
        console.log(data);
        this.aboutus = data;
      }
    );
    setTimeout (()=>{
      for(let i=0;i<this.aboutus.length;i++){
        this.clientService.getClientById(this.aboutus[i].idClient).subscribe(data=>this.aboutus[i].client=data.nom+" "+data.prenom)
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
        //this.commandeServiceClient.deleteUtilisateurs(id);
        this.commandeServiceClient.getAllDataByEntreprise(this.idEntreprise,this.pages).pipe(
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
    this.commandeServiceClient.getCommandeById(id).subscribe(
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
    this.commandeServiceClient.addCommande(this.commandeCliente).subscribe(
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
