import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../app-scans.test-samples';

import { AppScansFormService } from './app-scans-form.service';

describe('AppScans Form Service', () => {
  let service: AppScansFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AppScansFormService);
  });

  describe('Service methods', () => {
    describe('createAppScansFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createAppScansFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            appId: expect.any(Object),
            nameScan: expect.any(Object),
            technology: expect.any(Object),
            iastAgentType: expect.any(Object),
            iastAgentStatus: expect.any(Object),
            urlScan: expect.any(Object),
            appName: expect.any(Object),
            testOptimizationLevel: expect.any(Object),
            numberOfExecutions: expect.any(Object),
            createdAt: expect.any(Object),
            lastModified: expect.any(Object),
          }),
        );
      });

      it('passing IAppScans should create a new form with FormGroup', () => {
        const formGroup = service.createAppScansFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            appId: expect.any(Object),
            nameScan: expect.any(Object),
            technology: expect.any(Object),
            iastAgentType: expect.any(Object),
            iastAgentStatus: expect.any(Object),
            urlScan: expect.any(Object),
            appName: expect.any(Object),
            testOptimizationLevel: expect.any(Object),
            numberOfExecutions: expect.any(Object),
            createdAt: expect.any(Object),
            lastModified: expect.any(Object),
          }),
        );
      });
    });

    describe('getAppScans', () => {
      it('should return NewAppScans for default AppScans initial value', () => {
        const formGroup = service.createAppScansFormGroup(sampleWithNewData);

        const appScans = service.getAppScans(formGroup) as any;

        expect(appScans).toMatchObject(sampleWithNewData);
      });

      it('should return NewAppScans for empty AppScans initial value', () => {
        const formGroup = service.createAppScansFormGroup();

        const appScans = service.getAppScans(formGroup) as any;

        expect(appScans).toMatchObject({});
      });

      it('should return IAppScans', () => {
        const formGroup = service.createAppScansFormGroup(sampleWithRequiredData);

        const appScans = service.getAppScans(formGroup) as any;

        expect(appScans).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IAppScans should not enable id FormControl', () => {
        const formGroup = service.createAppScansFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewAppScans should disable id FormControl', () => {
        const formGroup = service.createAppScansFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
