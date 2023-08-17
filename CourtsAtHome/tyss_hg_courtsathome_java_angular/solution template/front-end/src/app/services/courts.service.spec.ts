import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';
import { of } from 'rxjs';

import { CourtsService } from './courts.service';

describe('CourtsService', () => {
  let service: CourtsService;
  let httpTestingController: HttpTestingController;
  let responseData = {
    "error": false,
    "msg": "Status Updated to Close  Successfully after Successfull Payment",
    "object": {} || []
  };

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
    let spy = spyOn(service, 'updateStatus').and.returnValue(of(responseData))
    service.updateStatus(3).subscribe();
    httpTestingController.verify();
    expect(spy).toHaveBeenCalled();
  })
  it('Should call getCaseDetails API', () => {
    let spy = spyOn(service, 'getCaseDetails').and.returnValue(of(responseData))
    service.getCaseDetails('',0).subscribe();
    httpTestingController.verify();
    expect(spy).toHaveBeenCalled();
  })
  it('Should call loginUser API', () => {
    let spy = spyOn(service, 'loginUser').and.returnValue(of(responseData))
    service.loginUser({}).subscribe();
    httpTestingController.verify();
    expect(spy).toHaveBeenCalled();
  })
  it('Should call registeruser API', () => {
    let spy = spyOn(service, 'registeruser').and.returnValue(of(responseData))
    service.registeruser({}).subscribe();
    httpTestingController.verify();
    expect(spy).toHaveBeenCalled();
  })
});
