import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { AppIssueService } from '../service/app-issue.service';
import { IAppIssue } from '../app-issue.model';
import { AppIssueFormService } from './app-issue-form.service';

import { AppIssueUpdateComponent } from './app-issue-update.component';

describe('AppIssue Management Update Component', () => {
  let comp: AppIssueUpdateComponent;
  let fixture: ComponentFixture<AppIssueUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let appIssueFormService: AppIssueFormService;
  let appIssueService: AppIssueService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), AppIssueUpdateComponent],
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
      .overrideTemplate(AppIssueUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(AppIssueUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    appIssueFormService = TestBed.inject(AppIssueFormService);
    appIssueService = TestBed.inject(AppIssueService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const appIssue: IAppIssue = { id: 456 };

      activatedRoute.data = of({ appIssue });
      comp.ngOnInit();

      expect(comp.appIssue).toEqual(appIssue);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IAppIssue>>();
      const appIssue = { id: 123 };
      jest.spyOn(appIssueFormService, 'getAppIssue').mockReturnValue(appIssue);
      jest.spyOn(appIssueService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ appIssue });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: appIssue }));
      saveSubject.complete();

      // THEN
      expect(appIssueFormService.getAppIssue).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(appIssueService.update).toHaveBeenCalledWith(expect.objectContaining(appIssue));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IAppIssue>>();
      const appIssue = { id: 123 };
      jest.spyOn(appIssueFormService, 'getAppIssue').mockReturnValue({ id: null });
      jest.spyOn(appIssueService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ appIssue: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: appIssue }));
      saveSubject.complete();

      // THEN
      expect(appIssueFormService.getAppIssue).toHaveBeenCalled();
      expect(appIssueService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IAppIssue>>();
      const appIssue = { id: 123 };
      jest.spyOn(appIssueService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ appIssue });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(appIssueService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
