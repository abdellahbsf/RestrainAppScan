import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../app-issue.test-samples';

import { AppIssueFormService } from './app-issue-form.service';

describe('AppIssue Form Service', () => {
  let service: AppIssueFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AppIssueFormService);
  });

  describe('Service methods', () => {
    describe('createAppIssueFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createAppIssueFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            appId: expect.any(Object),
            programmingLanguage: expect.any(Object),
            severities: expect.any(Object),
            statusIssue: expect.any(Object),
            issueType: expect.any(Object),
            locationIssue: expect.any(Object),
            dateCreated: expect.any(Object),
            lastUpdated: expect.any(Object),
            lastFound: expect.any(Object),
            callingMethod: expect.any(Object),
            isNewInScope: expect.any(Object),
            libraryName: expect.any(Object),
            libraryVersion: expect.any(Object),
            scaTechnology: expect.any(Object),
            fGStatus: expect.any(Object),
            applicationId: expect.any(Object),
            fixGroupId: expect.any(Object),
            apiIssue: expect.any(Object),
            sourceIssue: expect.any(Object),
            contextIssue: expect.any(Object),
            appscanVulnId: expect.any(Object),
            callingLine: expect.any(Object),
            classIssue: expect.any(Object),
            cveIssue: expect.any(Object),
          }),
        );
      });

      it('passing IAppIssue should create a new form with FormGroup', () => {
        const formGroup = service.createAppIssueFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            appId: expect.any(Object),
            programmingLanguage: expect.any(Object),
            severities: expect.any(Object),
            statusIssue: expect.any(Object),
            issueType: expect.any(Object),
            locationIssue: expect.any(Object),
            dateCreated: expect.any(Object),
            lastUpdated: expect.any(Object),
            lastFound: expect.any(Object),
            callingMethod: expect.any(Object),
            isNewInScope: expect.any(Object),
            libraryName: expect.any(Object),
            libraryVersion: expect.any(Object),
            scaTechnology: expect.any(Object),
            fGStatus: expect.any(Object),
            applicationId: expect.any(Object),
            fixGroupId: expect.any(Object),
            apiIssue: expect.any(Object),
            sourceIssue: expect.any(Object),
            contextIssue: expect.any(Object),
            appscanVulnId: expect.any(Object),
            callingLine: expect.any(Object),
            classIssue: expect.any(Object),
            cveIssue: expect.any(Object),
          }),
        );
      });
    });

    describe('getAppIssue', () => {
      it('should return NewAppIssue for default AppIssue initial value', () => {
        const formGroup = service.createAppIssueFormGroup(sampleWithNewData);

        const appIssue = service.getAppIssue(formGroup) as any;

        expect(appIssue).toMatchObject(sampleWithNewData);
      });

      it('should return NewAppIssue for empty AppIssue initial value', () => {
        const formGroup = service.createAppIssueFormGroup();

        const appIssue = service.getAppIssue(formGroup) as any;

        expect(appIssue).toMatchObject({});
      });

      it('should return IAppIssue', () => {
        const formGroup = service.createAppIssueFormGroup(sampleWithRequiredData);

        const appIssue = service.getAppIssue(formGroup) as any;

        expect(appIssue).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IAppIssue should not enable id FormControl', () => {
        const formGroup = service.createAppIssueFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewAppIssue should disable id FormControl', () => {
        const formGroup = service.createAppIssueFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
