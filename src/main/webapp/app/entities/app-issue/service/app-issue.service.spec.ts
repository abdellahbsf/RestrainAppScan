import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IAppIssue } from '../app-issue.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../app-issue.test-samples';

import { AppIssueService, RestAppIssue } from './app-issue.service';

const requireRestSample: RestAppIssue = {
  ...sampleWithRequiredData,
  dateCreated: sampleWithRequiredData.dateCreated?.toJSON(),
  lastUpdated: sampleWithRequiredData.lastUpdated?.toJSON(),
  lastFound: sampleWithRequiredData.lastFound?.toJSON(),
};

describe('AppIssue Service', () => {
  let service: AppIssueService;
  let httpMock: HttpTestingController;
  let expectedResult: IAppIssue | IAppIssue[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(AppIssueService);
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

    it('should create a AppIssue', () => {
      const appIssue = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(appIssue).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a AppIssue', () => {
      const appIssue = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(appIssue).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a AppIssue', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of AppIssue', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a AppIssue', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addAppIssueToCollectionIfMissing', () => {
      it('should add a AppIssue to an empty array', () => {
        const appIssue: IAppIssue = sampleWithRequiredData;
        expectedResult = service.addAppIssueToCollectionIfMissing([], appIssue);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(appIssue);
      });

      it('should not add a AppIssue to an array that contains it', () => {
        const appIssue: IAppIssue = sampleWithRequiredData;
        const appIssueCollection: IAppIssue[] = [
          {
            ...appIssue,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addAppIssueToCollectionIfMissing(appIssueCollection, appIssue);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a AppIssue to an array that doesn't contain it", () => {
        const appIssue: IAppIssue = sampleWithRequiredData;
        const appIssueCollection: IAppIssue[] = [sampleWithPartialData];
        expectedResult = service.addAppIssueToCollectionIfMissing(appIssueCollection, appIssue);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(appIssue);
      });

      it('should add only unique AppIssue to an array', () => {
        const appIssueArray: IAppIssue[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const appIssueCollection: IAppIssue[] = [sampleWithRequiredData];
        expectedResult = service.addAppIssueToCollectionIfMissing(appIssueCollection, ...appIssueArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const appIssue: IAppIssue = sampleWithRequiredData;
        const appIssue2: IAppIssue = sampleWithPartialData;
        expectedResult = service.addAppIssueToCollectionIfMissing([], appIssue, appIssue2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(appIssue);
        expect(expectedResult).toContain(appIssue2);
      });

      it('should accept null and undefined values', () => {
        const appIssue: IAppIssue = sampleWithRequiredData;
        expectedResult = service.addAppIssueToCollectionIfMissing([], null, appIssue, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(appIssue);
      });

      it('should return initial array if no AppIssue is added', () => {
        const appIssueCollection: IAppIssue[] = [sampleWithRequiredData];
        expectedResult = service.addAppIssueToCollectionIfMissing(appIssueCollection, undefined, null);
        expect(expectedResult).toEqual(appIssueCollection);
      });
    });

    describe('compareAppIssue', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareAppIssue(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareAppIssue(entity1, entity2);
        const compareResult2 = service.compareAppIssue(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareAppIssue(entity1, entity2);
        const compareResult2 = service.compareAppIssue(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareAppIssue(entity1, entity2);
        const compareResult2 = service.compareAppIssue(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
