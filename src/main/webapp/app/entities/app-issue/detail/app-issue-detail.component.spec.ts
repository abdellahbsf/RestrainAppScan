import { TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness, RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { AppIssueDetailComponent } from './app-issue-detail.component';

describe('AppIssue Management Detail Component', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AppIssueDetailComponent, RouterTestingModule.withRoutes([], { bindToComponentInputs: true })],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: AppIssueDetailComponent,
              resolve: { appIssue: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(AppIssueDetailComponent, '')
      .compileComponents();
  });

  describe('OnInit', () => {
    it('Should load appIssue on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', AppIssueDetailComponent);

      // THEN
      expect(instance.appIssue).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
