import { HttpClientModule } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterTestingModule } from '@angular/router/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';


import { ViewCaseDetailsComponent } from './view-case-details.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatTableModule } from '@angular/material/table';
import { MatTabsModule } from '@angular/material/tabs';
import { MatRadioModule } from '@angular/material/radio';
import { CourtsService } from '../services/courts.service';
import { of } from 'rxjs';

describe('ViewCaseDetailsComponent', () => {
  let component: ViewCaseDetailsComponent;
  let fixture: ComponentFixture<ViewCaseDetailsComponent>;
  let service: CourtsService;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ViewCaseDetailsComponent],
      imports: [
        HttpClientModule,
        ReactiveFormsModule,
        RouterTestingModule,
        HttpClientTestingModule,
        FormsModule,
        BrowserAnimationsModule,
        MatTableModule,
        MatTabsModule,
        MatRadioModule
      ],
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewCaseDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
    service = TestBed.inject(CourtsService)
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('On mobileNo Selection mobileNo control should be present in the searchBox form', () => {
    let payload;
    payload = { "searchBy": "mobileNo" }
    component.updateControls(payload);
    let mobileControl = component.searchBox.controls['mobileNo'];
    expect(mobileControl.valid).toBeFalsy
  });

  it('updateControls method should update searchBox Form based on vehicle or Mobile Selection', () => {
    let payload;
    payload = { "searchBy": "vehicalNo" }
    component.updateControls(payload);
    let vehicleNoControl = component.searchBox.controls['vehicalNo'];
    expect(vehicleNoControl.valid).toBeFalsy();
  });

  it('Process CaseDetails Responce remove Duplicates', () => {
    let caseDetails = [
      {
        "caseId": 2,
        "caseName": "Drunk & Drive",
        "dateTime": "10/11/2022, 12:12:12 PM",
        "status": "Active",
        "fine": 1500,
        "vehicleNo": "KA-19-AG-5465",
        "mobileNo": "9876543211",
        "newDateTime": "11/9/2022"
      },
      {
        "caseId": 6,
        "caseName": "Not Wearing Helmet",
        "dateTime": "6/7/2022, 11:12:12 AM",
        "status": "Closed",
        "fine": 2000,
        "vehicleNo": "KA-19-AG-5465",
        "mobileNo": "9876543211",
        "newDateTime": "7/5/2022"
      },
      {
        "caseId": 7,
        "caseName": "Not Wearing Helmet",
        "dateTime": "6/7/2022, 10:12:12 AM",
        "status": "Active",
        "fine": 2000,
        "vehicleNo": "KA-19-AG-5465",
        "mobileNo": "9876543211",
        "newDateTime": "7/5/2022"
      },
      {
        "caseId": 8,
        "caseName": "Not Wearing Helmet",
        "dateTime": "6/7/2022, 12:12:12 PM",
        "status": "Active",
        "fine": 2000,
        "vehicleNo": "KA-19-AG-5465",
        "mobileNo": "9876543211",
        "newDateTime": "7/5/2022"
      },
      {
        "caseId": 9,
        "caseName": "Signal Voilation",
        "dateTime": "6/7/2022, 12:12:12 PM",
        "status": "Active",
        "fine": 1500,
        "vehicleNo": "KA-19-AG-5465",
        "mobileNo": "9876543211",
        "newDateTime": "7/5/2022"
      }
    ];

    let expectedClosedCaseResult = [
      {
        "caseId": 6,
        "caseName": "Not Wearing Helmet",
        "dateTime": "07/06/2022, 11:12:12",
        "status": "Closed",
        "fine": 2000,
        "vehicleNo": "KA-19-AG-5465",
        "mobileNo": "9876543211",
        "newDateTime": "7/5/2022"
      }
    ];
    let expectedActiveCaseResult = [
      {
        "caseId": 2,
        "caseName": "Drunk & Drive",
        "dateTime": "11/10/2022, 12:12:12",
        "status": "Active",
        "fine": 1500,
        "vehicleNo": "KA-19-AG-5465",
        "mobileNo": "9876543211",
        "newDateTime": "11/9/2022"
      },
      {
        "caseId": 9,
        "caseName": "Signal Voilation",
        "dateTime": "07/06/2022, 12:12:12",
        "status": "Active",
        "fine": 1500,
        "vehicleNo": "KA-19-AG-5465",
        "mobileNo": "9876543211",
        "newDateTime": "7/5/2022"
      }
    ];
    component.processCaseResponce(caseDetails);
    console.log(component.activeCases.data);
    expect(component.tempActiveCases).toEqual(expectedActiveCaseResult);
    expect(component.tempColsedCases).toEqual(expectedClosedCaseResult);
  });

  it('Should call updateCaseStatus method', () => {
    let eventServiceMock = spyOn(component, 'updateCaseStatus').and.callThrough()
    component.updateCaseStatus(1);
    fixture.detectChanges();
    expect(eventServiceMock).toHaveBeenCalled()
  })

  it('should call updateCaseStatus to subscribe update Case Staus using services', () => {
    let expectedResult = {
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


    let eventServiceMock = spyOn(service, 'updateStatus').and.returnValue(of(expectedResult))
    component.updateCaseStatus(2)
    fixture.detectChanges()
    expect(eventServiceMock).toHaveBeenCalled()
  })

  it('Remove duplicate should work as expected', () => {
    let valueToPassToFunction = [
      {
        "caseId": 2,
        "caseName": "Drunk & Drive",
        "dateTime": "10/11/2022, 12:12:12 PM",
        "status": "Active",
        "fine": 1500,
        "vehicleNo": "KA-19-AG-5465",
        "mobileNo": "9876543211",
        "newDateTime": "11/9/2022"
      },
      {
        "caseId": 6,
        "caseName": "Not Wearing Helmet",
        "dateTime": "6/7/2022, 11:12:12 AM",
        "status": "Active",
        "fine": 2000,
        "vehicleNo": "KA-19-AG-5465",
        "mobileNo": "9876543211",
        "newDateTime": "7/5/2022"
      },
      {
        "caseId": 7,
        "caseName": "Not Wearing Helmet",
        "dateTime": "6/7/2022, 10:12:12 AM",
        "status": "Active",
        "fine": 2000,
        "vehicleNo": "KA-19-AG-5465",
        "mobileNo": "9876543211",
        "newDateTime": "7/5/2022"
      },
      {
        "caseId": 8,
        "caseName": "Not Wearing Helmet",
        "dateTime": "6/7/2022, 12:12:12 PM",
        "status": "Active",
        "fine": 2000,
        "vehicleNo": "KA-19-AG-5465",
        "mobileNo": "9876543211",
        "newDateTime": "7/5/2022"
      },
      {
        "caseId": 9,
        "caseName": "Signal Voilation",
        "dateTime": "6/7/2022, 12:12:12 PM",
        "status": "Active",
        "fine": 1500,
        "vehicleNo": "KA-19-AG-5465",
        "mobileNo": "9876543211",
        "newDateTime": "7/5/2022"
      }
    ];

    let expectedResult = [
      {
        "caseId": 2,
        "caseName": "Drunk & Drive",
        "dateTime": "10/11/2022, 12:12:12 PM",
        "status": "Active",
        "fine": 1500,
        "vehicleNo": "KA-19-AG-5465",
        "mobileNo": "9876543211",
        "newDateTime": "11/9/2022"
      },
      {
        "caseId": 6,
        "caseName": "Not Wearing Helmet",
        "dateTime": "6/7/2022, 11:12:12 AM",
        "status": "Active",
        "fine": 2000,
        "vehicleNo": "KA-19-AG-5465",
        "mobileNo": "9876543211",
        "newDateTime": "7/5/2022"
      },
      {
        "caseId": 9,
        "caseName": "Signal Voilation",
        "dateTime": "6/7/2022, 12:12:12 PM",
        "status": "Active",
        "fine": 1500,
        "vehicleNo": "KA-19-AG-5465",
        "mobileNo": "9876543211",
        "newDateTime": "7/5/2022"
      }
    ]

    let result = component.removeDuplicates(valueToPassToFunction);
    fixture.detectChanges();
    expect(result).toEqual(expectedResult);
  });

  it('Showtable should work Properly', () => {
    component.isCaseDetailsExists = false;
    component.showTable();
    expect(component.isCaseDetailsExists).toBeTruthy();
  });

  it('searchViolation should call getCaseDetails API from Service', () => {
    let eventServiceMock = spyOn(service, 'getCaseDetails').and.callThrough();
    component.searchViolation({});
    fixture.detectChanges();
    expect(eventServiceMock).toHaveBeenCalled();
  });

});
