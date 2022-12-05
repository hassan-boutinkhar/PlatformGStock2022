import {categoryDTO} from './categoryDTO';
import {ImageUploadModule} from './image-upload.module';

export class articleDTO {
  id: number;
  codeArticle: string;
  designation:string;
  prixUnitaireHt: number;
  touxTva: number;
  photo:ImageUploadModule=null;
  prixUnitaireTtc: number;
  categoryId: categoryDTO;
  idEntreprise:string;
  quantite:number;


  constructor() {
    this.codeArticle = "";
    this.designation = "";
    this.prixUnitaireHt = null;
    this.touxTva = null;
    this.photo = null;
    this.prixUnitaireTtc = null;
    this.categoryId = null;
    this.idEntreprise = "";
    this.quantite=null;
  }
}
