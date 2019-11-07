import { Component, OnInit } from '@angular/core';
import { AdminServiceService } from '../../admin-service.service';
import { Coupon } from '../../models/Coupon';

@Component({
  selector: 'app-get-company-coupons',
  templateUrl: './get-company-coupons.component.html',
  styleUrls: ['./get-company-coupons.component.css']
})
export class GetCompanyCouponsComponent implements OnInit {

    private adminService: AdminServiceService;
    public companyCoupons: Coupon[];

  constructor(adminService: AdminServiceService) {
      this.adminService = adminService;
  }


    public getCompanyCoupons(): void{
        
        let observable = this.adminService.getCompanyCoupons();
        observable.subscribe(coupons => this.companyCoupons = coupons);
    }


  ngOnInit() {
  }

}
