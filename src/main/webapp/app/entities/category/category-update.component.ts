import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ICategory, Category } from 'app/shared/model/category.model';
import { CategoryService } from './category.service';
import { IFile } from 'app/shared/model/file.model';
import { FileService } from 'app/entities/file/file.service';
import { IPage } from 'app/shared/model/page.model';
import { PageService } from 'app/entities/page/page.service';

type SelectableEntity = IFile | IPage | ICategory;

@Component({
  selector: 'jhi-category-update',
  templateUrl: './category-update.component.html'
})
export class CategoryUpdateComponent implements OnInit {
  isSaving = false;
  files: IFile[] = [];
  pages: IPage[] = [];
  pageuzs: IPage[] = [];
  categories: ICategory[] = [];

  editForm = this.fb.group({
    id: [],
    createdAt: [],
    updatedAt: [],
    name: [null, [Validators.required, Validators.minLength(1), Validators.maxLength(255)]],
    nameUz: [null, [Validators.required, Validators.minLength(1), Validators.maxLength(255)]],
    alias: [null, [Validators.required, Validators.minLength(1), Validators.maxLength(255)]],
    sorting: [null, [Validators.required, Validators.min(-9999), Validators.max(9999)]],
    status: [null, [Validators.required]],
    main: [null, [Validators.required]],
    fileId: [],
    pageId: [],
    pageUzId: [],
    parentId: []
  });

  constructor(
    protected categoryService: CategoryService,
    protected fileService: FileService,
    protected pageService: PageService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ category }) => {
      if (!category.id) {
        const today = moment().startOf('day');
        category.createdAt = today;
        category.updatedAt = today;
      }

      this.updateForm(category);

      this.fileService
        .query({ filter: 'category-is-null' })
        .pipe(
          map((res: HttpResponse<IFile[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IFile[]) => {
          if (!category.fileId) {
            this.files = resBody;
          } else {
            this.fileService
              .find(category.fileId)
              .pipe(
                map((subRes: HttpResponse<IFile>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IFile[]) => (this.files = concatRes));
          }
        });

      this.pageService
        .query({ filter: 'category-is-null' })
        .pipe(
          map((res: HttpResponse<IPage[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IPage[]) => {
          if (!category.pageId) {
            this.pages = resBody;
          } else {
            this.pageService
              .find(category.pageId)
              .pipe(
                map((subRes: HttpResponse<IPage>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IPage[]) => (this.pages = concatRes));
          }
        });

      this.pageService
        .query({ filter: 'category-is-null' })
        .pipe(
          map((res: HttpResponse<IPage[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IPage[]) => {
          if (!category.pageUzId) {
            this.pageuzs = resBody;
          } else {
            this.pageService
              .find(category.pageUzId)
              .pipe(
                map((subRes: HttpResponse<IPage>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IPage[]) => (this.pageuzs = concatRes));
          }
        });

      this.categoryService.query().subscribe((res: HttpResponse<ICategory[]>) => (this.categories = res.body || []));
    });
  }

  updateForm(category: ICategory): void {
    this.editForm.patchValue({
      id: category.id,
      createdAt: category.createdAt ? category.createdAt.format(DATE_TIME_FORMAT) : null,
      updatedAt: category.updatedAt ? category.updatedAt.format(DATE_TIME_FORMAT) : null,
      name: category.name,
      nameUz: category.nameUz,
      alias: category.alias,
      sorting: category.sorting,
      status: category.status,
      main: category.main,
      fileId: category.fileId,
      pageId: category.pageId,
      pageUzId: category.pageUzId,
      parentId: category.parentId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const category = this.createFromForm();
    if (category.id !== undefined) {
      this.subscribeToSaveResponse(this.categoryService.update(category));
    } else {
      this.subscribeToSaveResponse(this.categoryService.create(category));
    }
  }

  private createFromForm(): ICategory {
    return {
      ...new Category(),
      id: this.editForm.get(['id'])!.value,
      createdAt: this.editForm.get(['createdAt'])!.value ? moment(this.editForm.get(['createdAt'])!.value, DATE_TIME_FORMAT) : undefined,
      updatedAt: this.editForm.get(['updatedAt'])!.value ? moment(this.editForm.get(['updatedAt'])!.value, DATE_TIME_FORMAT) : undefined,
      name: this.editForm.get(['name'])!.value,
      nameUz: this.editForm.get(['nameUz'])!.value,
      alias: this.editForm.get(['alias'])!.value,
      sorting: this.editForm.get(['sorting'])!.value,
      status: this.editForm.get(['status'])!.value,
      main: this.editForm.get(['main'])!.value,
      fileId: this.editForm.get(['fileId'])!.value,
      pageId: this.editForm.get(['pageId'])!.value,
      pageUzId: this.editForm.get(['pageUzId'])!.value,
      parentId: this.editForm.get(['parentId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICategory>>): void {
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

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
