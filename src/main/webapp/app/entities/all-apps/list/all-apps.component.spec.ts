import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { AllAppsService } from '../service/all-apps.service';

import { AllAppsComponent } from './all-apps.component';

describe('AllApps Management Component', () => {
  let comp: AllAppsComponent;
  let fixture: ComponentFixture<AllAppsComponent>;
  let service: AllAppsService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule.withRoutes([{ path: 'all-apps', component: AllAppsComponent }]),
        HttpClientTestingModule,
        AllAppsComponent,
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
      .overrideTemplate(AllAppsComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(AllAppsComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(AllAppsService);

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
    expect(comp.allApps?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });

  describe('trackId', () => {
    it('Should forward to allAppsService', () => {
      const entity = { id: 123 };
      jest.spyOn(service, 'getAllAppsIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getAllAppsIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });
});
