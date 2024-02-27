import { TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness, RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { AppScansDetailComponent } from './app-scans-detail.component';

describe('AppScans Management Detail Component', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AppScansDetailComponent, RouterTestingModule.withRoutes([], { bindToComponentInputs: true })],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: AppScansDetailComponent,
              resolve: { appScans: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(AppScansDetailComponent, '')
      .compileComponents();
  });

  describe('OnInit', () => {
    it('Should load appScans on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', AppScansDetailComponent);

      // THEN
      expect(instance.appScans).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
