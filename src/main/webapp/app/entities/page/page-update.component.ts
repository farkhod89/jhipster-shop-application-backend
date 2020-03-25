import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IPage, Page } from 'app/shared/model/page.model';
import { PageService } from './page.service';

@Component({
  selector: 'jhi-page-update',
  templateUrl: './page-update.component.html'
})
export class PageUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    seoName: [null, [Validators.maxLength(255)]],
    seoTitle: [null, [Validators.maxLength(255)]],
    seoDescription: [null, [Validators.maxLength(255)]],
    seoKeys: [null, [Validators.maxLength(255)]],
    contentTop: [],
    contentBottom: []
  });

  constructor(protected pageService: PageService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ page }) => {
      this.updateForm(page);
    });
  }

  updateForm(page: IPage): void {
    this.editForm.patchValue({
      id: page.id,
      seoName: page.seoName,
      seoTitle: page.seoTitle,
      seoDescription: page.seoDescription,
      seoKeys: page.seoKeys,
      contentTop: page.contentTop,
      contentBottom: page.contentBottom
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const page = this.createFromForm();
    if (page.id !== undefined) {
      this.subscribeToSaveResponse(this.pageService.update(page));
    } else {
      this.subscribeToSaveResponse(this.pageService.create(page));
    }
  }

  private createFromForm(): IPage {
    return {
      ...new Page(),
      id: this.editForm.get(['id'])!.value,
      seoName: this.editForm.get(['seoName'])!.value,
      seoTitle: this.editForm.get(['seoTitle'])!.value,
      seoDescription: this.editForm.get(['seoDescription'])!.value,
      seoKeys: this.editForm.get(['seoKeys'])!.value,
      contentTop: this.editForm.get(['contentTop'])!.value,
      contentBottom: this.editForm.get(['contentBottom'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPage>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
