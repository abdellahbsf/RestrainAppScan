import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IAppDetails } from '../app-details.model';
import { AppDetailsService } from '../service/app-details.service';
import { AppDetailsFormService, AppDetailsFormGroup } from './app-details-form.service';

@Component({
  standalone: true,
  selector: 'jhi-app-details-update',
  templateUrl: './app-details-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class AppDetailsUpdateComponent implements OnInit {
  isSaving = false;
  appDetails: IAppDetails | null = null;

  editForm: AppDetailsFormGroup = this.appDetailsFormService.createAppDetailsFormGroup();

  constructor(
    protected appDetailsService: AppDetailsService,
    protected appDetailsFormService: AppDetailsFormService,
    protected activatedRoute: ActivatedRoute,
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ appDetails }) => {
      this.appDetails = appDetails;
      if (appDetails) {
        this.updateForm(appDetails);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const appDetails = this.appDetailsFormService.getAppDetails(this.editForm);
    if (appDetails.id !== null) {
      this.subscribeToSaveResponse(this.appDetailsService.update(appDetails));
    } else {
      this.subscribeToSaveResponse(this.appDetailsService.create(appDetails));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAppDetails>>): void {
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

  protected updateForm(appDetails: IAppDetails): void {
    this.appDetails = appDetails;
    this.appDetailsFormService.resetForm(this.editForm, appDetails);
  }
}
