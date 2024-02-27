import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../all-apps.test-samples';

import { AllAppsFormService } from './all-apps-form.service';

describe('AllApps Form Service', () => {
  let service: AllAppsFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AllAppsFormService);
  });

  describe('Service methods', () => {
    describe('createAllAppsFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createAllAppsFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            appId: expect.any(Object),
            name: expect.any(Object),
          }),
        );
      });

      it('passing IAllApps should create a new form with FormGroup', () => {
        const formGroup = service.createAllAppsFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            appId: expect.any(Object),
            name: expect.any(Object),
          }),
        );
      });
    });

    describe('getAllApps', () => {
      it('should return NewAllApps for default AllApps initial value', () => {
        const formGroup = service.createAllAppsFormGroup(sampleWithNewData);

        const allApps = service.getAllApps(formGroup) as any;

        expect(allApps).toMatchObject(sampleWithNewData);
      });

      it('should return NewAllApps for empty AllApps initial value', () => {
        const formGroup = service.createAllAppsFormGroup();

        const allApps = service.getAllApps(formGroup) as any;

        expect(allApps).toMatchObject({});
      });

      it('should return IAllApps', () => {
        const formGroup = service.createAllAppsFormGroup(sampleWithRequiredData);

        const allApps = service.getAllApps(formGroup) as any;

        expect(allApps).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IAllApps should not enable id FormControl', () => {
        const formGroup = service.createAllAppsFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewAllApps should disable id FormControl', () => {
        const formGroup = service.createAllAppsFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
