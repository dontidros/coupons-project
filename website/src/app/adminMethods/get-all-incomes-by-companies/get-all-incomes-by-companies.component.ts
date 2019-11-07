import { Component, OnInit } from '@angular/core';
import { AdminServiceService } from '../../admin-service.service';
import { Income } from '../../models/Income';

@Component({
  selector: 'app-get-all-incomes-by-companies',
  templateUrl: './get-all-incomes-by-companies.component.html',
  styleUrls: ['./get-all-incomes-by-companies.component.css']
})
export class GetAllIncomesByCompaniesComponent  {
    private adminService: AdminServiceService;
    public allIncomesByCompanies: Income[];
    constructor(adminService: AdminServiceService) { 
        this.adminService = adminService;
    }
  
    public getAllIncomesByCompanies(): void{
        let observable = this.adminService.getAllIncomesByCompanies();
        observable.subscribe(incomes => this.allIncomesByCompanies = incomes);
    }
}
