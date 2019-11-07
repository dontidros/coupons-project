import { Component, OnInit } from '@angular/core';
import { AdminServiceService } from '../../admin-service.service';
import { Company } from '../../models/Company';

@Component({
  selector: 'app-get-company-details',
  templateUrl: './get-company-details.component.html',
  styleUrls: ['./get-company-details.component.css']
})
export class GetCompanyDetailsComponent implements OnInit {

    private adminService: AdminServiceService;
    public oneCompany: Company;
    constructor(adminService: AdminServiceService) {
        this.adminService = adminService;
    }

    public getCompanyDetails(): void{
        
        let observable = this.adminService.getCompanyDetails();
        observable.subscribe(company => this.oneCompany = company);
    }

  ngOnInit() {
  }

}
