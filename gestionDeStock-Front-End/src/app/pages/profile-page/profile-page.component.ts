import { Component, OnInit } from '@angular/core';
import {ImageUploadModule} from '../../DTO/image-upload.module';
import {Constante} from '../../DTO/constante';

@Component({
  selector: 'app-profile-page',
  templateUrl: './profile-page.component.html',
  styleUrls: ['./profile-page.component.scss']
})
export class ProfilePageComponent implements OnInit {

  profileUser:any= JSON.parse(sessionStorage.getItem("userDetails")) as any;  // if it's object
  constructor() { }

  ngOnInit(): void {
    console.log(this.profileUser)
  }

}
