import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IAppScans } from '../app-scans.model';
import { AppScansService } from '../service/app-scans.service';

@Component({
  standalone: true,
  templateUrl: './app-scans-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class AppScansDeleteDialogComponent {
  appScans?: IAppScans;

  constructor(
    protected appScansService: AppScansService,
    protected activeModal: NgbActiveModal,
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.appScansService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
