import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {UntypedFormBuilder, UntypedFormGroup, Validators} from '@angular/forms';
import {Observable} from 'rxjs';
import {entrepriseId} from '../../../../utils/entrepriseId';
import {categoryDTO} from '../../../DTO/categoryDTO';
import {ImageUploadModule} from '../../../DTO/image-upload.module';
import {clientsDTO} from '../../../DTO/clientsDTO';
import {CategoryService} from '../../../services/category/category.service';
import {UploadFileService} from '../../../services/uploadFile/upload-file.service';
import {UserServicesService} from '../../../services/userServices/user-services.service';
import {MatSnackBar} from '@angular/material/snack-bar';
import {Location} from '@angular/common';
import {DomSanitizer} from '@angular/platform-browser';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {ClientsServicesService} from '../../../services/clientsServices/clients-services.service';
import {FournisseurServicesService} from '../../../services/fournisseurServices/fournisseur-services.service';

@Component({
  selector: 'app-page-add-user',
  templateUrl: './page-add-user.component.html',
  styleUrls: ['./page-add-user.component.scss']
})
export class PageAddUserComponent implements OnInit {
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
  oBT:Observable<clientsDTO>;//TO MAKE DATA SELECTED IN UPDATE
  articleObject:clientsDTO=new clientsDTO();
  title!: string;
  path1!: string;
  private idClient: any;
  private idFournisseur: any;
  constructor(
    private router: ActivatedRoute,
    private route1: Router,
    private fb: UntypedFormBuilder,
    private categoryService: CategoryService,
    private uploadFileService:UploadFileService,
    private userService: UserServicesService,
    private snackBar: MatSnackBar,
    private location: Location,
    private sanitizer: DomSanitizer,
    private http: HttpClient,
    private clientService:ClientsServicesService,
    private fournisseurService:FournisseurServicesService
  ) { }

  ngOnInit(): void {
    this.router.data.subscribe(data =>
      this.title = data.title,
    );
    this.router.data.subscribe(data =>
      this.path1 = data.path1
    );
    this.idClient = this.router.snapshot.paramMap.get('clientId');
    this.idFournisseur = this.router.snapshot.paramMap.get('fournisseurId');
    if(this.idClient || this.idFournisseur){
      if(this.title=="Fournisseur"){
        this.oBT=this.fournisseurService.getFournisseurById(this.idFournisseur);//TO MAKE DATA SELECTED IN UPDATE
        this.fournisseurService.getFournisseurById(this.idFournisseur).subscribe(
          (data) =>{this.object=data;
            this.fileName=this.object.fournisseurImageId;
            this.myForm = this.fb.group({
              id: [this.object?.id],
              nom: [this.object?.nom, Validators.required],
              mail: [this.object?.mail, [Validators.required]],
              prenom: [this.object?.prenom, [Validators.required]],
              adress1: [this.object?.adress1, [Validators.required]],
              adress2: [this.object?.adress2],
              photo: [this.object?.photo],
              ville: [this.object?.ville, [Validators.required]],
              pays: [this.object?.pays, [Validators.required]],
              codePostale: [this.object?.codePostale, [Validators.required]],
              numTele:[this.object?.numTel, [Validators.required]],
              dateDeNaissance:[this.object?.dateDeNaissance, [Validators.required]]
            });

          }
        );
      }
      else{
        this.oBT=this.clientService.getClientById(this.idClient);//TO MAKE DATA SELECTED IN UPDATE
        this.clientService.getClientById(this.idClient).subscribe(
          (data) =>{this.object=data;
            this.fileName=this.object.clientImageId;
            this.myForm = this.fb.group({
              id: [this.object?.id],
              nom: [this.object?.nom, Validators.required],
              mail: [this.object?.mail, [Validators.required]],
              prenom: [this.object?.prenom, [Validators.required]],
              adress1: [this.object?.adress1, [Validators.required]],
              adress2: [this.object?.adress2],
              photo: [this.object?.photo],
              ville: [this.object?.ville, [Validators.required]],
              pays: [this.object?.pays, [Validators.required]],
              codePostale: [this.object?.codePostale, [Validators.required]],
              dateDeNaissance:[this.object?.dateDeNaissance, [Validators.required]],
              numTele:[this.object?.numTel, [Validators.required]]
            });

          }
        );
      }

    }
    this.myForm = this.fb.group({
      id: [this.object?.id],
      nom: [this.object?.nom, Validators.required],
      mail: [this.object?.mail, [Validators.required]],
      prenom: [this.object?.prenom, [Validators.required]],
      adress1: [this.object?.adress1, [Validators.required]],
      adress2: [this.object?.adress2],
      photo: [this.object?.photo],
      ville: [this.object?.ville, [Validators.required]],
      pays: [this.object?.pays, [Validators.required]],
      numTele:[this.object?.numTel, [Validators.required]],
      codePostale: [this.object?.codePostale, [Validators.required]],
      dateDeNaissance:[this.object?.dateDeNaissance, [Validators.required]]
    });
  }
  onSubmit(form: UntypedFormGroup) {
    console.log(form)
    this.articleObject.nom=form.value.nom;
    this.articleObject.mail=form.value.mail;
    this.articleObject.prenom=form.value.prenom;
    this.articleObject.adress1=form.value.adress1;
    this.articleObject.adress2=form.value.adress2;
    this.articleObject.idEntreprise=entrepriseId.entrepriseIdValus;
    this.articleObject.ville=form.value.ville;
    this.articleObject.pays=form.value.pays;
    this.articleObject.codePostale=form.value.codePostale;
    this.articleObject.numTel=form.value.numTele;
    this.articleObject.dateDeNaissance=form.value.dateDeNaissance.toString();
    form.value.idEntreprise=entrepriseId.entrepriseIdValus;
    if(this.idFournisseur || this.idClient ){
      this.testVar=true;
      if(this.title=="Fournisseur"){
        this.articleObject.id=this.idFournisseur;
        const formDataZ:FormData=this.prapreFormDataF(this.articleObject);
        this.fournisseurService.updateFournisseur(formDataZ);
      }else{
        this.articleObject.id=this.idClient;
        const formDataZ:FormData=this.prapreFormDataC(this.articleObject);
        this.clientService.updateClient(formDataZ);
      }
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
      if(this.title=="Fournisseur"){
        const formDataZ:FormData=this.prapreFormDataF(this.articleObject);
        this.fournisseurService.getFournisseurByMailAndIdEntreprise(form.value.mail, form.value.idEntreprise).subscribe(
          (data) => {
            this.testVar=true;
            if(data == null)
            {
              this.fournisseurService.addFournisseur(formDataZ).subscribe(
                (response:clientsDTO)=>{
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
                this.snackBar.open('Le fournisseur est deja exist !', '', {
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
      }else{
        const formDataZ:FormData=this.prapreFormDataC(this.articleObject);
        this.clientService.getClientByMailAndIdEntreprise(form.value.mail, form.value.idEntreprise).subscribe(
          (data) => {
            this.testVar=true;
            if(data == null)
            {
              this.clientService.addClient(formDataZ).subscribe(
                (response:clientsDTO)=>{
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
                this.snackBar.open('Le client est deja exist !', '', {
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
  prapreFormDataC(article:clientsDTO):FormData {
    const formData = new FormData();
    formData.append(
      'client',
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
  prapreFormDataF(article:clientsDTO):FormData {
    const formData = new FormData();
    formData.append(
      'fournisseur',
      new Blob([JSON.stringify(article)], {type: 'application/json'})
    );
    if (this.fileName?.file != null) {
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
