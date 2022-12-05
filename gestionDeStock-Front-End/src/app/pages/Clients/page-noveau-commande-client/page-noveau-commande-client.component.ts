import {Component, HostListener, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {MatDialog} from '@angular/material/dialog';
import {PageDetailsClientsComponent} from '../page-details-clients/page-details-clients.component';
import {UntypedFormBuilder, UntypedFormGroup, Validators} from '@angular/forms';
import {CategoryService} from '../../../services/category/category.service';
import {MatSnackBar} from '@angular/material/snack-bar';
import {Location} from '@angular/common';
import {LigneCommandeDTO} from '../../../DTO/ligneCommandeDTO';
import {ArticleServiceService} from '../../../services/article/article-service.service';
import {ClientsServicesService} from '../../../services/clientsServices/clients-services.service';
import {entrepriseId} from '../../../../utils/entrepriseId';
import {commandeDTO} from '../../../DTO/commandeDTO';
import {CommandeClientServiceService} from '../../../services/commandeClientServices/commande-client-service.service';
import {articleDTO} from '../../../DTO/articleDTO';
import {HttpErrorResponse} from '@angular/common/http';
import {ConfirmDialogModel, ConfirmDialogueComponent} from '../../confirm-dialogue/confirm-dialogue.component';
import {map} from 'rxjs';
import {VerficationDialogueComponent} from '../../verfication-dialogue/verfication-dialogue.component';

@Component({
  selector: 'app-page-noveau-commande-client',
  templateUrl: './page-noveau-commande-client.component.html',
  styleUrls: ['./page-noveau-commande-client.component.scss']
})
export class PageNoveauCommandeClientComponent implements OnInit {

  infoclients = {
    nom : 'NOM',
    icon : '../assets/img/theme/_blog20220318100414185297078.jpg',
    prenom : 'PRENOM' ,
    numtelephone :  'NUMERO DE TELEPHONE' ,
    adress : 'ADRESS @' ,
    ville : 'code de postal VILLE',
    pays : 'PAYS',
    mail:'hassanboutinkhar15@gmail.com'
  };
  articleshow = {
    icon : '../assets/img/theme/bootstrap.jpg',
    codearticle : 'code article' ,
    description :  'description' ,
    prixnt : 1000 ,
    prixttc : 2000,
    codecategorie : 'code de categorie' ,
    descriptioncategorie : 'description de categorie'
  };
  formCommande: UntypedFormGroup;
  formLigneCommande: UntypedFormGroup;
  commandeObject: commandeDTO=new commandeDTO();
  ligneCommandeObject: LigneCommandeDTO=new LigneCommandeDTO();
  varTest: boolean;
  screenHeight: number;
  screenWidth: number;
  testVar:boolean=false;
  idEntreprise=entrepriseId.entrepriseIdValus;
  lignesCommandesClients:Array<LigneCommandeDTO>=new Array<LigneCommandeDTO>();
  article:any=null;
   listArticles: any;
  listClients: any;
  priceTotale: number=0;
  quantite: any=null;
  articleCommande:any;
  object: any;
  curUser: any=null;
  constructor(
    private router: ActivatedRoute,
    private router1: Router,
    private fb: UntypedFormBuilder,
    private categoryService: CategoryService,
    private snackBar: MatSnackBar,
    private location: Location,
    public dialog: MatDialog,
    private articleServiceService:ArticleServiceService,
    private clientsServicesService:ClientsServicesService,
    private commandeServiceService:CommandeClientServiceService) {
    this.getScreenSize();
  }
  openDialog(nmb ?: number): void {
    const dialogRef = this.dialog.open(PageDetailsClientsComponent, {
      width: '80%', height: '80%',
      data: nmb,
    });

    dialogRef.afterClosed().subscribe(result => {
    });
  }

  @HostListener('window:resize', ['$event'])
  getScreenSize(event?) {
    this.screenHeight = window.innerHeight;
    this.screenWidth = window.innerWidth;
    console.log(this.screenHeight, this.screenWidth);
  }

  ngOnInit(): void {
    this.formLigneCommande = this.fb.group({
      id:[this.ligneCommandeObject.id],
      article: [this.ligneCommandeObject.articleIdCC, Validators.required],
      quantite: [this.ligneCommandeObject.quantite, Validators.required],
    });
    this.formCommande = this.fb.group({
      id:[this.commandeObject.id],
      client: [this.commandeObject.idClient, Validators.required],
      codeCommande: [this.commandeObject.code, Validators.required],
      dateCommande: [this.commandeObject.dateCommande, Validators.required],
    });
    this.articleServiceService.getAllDataByEntreprise(this.idEntreprise,0).subscribe(
      data=>this.listArticles=data
    )
    this.clientsServicesService.getAllDataByEntreprise(this.idEntreprise,0).subscribe(
      data=>this.listClients=data
    )
  }

  addLigneSubmit(form: UntypedFormGroup) {
    this.ligneCommandeObject.quantite=form.value.quantite;
    this.ligneCommandeObject.articleIdCC=form.value.article.designation;
    this.ligneCommandeObject.prixTotale=form.value.article.prixUnitaireTtc*this.ligneCommandeObject.quantite;
    this.lignesCommandesClients.push(this.ligneCommandeObject);
    this.lignesCommandesClients.forEach(
      data=>this.priceTotale+=data.prixTotale
    )
    this.ligneCommandeObject=new LigneCommandeDTO();
  }



  get selectedClient(){
    let countryId = this.formCommande.controls.client.value;
    return countryId;
  }

  delete(ligneCommande: LigneCommandeDTO) {
    let index=this.lignesCommandesClients.indexOf(ligneCommande);
     this.lignesCommandesClients.splice(index,1);
    this.lignesCommandesClients=this.lignesCommandesClients;
    this.priceTotale=0;
    this.lignesCommandesClients.forEach(
      data=>this.priceTotale+=data.prixTotale
    );

  }

  onSubmit(formCommande: UntypedFormGroup) {
    const message = `ce article`;

    const dialogData = new ConfirmDialogModel("l'article", message);

    const dialogRef = this.dialog.open(
      VerficationDialogueComponent, {
      maxWidth: "400px",
      data: dialogData
    });

    dialogRef.afterClosed().subscribe(dialogResult => {
      if(dialogResult){
        this.commandeObject.code=formCommande.value.codeCommande;
        this.commandeObject.idEntreprise=this.idEntreprise;
        this.commandeObject.etatCommande="en cours";
        this.commandeObject.ligneCommandeClients=this.lignesCommandesClients;
        this.commandeObject.dateCommande=formCommande.value.dateCommande;
        this.commandeObject.idClient=formCommande.value.client.id;
        this.commandeObject.prixTotale=this.priceTotale;
        this.commandeServiceService.getCommandeByCodeCommandeAndIdEntreprise(formCommande.value.codeCommande, this.idEntreprise).subscribe(
          (data) => {
            this.testVar=true;
            if(data == null)
            {
              this.commandeServiceService.addCommande(this.commandeObject).subscribe(
                (response:commandeDTO)=>{
                  formCommande.reset();
                  this.formLigneCommande.reset();
                  this.lignesCommandesClients=[];
                  this.priceTotale=0;

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
              setTimeout(()=>{
                this.snackBar.open('La commande est deja exist !', '', {
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
    });


  }

  close() {
    this.location.back();
  }
  deleteDiague(): void {

  }

}
