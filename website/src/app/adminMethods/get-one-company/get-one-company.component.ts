import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators, NgModel } from '@angular/forms';
import { AdminServiceService } from '../../admin-service.service';
import { Company } from '../../models/Company';
import { ViewChild, ElementRef } from '@angular/core';

@Component({
  selector: 'app-get-one-company',
  templateUrl: './get-one-company.component.html',
  styleUrls: ['./get-one-company.component.css']
})
export class GetOneCompanyComponent implements OnInit {
    private adminService: AdminServiceService;
    public oneCompany: Company;
    public secondCompany: Company;
    constructor(adminService: AdminServiceService) {
        this.adminService = adminService;
    }
        
     
    companyId = {
          id: ""
    }
    
    @ViewChild('idInput') nameInputRef: ElementRef;
     


    
    
    onSubmit() {
       

        console.log(this.nameInputRef.nativeElement.value);
        this.getOneCompany(this.nameInputRef.nativeElement.value);
        
    }

 
    
    public getOneCompany(num: string): void{
        
        let observable = this.adminService.getOneCompany(num);
        observable.subscribe(company => {
            this.oneCompany = company;
            console.log(this.oneCompany.id);
            this.secondCompany = new Company (this.oneCompany.id, this.oneCompany.name, this.oneCompany.email, this.oneCompany.password, this.oneCompany.coupons);
            console.log(this.secondCompany.password);
        });
    }






  

  ngOnInit() {
  }

}
