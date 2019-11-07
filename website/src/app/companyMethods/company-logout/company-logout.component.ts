import { Component, OnInit } from '@angular/core';
import { AdminServiceService } from '../../admin-service.service';
import { GeneralMessage } from '../../models/GeneralMessage';

@Component({
  selector: 'app-company-logout',
  templateUrl: './company-logout.component.html',
  styleUrls: ['./company-logout.component.css']
})
export class CompanyLogoutComponent implements OnInit {

    private adminService: AdminServiceService;
    public logoutMessage: GeneralMessage;

    constructor(adminService: AdminServiceService) {
        this.adminService = adminService;
    }

    onSubmit(){
        if (confirm('Are you sure you want to logout?')) {
            console.log('logout');
            this.companyLogout();
        } else {
            return;
        } 
    }

    public companyLogout(): void{
        let observable = this.adminService.companyLogout();
        observable.subscribe(logoutMessage => this.logoutMessage = logoutMessage);
    }

  ngOnInit() {
  }

}
