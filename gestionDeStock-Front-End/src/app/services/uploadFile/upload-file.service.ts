import { Injectable } from '@angular/core';
import {HttpClient, HttpEvent, HttpRequest} from '@angular/common/http';
import {Observable, Subscription} from 'rxjs';
import {environment} from '../../../utils/envirments';
import {DomSanitizer} from '@angular/platform-browser';
import {ImageUploadModule} from '../../DTO/image-upload.module';

@Injectable({
  providedIn: 'root'
})
export class UploadFileService {
  constructor(private http: HttpClient,private sanitizer: DomSanitizer) { }

  public createImage(article:any){
    if(article.articleImage!=null){
      const articleImage:any=article.articleImage;
      const imageBlob=  this.dataUriToBlob(articleImage.picByte,articleImage.type);
      const imageFile=new File([imageBlob],articleImage.name,{type:articleImage.type});
      const articleImageToFileHandle : ImageUploadModule={
        file:imageFile,
        url: this.sanitizer.bypassSecurityTrustUrl(window.URL.createObjectURL(imageFile))
      } ;

      article.articleImage=articleImageToFileHandle;
      console.log(article.articleImage);
      return article;
    }else{
      return article;

    }
  }
  public createImageUser(user:any){
    console.log(user)
    if(user.userImageId!=null){
      console.log(user.userImageId)
      const userImageId:any=user.userImageId;
      const imageBlob=  this.dataUriToBlob(userImageId.picByte,userImageId.type);
      const imageFile=new File([imageBlob],userImageId.name,{type:userImageId.type});
      const userImageIdToFileHandle : ImageUploadModule={
        file:imageFile,
        url: this.sanitizer.bypassSecurityTrustUrl(window.URL.createObjectURL(imageFile))
      } ;

      user.userImageId=userImageIdToFileHandle;
      console.log(user.userImageId);
      return user;
    }else{
      return user;

    }
  }
  public createImageFournisseur(fournisseur:any){
    if(fournisseur.fournisseurImageId!=null){
      const fournisseurImageId:any=fournisseur.fournisseurImageId;
      const imageBlob=  this.dataUriToBlob(fournisseurImageId.picByte,fournisseurImageId.type);
      const imageFile=new File([imageBlob],fournisseurImageId.name,{type:fournisseurImageId.type});
      const fournisseurImageIdToFileHandle : ImageUploadModule={
        file:imageFile,
        url: this.sanitizer.bypassSecurityTrustUrl(window.URL.createObjectURL(imageFile))
      } ;

      fournisseur.fournisseurImageId=fournisseurImageIdToFileHandle;
      console.log(fournisseur.fournisseurImageId);
      return fournisseur;
    }else{
      return fournisseur;

    }
  }

  public createImageClient(client:any){
    if(client.clientImageId!=null){
      const clientImageId:any=client.clientImageId;
      const imageBlob=  this.dataUriToBlob(clientImageId.picByte,clientImageId.type);
      const imageFile=new File([imageBlob],clientImageId.name,{type:clientImageId.type});
      const clientImageIdToFileHandle : ImageUploadModule={
        file:imageFile,
        url: this.sanitizer.bypassSecurityTrustUrl(window.URL.createObjectURL(imageFile))
      } ;

      client.clientImageId=clientImageIdToFileHandle;
      console.log(client.clientImageId);
      return client;
    }else{
      return client;

    }
  }
  private dataUriToBlob(picByte: any, imageType:any) {
    const byteString=window.atob(picByte);
    const arrayBuffer= new ArrayBuffer(byteString.length);
    const int8Array=new Uint8Array(arrayBuffer);
    for(let i=0;i<byteString.length;i++){
      int8Array[i]=byteString.charCodeAt(i);
    }
    const blob=new Blob([int8Array],{ type: imageType});
    return blob;

  }
}
