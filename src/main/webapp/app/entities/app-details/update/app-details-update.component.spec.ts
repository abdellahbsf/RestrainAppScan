import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { AppDetailsService } from '../service/app-details.service';
import { IAppDetails } from '../app-details.model';
import { AppDetailsFormService } from './app-details-form.service';

import { AppDetailsUpdateComponent } from './app-details-update.component';

describe('AppDetails Management Update Component', () => {
  let comp: AppDetailsUpdateComponent;
  let fixture: ComponentFixture<AppDetailsUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let appDetailsFormService: AppDetailsFormService;
  let appDetailsService: AppDetailsService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), AppDetailsUpdateComponent],
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
      .overrideTemplate(AppDetailsUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(AppDetailsUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    appDetailsFormService = TestBed.inject(AppDetailsFormService);
    appDetailsService = TestBed.inject(AppDetailsService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const appDetails: IAppDetails = { id: 456 };

      activatedRoute.data = of({ appDetails });
      comp.ngOnInit();

      expect(comp.appDetails).toEqual(appDetails);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IAppDetails>>();
      const appDetails = { id: 123 };
      jest.spyOn(appDetailsFormService, 'getAppDetails').mockReturnValue(appDetails);
      jest.spyOn(appDetailsService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ appDetails });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: appDetails }));
      saveSubject.complete();

      // THEN
      expect(appDetailsFormService.getAppDetails).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(appDetailsService.update).toHaveBeenCalledWith(expect.objectContaining(appDetails));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IAppDetails>>();
      const appDetails = { id: 123 };
      jest.spyOn(appDetailsFormService, 'getAppDetails').mockReturnValue({ id: null });
      jest.spyOn(appDetailsService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ appDetails: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: appDetails }));
      saveSubject.complete();

      // THEN
      expect(appDetailsFormService.getAppDetails).toHaveBeenCalled();
      expect(appDetailsService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IAppDetails>>();
      const appDetails = { id: 123 };
      jest.spyOn(appDetailsService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ appDetails });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(appDetailsService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
