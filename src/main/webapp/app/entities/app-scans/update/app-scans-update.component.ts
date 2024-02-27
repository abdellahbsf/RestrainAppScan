import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IAppScans } from '../app-scans.model';
import { AppScansService } from '../service/app-scans.service';
import { AppScansFormService, AppScansFormGroup } from './app-scans-form.service';

@Component({
  standalone: true,
  selector: 'jhi-app-scans-update',
  templateUrl: './app-scans-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class AppScansUpdateComponent implements OnInit {
  isSaving = false;
  appScans: IAppScans | null = null;

  editForm: AppScansFormGroup = this.appScansFormService.createAppScansFormGroup();

  constructor(
    protected appScansService: AppScansService,
    protected appScansFormService: AppScansFormService,
    protected activatedRoute: ActivatedRoute,
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ appScans }) => {
      this.appScans = appScans;
      if (appScans) {
        this.updateForm(appScans);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const appScans = this.appScansFormService.getAppScans(this.editForm);
    if (appScans.id !== null) {
      this.subscribeToSaveResponse(this.appScansService.update(appScans));
    } else {
      this.subscribeToSaveResponse(this.appScansService.create(appScans));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAppScans>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(appScans: IAppScans): void {
    this.appScans = appScans;
    this.appScansFormService.resetForm(this.editForm, appScans);
  }
}
