import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { IAppDetails, NewAppDetails } from '../app-details.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IAppDetails for edit and NewAppDetailsFormGroupInput for create.
 */
type AppDetailsFormGroupInput = IAppDetails | PartialWithRequiredKeyOf<NewAppDetails>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends IAppDetails | NewAppDetails> = Omit<T, 'dateCreated' | 'lastUpdated'> & {
  dateCreated?: string | null;
  lastUpdated?: string | null;
};

type AppDetailsFormRawValue = FormValueOf<IAppDetails>;

type NewAppDetailsFormRawValue = FormValueOf<NewAppDetails>;

type AppDetailsFormDefaults = Pick<
  NewAppDetails,
  | 'id'
  | 'dateCreated'
  | 'lastUpdated'
  | 'overallCompliance'
  | 'canBeDeleted'
  | 'lockedToSubscription'
  | 'hasExceedingIssuesNumber'
  | 'hasExceedingScansNumber'
  | 'autoDeleteExceededScans'
>;

type AppDetailsFormGroupContent = {
  id: FormControl<AppDetailsFormRawValue['id'] | NewAppDetails['id']>;
  appId: FormControl<AppDetailsFormRawValue['appId']>;
  riskRating: FormControl<AppDetailsFormRawValue['riskRating']>;
  criticalIssues: FormControl<AppDetailsFormRawValue['criticalIssues']>;
  highIssues: FormControl<AppDetailsFormRawValue['highIssues']>;
  mediumIssues: FormControl<AppDetailsFormRawValue['mediumIssues']>;
  lowIssues: FormControl<AppDetailsFormRawValue['lowIssues']>;
  informationalIssues: FormControl<AppDetailsFormRawValue['informationalIssues']>;
  issuesInProgress: FormControl<AppDetailsFormRawValue['issuesInProgress']>;
  maxSeverity: FormControl<AppDetailsFormRawValue['maxSeverity']>;
  correlationState: FormControl<AppDetailsFormRawValue['correlationState']>;
  rRMaxSeverity: FormControl<AppDetailsFormRawValue['rRMaxSeverity']>;
  assetGroupId: FormControl<AppDetailsFormRawValue['assetGroupId']>;
  businessImpact: FormControl<AppDetailsFormRawValue['businessImpact']>;
  url: FormControl<AppDetailsFormRawValue['url']>;
  description: FormControl<AppDetailsFormRawValue['description']>;
  businessUnit: FormControl<AppDetailsFormRawValue['businessUnit']>;
  businessUnitId: FormControl<AppDetailsFormRawValue['businessUnitId']>;
  types: FormControl<AppDetailsFormRawValue['types']>;
  technology: FormControl<AppDetailsFormRawValue['technology']>;
  testingStatus: FormControl<AppDetailsFormRawValue['testingStatus']>;
  appHosts: FormControl<AppDetailsFormRawValue['appHosts']>;
  collateralDamagePotential: FormControl<AppDetailsFormRawValue['collateralDamagePotential']>;
  targetDistribution: FormControl<AppDetailsFormRawValue['targetDistribution']>;
  confidentialityRequirement: FormControl<AppDetailsFormRawValue['confidentialityRequirement']>;
  integrityRequirement: FormControl<AppDetailsFormRawValue['integrityRequirement']>;
  availabilityRequirement: FormControl<AppDetailsFormRawValue['availabilityRequirement']>;
  tester: FormControl<AppDetailsFormRawValue['tester']>;
  businessOwner: FormControl<AppDetailsFormRawValue['businessOwner']>;
  developmentContact: FormControl<AppDetailsFormRawValue['developmentContact']>;
  preferredOfferingType: FormControl<AppDetailsFormRawValue['preferredOfferingType']>;
  assetGroupName: FormControl<AppDetailsFormRawValue['assetGroupName']>;
  dateCreated: FormControl<AppDetailsFormRawValue['dateCreated']>;
  lastUpdated: FormControl<AppDetailsFormRawValue['lastUpdated']>;
  lastComment: FormControl<AppDetailsFormRawValue['lastComment']>;
  createdBy: FormControl<AppDetailsFormRawValue['createdBy']>;
  newIssues: FormControl<AppDetailsFormRawValue['newIssues']>;
  openIssues: FormControl<AppDetailsFormRawValue['openIssues']>;
  totalIssues: FormControl<AppDetailsFormRawValue['totalIssues']>;
  overallCompliance: FormControl<AppDetailsFormRawValue['overallCompliance']>;
  canBeDeleted: FormControl<AppDetailsFormRawValue['canBeDeleted']>;
  lockedToSubscription: FormControl<AppDetailsFormRawValue['lockedToSubscription']>;
  totalScans: FormControl<AppDetailsFormRawValue['totalScans']>;
  nScanExecutions: FormControl<AppDetailsFormRawValue['nScanExecutions']>;
  hasExceedingIssuesNumber: FormControl<AppDetailsFormRawValue['hasExceedingIssuesNumber']>;
  hasExceedingScansNumber: FormControl<AppDetailsFormRawValue['hasExceedingScansNumber']>;
  autoDeleteExceededScans: FormControl<AppDetailsFormRawValue['autoDeleteExceededScans']>;
};

export type AppDetailsFormGroup = FormGroup<AppDetailsFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class AppDetailsFormService {
  createAppDetailsFormGroup(appDetails: AppDetailsFormGroupInput = { id: null }): AppDetailsFormGroup {
    const appDetailsRawValue = this.convertAppDetailsToAppDetailsRawValue({
      ...this.getFormDefaults(),
      ...appDetails,
    });
    return new FormGroup<AppDetailsFormGroupContent>({
      id: new FormControl(
        { value: appDetailsRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      appId: new FormControl(appDetailsRawValue.appId),
      riskRating: new FormControl(appDetailsRawValue.riskRating),
      criticalIssues: new FormControl(appDetailsRawValue.criticalIssues),
      highIssues: new FormControl(appDetailsRawValue.highIssues),
      mediumIssues: new FormControl(appDetailsRawValue.mediumIssues),
      lowIssues: new FormControl(appDetailsRawValue.lowIssues),
      informationalIssues: new FormControl(appDetailsRawValue.informationalIssues),
      issuesInProgress: new FormControl(appDetailsRawValue.issuesInProgress),
      maxSeverity: new FormControl(appDetailsRawValue.maxSeverity),
      correlationState: new FormControl(appDetailsRawValue.correlationState),
      rRMaxSeverity: new FormControl(appDetailsRawValue.rRMaxSeverity),
      assetGroupId: new FormControl(appDetailsRawValue.assetGroupId),
      businessImpact: new FormControl(appDetailsRawValue.businessImpact),
      url: new FormControl(appDetailsRawValue.url),
      description: new FormControl(appDetailsRawValue.description),
      businessUnit: new FormControl(appDetailsRawValue.businessUnit),
      businessUnitId: new FormControl(appDetailsRawValue.businessUnitId),
      types: new FormControl(appDetailsRawValue.types),
      technology: new FormControl(appDetailsRawValue.technology),
      testingStatus: new FormControl(appDetailsRawValue.testingStatus),
      appHosts: new FormControl(appDetailsRawValue.appHosts),
      collateralDamagePotential: new FormControl(appDetailsRawValue.collateralDamagePotential),
      targetDistribution: new FormControl(appDetailsRawValue.targetDistribution),
      confidentialityRequirement: new FormControl(appDetailsRawValue.confidentialityRequirement),
      integrityRequirement: new FormControl(appDetailsRawValue.integrityRequirement),
      availabilityRequirement: new FormControl(appDetailsRawValue.availabilityRequirement),
      tester: new FormControl(appDetailsRawValue.tester),
      businessOwner: new FormControl(appDetailsRawValue.businessOwner),
      developmentContact: new FormControl(appDetailsRawValue.developmentContact),
      preferredOfferingType: new FormControl(appDetailsRawValue.preferredOfferingType),
      assetGroupName: new FormControl(appDetailsRawValue.assetGroupName),
      dateCreated: new FormControl(appDetailsRawValue.dateCreated),
      lastUpdated: new FormControl(appDetailsRawValue.lastUpdated),
      lastComment: new FormControl(appDetailsRawValue.lastComment),
      createdBy: new FormControl(appDetailsRawValue.createdBy),
      newIssues: new FormControl(appDetailsRawValue.newIssues),
      openIssues: new FormControl(appDetailsRawValue.openIssues),
      totalIssues: new FormControl(appDetailsRawValue.totalIssues),
      overallCompliance: new FormControl(appDetailsRawValue.overallCompliance),
      canBeDeleted: new FormControl(appDetailsRawValue.canBeDeleted),
      lockedToSubscription: new FormControl(appDetailsRawValue.lockedToSubscription),
      totalScans: new FormControl(appDetailsRawValue.totalScans),
      nScanExecutions: new FormControl(appDetailsRawValue.nScanExecutions),
      hasExceedingIssuesNumber: new FormControl(appDetailsRawValue.hasExceedingIssuesNumber),
      hasExceedingScansNumber: new FormControl(appDetailsRawValue.hasExceedingScansNumber),
      autoDeleteExceededScans: new FormControl(appDetailsRawValue.autoDeleteExceededScans),
    });
  }

  getAppDetails(form: AppDetailsFormGroup): IAppDetails | NewAppDetails {
    return this.convertAppDetailsRawValueToAppDetails(form.getRawValue() as AppDetailsFormRawValue | NewAppDetailsFormRawValue);
  }

  resetForm(form: AppDetailsFormGroup, appDetails: AppDetailsFormGroupInput): void {
    const appDetailsRawValue = this.convertAppDetailsToAppDetailsRawValue({ ...this.getFormDefaults(), ...appDetails });
    form.reset(
      {
        ...appDetailsRawValue,
        id: { value: appDetailsRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): AppDetailsFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      dateCreated: currentTime,
      lastUpdated: currentTime,
      overallCompliance: false,
      canBeDeleted: false,
      lockedToSubscription: false,
      hasExceedingIssuesNumber: false,
      hasExceedingScansNumber: false,
      autoDeleteExceededScans: false,
    };
  }

  private convertAppDetailsRawValueToAppDetails(
    rawAppDetails: AppDetailsFormRawValue | NewAppDetailsFormRawValue,
  ): IAppDetails | NewAppDetails {
    return {
      ...rawAppDetails,
      dateCreated: dayjs(rawAppDetails.dateCreated, DATE_TIME_FORMAT),
      lastUpdated: dayjs(rawAppDetails.lastUpdated, DATE_TIME_FORMAT),
    };
  }

  private convertAppDetailsToAppDetailsRawValue(
    appDetails: IAppDetails | (Partial<NewAppDetails> & AppDetailsFormDefaults),
  ): AppDetailsFormRawValue | PartialWithRequiredKeyOf<NewAppDetailsFormRawValue> {
    return {
      ...appDetails,
      dateCreated: appDetails.dateCreated ? appDetails.dateCreated.format(DATE_TIME_FORMAT) : undefined,
      lastUpdated: appDetails.lastUpdated ? appDetails.lastUpdated.format(DATE_TIME_FORMAT) : undefined,
    };
  }
}
