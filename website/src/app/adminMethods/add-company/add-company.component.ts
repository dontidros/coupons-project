import { Component, OnInit } from '@angular/core';
import { AdminServiceService } from '../../admin-service.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Company } from '../../models/Company';
import { ViewChild, ElementRef } from '@angular/core';


@Component({
  selector: 'app-add-company',
  templateUrl: './add-company.component.html',
  styleUrls: ['./add-company.component.css']
})
export class AddCompanyComponent {
  private adminService: AdminServiceService;
  public company: Company;
  
  constructor(adminService: AdminServiceService) {
    this.adminService = adminService;
  }


@ViewChild('nameInput') nameInputRef: ElementRef;
@ViewChild('emailInput') emailInputRef: ElementRef;
@ViewChild('passwordInput') passwordInputRef: ElementRef;


  form = new FormGroup ({
      name: new FormControl("", Validators.compose([
          Validators.required
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
    this.company = new Company(0,this.nameInputRef.nativeElement.value, this.emailInputRef.nativeElement.value, this.passwordInputRef.nativeElement.value, null  );
    
    console.log(this.company.name);
    let observable = this.adminService.addCompany(this.company);
    observable.subscribe(company => this.company = company); 

    

  }


  


    
 



}
