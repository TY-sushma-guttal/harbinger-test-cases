import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { CourtsService } from '../services/courts.service';
@Component({
  selector: 'app-view-case-details',
  templateUrl: './view-case-details.component.html',
  styleUrls: ['./view-case-details.component.css']
})

export class ViewCaseDetailsComponent implements OnInit {
  displayedColumns: string[] = ['caseName', 'Date', 'Time', 'Pay'];
  violationCases: any = [];
  violationDetails: any;
  violationPay: any = []
  isCaseDetailsExists: boolean = false;
  searchBox!: FormGroup;
  payNowKeys: Array<string> = [];
  activeCases = new MatTableDataSource([]);
  closedCases = new MatTableDataSource([]);
  searchbyForm!: FormGroup;

  @ViewChild('closeModal') closePaymentModal!: ElementRef;
  tempActiveCases: any;
  tempColsedCases: any;
  properCaseDetails: any;

  constructor(private fb: FormBuilder, private courtService: CourtsService, private router: Router) { }

  ngOnInit(): void {
    this.searchBox = this.fb.group({
      vehicalNo: ['', [Validators.required, Validators.pattern('^[a-zA-Z0-9]*$')]]
    })
    this.searchbyForm = this.fb.group({
      searchBy: ['vehicleNo', [Validators.required]]
    })
  }

  showTable() {
    this.isCaseDetailsExists = true;
  }
  searchViolation(searchCases: any) {
    const vehicalNo = searchCases?.vehicalNo || undefined;
    const mobileNo = searchCases?.mobileNo || undefined;
    this.courtService.getCaseDetails(vehicalNo, mobileNo).subscribe({
      next: (caseDetailsResponce: any) => {
        if (!caseDetailsResponce?.error) {
          this.isCaseDetailsExists = true;
          this.processCaseResponce(caseDetailsResponce?.object);
        } else {
          console.error('Something went wrong Pls seach again');
          this.isCaseDetailsExists = false;
        }
      },
      error: (errMsg) => {
        console.error(errMsg);
      }
    });
  }

  processCaseResponce(caseDetailsResponce: any) {
    this.tempActiveCases = [];
    this.tempColsedCases = [];
    this.properCaseDetails = caseDetailsResponce.map((caseObject: any) => {
      let date = new Date(caseObject?.dateTime);
      return {
        ...caseObject,
        dateTime: date.toLocaleString(),
        newDateTime: `${new Date(caseObject?.dateTime).getDate()}/${new Date(caseObject?.dateTime).getMonth()}/${new Date(caseObject?.dateTime).getFullYear()}`
      }
    });
    this.properCaseDetails = this.removeDuplicates(this.properCaseDetails);
    this.properCaseDetails.forEach((caseObj: any) => {
      if (caseObj.status === 'Active') {
        this.tempActiveCases.push(caseObj);
      } else {
        this.tempColsedCases.push(caseObj);
      }
    });
    this.activeCases = new MatTableDataSource(this.tempActiveCases);
    this.closedCases = new MatTableDataSource(this.tempColsedCases);
  }

  removeDuplicates(array: any) {
    array.forEach((caseDetailsobj: any, index: number) => {
      let duplicates: Array<any> = array.filter((duplicateSearch: any) => duplicateSearch?.newDateTime === caseDetailsobj?.newDateTime && duplicateSearch?.caseId !== caseDetailsobj?.caseId && duplicateSearch?.caseName === caseDetailsobj?.caseName);
      if (duplicates.length > 0) {
        duplicates.forEach((caseToDelete: any) => {
          let indexToDelete = array.findIndex((curentObj: any) => curentObj.caseId === caseToDelete.caseId);
          array.splice(indexToDelete, 1);
        })
      }
    });
    return array;
  }



  updateControls(searchbyForm: any) {
    if (searchbyForm?.searchBy === "mobileNo") {
      this.searchBox.removeControl('vehicalNo');
      this.searchBox.addControl('mobileNo', this.fb.control('', [Validators.required, Validators.minLength(10), Validators.maxLength(10)]))
    } else {
      this.searchBox.removeControl('mobileNo');
      this.searchBox.addControl('vehicalNo', this.fb.control('', [Validators.required, Validators.pattern('^[a-zA-Z0-9]*$')]))
    }
  }
  payViolation(data: any) {
    this.violationDetails = data;
    this.payNowKeys = ['caseName', 'dateTime', 'fine'];
  }

  updateCaseStatus(caseId: any) {
    this.courtService.updateStatus(caseId?.caseId).subscribe({
      next: (updateStatus: any) => {
        this.closePaymentModal.nativeElement.click();
        this.searchViolation({ searchCases: updateStatus?.object?.vehicleNo });
      },
      error: (err: any) => {
        console.error(err);
      }
    });
  }

  logout() {
    this.router.navigate(['auth']);
  }

}


