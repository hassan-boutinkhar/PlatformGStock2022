import {LigneCommandeDTO} from './ligneCommandeDTO';

export class commandeDTO{
  id:number;

  code:string;

  dateCommande:string;

  idClient:string;

  etatCommande:string;

  idEntreprise:string;

  ligneCommandeClients:Array<LigneCommandeDTO>;

  prixTotale:number;


  constructor() {
    this.code = null;
    this.dateCommande = null;
    this.idClient = null;
    this.etatCommande = null;
    this.idEntreprise = null;
    this.ligneCommandeClients = [];
    this.prixTotale=null;
  }
}
