import { Component, OnInit } from '@angular/core';
import { AdminServiceService } from '../../admin-service.service';
import { Income } from '../../models/Income';

@Component({
  selector: 'app-get-all-incomes-by-customers',
  templateUrl: './get-all-incomes-by-customers.component.html',
  styleUrls: ['./get-all-incomes-by-customers.component.css']
})
export class GetAllIncomesByCustomersComponent  {

    private adminService: AdminServiceService;
    public allIncomesByCustomers: Income[];
    constructor(adminService: AdminServiceService) { 
        this.adminService = adminService;
    }

    public getAllIncomesByCustomers(): void{
        let observable = this.adminService.getAllIncomesByCustomers();
        observable.subscribe(incomes => this.allIncomesByCustomers = incomes);
    }

}
