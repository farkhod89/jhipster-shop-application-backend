import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'file',
        loadChildren: () => import('./file/file.module').then(m => m.JhipsterShopApplicationFileModule)
      },
      {
        path: 'page',
        loadChildren: () => import('./page/page.module').then(m => m.JhipsterShopApplicationPageModule)
      },
      {
        path: 'category',
        loadChildren: () => import('./category/category.module').then(m => m.JhipsterShopApplicationCategoryModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ]
})
export class JhipsterShopApplicationEntityModule {}
