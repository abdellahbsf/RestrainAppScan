import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IAppScans } from '../app-scans.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../app-scans.test-samples';

import { AppScansService, RestAppScans } from './app-scans.service';

const requireRestSample: RestAppScans = {
  ...sampleWithRequiredData,
  createdAt: sampleWithRequiredData.createdAt?.toJSON(),
  lastModified: sampleWithRequiredData.lastModified?.toJSON(),
};

describe('AppScans Service', () => {
  let service: AppScansService;
  let httpMock: HttpTestingController;
  let expectedResult: IAppScans | IAppScans[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(AppScansService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.find(123).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should create a AppScans', () => {
      const appScans = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(appScans).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a AppScans', () => {
      const appScans = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(appScans).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a AppScans', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of AppScans', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a AppScans', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addAppScansToCollectionIfMissing', () => {
      it('should add a AppScans to an empty array', () => {
        const appScans: IAppScans = sampleWithRequiredData;
        expectedResult = service.addAppScansToCollectionIfMissing([], appScans);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(appScans);
      });

      it('should not add a AppScans to an array that contains it', () => {
        const appScans: IAppScans = sampleWithRequiredData;
        const appScansCollection: IAppScans[] = [
          {
            ...appScans,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addAppScansToCollectionIfMissing(appScansCollection, appScans);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a AppScans to an array that doesn't contain it", () => {
        const appScans: IAppScans = sampleWithRequiredData;
        const appScansCollection: IAppScans[] = [sampleWithPartialData];
        expectedResult = service.addAppScansToCollectionIfMissing(appScansCollection, appScans);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(appScans);
      });

      it('should add only unique AppScans to an array', () => {
        const appScansArray: IAppScans[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const appScansCollection: IAppScans[] = [sampleWithRequiredData];
        expectedResult = service.addAppScansToCollectionIfMissing(appScansCollection, ...appScansArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const appScans: IAppScans = sampleWithRequiredData;
        const appScans2: IAppScans = sampleWithPartialData;
        expectedResult = service.addAppScansToCollectionIfMissing([], appScans, appScans2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(appScans);
        expect(expectedResult).toContain(appScans2);
      });

      it('should accept null and undefined values', () => {
        const appScans: IAppScans = sampleWithRequiredData;
        expectedResult = service.addAppScansToCollectionIfMissing([], null, appScans, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(appScans);
      });

      it('should return initial array if no AppScans is added', () => {
        const appScansCollection: IAppScans[] = [sampleWithRequiredData];
        expectedResult = service.addAppScansToCollectionIfMissing(appScansCollection, undefined, null);
        expect(expectedResult).toEqual(appScansCollection);
      });
    });

    describe('compareAppScans', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareAppScans(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareAppScans(entity1, entity2);
        const compareResult2 = service.compareAppScans(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareAppScans(entity1, entity2);
        const compareResult2 = service.compareAppScans(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareAppScans(entity1, entity2);
        const compareResult2 = service.compareAppScans(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
