import {Component, HostListener, OnInit} from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-page-users',
  templateUrl: './page-users.component.html',
  styleUrls: ['./page-users.component.scss']
})
export class PageUsersComponent implements OnInit {


  infoclients = {
    nom : 'NOM',
    icon : '../assets/img/theme/bootstrap.jpg',
    prenom : 'PRENOM' ,
    numtelephone :  'NUMERO DE TELEPHONE' ,
    adress : 'ADRESS @' ,
    ville : 'code de postal VILLE',
    pays : 'PAYS',
  };
  screenHeight: number;
  screenWidth: number;

  constructor(private router: Router) {
    this.getScreenSize();
  }

  @HostListener('window:resize', ['$event'])
  getScreenSize(event?) {
    this.screenHeight = window.innerHeight;
    this.screenWidth = window.innerWidth;
    console.log(this.screenHeight, this.screenWidth);
  }

  ngOnInit(): void {
  }
  noveaux() {
    this.router.navigate(['/adduser']);
  }

}
