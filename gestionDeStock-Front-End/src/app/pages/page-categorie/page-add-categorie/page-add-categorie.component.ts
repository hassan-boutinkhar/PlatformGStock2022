import {Component, HostListener, OnInit} from '@angular/core';
import {UntypedFormBuilder, UntypedFormGroup, Validators} from '@angular/forms';
import {CategoryService} from '../../../services/category/category.service';
import {categoryDTO} from '../../../DTO/categoryDTO';
import {MatSnackBar} from '@angular/material/snack-bar';
import {ActivatedRoute, Router} from '@angular/router';
import {map} from 'rxjs';
import {Location} from '@angular/common';
import {entrepriseId} from '../../../../utils/entrepriseId';


@Component({
  selector: 'app-page-add-categorie',
  templateUrl: './page-add-categorie.component.html',
  styleUrls: ['./page-add-categorie.component.scss']
})
export class PageAddCategorieComponent implements OnInit {

  myForm: UntypedFormGroup;
  categoryUpdate: any;
  varTest: boolean;
  screenHeight: number;
  screenWidth: number;
  testVar:boolean=false;
  constructor(private router: ActivatedRoute,private router1: Router,private fb: UntypedFormBuilder, private categoryService: CategoryService, private snackBar: MatSnackBar,private location: Location) {
    this.getScreenSize();
  }

  @HostListener('window:resize', ['$event'])
  getScreenSize(event?) {
    this.screenHeight = window.innerHeight;
    this.screenWidth = window.innerWidth;
  }

  ngOnInit(): void {
 this.router.paramMap
      .pipe(map(() => window.history.state)).subscribe(
        data=> {
            this.categoryUpdate=data,
            this.varTest=data.varTest
          ,console.log(this.categoryUpdate)
        }
      );
    this.myForm = this.fb.group({
      id:[this.categoryUpdate.id],
      codeCategory: [this.categoryUpdate.codeCtegory, Validators.required],
      description: [this.categoryUpdate.designation, Validators.required],
      idEntreprise: [this.categoryUpdate.idEntreprise],
    });
  }
  onSubmit(form: UntypedFormGroup) {
    form.value.idEntreprise=entrepriseId.entrepriseIdValus;
    //FIX ME LATER WHEN WE DO LOGIN
    if(this.varTest){
      this.categoryService.updateCategory(this.dataToDATO(form));
      this.testVar=true;
      setTimeout(()=>{
        this.snackBar.open('Operation avec succés !', '', {
          duration: 3000,
          verticalPosition: 'bottom', // 'top' | 'bottom'
          horizontalPosition: 'end',
          panelClass: ['blue-snackbar']
        });
        this.myForm.reset({});
        this.testVar=false;
      },3000);

    }else {
      this.categoryService.getCategoryByCodeCategoryAndIdEntreprise(form.value.codeCategory, form.value.idEntreprise).subscribe(
        (data) => {
          this.testVar=true;
          if(data == null){
            this.categoryService.addCategory(this.dataToDATO(form));
            setTimeout(()=>{
              this.snackBar.open('Operation avec succés !', '', {
                duration: 3000,
                verticalPosition: 'bottom', // 'top' | 'bottom'
                horizontalPosition: 'end',
                panelClass: ['blue-snackbar']
              });
              this.myForm.reset({});
              this.testVar=false;
            },3000);
          }else{
            setTimeout(()=>{
              this.snackBar.open('Le categorie est deja exist !', '', {
                duration: 3000,
                verticalPosition: 'bottom', // 'top' | 'bottom'
                horizontalPosition: 'end',
                panelClass: ['red-snackbar']
              });
              this.testVar=false;
            },3000);

          }
        }
      );
    }


  }

  dataToDATO(form: UntypedFormGroup): categoryDTO {
    const categoryDTOV: categoryDTO = new categoryDTO();
    categoryDTOV.id=form.value.id;
    categoryDTOV.codeCtegory = form.value.codeCategory;
    categoryDTOV.idEntreprise = form.value.idEntreprise;
    categoryDTOV.designation = form.value.description;
    return categoryDTOV;
  }

  close() {
    this.location.back();
  }
}
