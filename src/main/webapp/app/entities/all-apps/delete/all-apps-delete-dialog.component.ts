import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IAllApps } from '../all-apps.model';
import { AllAppsService } from '../service/all-apps.service';

@Component({
  standalone: true,
  templateUrl: './all-apps-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class AllAppsDeleteDialogComponent {
  allApps?: IAllApps;

  constructor(
    protected allAppsService: AllAppsService,
    protected activeModal: NgbActiveModal,
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.allAppsService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
