import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IAppDetails } from '../app-details.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../app-details.test-samples';

import { AppDetailsService, RestAppDetails } from './app-details.service';

const requireRestSample: RestAppDetails = {
  ...sampleWithRequiredData,
  dateCreated: sampleWithRequiredData.dateCreated?.toJSON(),
  lastUpdated: sampleWithRequiredData.lastUpdated?.toJSON(),
};

describe('AppDetails Service', () => {
  let service: AppDetailsService;
  let httpMock: HttpTestingController;
  let expectedResult: IAppDetails | IAppDetails[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(AppDetailsService);
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

    it('should create a AppDetails', () => {
      const appDetails = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(appDetails).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a AppDetails', () => {
      const appDetails = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(appDetails).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a AppDetails', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of AppDetails', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a AppDetails', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addAppDetailsToCollectionIfMissing', () => {
      it('should add a AppDetails to an empty array', () => {
        const appDetails: IAppDetails = sampleWithRequiredData;
        expectedResult = service.addAppDetailsToCollectionIfMissing([], appDetails);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(appDetails);
      });

      it('should not add a AppDetails to an array that contains it', () => {
        const appDetails: IAppDetails = sampleWithRequiredData;
        const appDetailsCollection: IAppDetails[] = [
          {
            ...appDetails,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addAppDetailsToCollectionIfMissing(appDetailsCollection, appDetails);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a AppDetails to an array that doesn't contain it", () => {
        const appDetails: IAppDetails = sampleWithRequiredData;
        const appDetailsCollection: IAppDetails[] = [sampleWithPartialData];
        expectedResult = service.addAppDetailsToCollectionIfMissing(appDetailsCollection, appDetails);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(appDetails);
      });

      it('should add only unique AppDetails to an array', () => {
        const appDetailsArray: IAppDetails[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const appDetailsCollection: IAppDetails[] = [sampleWithRequiredData];
        expectedResult = service.addAppDetailsToCollectionIfMissing(appDetailsCollection, ...appDetailsArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const appDetails: IAppDetails = sampleWithRequiredData;
        const appDetails2: IAppDetails = sampleWithPartialData;
        expectedResult = service.addAppDetailsToCollectionIfMissing([], appDetails, appDetails2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(appDetails);
        expect(expectedResult).toContain(appDetails2);
      });

      it('should accept null and undefined values', () => {
        const appDetails: IAppDetails = sampleWithRequiredData;
        expectedResult = service.addAppDetailsToCollectionIfMissing([], null, appDetails, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(appDetails);
      });

      it('should return initial array if no AppDetails is added', () => {
        const appDetailsCollection: IAppDetails[] = [sampleWithRequiredData];
        expectedResult = service.addAppDetailsToCollectionIfMissing(appDetailsCollection, undefined, null);
        expect(expectedResult).toEqual(appDetailsCollection);
      });
    });

    describe('compareAppDetails', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareAppDetails(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareAppDetails(entity1, entity2);
        const compareResult2 = service.compareAppDetails(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareAppDetails(entity1, entity2);
        const compareResult2 = service.compareAppDetails(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareAppDetails(entity1, entity2);
        const compareResult2 = service.compareAppDetails(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
