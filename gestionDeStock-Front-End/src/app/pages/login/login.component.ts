import { Component, OnInit, OnDestroy } from '@angular/core';
import {Router} from '@angular/router';
import {UserServicesService} from '../../services/userServices/user-services.service';
import {UntypedFormBuilder, UntypedFormGroup, Validators} from '@angular/forms';
import {userDTO} from '../../DTO/userDTO';
import {HttpErrorResponse} from '@angular/common/http';
import {LoginData} from '../../DTO/LoginData';
import {Constante} from '../../DTO/constante';
import {UploadFileService} from '../../services/uploadFile/upload-file.service';
import {map} from 'rxjs';
import {MatSnackBar} from '@angular/material/snack-bar';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit, OnDestroy {
  object:any;
  myForm: UntypedFormGroup;
  testVar:boolean=false;
  constructor(
    private router: Router,
    private fb: UntypedFormBuilder,
    private serviceUploadImage:UploadFileService,
    private userService: UserServicesService,
    private snackBar: MatSnackBar) {}
  ngOnInit() {
    this.myForm = this.fb.group({
      password: ['',[Validators.required]],
      userName: ['',[Validators.required]]
    });
  }
  ngOnDestroy() {
  }

  onSubmit(form: UntypedFormGroup) {
    let userLogin: LoginData = new LoginData(form.value.userName,form.value.password);
    console.log(userLogin)
    this.userService.findUserLogin(form.value.userName,form.value.password).pipe(
      map((x:any)=>x.map((y:any)=>
        this.serviceUploadImage.createImageUser(y))))
      .subscribe(
      (response)=>{
          this.testVar=true;
          if(response[0]!=null){
            Constante.userAccount=response[0];
            console.log(Constante.userAccount)
            setTimeout(()=>{
              this.snackBar.open('Vous étes bien connecté !', '', {
                duration: 3000,
                verticalPosition: 'bottom', // 'top' | 'bottom'
                horizontalPosition: 'end',
                panelClass: ['blue-snackbar']
              });
              this.testVar=false;

            },3000);
            sessionStorage.setItem('userMail', Constante.userAccount.mail);// if it's object
            sessionStorage.setItem('userPassword', Constante.userAccount.motDepass);// if it's object
            sessionStorage.setItem('userId', Constante.userAccount.id);// if it's object
            setTimeout(this.codingCourse.bind(this),1000);
          }else{
            console.log("hhhhh")
            this.testVar=true;
            setTimeout(()=>{
              this.snackBar.open('Le nom d\'utilisteur ou le mot de pass est incorrecte !', '', {
                duration: 3000,
                verticalPosition: 'bottom', // 'top' | 'bottom'
                horizontalPosition: 'end',
                panelClass: ['red-snackbar']
              });
              this.testVar=false;
            },3000);
          }
      },
        (error)=>{

        }
    );
  }
  codingCourse(){
    this.router.navigate(['dashboard']);
  }
}
