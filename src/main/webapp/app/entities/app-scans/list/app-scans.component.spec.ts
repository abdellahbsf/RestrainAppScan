import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { AppScansService } from '../service/app-scans.service';

import { AppScansComponent } from './app-scans.component';

describe('AppScans Management Component', () => {
  let comp: AppScansComponent;
  let fixture: ComponentFixture<AppScansComponent>;
  let service: AppScansService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule.withRoutes([{ path: 'app-scans', component: AppScansComponent }]),
        HttpClientTestingModule,
        AppScansComponent,
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
      .overrideTemplate(AppScansComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(AppScansComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(AppScansService);

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
    expect(comp.appScans?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });

  describe('trackId', () => {
    it('Should forward to appScansService', () => {
      const entity = { id: 123 };
      jest.spyOn(service, 'getAppScansIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getAppScansIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });
});
