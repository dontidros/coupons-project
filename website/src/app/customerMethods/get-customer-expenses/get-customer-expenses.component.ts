import { Component, OnInit } from '@angular/core';
import { AdminServiceService } from '../../admin-service.service';
import { Income } from '../../models/Income';

@Component({
  selector: 'app-get-customer-expenses',
  templateUrl: './get-customer-expenses.component.html',
  styleUrls: ['./get-customer-expenses.component.css']
})
export class GetCustomerExpensesComponent  {

  private adminService: AdminServiceService;
  public incomesByCustomer: Income[];

  constructor(adminService: AdminServiceService) {
        this.adminService = adminService;
  }

  public getCustomerExpenses(): void{
      let observable = this.adminService.getCustomerExpenses();
      observable.subscribe(incomes => this.incomesByCustomer = incomes);
  }



}
