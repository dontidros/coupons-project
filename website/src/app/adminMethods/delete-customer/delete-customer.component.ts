import { Component, OnInit } from '@angular/core';
import { AdminServiceService } from '../../admin-service.service';
import { GeneralMessage } from '../../models/GeneralMessage';
import { ViewChild, ElementRef } from '@angular/core';

@Component({
  selector: 'app-delete-customer',
  templateUrl: './delete-customer.component.html',
  styleUrls: ['./delete-customer.component.css']
})
export class DeleteCustomerComponent implements OnInit {

    private adminService: AdminServiceService;
    public deleteSucceededMessage: GeneralMessage;
    constructor(adminService: AdminServiceService) {
        this.adminService = adminService;
    }

    @ViewChild('idInput') idInputRef: ElementRef;
     


    onSubmit() {

       if (confirm('Are you sure you want to delete this customer?')) {
           console.log(this.idInputRef.nativeElement.value);
           this.deleteCustomer(this.idInputRef.nativeElement.value);
       } 
       else {
           return;
       }

 
     }

    public deleteCustomer(num: string): void {
        let observable = this.adminService.deleteCustomer(num);
        observable.subscribe(deleteSucceededMessage => this.deleteSucceededMessage = deleteSucceededMessage);
    }

    ngOnInit() {
    }

}
