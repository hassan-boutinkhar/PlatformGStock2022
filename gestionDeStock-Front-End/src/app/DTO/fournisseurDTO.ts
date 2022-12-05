import {ImageUploadModule} from './image-upload.module';

export class fournisseurDTO {
  id: number;
  adress1: string;
  adress2:string;
  codePostale: string;
  pays: string;
  ville: string;
  dateDeNaissance: Date;
  idEntreprise:string;
  mail:string;
  nom:string;
  numTele:string;
  prenom:string;
  photo:ImageUploadModule=null;

  constructor() {
    this.adress1 = "";
    this.adress2 = "";
    this.codePostale = "";
    this.pays = "";
    this.ville = "";
    this.dateDeNaissance = null;
    this.idEntreprise = "";
    this.mail = "";
    this.nom = "";
    this.numTele = "";
    this.prenom = "";
    this.photo = null;
  }


}
