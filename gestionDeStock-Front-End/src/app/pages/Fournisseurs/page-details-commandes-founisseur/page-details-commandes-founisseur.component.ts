import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';

@Component({
  selector: 'app-page-details-commandes-founisseur',
  templateUrl: './page-details-commandes-founisseur.component.html',
  styleUrls: ['./page-details-commandes-founisseur.component.scss']
})
export class PageDetailsCommandesFounisseurComponent implements OnInit {

  variable!: number;
  constructor(
    public dialogRef: MatDialogRef<PageDetailsCommandesFounisseurComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any ) {
    this.variable = data ;
  }

  onCancel(): void {
    this.dialogRef.close();
  }

  ngOnInit(): void {
    throw new Error('Method not implemented.');
  }


}
