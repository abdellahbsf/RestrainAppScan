import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { AppDetailsService } from '../service/app-details.service';

import { AppDetailsComponent } from './app-details.component';

describe('AppDetails Management Component', () => {
  let comp: AppDetailsComponent;
  let fixture: ComponentFixture<AppDetailsComponent>;
  let service: AppDetailsService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule.withRoutes([{ path: 'app-details', component: AppDetailsComponent }]),
        HttpClientTestingModule,
        AppDetailsComponent,
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
      .overrideTemplate(AppDetailsComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(AppDetailsComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(AppDetailsService);

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
    expect(comp.appDetails?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });

  describe('trackId', () => {
    it('Should forward to appDetailsService', () => {
      const entity = { id: 123 };
      jest.spyOn(service, 'getAppDetailsIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getAppDetailsIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });
});
