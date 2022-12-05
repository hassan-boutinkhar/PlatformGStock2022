import {ImageUploadModule} from './image-upload.module';

export class userDTO {
  id: number;
  adress1: string;
  adress2:string;
  codePostale: string;
  pays: string;
  ville: string;
  dateDeNaissance: string;
  idEntreprise:string;
  mail:string;
  nom:string;
  numTel:string;
  prenom:string;
  motDepass:string;
  roles:string;
  photo:ImageUploadModule=null;

  constructor() {
    this.adress1 = "";
    this.adress2 = "";
    this.codePostale = "";
    this.pays = "";
    this.ville = "";
    this.dateDeNaissance ="";
    this.idEntreprise = "";
    this.mail = "";
    this.nom = "";
    this.numTel = "";
    this.prenom = "";
    this.motDepass = "";
    this.roles = "";
    this.photo = null;
  }


}
