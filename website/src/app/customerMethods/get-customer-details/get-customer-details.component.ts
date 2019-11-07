import { Component, OnInit } from '@angular/core';
import { AdminServiceService } from '../../admin-service.service';
import { Customer } from '../../models/Customer';

@Component({
  selector: 'app-get-customer-details',
  templateUrl: './get-customer-details.component.html',
  styleUrls: ['./get-customer-details.component.css']
})
export class GetCustomerDetailsComponent implements OnInit {
    private adminService: AdminServiceService;
    public oneCustomer: Customer;
    constructor(adminService: AdminServiceService) {
        this.adminService = adminService;
    }

    public getCustomerDetails(): void{
        
        let observable = this.adminService.getCustomerDetails();
        observable.subscribe(customer => this.oneCustomer = customer);
    }


  ngOnInit() {
  }

}
