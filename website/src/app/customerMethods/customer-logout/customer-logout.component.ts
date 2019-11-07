import { Component, OnInit } from '@angular/core';
import { AdminServiceService } from '../../admin-service.service';
import { GeneralMessage } from '../../models/GeneralMessage';

@Component({
  selector: 'app-customer-logout',
  templateUrl: './customer-logout.component.html',
  styleUrls: ['./customer-logout.component.css']
})
export class CustomerLogoutComponent implements OnInit {

    private adminService: AdminServiceService;
    public logoutMessage: GeneralMessage;

    constructor(adminService: AdminServiceService) {
        this.adminService = adminService;
    }

    onSubmit(){
        if (confirm('Are you sure you want to logout?')) {
            console.log('logout');
            this.customerLogout();
        } else {
            return;
        } 
    }

    public customerLogout(): void{
        let observable = this.adminService.customerLogout();
        observable.subscribe(logoutMessage => this.logoutMessage = logoutMessage);
    }

  ngOnInit() {
  }

}
