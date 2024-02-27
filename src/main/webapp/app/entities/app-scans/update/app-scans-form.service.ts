import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { IAppScans, NewAppScans } from '../app-scans.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IAppScans for edit and NewAppScansFormGroupInput for create.
 */
type AppScansFormGroupInput = IAppScans | PartialWithRequiredKeyOf<NewAppScans>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends IAppScans | NewAppScans> = Omit<T, 'createdAt' | 'lastModified'> & {
  createdAt?: string | null;
  lastModified?: string | null;
};

type AppScansFormRawValue = FormValueOf<IAppScans>;

type NewAppScansFormRawValue = FormValueOf<NewAppScans>;

type AppScansFormDefaults = Pick<NewAppScans, 'id' | 'createdAt' | 'lastModified'>;

type AppScansFormGroupContent = {
  id: FormControl<AppScansFormRawValue['id'] | NewAppScans['id']>;
  appId: FormControl<AppScansFormRawValue['appId']>;
  nameScan: FormControl<AppScansFormRawValue['nameScan']>;
  technology: FormControl<AppScansFormRawValue['technology']>;
  iastAgentType: FormControl<AppScansFormRawValue['iastAgentType']>;
  iastAgentStatus: FormControl<AppScansFormRawValue['iastAgentStatus']>;
  urlScan: FormControl<AppScansFormRawValue['urlScan']>;
  appName: FormControl<AppScansFormRawValue['appName']>;
  testOptimizationLevel: FormControl<AppScansFormRawValue['testOptimizationLevel']>;
  numberOfExecutions: FormControl<AppScansFormRawValue['numberOfExecutions']>;
  createdAt: FormControl<AppScansFormRawValue['createdAt']>;
  lastModified: FormControl<AppScansFormRawValue['lastModified']>;
};

export type AppScansFormGroup = FormGroup<AppScansFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class AppScansFormService {
  createAppScansFormGroup(appScans: AppScansFormGroupInput = { id: null }): AppScansFormGroup {
    const appScansRawValue = this.convertAppScansToAppScansRawValue({
      ...this.getFormDefaults(),
      ...appScans,
    });
    return new FormGroup<AppScansFormGroupContent>({
      id: new FormControl(
        { value: appScansRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      appId: new FormControl(appScansRawValue.appId),
      nameScan: new FormControl(appScansRawValue.nameScan),
      technology: new FormControl(appScansRawValue.technology),
      iastAgentType: new FormControl(appScansRawValue.iastAgentType),
      iastAgentStatus: new FormControl(appScansRawValue.iastAgentStatus),
      urlScan: new FormControl(appScansRawValue.urlScan),
      appName: new FormControl(appScansRawValue.appName),
      testOptimizationLevel: new FormControl(appScansRawValue.testOptimizationLevel),
      numberOfExecutions: new FormControl(appScansRawValue.numberOfExecutions),
      createdAt: new FormControl(appScansRawValue.createdAt),
      lastModified: new FormControl(appScansRawValue.lastModified),
    });
  }

  getAppScans(form: AppScansFormGroup): IAppScans | NewAppScans {
    return this.convertAppScansRawValueToAppScans(form.getRawValue() as AppScansFormRawValue | NewAppScansFormRawValue);
  }

  resetForm(form: AppScansFormGroup, appScans: AppScansFormGroupInput): void {
    const appScansRawValue = this.convertAppScansToAppScansRawValue({ ...this.getFormDefaults(), ...appScans });
    form.reset(
      {
        ...appScansRawValue,
        id: { value: appScansRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): AppScansFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      createdAt: currentTime,
      lastModified: currentTime,
    };
  }

  private convertAppScansRawValueToAppScans(rawAppScans: AppScansFormRawValue | NewAppScansFormRawValue): IAppScans | NewAppScans {
    return {
      ...rawAppScans,
      createdAt: dayjs(rawAppScans.createdAt, DATE_TIME_FORMAT),
      lastModified: dayjs(rawAppScans.lastModified, DATE_TIME_FORMAT),
    };
  }

  private convertAppScansToAppScansRawValue(
    appScans: IAppScans | (Partial<NewAppScans> & AppScansFormDefaults),
  ): AppScansFormRawValue | PartialWithRequiredKeyOf<NewAppScansFormRawValue> {
    return {
      ...appScans,
      createdAt: appScans.createdAt ? appScans.createdAt.format(DATE_TIME_FORMAT) : undefined,
      lastModified: appScans.lastModified ? appScans.lastModified.format(DATE_TIME_FORMAT) : undefined,
    };
  }
}
