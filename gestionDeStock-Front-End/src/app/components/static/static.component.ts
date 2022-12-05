import { Component, OnInit } from '@angular/core';
import {FournisseurServicesService} from '../../services/fournisseurServices/fournisseur-services.service';
import {ClientsServicesService} from '../../services/clientsServices/clients-services.service';
import {CommandeFournisseursServiceService} from '../../services/commandeFournisseurService/commande-fournisseurs-service.service';
import {CommandeClientServiceService} from '../../services/commandeClientServices/commande-client-service.service';
import {entrepriseId} from '../../../utils/entrepriseId';

@Component({
  selector: 'app-static',
  templateUrl: './static.component.html',
  styleUrls: ['./static.component.scss']
})
export class StaticComponent implements OnInit {
  nbrCommandeFournisseur: any=0;
  nbrFournisseurs: any=0;
  nbrClients: any=0;
  nbrCommandesClients: any=0;
  idEntreprise=entrepriseId.entrepriseIdValus;
  constructor(
    private fournisseurServicesService:FournisseurServicesService,
    private clientsService:ClientsServicesService,
    private commandeFrService:CommandeFournisseursServiceService,
    private commandeClService:CommandeClientServiceService
  ) { }

  ngOnInit(): void {
    this.clientsService.getNumberOfData(this.idEntreprise).subscribe(
      data=>this.nbrClients=data
    );
    this.fournisseurServicesService.getNumberOfData(this.idEntreprise).subscribe(
      data=>this.nbrFournisseurs=data
    );
    this.commandeFrService.getNumberOfData(this.idEntreprise).subscribe(
      data=>this.nbrCommandeFournisseur=data
    );
    this.commandeClService.getNumberOfData(this.idEntreprise).subscribe(
      data=>this.nbrCommandesClients=data
    );
  }

}
