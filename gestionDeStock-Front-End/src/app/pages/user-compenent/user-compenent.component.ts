import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {UntypedFormBuilder, UntypedFormGroup, Validators} from '@angular/forms';
import {Observable} from 'rxjs';
import {categoryDTO} from '../../DTO/categoryDTO';
import {ImageUploadModule} from '../../DTO/image-upload.module';
import {userDTO} from '../../DTO/userDTO';
import {CategoryService} from '../../services/category/category.service';
import {UploadFileService} from '../../services/uploadFile/upload-file.service';
import {MatSnackBar} from '@angular/material/snack-bar';
import {entrepriseId} from '../../../utils/entrepriseId';
import {Location} from '@angular/common';
import {DomSanitizer} from '@angular/platform-browser';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {UserServicesService} from '../../services/userServices/user-services.service';

@Component({
  selector: 'app-user-compenent',
  templateUrl: './user-compenent.component.html',
  styleUrls: ['./user-compenent.component.scss']
})
export class UserCompenentComponent implements OnInit {

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
    private http: HttpClient
  ) { }

  ngOnInit(): void {
    this.router.data.subscribe(data =>
      this.title = data.title,
    );
    this.router.data.subscribe(data =>
      this.path1 = data.path1
    );
    this.id = this.router.snapshot.paramMap.get('userId');
    console.log(this.id)
    if(this.id){
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


}
