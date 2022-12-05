import {Component, HostListener, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {CategoryService} from '../../services/category/category.service';
import {categoryDTO} from '../../DTO/categoryDTO';
import {delay, Observable, Subscription} from 'rxjs';
import {MatSnackBar} from '@angular/material/snack-bar';
import {entrepriseId} from '../../../utils/entrepriseId';
import {NgxSpinnerService} from 'ngx-spinner';
import {MatDialog} from '@angular/material/dialog';
import {ConfirmDialogModel, ConfirmDialogueComponent} from '../confirm-dialogue/confirm-dialogue.component';

@Component({
  selector: 'app-page-categorie',
  templateUrl: './page-categorie.component.html',
  styleUrls: ['./page-categorie.component.scss']
})
export class PageCategorieComponent implements OnInit {


  listCategory1!: Observable<categoryDTO[]>;
  categoryTest!: Observable<categoryDTO>;
  screenHeight: number;
  screenWidth: number;
  idEntreprise=entrepriseId.entrepriseIdValus;
  pages:number=0;
  pagesTabes: Observable<number>;
  testVar:boolean=false;
  private result: any;
  constructor(private router: Router, private categoryService: CategoryService, private snackBar: MatSnackBar,private spinner: NgxSpinnerService,public dialog: MatDialog) {
    this.getScreenSize();
  }

  @HostListener('window:resize', ['$event'])
  getScreenSize(event?) {
    this.screenHeight = window.innerHeight;
    this.screenWidth = window.innerWidth;
  }

  ngOnInit(): void {
    this.listCategory1 = this.categoryService.getAllDataByEntreprise(this.idEntreprise,this.pages);
    this.pagesTabes=this.categoryService.getNumberOfPages(this.idEntreprise);
  }
  noveaux() {
    this.testVar=true;
    setTimeout(this.codingCourse.bind(this),1000);
  }
  codingCourse(){
    this.router.navigateByUrl('/addcategory');
    this.testVar=false;
  }

  update(codeCtegory: categoryDTO) {
    this.router.navigateByUrl('/addcategory', { state:
        {
          id: codeCtegory.id,
          codeCtegory: codeCtegory.codeCtegory,
          designation: codeCtegory.designation,
          idEntreprise: codeCtegory.idEntreprise,
          varTest: true
        }
    });

  }

  Next() {
    this.listCategory1 = this.categoryService.getAllDataByEntreprise(this.idEntreprise,++this.pages);
  }

  Previous() {
    this.listCategory1 = this.categoryService.getAllDataByEntreprise(this.idEntreprise,--this.pages);

  }

  deleteDiague(id: number): void {
    const message = `cette catégorie`;

    const dialogData = new ConfirmDialogModel("le catégorie", message);

    const dialogRef = this.dialog.open(ConfirmDialogueComponent, {
      maxWidth: "400px",
      data: dialogData
    });

    dialogRef.afterClosed().subscribe(dialogResult => {
      if(dialogResult){
        this.testVar=true;
        this.categoryService.deleteCategory(id);
        this.listCategory1 = this.categoryService.getAllDataByEntreprise(this.idEntreprise,this.pages);
        setTimeout(()=>{
          this.snackBar.open('Operation avec succés !', '', {
            duration: 3000,
            verticalPosition: 'bottom', // 'top' | 'bottom'
            horizontalPosition: 'end',
            panelClass: ['blue-snackbar']
          });
          this.testVar=false;
        },3000);
      }
    });
  }
}

