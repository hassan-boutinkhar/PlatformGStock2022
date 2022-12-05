import {Component, HostListener, OnInit} from '@angular/core';

@Component({
  selector: 'app-page-ajoute-fournisseur',
  templateUrl: './page-ajoute-fournisseur.component.html',
  styleUrls: ['./page-ajoute-fournisseur.component.scss']
})
export class PageAjouteFournisseurComponent implements OnInit {

  articleshow = {
    icon : '../assets/img/theme/bootstrap.jpg',
    codearticle : 'code article' ,
    description :  'description' ,
    prixnt : 1000 ,
    prixttc : 2000,
    codecategorie : 'code de categorie' ,
    descriptioncategorie : 'description de categorie'
  };
  screenHeight: number;
  screenWidth: number;

  constructor() {
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

}
