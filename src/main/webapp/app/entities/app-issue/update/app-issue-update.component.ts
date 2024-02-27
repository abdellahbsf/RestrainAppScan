import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IAppIssue } from '../app-issue.model';
import { AppIssueService } from '../service/app-issue.service';
import { AppIssueFormService, AppIssueFormGroup } from './app-issue-form.service';

@Component({
  standalone: true,
  selector: 'jhi-app-issue-update',
  templateUrl: './app-issue-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class AppIssueUpdateComponent implements OnInit {
  isSaving = false;
  appIssue: IAppIssue | null = null;

  editForm: AppIssueFormGroup = this.appIssueFormService.createAppIssueFormGroup();

  constructor(
    protected appIssueService: AppIssueService,
    protected appIssueFormService: AppIssueFormService,
    protected activatedRoute: ActivatedRoute,
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ appIssue }) => {
      this.appIssue = appIssue;
      if (appIssue) {
        this.updateForm(appIssue);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const appIssue = this.appIssueFormService.getAppIssue(this.editForm);
    if (appIssue.id !== null) {
      this.subscribeToSaveResponse(this.appIssueService.update(appIssue));
    } else {
      this.subscribeToSaveResponse(this.appIssueService.create(appIssue));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAppIssue>>): void {
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

  protected updateForm(appIssue: IAppIssue): void {
    this.appIssue = appIssue;
    this.appIssueFormService.resetForm(this.editForm, appIssue);
  }
}
