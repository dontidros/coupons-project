import { Component, OnInit } from '@angular/core';
import { AdminServiceService } from '../../admin-service.service';
import { Coupon } from '../../models/Coupon';
import { ViewChild, ElementRef } from '@angular/core';


@Component({
  selector: 'app-purchase-coupon',
  templateUrl: './purchase-coupon.component.html',
  styleUrls: ['./purchase-coupon.component.css']
})

export class PurchaseCouponComponent implements OnInit {

    private adminService: AdminServiceService;
    public couponPurchased: Coupon;
    public coupon: Coupon;
    public allCoupons: Coupon[];

    constructor(adminService: AdminServiceService) { 
        this.adminService = adminService;
    }

   @ViewChild('idInput') idInputRef: ElementRef;



    onSubmit() {
       console.log(this.idInputRef.nativeElement.value);

        
       let observable = this.adminService.purchaseCoupon(this.idInputRef.nativeElement.value);
       observable.subscribe(couponPurchased => this.couponPurchased = couponPurchased);


    }


     
    public getOneCoupon(num: string): void{
       
       let observable = this.adminService.getOneCoupon(num);
       observable.subscribe(couponPurchased => this.couponPurchased = couponPurchased);
    }


    ngOnInit() {
        let observable = this.adminService.getAllCoupons();
        observable.subscribe(allCoupons => this.allCoupons = allCoupons);
    }

}
