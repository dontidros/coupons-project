import { Component, OnInit } from '@angular/core';
import { AdminServiceService } from '../admin-service.service';
import { LoginFields } from '../models/LoginFields';
import { LoginMessage } from '../models/loginMessage';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ViewChild, ElementRef } from '@angular/core';

@Component({
  selector: 'app-company-facade',
  templateUrl: './company-facade.component.html',
  styleUrls: ['./company-facade.component.css']
})
export class CompanyFacadeComponent implements OnInit {

    private adminService: AdminServiceService;
    
    public loginFields: LoginFields;
    public loginMessage: LoginMessage;

    constructor(adminService: AdminServiceService) {
        this.adminService = adminService;
    }

    @ViewChild('emailInput') emailInputRef: ElementRef;
    @ViewChild('passwordInput') passwordInputRef: ElementRef;

    form = new FormGroup ({

        email: new FormControl("", Validators.compose([
            Validators.required,
            Validators.email
        ])),
        password: new FormControl("", Validators.compose([
            Validators.required,
            Validators.minLength(5)
        ]))
    });


    public onSubmit(frm){
     this.loginFields = new LoginFields(this.emailInputRef.nativeElement.value,
                                        this.passwordInputRef.nativeElement.value);

        let observable = this.adminService.companyLogin(this.loginFields);
        observable.subscribe(loginMessage => this.loginMessage = loginMessage);                                     
    }

  ngOnInit() {
  }

}
