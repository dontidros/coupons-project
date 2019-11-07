import { Component, OnInit } from '@angular/core';
import { AdminServiceService } from '../../admin-service.service';
import { GeneralMessage } from '../../models/GeneralMessage';
import { ViewChild, ElementRef } from '@angular/core';


@Component({
  selector: 'app-delete-company',
  templateUrl: './delete-company.component.html',
  styleUrls: ['./delete-company.component.css']
})
export class DeleteCompanyComponent implements OnInit {
    private adminService: AdminServiceService;
    public deleteSucceededMessage: GeneralMessage;
    constructor(adminService: AdminServiceService) {
        this.adminService = adminService;
     }

     @ViewChild('idInput') idInputRef: ElementRef;
     


     onSubmit() {

        if (confirm('Are you sure you want to delete this company?')) {
            console.log(this.idInputRef.nativeElement.value);
            this.deleteCompany(this.idInputRef.nativeElement.value);
        }
        else {
            return;
        }

  
      }

     public deleteCompany(num: string): void {
         let observable = this.adminService.deleteCompany(num);
         observable.subscribe(deleteSucceededMessage => this.deleteSucceededMessage = deleteSucceededMessage);
     }
     
     

   

    ngOnInit() {
    }

}
