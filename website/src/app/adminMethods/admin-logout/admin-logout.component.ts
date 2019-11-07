import { Component, OnInit } from '@angular/core';
import { AdminServiceService } from '../../admin-service.service';
import { GeneralMessage } from '../../models/GeneralMessage';

@Component({
  selector: 'app-admin-logout',
  templateUrl: './admin-logout.component.html',
  styleUrls: ['./admin-logout.component.css']
})
export class AdminLogoutComponent implements OnInit {
    private adminService: AdminServiceService;
    public logoutMessage: GeneralMessage;

    constructor(adminService: AdminServiceService) {
        this.adminService = adminService;
    }

    onSubmit(){
        if (confirm('Are you sure you want to logout?')) {
            console.log('logout');
            this.adminLogout();
        }
        else {
            return;
        } 
    }

    public adminLogout(): void{
        let observable = this.adminService.adminLogout();
        observable.subscribe(logoutMessage => this.logoutMessage = logoutMessage);
    }

    ngOnInit() {
    } 

}
