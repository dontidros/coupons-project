import { Component, OnInit } from '@angular/core';
import { Customer } from '../../models/Customer';
import { AdminServiceService } from '../../admin-service.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ViewChild, ElementRef } from '@angular/core';

@Component({
  selector: 'app-add-customer',
  templateUrl: './add-customer.component.html',
  styleUrls: ['./add-customer.component.css']
})
export class AddCustomerComponent implements OnInit {
  private adminService: AdminServiceService;
  public customer: Customer;  
  constructor(adminService: AdminServiceService) {
        this.adminService = adminService;
   }

@ViewChild('firstNameInput') firstNameInputRef: ElementRef;
@ViewChild('lastNameInput') lastNameInputRef: ElementRef;
@ViewChild('emailInput') emailInputRef: ElementRef;
@ViewChild('passwordInput') passwordInputRef: ElementRef;

form = new FormGroup ({
    firstName: new FormControl("", Validators.compose([
        Validators.required,
        Validators.minLength(2)
    ])),
    lastName: new FormControl("", Validators.compose([
        Validators.required,
        Validators.minLength(2)
    ])),
    email: new FormControl("", Validators.compose([
        Validators.required,
        Validators.email
    ])),
    password: new FormControl("", Validators.compose([
        Validators.required,
        Validators.minLength(5)
    ]))
});

onSubmit(frm){
   this.customer = new Customer(0,this.firstNameInputRef.nativeElement.value,
                                  this.lastNameInputRef.nativeElement.value,
                                  this.emailInputRef.nativeElement.value,
                                  this.passwordInputRef.nativeElement.value, null  );

  console.log(this.customer.firstName);
  let observable = this.adminService.addCustomer(this.customer);
  observable.subscribe(customer => this.customer = customer); 

  

}

  ngOnInit() {
  }

}
