import {Component, HostListener, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {MatDialog} from '@angular/material/dialog';
import {DetailsComponent} from './details/details.component';
import {ArticleServiceService} from '../../services/article/article-service.service';
import {map, Observable} from 'rxjs';
import {entrepriseId} from '../../../utils/entrepriseId';
import {UploadFileService} from '../../services/uploadFile/upload-file.service';
import {UntypedFormBuilder, UntypedFormGroup, Validators} from '@angular/forms';
import {mvtDeStockDTO} from '../../DTO/mvtDeStockDTO';
import {MvtDeStockServiceService} from '../../services/mvtDeStock/mvt-de-stock-service.service';
import {TypeMvt} from '../../enumeration/TypeMvt';
import {MatSnackBar} from '@angular/material/snack-bar';


@Component({
  selector: 'app-mvstocks',
  templateUrl: './mvstocks.component.html',
  styleUrls: ['./mvstocks.component.scss']
})
export class MvstocksComponent implements OnInit {
  // @ts-ignore
  showv: boolean[] ;
  a = false;
  screenHeight: number;
  screenWidth: number;
  animal: string;
  display = 'none';
  name: string;
  nombre: number[] = [1, 2, 3] ;


  imagePath: any="../assets/img/icons/22546762.jpg";
  idEntreprise=entrepriseId.entrepriseIdValus;
  pages:number=0;
  pagesTabes: Observable<number>;
  aboutus: any = null;
  testVar:boolean=false;
  typeMvt: typeof TypeMvt;
  value1:any=TypeMvt.ENTRE
  value2:any=TypeMvt.SORTIE
  myForm: UntypedFormGroup;
  //data:mvtDeStockDTO[this.pagesTabes*4]
  mvtUpdate: mvtDeStockDTO=new mvtDeStockDTO();
  private lastMvt: any;
  private emptyOrNot: boolean;
  private qT: any;
  private qT2: number;
  constructor(private router: Router,
              public dialog: MatDialog,
              private serviceArticle:ArticleServiceService,
              private uploadFileServiceprivate:UploadFileService,
              private fb: UntypedFormBuilder,
              private mvtDeStockServiceService:MvtDeStockServiceService,
              private snackBar: MatSnackBar
  ) {
    this.getScreenSize();
  }
  openDialog(nmb ?: number): void {
    const dialogRef = this.dialog.open(DetailsComponent, {
      width: '80%', height: '80%',
      data: nmb,
    });

    dialogRef.afterClosed().subscribe(result => {
      this.animal = result;
    });
  }
  @HostListener('window:resize', ['$event'])
  getScreenSize(event?) {
    this.screenHeight = window.innerHeight;
    this.screenWidth = window.innerWidth;
  }

  ngOnInit(): void {
    this.serviceArticle.getAllDataByEntreprise(this.idEntreprise,this.pages).pipe(
      map((x:any)=>x.map((y:any)=>
        this.uploadFileServiceprivate.createImage(y))))
      .subscribe(
        (data)=> {
          this.aboutus = data;
        });
    this.pagesTabes=this.serviceArticle.getNumberOfPages(this.idEntreprise);
    this.myForm = this.fb.group({
      quantite: ['', Validators.required],
      typeMvt: ['', Validators.required],
      dateMvt: [''],
    });
    setTimeout (()=>{
      for(let i=0;i<this.aboutus.length;i++){
        this.mvtDeStockServiceService.getLastMvt(this.idEntreprise,this.aboutus[i].id).subscribe(data=>this.aboutus[i].quantite=data.quantiteT)
      }
      },50)
  }
  afficher(id: number) {
    (this.showv[id] === true) ? this.showv[id]  = false : this.showv[id]  = true;
  }

  openModal(id:number) {
    this.display = 'block';
    this.mvtUpdate.articleId=id;
    this.mvtDeStockServiceService.mvtIsEmpty(this.idEntreprise,this.mvtUpdate.articleId).subscribe(
      data=>this.emptyOrNot=data
    )
    for (const argument of this.aboutus) {
      if(argument.id==id){
        this.qT=argument.quantite;
      }
    }
    this.mvtDeStockServiceService.getLastMvt(this.idEntreprise,this.mvtUpdate.articleId).subscribe(data=>this.qT2=data.quantiteT)

  }
  onCloseHandled() {
    this.display = 'none';
  }
  affichage() {
    (this.a === true) ? this.a  = false : this.a  = true;
  }
  Next() {

    this.serviceArticle.getAllDataByEntreprise(this.idEntreprise,++this.pages).pipe(
      map((x:any)=>x.map((y:any)=>
        this.uploadFileServiceprivate.createImage(y)
      ))
    ).subscribe(
      (data)=> {
        this.aboutus = data;
      }
    );
    setTimeout (()=>{
      for(let i=0;i<this.aboutus.length;i++){
        this.mvtDeStockServiceService.getLastMvt(this.idEntreprise,this.aboutus[i].id).subscribe(data=>this.aboutus[i].quantite=data.quantite)
      }
    },100)
  }

  Previous() {
    this.serviceArticle.getAllDataByEntreprise(this.idEntreprise,--this.pages).pipe(
      map((x:any)=>x.map((y:any)=>
        this.uploadFileServiceprivate.createImage(y)
      ))
    ).subscribe(
      (data)=> {
        this.aboutus = data;
      }
    );
    setTimeout (()=>{
      for(let i=0;i<this.aboutus.length;i++){
        this.mvtDeStockServiceService.getLastMvt(this.idEntreprise,this.aboutus[i].id).subscribe(data=>this.aboutus[i].quantite=data.quantite)
      }
    },100)
  }

  onSubmit(myForm: UntypedFormGroup) {
    this.mvtUpdate.quantite=myForm.value.quantite;
    this.mvtUpdate.dateMvt=this.formatDate(new Date());
    this.mvtUpdate.typeMvt=myForm.value.typeMvt;
    this.mvtUpdate.idEntreprise=this.idEntreprise;
    if(this.emptyOrNot){
      this.mvtUpdate.quantiteT=this.qT;
      if(myForm.value.typeMvt==TypeMvt.ENTRE){
        this.mvtUpdate.quantiteT+=myForm.value.quantite;
      }else{
        this.mvtUpdate.quantiteT-=myForm.value.quantite;
      }
    }else{
      this.mvtUpdate.quantiteT=this.qT2;
      if(myForm.value.typeMvt==TypeMvt.ENTRE){
        this.mvtUpdate.quantiteT+=myForm.value.quantite;
      }else{
        this.mvtUpdate.quantiteT-=myForm.value.quantite;
      }
    }
    this.mvtDeStockServiceService.addMvtDeStock(this.mvtUpdate);
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
    setTimeout(()=>{
      location.reload();
    },3000)
  }
   padTo2Digits(num) {
    return num.toString().padStart(2, '0');
  }

   formatDate(date) {
    return (
      [
        date.getFullYear(),
        this.padTo2Digits(date.getMonth() + 1),
        this.padTo2Digits(date.getDate()),
      ].join('-') +
      ' ' +
      [
        this.padTo2Digits(date.getHours()),
        this.padTo2Digits(date.getMinutes()),
        this.padTo2Digits(date.getSeconds()),
      ].join(':')
    );
  }

}
