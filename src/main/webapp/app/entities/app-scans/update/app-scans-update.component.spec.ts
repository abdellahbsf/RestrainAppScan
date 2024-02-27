import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { AppScansService } from '../service/app-scans.service';
import { IAppScans } from '../app-scans.model';
import { AppScansFormService } from './app-scans-form.service';

import { AppScansUpdateComponent } from './app-scans-update.component';

describe('AppScans Management Update Component', () => {
  let comp: AppScansUpdateComponent;
  let fixture: ComponentFixture<AppScansUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let appScansFormService: AppScansFormService;
  let appScansService: AppScansService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), AppScansUpdateComponent],
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
      .overrideTemplate(AppScansUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(AppScansUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    appScansFormService = TestBed.inject(AppScansFormService);
    appScansService = TestBed.inject(AppScansService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const appScans: IAppScans = { id: 456 };

      activatedRoute.data = of({ appScans });
      comp.ngOnInit();

      expect(comp.appScans).toEqual(appScans);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IAppScans>>();
      const appScans = { id: 123 };
      jest.spyOn(appScansFormService, 'getAppScans').mockReturnValue(appScans);
      jest.spyOn(appScansService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ appScans });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: appScans }));
      saveSubject.complete();

      // THEN
      expect(appScansFormService.getAppScans).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(appScansService.update).toHaveBeenCalledWith(expect.objectContaining(appScans));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IAppScans>>();
      const appScans = { id: 123 };
      jest.spyOn(appScansFormService, 'getAppScans').mockReturnValue({ id: null });
      jest.spyOn(appScansService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ appScans: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: appScans }));
      saveSubject.complete();

      // THEN
      expect(appScansFormService.getAppScans).toHaveBeenCalled();
      expect(appScansService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IAppScans>>();
      const appScans = { id: 123 };
      jest.spyOn(appScansService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ appScans });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(appScansService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
