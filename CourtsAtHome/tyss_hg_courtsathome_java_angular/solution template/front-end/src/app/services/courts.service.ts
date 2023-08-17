import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CourtsService {

  constructor(private http:HttpClient) { }

  getCaseDetails(vehicleNo:string,mobileNo:number){
    return this.http.get(`${environment.baseUrl}api/v1/getCaseDetails?vehicleNo=${vehicleNo}&mobileNo=${mobileNo}`);
  }

  updateStatus(caseId:number){
    return this.http.put(`${environment.baseUrl}api/v1/updateCaseStatus`,{caseId:caseId});
  }

  loginUser(loginCreds:any){
    let vehicalNumber:string |null = loginCreds?.vehicle_phone_no || null;
    let mobileNumber:string | null = null;
    let passwordd:string | null = loginCreds?.password || null;
    return this.http.get(`${environment.baseUrl}api/v1/login?vehcileNo=${vehicalNumber}&mobileNo=${mobileNumber}&password=${passwordd}`)
  }

  registeruser(payload:any){
    return this.http.post(`${environment.baseUrl}api/v1/register`,payload);
  }

}
