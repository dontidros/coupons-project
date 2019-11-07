import { Component, OnInit } from '@angular/core';
import { AdminServiceService } from '../../admin-service.service';
import { Income } from '../../models/Income';

@Component({
  selector: 'app-get-company-expenses',
  templateUrl: './get-company-expenses.component.html',
  styleUrls: ['./get-company-expenses.component.css']
})
export class GetCompanyExpensesComponent {
  
  private adminService: AdminServiceService;
  public incomesByCompany: Income[];

  constructor(adminService: AdminServiceService) {
        this.adminService = adminService;
  }

  public getCompanyExpenses(): void{
      let observable = this.adminService.getCompanyExpenses();
      observable.subscribe(incomes => this.incomesByCompany = incomes);
  }





}
