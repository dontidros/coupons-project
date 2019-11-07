import { Component, OnInit } from '@angular/core';
import { AdminServiceService } from '../../admin-service.service';
import { Customer } from '../../models/Customer';
import { ViewChild, ElementRef } from '@angular/core';

@Component({
  selector: 'app-get-one-customer',
  templateUrl: './get-one-customer.component.html',
  styleUrls: ['./get-one-customer.component.css']
})
export class GetOneCustomerComponent implements OnInit {
    private adminService: AdminServiceService;
    public oneCustomer: Customer;
    constructor(adminService: AdminServiceService) {
        this.adminService = adminService;

        
     }

     @ViewChild('idInput') nameInputRef: ElementRef;

     onSubmit() {
        console.log(this.nameInputRef.nativeElement.value);
        this.getOneCustomer(this.nameInputRef.nativeElement.value);
     }
      
     public getOneCustomer(num: string): void{
        
        let observable = this.adminService.getOneCustomer(num);
        observable.subscribe(customer => this.oneCustomer = customer);
    }

    ngOnInit() {
    }

}

