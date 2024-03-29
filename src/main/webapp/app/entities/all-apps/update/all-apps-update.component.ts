import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IAllApps } from '../all-apps.model';
import { AllAppsService } from '../service/all-apps.service';
import { AllAppsFormService, AllAppsFormGroup } from './all-apps-form.service';

@Component({
  standalone: true,
  selector: 'jhi-all-apps-update',
  templateUrl: './all-apps-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class AllAppsUpdateComponent implements OnInit {
  isSaving = false;
  allApps: IAllApps | null = null;

  editForm: AllAppsFormGroup = this.allAppsFormService.createAllAppsFormGroup();

  constructor(
    protected allAppsService: AllAppsService,
    protected allAppsFormService: AllAppsFormService,
    protected activatedRoute: ActivatedRoute,
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ allApps }) => {
      this.allApps = allApps;
      if (allApps) {
        this.updateForm(allApps);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const allApps = this.allAppsFormService.getAllApps(this.editForm);
    if (allApps.id !== null) {
      this.subscribeToSaveResponse(this.allAppsService.update(allApps));
    } else {
      this.subscribeToSaveResponse(this.allAppsService.create(allApps));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAllApps>>): void {
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

  protected updateForm(allApps: IAllApps): void {
    this.allApps = allApps;
    this.allAppsFormService.resetForm(this.editForm, allApps);
  }
}
