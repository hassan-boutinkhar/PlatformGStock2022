import {Component, Inject, OnInit} from '@angular/core';
import {
  MatDialogRef,
  MAT_DIALOG_DATA
} from '@angular/material/dialog';
import {MvtDeStockServiceService} from '../../../services/mvtDeStock/mvt-de-stock-service.service';
import {mvtDeStockDTO} from '../../../DTO/mvtDeStockDTO';
import {entrepriseId} from '../../../../utils/entrepriseId';
import {TypeMvt} from '../../../enumeration/TypeMvt';

@Component({
  selector: 'app-details',
  templateUrl: './details.component.html',
  styleUrls: ['./details.component.scss']
})
export class DetailsComponent implements OnInit {
  idArticle!: number;
  mvtsArticle:mvtDeStockDTO[];
  idEntreprise=entrepriseId.entrepriseIdValus;
  value1:any=TypeMvt.ENTRE
  constructor(
    private mvtDeStockServiceService:MvtDeStockServiceService,
    public dialogRef: MatDialogRef<DetailsComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any ) {
    this.idArticle = data ;
  }

  onCancel(): void {
    this.dialogRef.close();
  }

  ngOnInit(): void {
        //throw new Error('Method not implemented.');
        console.log(this.idArticle)
        this.mvtDeStockServiceService.getArticleMvements(this.idEntreprise,this.idArticle).subscribe(
          data=>this.mvtsArticle=data
        )
    }



}
