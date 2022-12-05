export class LigneCommandeDTO{
  id:number;

  articleIdCC:string;

  quantite:number;

  prixTotale:number;

  constructor() {
    this.articleIdCC = null;
    this.quantite = null;
    this.prixTotale = null;
  }
}
