import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';
import { of } from 'rxjs';

import { CourtsService } from './courts.service';

describe('CourtsService', () => {
  let service: CourtsService;
  let httpTestingController: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [CourtsService],
    });
    service = TestBed.inject(CourtsService);
    httpTestingController = TestBed.inject(HttpTestingController);

  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('Should call update Case Status API', () => {
    let responseData = {
      "error": false,
      "msg": "Status Updated to Close  Successfully after Successfull Payment",
      "object": {
        "caseId": 3,
        "caseName": "Not Wearing Helmet",
        "dateTime": "12/12/2022 11:12:12",
        "status": "Closed",
        "fine": 2000,
        "vehicleNo": "MH-19-AG-5465",
        "mobileNo": "9876543212"
      }
    }
    let spy = spyOn(service, 'updateStatus').and.returnValue(of(responseData))
    service.updateStatus(3).subscribe();
    httpTestingController.verify();
    expect(spy).toHaveBeenCalled();
  })
});
