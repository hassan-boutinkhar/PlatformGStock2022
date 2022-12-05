import {TypeMvt} from '../enumeration/TypeMvt';

export class mvtDeStockDTO{
  id:number;

  dateMvt:string;

  quantite:number;

  articleId:number;

  typeMvt:TypeMvt;

  idEntreprise:String;

  quantiteT:number;

  constructor() {
    this.id = null;
    this.dateMvt = null;
    this.quantite = null;
    this.articleId = null;
    this.typeMvt = null;
    this.idEntreprise = null;
    this.quantiteT=null;
  }
}
