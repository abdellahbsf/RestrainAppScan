import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IAllApps, NewAllApps } from '../all-apps.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IAllApps for edit and NewAllAppsFormGroupInput for create.
 */
type AllAppsFormGroupInput = IAllApps | PartialWithRequiredKeyOf<NewAllApps>;

type AllAppsFormDefaults = Pick<NewAllApps, 'id'>;

type AllAppsFormGroupContent = {
  id: FormControl<IAllApps['id'] | NewAllApps['id']>;
  appId: FormControl<IAllApps['appId']>;
  name: FormControl<IAllApps['name']>;
};

export type AllAppsFormGroup = FormGroup<AllAppsFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class AllAppsFormService {
  createAllAppsFormGroup(allApps: AllAppsFormGroupInput = { id: null }): AllAppsFormGroup {
    const allAppsRawValue = {
      ...this.getFormDefaults(),
      ...allApps,
    };
    return new FormGroup<AllAppsFormGroupContent>({
      id: new FormControl(
        { value: allAppsRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      appId: new FormControl(allAppsRawValue.appId),
      name: new FormControl(allAppsRawValue.name),
    });
  }

  getAllApps(form: AllAppsFormGroup): IAllApps | NewAllApps {
    return form.getRawValue() as IAllApps | NewAllApps;
  }

  resetForm(form: AllAppsFormGroup, allApps: AllAppsFormGroupInput): void {
    const allAppsRawValue = { ...this.getFormDefaults(), ...allApps };
    form.reset(
      {
        ...allAppsRawValue,
        id: { value: allAppsRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): AllAppsFormDefaults {
    return {
      id: null,
    };
  }
}
