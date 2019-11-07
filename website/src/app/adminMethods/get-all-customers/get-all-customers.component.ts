import { Component, OnInit } from '@angular/core';
import { Customer } from '../../models/Customer';
import { AdminServiceService } from '../../admin-service.service';

@Component({
  selector: 'app-get-all-customers',
  templateUrl: './get-all-customers.component.html',
  styleUrls: ['./get-all-customers.component.css']
})
export class GetAllCustomersComponent {
    private adminService: AdminServiceService;
    public allCustomers: Customer[];
    
    constructor(adminService: AdminServiceService) {
        this.adminService = adminService;
     }

     public getAllCustomers(): void{
        let observable = this.adminService.getAllCustomers();
        observable.subscribe(customers => this.allCustomers = customers);
    }




}
