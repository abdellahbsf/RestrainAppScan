import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../app-details.test-samples';

import { AppDetailsFormService } from './app-details-form.service';

describe('AppDetails Form Service', () => {
  let service: AppDetailsFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AppDetailsFormService);
  });

  describe('Service methods', () => {
    describe('createAppDetailsFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createAppDetailsFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            appId: expect.any(Object),
            riskRating: expect.any(Object),
            criticalIssues: expect.any(Object),
            highIssues: expect.any(Object),
            mediumIssues: expect.any(Object),
            lowIssues: expect.any(Object),
            informationalIssues: expect.any(Object),
            issuesInProgress: expect.any(Object),
            maxSeverity: expect.any(Object),
            correlationState: expect.any(Object),
            rRMaxSeverity: expect.any(Object),
            assetGroupId: expect.any(Object),
            businessImpact: expect.any(Object),
            url: expect.any(Object),
            description: expect.any(Object),
            businessUnit: expect.any(Object),
            businessUnitId: expect.any(Object),
            types: expect.any(Object),
            technology: expect.any(Object),
            testingStatus: expect.any(Object),
            appHosts: expect.any(Object),
            collateralDamagePotential: expect.any(Object),
            targetDistribution: expect.any(Object),
            confidentialityRequirement: expect.any(Object),
            integrityRequirement: expect.any(Object),
            availabilityRequirement: expect.any(Object),
            tester: expect.any(Object),
            businessOwner: expect.any(Object),
            developmentContact: expect.any(Object),
            preferredOfferingType: expect.any(Object),
            assetGroupName: expect.any(Object),
            dateCreated: expect.any(Object),
            lastUpdated: expect.any(Object),
            lastComment: expect.any(Object),
            createdBy: expect.any(Object),
            newIssues: expect.any(Object),
            openIssues: expect.any(Object),
            totalIssues: expect.any(Object),
            overallCompliance: expect.any(Object),
            canBeDeleted: expect.any(Object),
            lockedToSubscription: expect.any(Object),
            totalScans: expect.any(Object),
            nScanExecutions: expect.any(Object),
            hasExceedingIssuesNumber: expect.any(Object),
            hasExceedingScansNumber: expect.any(Object),
            autoDeleteExceededScans: expect.any(Object),
          }),
        );
      });

      it('passing IAppDetails should create a new form with FormGroup', () => {
        const formGroup = service.createAppDetailsFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            appId: expect.any(Object),
            riskRating: expect.any(Object),
            criticalIssues: expect.any(Object),
            highIssues: expect.any(Object),
            mediumIssues: expect.any(Object),
            lowIssues: expect.any(Object),
            informationalIssues: expect.any(Object),
            issuesInProgress: expect.any(Object),
            maxSeverity: expect.any(Object),
            correlationState: expect.any(Object),
            rRMaxSeverity: expect.any(Object),
            assetGroupId: expect.any(Object),
            businessImpact: expect.any(Object),
            url: expect.any(Object),
            description: expect.any(Object),
            businessUnit: expect.any(Object),
            businessUnitId: expect.any(Object),
            types: expect.any(Object),
            technology: expect.any(Object),
            testingStatus: expect.any(Object),
            appHosts: expect.any(Object),
            collateralDamagePotential: expect.any(Object),
            targetDistribution: expect.any(Object),
            confidentialityRequirement: expect.any(Object),
            integrityRequirement: expect.any(Object),
            availabilityRequirement: expect.any(Object),
            tester: expect.any(Object),
            businessOwner: expect.any(Object),
            developmentContact: expect.any(Object),
            preferredOfferingType: expect.any(Object),
            assetGroupName: expect.any(Object),
            dateCreated: expect.any(Object),
            lastUpdated: expect.any(Object),
            lastComment: expect.any(Object),
            createdBy: expect.any(Object),
            newIssues: expect.any(Object),
            openIssues: expect.any(Object),
            totalIssues: expect.any(Object),
            overallCompliance: expect.any(Object),
            canBeDeleted: expect.any(Object),
            lockedToSubscription: expect.any(Object),
            totalScans: expect.any(Object),
            nScanExecutions: expect.any(Object),
            hasExceedingIssuesNumber: expect.any(Object),
            hasExceedingScansNumber: expect.any(Object),
            autoDeleteExceededScans: expect.any(Object),
          }),
        );
      });
    });

    describe('getAppDetails', () => {
      it('should return NewAppDetails for default AppDetails initial value', () => {
        const formGroup = service.createAppDetailsFormGroup(sampleWithNewData);

        const appDetails = service.getAppDetails(formGroup) as any;

        expect(appDetails).toMatchObject(sampleWithNewData);
      });

      it('should return NewAppDetails for empty AppDetails initial value', () => {
        const formGroup = service.createAppDetailsFormGroup();

        const appDetails = service.getAppDetails(formGroup) as any;

        expect(appDetails).toMatchObject({});
      });

      it('should return IAppDetails', () => {
        const formGroup = service.createAppDetailsFormGroup(sampleWithRequiredData);

        const appDetails = service.getAppDetails(formGroup) as any;

        expect(appDetails).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IAppDetails should not enable id FormControl', () => {
        const formGroup = service.createAppDetailsFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewAppDetails should disable id FormControl', () => {
        const formGroup = service.createAppDetailsFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
