import { Component, OnInit } from '@angular/core';
import { Coupon } from '../../models/Coupon';
import { AdminServiceService } from '../../admin-service.service';
import { ViewChild, ElementRef } from '@angular/core';

@Component({
  selector: 'app-get-company-coupons-at-or-below-the-maximum-price',
  templateUrl: './get-company-coupons-at-or-below-the-maximum-price.component.html',
  styleUrls: ['./get-company-coupons-at-or-below-the-maximum-price.component.css']
})
export class GetCompanyCouponsAtOrBelowTheMaximumPriceComponent implements OnInit {

  private adminService: AdminServiceService;
  public couponsAtOrBelowTheMaximumPrice: Coupon[];  
  constructor(adminService: AdminServiceService) {
        this.adminService = adminService;
   }
@ViewChild('maxInput') nameInputRef: ElementRef;

onSubmit(){
    this.getCompanyCouponsAtOrBelowTheMaximumPrice(this.nameInputRef.nativeElement.value);
}

public getCompanyCouponsAtOrBelowTheMaximumPrice(max: string): void{
    let observable = this.adminService.getCompanyCouponsAtOrBelowTheMaximumPrice(max);
    observable.subscribe(coupons=> this.couponsAtOrBelowTheMaximumPrice=coupons);
}

  ngOnInit() {
  }

}
