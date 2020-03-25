import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterShopApplicationSharedModule } from 'app/shared/shared.module';
import { PageComponent } from './page.component';
import { PageDetailComponent } from './page-detail.component';
import { PageUpdateComponent } from './page-update.component';
import { PageDeleteDialogComponent } from './page-delete-dialog.component';
import { pageRoute } from './page.route';

@NgModule({
  imports: [JhipsterShopApplicationSharedModule, RouterModule.forChild(pageRoute)],
  declarations: [PageComponent, PageDetailComponent, PageUpdateComponent, PageDeleteDialogComponent],
  entryComponents: [PageDeleteDialogComponent]
})
export class JhipsterShopApplicationPageModule {}
