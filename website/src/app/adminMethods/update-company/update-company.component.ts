import { Component, OnInit } from '@angular/core';
import { AdminServiceService } from '../../admin-service.service';
import { Company } from '../../models/Company';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ViewChild, ElementRef } from '@angular/core';

@Component({
  selector: 'app-update-company',
  templateUrl: './update-company.component.html',
  styleUrls: ['./update-company.component.css']
})
export class UpdateCompanyComponent implements OnInit {

    private adminService: AdminServiceService;
    public company: Company;  
    constructor(adminService: AdminServiceService) {
        this.adminService = adminService;
    }

    @ViewChild('idInput') idInputRef: ElementRef;
    @ViewChild('nameInput') nameInputRef: ElementRef;
    @ViewChild('emailInput') emailInputRef: ElementRef;
    @ViewChild('passwordInput') passwordInputRef: ElementRef;

    form = new FormGroup ({
        id: new FormControl("", Validators.compose([
            Validators.required
        ])),
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
        this.company = new Company(this.idInputRef.nativeElement.value,
                                   this.nameInputRef.nativeElement.value, 
                                   this.emailInputRef.nativeElement.value,
                                   this.passwordInputRef.nativeElement.value, null  );
        
        console.log(this.company.name);
        let observable = this.adminService.updateCompany(this.company);
        observable.subscribe(company => this.company = company); 
    
        
    
      }



  ngOnInit() {
  }

}
