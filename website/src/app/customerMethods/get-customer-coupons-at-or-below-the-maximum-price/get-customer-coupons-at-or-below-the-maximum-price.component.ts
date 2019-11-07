import { Component, OnInit } from '@angular/core';
import { AdminServiceService } from '../../admin-service.service';
import { Coupon } from '../../models/Coupon';
import { ViewChild, ElementRef } from '@angular/core';

@Component({
  selector: 'app-get-customer-coupons-at-or-below-the-maximum-price',
  templateUrl: './get-customer-coupons-at-or-below-the-maximum-price.component.html',
  styleUrls: ['./get-customer-coupons-at-or-below-the-maximum-price.component.css']
})
export class GetCustomerCouponsAtOrBelowTheMaximumPriceComponent implements OnInit {

  private adminService: AdminServiceService;
  public couponsAtOrBelowTheMaximumPrice: Coupon[];  
  constructor(adminService: AdminServiceService) {
        this.adminService = adminService;
   }
@ViewChild('maxInput') nameInputRef: ElementRef;

onSubmit(){
    this.getCustomerCouponsAtOrBelowTheMaximumPrice(this.nameInputRef.nativeElement.value);
}

public getCustomerCouponsAtOrBelowTheMaximumPrice(max: string): void{
    let observable = this.adminService.getCustomerCouponsAtOrBelowTheMaximumPrice(max);
    observable.subscribe(coupons=> this.couponsAtOrBelowTheMaximumPrice=coupons);
}

  ngOnInit() {
  }

}
