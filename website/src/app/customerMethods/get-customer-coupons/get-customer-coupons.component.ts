import { Component, OnInit } from '@angular/core';
import { Coupon } from '../../models/Coupon';
import { AdminServiceService } from '../../admin-service.service';

@Component({
  selector: 'app-get-customer-coupons',
  templateUrl: './get-customer-coupons.component.html',
  styleUrls: ['./get-customer-coupons.component.css']
})
export class GetCustomerCouponsComponent implements OnInit {

    private adminService: AdminServiceService;
    public customerCoupons: Coupon[];

  constructor(adminService: AdminServiceService) {
      this.adminService = adminService;
  }


    public getCustomerCoupons(): void{
        
        let observable = this.adminService.getCustomerCoupons();
        observable.subscribe(coupons => this.customerCoupons = coupons);
    }

  ngOnInit() {
  }

}
