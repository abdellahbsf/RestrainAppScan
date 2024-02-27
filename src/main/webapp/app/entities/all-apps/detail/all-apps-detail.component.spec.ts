import { TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness, RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { AllAppsDetailComponent } from './all-apps-detail.component';

describe('AllApps Management Detail Component', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AllAppsDetailComponent, RouterTestingModule.withRoutes([], { bindToComponentInputs: true })],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: AllAppsDetailComponent,
              resolve: { allApps: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(AllAppsDetailComponent, '')
      .compileComponents();
  });

  describe('OnInit', () => {
    it('Should load allApps on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', AllAppsDetailComponent);

      // THEN
      expect(instance.allApps).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
