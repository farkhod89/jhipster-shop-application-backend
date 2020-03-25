import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IFile, File } from 'app/shared/model/file.model';
import { FileService } from './file.service';

@Component({
  selector: 'jhi-file-update',
  templateUrl: './file-update.component.html'
})
export class FileUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    createdAt: [],
    fileName: [null, [Validators.required]],
    fileType: [null, [Validators.required]],
    filePath: [null, [Validators.required]]
  });

  constructor(protected fileService: FileService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ file }) => {
      if (!file.id) {
        const today = moment().startOf('day');
        file.createdAt = today;
      }

      this.updateForm(file);
    });
  }

  updateForm(file: IFile): void {
    this.editForm.patchValue({
      id: file.id,
      createdAt: file.createdAt ? file.createdAt.format(DATE_TIME_FORMAT) : null,
      fileName: file.fileName,
      fileType: file.fileType,
      filePath: file.filePath
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const file = this.createFromForm();
    if (file.id !== undefined) {
      this.subscribeToSaveResponse(this.fileService.update(file));
    } else {
      this.subscribeToSaveResponse(this.fileService.create(file));
    }
  }

  private createFromForm(): IFile {
    return {
      ...new File(),
      id: this.editForm.get(['id'])!.value,
      createdAt: this.editForm.get(['createdAt'])!.value ? moment(this.editForm.get(['createdAt'])!.value, DATE_TIME_FORMAT) : undefined,
      fileName: this.editForm.get(['fileName'])!.value,
      fileType: this.editForm.get(['fileType'])!.value,
      filePath: this.editForm.get(['filePath'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFile>>): void {
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
