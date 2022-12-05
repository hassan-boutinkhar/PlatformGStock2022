import { Component, OnInit, ElementRef } from '@angular/core';
import { ROUTES } from '../sidebar/sidebar.component';
import { Location, LocationStrategy, PathLocationStrategy } from '@angular/common';
import {ActivatedRoute, Router} from '@angular/router';
import {Constante} from '../../DTO/constante';
import {UploadFileService} from '../../services/uploadFile/upload-file.service';
import {UserServicesService} from '../../services/userServices/user-services.service';
import {map} from 'rxjs';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {
  public focus;
  title !: string;
  public listTitles: any[];
  public location: Location;
  profileUser:any=null;
  constructor(location: Location,  private element: ElementRef, private router: Router, private activtedrouter: ActivatedRoute,    private serviceUploadImage:UploadFileService,
              private userService: UserServicesService) {
    this.location = location;
  }

  ngOnInit() {
    this.listTitles = ROUTES.filter(listTitle => listTitle);
    this.userService.findUserLogin(sessionStorage.getItem('userMail'),sessionStorage.getItem('userPassword')).pipe(
      map((x:any)=>x.map((y:any)=>
        this.serviceUploadImage.createImageUser(y))))
      .subscribe(
        (response)=>{
            this.profileUser=response[0];
        });
  }
  getTitle(){
    var titlee = this.location.prepareExternalUrl(this.location.path());
    if(titlee.charAt(0) === '#'){
        titlee = titlee.slice( 1 );
    }

    for(var item = 0; item < this.listTitles.length; item++) {
        if(this.listTitles[item].path === titlee) {
            return this.listTitles[item].title;
        }
    }
    return 'Tableau d';
  }

  deconnecter() {
    Constante.userAccount=null;
    sessionStorage.clear();   // localStorage.clear();
  }
}
