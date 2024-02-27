import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IAppIssue } from '../app-issue.model';
import { AppIssueService } from '../service/app-issue.service';

@Component({
  standalone: true,
  templateUrl: './app-issue-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class AppIssueDeleteDialogComponent {
  appIssue?: IAppIssue;

  constructor(
    protected appIssueService: AppIssueService,
    protected activeModal: NgbActiveModal,
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.appIssueService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
