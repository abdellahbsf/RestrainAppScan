import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { IAppIssue, NewAppIssue } from '../app-issue.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IAppIssue for edit and NewAppIssueFormGroupInput for create.
 */
type AppIssueFormGroupInput = IAppIssue | PartialWithRequiredKeyOf<NewAppIssue>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends IAppIssue | NewAppIssue> = Omit<T, 'dateCreated' | 'lastUpdated' | 'lastFound'> & {
  dateCreated?: string | null;
  lastUpdated?: string | null;
  lastFound?: string | null;
};

type AppIssueFormRawValue = FormValueOf<IAppIssue>;

type NewAppIssueFormRawValue = FormValueOf<NewAppIssue>;

type AppIssueFormDefaults = Pick<NewAppIssue, 'id' | 'dateCreated' | 'lastUpdated' | 'lastFound' | 'isNewInScope'>;

type AppIssueFormGroupContent = {
  id: FormControl<AppIssueFormRawValue['id'] | NewAppIssue['id']>;
  appId: FormControl<AppIssueFormRawValue['appId']>;
  programmingLanguage: FormControl<AppIssueFormRawValue['programmingLanguage']>;
  severities: FormControl<AppIssueFormRawValue['severities']>;
  statusIssue: FormControl<AppIssueFormRawValue['statusIssue']>;
  issueType: FormControl<AppIssueFormRawValue['issueType']>;
  locationIssue: FormControl<AppIssueFormRawValue['locationIssue']>;
  dateCreated: FormControl<AppIssueFormRawValue['dateCreated']>;
  lastUpdated: FormControl<AppIssueFormRawValue['lastUpdated']>;
  lastFound: FormControl<AppIssueFormRawValue['lastFound']>;
  callingMethod: FormControl<AppIssueFormRawValue['callingMethod']>;
  isNewInScope: FormControl<AppIssueFormRawValue['isNewInScope']>;
  libraryName: FormControl<AppIssueFormRawValue['libraryName']>;
  libraryVersion: FormControl<AppIssueFormRawValue['libraryVersion']>;
  scaTechnology: FormControl<AppIssueFormRawValue['scaTechnology']>;
  fGStatus: FormControl<AppIssueFormRawValue['fGStatus']>;
  applicationId: FormControl<AppIssueFormRawValue['applicationId']>;
  fixGroupId: FormControl<AppIssueFormRawValue['fixGroupId']>;
  apiIssue: FormControl<AppIssueFormRawValue['apiIssue']>;
  sourceIssue: FormControl<AppIssueFormRawValue['sourceIssue']>;
  contextIssue: FormControl<AppIssueFormRawValue['contextIssue']>;
  appscanVulnId: FormControl<AppIssueFormRawValue['appscanVulnId']>;
  callingLine: FormControl<AppIssueFormRawValue['callingLine']>;
  classIssue: FormControl<AppIssueFormRawValue['classIssue']>;
  cveIssue: FormControl<AppIssueFormRawValue['cveIssue']>;
};

export type AppIssueFormGroup = FormGroup<AppIssueFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class AppIssueFormService {
  createAppIssueFormGroup(appIssue: AppIssueFormGroupInput = { id: null }): AppIssueFormGroup {
    const appIssueRawValue = this.convertAppIssueToAppIssueRawValue({
      ...this.getFormDefaults(),
      ...appIssue,
    });
    return new FormGroup<AppIssueFormGroupContent>({
      id: new FormControl(
        { value: appIssueRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      appId: new FormControl(appIssueRawValue.appId),
      programmingLanguage: new FormControl(appIssueRawValue.programmingLanguage),
      severities: new FormControl(appIssueRawValue.severities),
      statusIssue: new FormControl(appIssueRawValue.statusIssue),
      issueType: new FormControl(appIssueRawValue.issueType),
      locationIssue: new FormControl(appIssueRawValue.locationIssue),
      dateCreated: new FormControl(appIssueRawValue.dateCreated),
      lastUpdated: new FormControl(appIssueRawValue.lastUpdated),
      lastFound: new FormControl(appIssueRawValue.lastFound),
      callingMethod: new FormControl(appIssueRawValue.callingMethod),
      isNewInScope: new FormControl(appIssueRawValue.isNewInScope),
      libraryName: new FormControl(appIssueRawValue.libraryName),
      libraryVersion: new FormControl(appIssueRawValue.libraryVersion),
      scaTechnology: new FormControl(appIssueRawValue.scaTechnology),
      fGStatus: new FormControl(appIssueRawValue.fGStatus),
      applicationId: new FormControl(appIssueRawValue.applicationId),
      fixGroupId: new FormControl(appIssueRawValue.fixGroupId),
      apiIssue: new FormControl(appIssueRawValue.apiIssue),
      sourceIssue: new FormControl(appIssueRawValue.sourceIssue),
      contextIssue: new FormControl(appIssueRawValue.contextIssue),
      appscanVulnId: new FormControl(appIssueRawValue.appscanVulnId),
      callingLine: new FormControl(appIssueRawValue.callingLine),
      classIssue: new FormControl(appIssueRawValue.classIssue),
      cveIssue: new FormControl(appIssueRawValue.cveIssue),
    });
  }

  getAppIssue(form: AppIssueFormGroup): IAppIssue | NewAppIssue {
    return this.convertAppIssueRawValueToAppIssue(form.getRawValue() as AppIssueFormRawValue | NewAppIssueFormRawValue);
  }

  resetForm(form: AppIssueFormGroup, appIssue: AppIssueFormGroupInput): void {
    const appIssueRawValue = this.convertAppIssueToAppIssueRawValue({ ...this.getFormDefaults(), ...appIssue });
    form.reset(
      {
        ...appIssueRawValue,
        id: { value: appIssueRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): AppIssueFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      dateCreated: currentTime,
      lastUpdated: currentTime,
      lastFound: currentTime,
      isNewInScope: false,
    };
  }

  private convertAppIssueRawValueToAppIssue(rawAppIssue: AppIssueFormRawValue | NewAppIssueFormRawValue): IAppIssue | NewAppIssue {
    return {
      ...rawAppIssue,
      dateCreated: dayjs(rawAppIssue.dateCreated, DATE_TIME_FORMAT),
      lastUpdated: dayjs(rawAppIssue.lastUpdated, DATE_TIME_FORMAT),
      lastFound: dayjs(rawAppIssue.lastFound, DATE_TIME_FORMAT),
    };
  }

  private convertAppIssueToAppIssueRawValue(
    appIssue: IAppIssue | (Partial<NewAppIssue> & AppIssueFormDefaults),
  ): AppIssueFormRawValue | PartialWithRequiredKeyOf<NewAppIssueFormRawValue> {
    return {
      ...appIssue,
      dateCreated: appIssue.dateCreated ? appIssue.dateCreated.format(DATE_TIME_FORMAT) : undefined,
      lastUpdated: appIssue.lastUpdated ? appIssue.lastUpdated.format(DATE_TIME_FORMAT) : undefined,
      lastFound: appIssue.lastFound ? appIssue.lastFound.format(DATE_TIME_FORMAT) : undefined,
    };
  }
}
