import { Component, OnInit } from '@angular/core';
import { AdminServiceService } from '../../admin-service.service';
import { Income } from '../../models/Income';

@Component({
  selector: 'app-get-all-incomes',
  templateUrl: './get-all-incomes.component.html',
  styleUrls: ['./get-all-incomes.component.css']
})
export class GetAllIncomesComponent  {
    private adminService: AdminServiceService;
    public allIncomes: Income[];
  constructor(adminService: AdminServiceService) { 
        this.adminService = adminService;
  }

  public getAllIncomes(): void{
      let observable = this.adminService.getAllIncomes();
      observable.subscribe(incomes => this.allIncomes = incomes);
  }

  

}
