import { Component, OnInit } from '@angular/core';
import { AdminServiceService } from '../../admin-service.service';
import { Company } from '../../models/Company';




@Component({
  selector: 'app-get-all-companies',
  templateUrl: './get-all-companies.component.html',
  styleUrls: ['./get-all-companies.component.css']
})
export class GetAllCompaniesComponent{
    private adminService: AdminServiceService;
    public allCompanies: Company[];

  constructor(adminService: AdminServiceService) {
      this.adminService = adminService;
   }

   public getAllCompanies(): void{
    let observable = this.adminService.getAllCompanies();
    observable.subscribe(companies => this.allCompanies = companies);
}

}
