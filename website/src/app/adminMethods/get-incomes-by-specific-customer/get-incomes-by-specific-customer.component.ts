import { Component, OnInit } from '@angular/core';
import { AdminServiceService } from '../../admin-service.service';
import { Income } from '../../models/Income';
import { ViewChild, ElementRef } from '@angular/core';

@Component({
  selector: 'app-get-incomes-by-specific-customer',
  templateUrl: './get-incomes-by-specific-customer.component.html',
  styleUrls: ['./get-incomes-by-specific-customer.component.css']
})
export class GetIncomesBySpecificCustomerComponent  {
  
  private adminService: AdminServiceService;
  public incomesByCustomer: Income[];
    
  constructor(adminService: AdminServiceService) {
        this.adminService = adminService;
  }

  @ViewChild('idInput') nameInputRef: ElementRef;

  onSubmit() {
       
    this.getIncomesBySpecificCustomer(this.nameInputRef.nativeElement.value);
 }

 public getIncomesBySpecificCustomer(num: string): void{
     let observable = this.adminService.getIncomesBySpecificCustomer(num);
     observable.subscribe(incomes => this.incomesByCustomer = incomes);
 }

}
