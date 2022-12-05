import { Component, OnInit } from '@angular/core';
import {Constante} from '../../DTO/constante';
import {UntypedFormBuilder, UntypedFormGroup, Validators} from '@angular/forms';
import {map, Observable} from 'rxjs';
import {categoryDTO} from '../../DTO/categoryDTO';
import {ImageUploadModule} from '../../DTO/image-upload.module';
import {userDTO} from '../../DTO/userDTO';
import {ActivatedRoute, Router} from '@angular/router';
import {CategoryService} from '../../services/category/category.service';
import {UploadFileService} from '../../services/uploadFile/upload-file.service';
import {UserServicesService} from '../../services/userServices/user-services.service';
import {MatSnackBar} from '@angular/material/snack-bar';
import {Location} from '@angular/common';
import {DomSanitizer} from '@angular/platform-browser';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {entrepriseId} from '../../../utils/entrepriseId';

@Component({
  selector: 'app-page-user-prorfile',
  templateUrl: './page-user-prorfile.component.html',
  styleUrls: ['./page-user-prorfile.component.scss']
})
export class PageUserProrfileComponent implements OnInit {

  profileUser:any=Constante.userAccount;
  private entrepriseId = entrepriseId.entrepriseIdValus;
  object:any;
  myForm: UntypedFormGroup;
  myForm2: UntypedFormGroup;
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
  oBT:Observable<userDTO>;//TO MAKE DATA SELECTED IN UPDATE
  articleObject:userDTO=new userDTO();
  title!: string;
  path1!: string;
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
    private serviceUploadImage:UploadFileService) { }

  ngOnInit(): void {
    this.userService.findUserLogin(sessionStorage.getItem('userMail'),sessionStorage.getItem('userPassword')).pipe(
      map((x:any)=>x.map((y:any)=>
        this.serviceUploadImage.createImageUser(y))))
      .subscribe(
        (response)=>{
          this.profileUser=response[0];
        });
    /*localStorage.setItem("profileUser",this.profileUser);
    this.profileUser=localStorage.getItem("profileUser");*/
    this.myForm2 = this.fb.group({
      password: ['',[Validators.required]],
      passwordConfirmation: ['',[Validators.required]]
    });
    this.router.data.subscribe(data =>
      this.title = data.title,
    );
    this.router.data.subscribe(data =>
      this.path1 = data.path1
    );
    this.id = sessionStorage.getItem('userId');
    console.log(this.id)
      this.oBT=this.userService.getUtilisateursById(this.id);//TO MAKE DATA SELECTED IN UPDATE
      this.userService.getUtilisateursById(this.id).subscribe(
        (data) =>{
          this.object=data;
          console.log(this.object)
          this.fileName=this.object.userImageId;
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
            roles: [this.object?.roles, [Validators.required]],
            motDepass: [this.object?.motDepass, [Validators.required]],
            numTele:[this.object?.numTel, [Validators.required]],
            dateDeNaissance:[this.object?.dateDeNaissance, [Validators.required]]
          });
          console.log(this.object)
        }
      );
  }
  onSubmit(form: UntypedFormGroup) {
    this.articleObject.nom=form.value.nom;
    this.articleObject.mail=form.value.mail;
    this.articleObject.prenom=form.value.prenom;
    this.articleObject.adress1=form.value.adress1;
    this.articleObject.adress2=form.value.adress2;
    this.articleObject.idEntreprise=entrepriseId.entrepriseIdValus;
    this.articleObject.ville=form.value.ville;
    this.articleObject.pays=form.value.pays;
    this.articleObject.codePostale=form.value.codePostale;
    this.articleObject.roles=form.value.roles;
    this.articleObject.motDepass=form.value.motDepass;
    this.articleObject.numTel=form.value.numTele;
    this.articleObject.dateDeNaissance=form.value.dateDeNaissance.toString();
    form.value.idEntreprise=entrepriseId.entrepriseIdValus;
    console.log(this.articleObject)
    if(this.id){
      this.articleObject.id=this.id;
      const formDataZ:FormData=this.prapreFormData(this.articleObject);
      this.testVar=true;
      console.log(formDataZ)
      this.userService.updateUtilisateurs(formDataZ);
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
      console.log(this.articleObject)
      const formDataZ:FormData=this.prapreFormData(this.articleObject);
      console.log(formDataZ)
      this.userService.getUtilisateursByMailAndIdEntreprise(form.value.mail, form.value.idEntreprise).subscribe(
        (data) => {
          this.testVar=true;
          if(data == null)
          {
            this.userService.addUtilisateurs(formDataZ).subscribe(
              (response:userDTO)=>{
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
            location.reload();
          }
          else
          {
            console.log(form)
            setTimeout(()=>{
              this.snackBar.open('L\'utilisateur est deja exist !', '', {
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
  prapreFormData(article:userDTO):FormData {
    const formData = new FormData();
    formData.append(
      'user',
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


  onSubmitPassWord(form: UntypedFormGroup) {
    // this.articleObject.nom=form.value.nom;
    this.articleObject.mail=this.object?.mail;
    this.articleObject.nom=this.object?.nom;
    this.articleObject.prenom=this.object?.prenom;
    this.articleObject.adress1=this.object?.adress1;
    this.articleObject.adress2=this.object?.adress2;
    this.articleObject.idEntreprise=entrepriseId.entrepriseIdValus;
    this.articleObject.ville=this.object?.ville;
    this.articleObject.pays=this.object?.pays;
    this.articleObject.codePostale=this.object?.codePostale;
    this.articleObject.roles=this.object?.roles;
    this.articleObject.numTel=this.object?.numTel;
    this.articleObject.dateDeNaissance=this.object?.dateDeNaissance;
    form.value.idEntreprise=entrepriseId.entrepriseIdValus;
    this.articleObject.id=this.object?.id;
    this.articleObject.motDepass=form.value.password;
    const formDataZ:FormData=this.prapreFormData(this.articleObject);
    this.testVar=true;
    console.log(this.articleObject)
    this.userService.updateUtilisateurs(formDataZ);
    setTimeout(()=>{
      this.snackBar.open('Operation avec succés !', '', {
        duration: 3000,
        verticalPosition: 'bottom', // 'top' | 'bottom'
        horizontalPosition: 'end',
        panelClass: ['blue-snackbar']
      });
      this.myForm2.reset({});
      this.testVar=false;
    },3000);
  }
}
