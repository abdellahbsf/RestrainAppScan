import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { AllAppsService } from '../service/all-apps.service';
import { IAllApps } from '../all-apps.model';
import { AllAppsFormService } from './all-apps-form.service';

import { AllAppsUpdateComponent } from './all-apps-update.component';

describe('AllApps Management Update Component', () => {
  let comp: AllAppsUpdateComponent;
  let fixture: ComponentFixture<AllAppsUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let allAppsFormService: AllAppsFormService;
  let allAppsService: AllAppsService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), AllAppsUpdateComponent],
      providers: [
        FormBuilder,
        {
          provide: ActivatedRoute,
          useValue: {
            params: from([{}]),
          },
        },
      ],
    })
      .overrideTemplate(AllAppsUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(AllAppsUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    allAppsFormService = TestBed.inject(AllAppsFormService);
    allAppsService = TestBed.inject(AllAppsService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const allApps: IAllApps = { id: 456 };

      activatedRoute.data = of({ allApps });
      comp.ngOnInit();

      expect(comp.allApps).toEqual(allApps);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IAllApps>>();
      const allApps = { id: 123 };
      jest.spyOn(allAppsFormService, 'getAllApps').mockReturnValue(allApps);
      jest.spyOn(allAppsService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ allApps });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: allApps }));
      saveSubject.complete();

      // THEN
      expect(allAppsFormService.getAllApps).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(allAppsService.update).toHaveBeenCalledWith(expect.objectContaining(allApps));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IAllApps>>();
      const allApps = { id: 123 };
      jest.spyOn(allAppsFormService, 'getAllApps').mockReturnValue({ id: null });
      jest.spyOn(allAppsService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ allApps: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: allApps }));
      saveSubject.complete();

      // THEN
      expect(allAppsFormService.getAllApps).toHaveBeenCalled();
      expect(allAppsService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IAllApps>>();
      const allApps = { id: 123 };
      jest.spyOn(allAppsService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ allApps });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(allAppsService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
