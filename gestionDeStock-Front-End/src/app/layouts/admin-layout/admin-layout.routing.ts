import {Routes} from '@angular/router';

import {DashboardComponent} from '../../pages/dashboard/dashboard.component';
import {IconsComponent} from '../../pages/icons/icons.component';
import {MapsComponent} from '../../pages/maps/maps.component';
import {UserProfileComponent} from '../../pages/user-profile/user-profile.component';
import {TablesComponent} from '../../pages/tables/tables.component';
import {PageArticleComponent} from 'src/app/pages/page-article/page-article.component';
import {PageAjouterArticleComponent} from '../../pages/page-article/page-ajouter-article/page-ajouter-article.component';
import {MvstocksComponent} from '../../pages/mvstocks/mvstocks.component';
import {DetailsComponent} from '../../pages/mvstocks/details/details.component';
import {PageListClientsComponent} from '../../pages/Clients/page-list-clients/page-list-clients.component';
import {PageListFournisseursComponent} from '../../pages/Fournisseurs/page-list-fournisseurs/page-list-fournisseurs.component';
import {PageAjouteFournisseurComponent} from '../../pages/Fournisseurs/page-ajoute-fournisseur/page-ajoute-fournisseur.component';
import {PageAjouteClientComponent} from '../../pages/Clients/page-ajoute-client/page-ajoute-client.component';
import {PageListCommandeComponent} from '../../pages/Clients/page-list-commande/page-list-commande.component';
import {
  PageListCommandeFournisseurComponent
} from '../../pages/Fournisseurs/page-list-commande-fournisseur/page-list-commande-fournisseur.component';
import {PageNoveauCommandefrComponent} from '../../pages/Fournisseurs/page-noveau-commandefr/page-noveau-commandefr.component';
import {PageNoveauCommandeClientComponent} from '../../pages/Clients/page-noveau-commande-client/page-noveau-commande-client.component';
import {PageCategorieComponent} from '../../pages/page-categorie/page-categorie.component';
import {PageAddCategorieComponent} from '../../pages/page-categorie/page-add-categorie/page-add-categorie.component';
import {PageUsersComponent} from '../../pages/page-users/page-users.component';
import {PageAddUserComponent} from '../../pages/page-users/page-add-user/page-add-user.component';
import {PageUserProrfileComponent} from '../../pages/page-user-prorfile/page-user-prorfile.component';
import {SuccsAlertComponent} from '../../succs-alert/succs-alert.component';
import {UserCompenentComponent} from '../../pages/user-compenent/user-compenent.component';
import {ListUserComponent} from '../../pages/user-compenent/list-user/list-user.component';

export const AdminLayoutRoutes: Routes = [
  { path : 'profileuser' , component : PageUserProrfileComponent},
  { path : 'adduser' , component :     UserCompenentComponent,
    data : {
      title: 'Utilisateur',
      path1: 'pageusers'
    }},
  { path : 'addfr' , component : PageAddUserComponent ,
    data : {
      title: 'Fournisseur',
      path1: 'listfournisseur'
    }},
  { path : 'addclient' , component : PageAddUserComponent ,
    data : {
      title: 'Client',
      path1: 'listclient'
    }},
  { path : 'pageusers' , component : PageUsersComponent},
  { path : 'listUser' , component : ListUserComponent},
  { path : 'addcategory' , component : PageAddCategorieComponent
  , data : {
      id: '',
      codeCtegory: '',
      designation: '',
      idEntreprise: 'DHL123',
      varTest: false
    }
  },
  { path: 'pagecategorie',    component: PageCategorieComponent },
  { path: 'nvcommandeclients',    component: PageNoveauCommandeClientComponent },
  { path: 'noveaucommandefournisseur',    component: PageNoveauCommandefrComponent },
  { path: 'listcommandefournisseur',    component: PageListCommandeFournisseurComponent},
  { path: 'listcommandeclients',      component: PageListCommandeComponent },
  { path: 'ajouteclients',      component: PageAjouteClientComponent },
   { path: 'ajoutefournisseur',      component: PageAjouteFournisseurComponent },
    { path: 'listfournisseur',      component: PageListFournisseursComponent },
    { path: 'listclient',      component:  PageListClientsComponent },
    { path: 'details',      component:  DetailsComponent },
    { path: 'mvtstocks',      component: MvstocksComponent  },
    { path: 'ajouterarticle',      component: PageAjouterArticleComponent,
      data:{
        id: '',
        codeArticle : '',
        designation : '',
        prixUnitaireHt : 0,
        touxTva : 0,
        photo :null,
        prixUnitaireTtc : 0,
        categoryId : null,
        idEntreprise :'DHL123',
        varTest: false,
      }
    },
    { path: 'particle',      component: PageArticleComponent  },
    { path: 'dashboard',      component: DashboardComponent },
    { path: 'user-profile',   component: UserProfileComponent },
    { path: 'tables',         component: TablesComponent },
    { path: 'icons',          component: IconsComponent },
  { path: 'maps',           component: MapsComponent  },
  { path: 'loading',           component:  SuccsAlertComponent }

];
