import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IAllApps } from '../all-apps.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../all-apps.test-samples';

import { AllAppsService } from './all-apps.service';

const requireRestSample: IAllApps = {
  ...sampleWithRequiredData,
};

describe('AllApps Service', () => {
  let service: AllAppsService;
  let httpMock: HttpTestingController;
  let expectedResult: IAllApps | IAllApps[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(AllAppsService);
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

    it('should create a AllApps', () => {
      const allApps = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(allApps).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a AllApps', () => {
      const allApps = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(allApps).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a AllApps', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of AllApps', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a AllApps', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addAllAppsToCollectionIfMissing', () => {
      it('should add a AllApps to an empty array', () => {
        const allApps: IAllApps = sampleWithRequiredData;
        expectedResult = service.addAllAppsToCollectionIfMissing([], allApps);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(allApps);
      });

      it('should not add a AllApps to an array that contains it', () => {
        const allApps: IAllApps = sampleWithRequiredData;
        const allAppsCollection: IAllApps[] = [
          {
            ...allApps,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addAllAppsToCollectionIfMissing(allAppsCollection, allApps);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a AllApps to an array that doesn't contain it", () => {
        const allApps: IAllApps = sampleWithRequiredData;
        const allAppsCollection: IAllApps[] = [sampleWithPartialData];
        expectedResult = service.addAllAppsToCollectionIfMissing(allAppsCollection, allApps);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(allApps);
      });

      it('should add only unique AllApps to an array', () => {
        const allAppsArray: IAllApps[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const allAppsCollection: IAllApps[] = [sampleWithRequiredData];
        expectedResult = service.addAllAppsToCollectionIfMissing(allAppsCollection, ...allAppsArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const allApps: IAllApps = sampleWithRequiredData;
        const allApps2: IAllApps = sampleWithPartialData;
        expectedResult = service.addAllAppsToCollectionIfMissing([], allApps, allApps2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(allApps);
        expect(expectedResult).toContain(allApps2);
      });

      it('should accept null and undefined values', () => {
        const allApps: IAllApps = sampleWithRequiredData;
        expectedResult = service.addAllAppsToCollectionIfMissing([], null, allApps, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(allApps);
      });

      it('should return initial array if no AllApps is added', () => {
        const allAppsCollection: IAllApps[] = [sampleWithRequiredData];
        expectedResult = service.addAllAppsToCollectionIfMissing(allAppsCollection, undefined, null);
        expect(expectedResult).toEqual(allAppsCollection);
      });
    });

    describe('compareAllApps', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareAllApps(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareAllApps(entity1, entity2);
        const compareResult2 = service.compareAllApps(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareAllApps(entity1, entity2);
        const compareResult2 = service.compareAllApps(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareAllApps(entity1, entity2);
        const compareResult2 = service.compareAllApps(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
