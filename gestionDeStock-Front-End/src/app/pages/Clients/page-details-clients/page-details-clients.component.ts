import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';

@Component({
  selector: 'app-page-details-clients',
  templateUrl: './page-details-clients.component.html',
  styleUrls: ['./page-details-clients.component.scss']
})
export class PageDetailsClientsComponent implements OnInit {

  variable!: number;
  constructor(
    public dialogRef: MatDialogRef<PageDetailsClientsComponent>,
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
