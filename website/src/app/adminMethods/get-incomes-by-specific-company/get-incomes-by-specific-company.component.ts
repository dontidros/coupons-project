import { Component, OnInit } from '@angular/core';
import { AdminServiceService } from '../../admin-service.service';
import { Income } from '../../models/Income';
import { ViewChild, ElementRef } from '@angular/core';

@Component({
  selector: 'app-get-incomes-by-specific-company',
  templateUrl: './get-incomes-by-specific-company.component.html',
  styleUrls: ['./get-incomes-by-specific-company.component.css']
})
export class GetIncomesBySpecificCompanyComponent  {
  private adminService: AdminServiceService;
  public incomesByCompany: Income[];  
  constructor(adminService: AdminServiceService) {
        this.adminService = adminService;
  }

  @ViewChild('idInput') nameInputRef: ElementRef;

    onSubmit() {
       
       this.getIncomesBySpecificCompany(this.nameInputRef.nativeElement.value);
    }

    public getIncomesBySpecificCompany(num: string): void{
        let observable = this.adminService.getIncomesBySpecificCompany(num);
        observable.subscribe(incomes => this.incomesByCompany = incomes);
    }

 

}
