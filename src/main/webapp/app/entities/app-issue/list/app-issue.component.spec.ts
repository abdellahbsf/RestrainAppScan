import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { AppIssueService } from '../service/app-issue.service';

import { AppIssueComponent } from './app-issue.component';

describe('AppIssue Management Component', () => {
  let comp: AppIssueComponent;
  let fixture: ComponentFixture<AppIssueComponent>;
  let service: AppIssueService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule.withRoutes([{ path: 'app-issue', component: AppIssueComponent }]),
        HttpClientTestingModule,
        AppIssueComponent,
      ],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: {
            data: of({
              defaultSort: 'id,asc',
            }),
            queryParamMap: of(
              jest.requireActual('@angular/router').convertToParamMap({
                page: '1',
                size: '1',
                sort: 'id,desc',
              }),
            ),
            snapshot: { queryParams: {} },
          },
        },
      ],
    })
      .overrideTemplate(AppIssueComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(AppIssueComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(AppIssueService);

    const headers = new HttpHeaders();
    jest.spyOn(service, 'query').mockReturnValue(
      of(
        new HttpResponse({
          body: [{ id: 123 }],
          headers,
        }),
      ),
    );
  });

  it('Should call load all on init', () => {
    // WHEN
    comp.ngOnInit();

    // THEN
    expect(service.query).toHaveBeenCalled();
    expect(comp.appIssues?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });

  describe('trackId', () => {
    it('Should forward to appIssueService', () => {
      const entity = { id: 123 };
      jest.spyOn(service, 'getAppIssueIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getAppIssueIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });
});
