import { Component, OnInit } from '@angular/core';
import {MatDialogRef} from '@angular/material/dialog';
import {PageCategorieComponent} from '../pages/page-categorie/page-categorie.component';
import {NgxSpinnerService} from 'ngx-spinner';
@Component({
  selector: 'app-succs-alert',
  templateUrl: './succs-alert.component.html',
  styleUrls: ['./succs-alert.component.scss']
})
export class SuccsAlertComponent implements OnInit {

  constructor(private spinner: NgxSpinnerService) { }

  ngOnInit(): void {
    this.spinner.show();
    setTimeout(() => {
      /** spinner ends after 5 seconds */
      this.spinner.hide();
    }, 2000);
  }

}
