import { Component, OnInit } from '@angular/core';
import { AdminServiceService } from '../../admin-service.service';
import { Coupon } from '../../models/Coupon';
import { ViewChild, ElementRef } from '@angular/core';

@Component({
  selector: 'app-get-customer-coupons-from-specific-category',
  templateUrl: './get-customer-coupons-from-specific-category.component.html',
  styleUrls: ['./get-customer-coupons-from-specific-category.component.css']
})
export class GetCustomerCouponsFromSpecificCategoryComponent implements OnInit {

  private adminService: AdminServiceService;
  public couponsFromSpecificCategory: Coupon[];  
  constructor(adminService: AdminServiceService) {
        this.adminService = adminService;
   }

   @ViewChild('categoryInput') nameInputRef: ElementRef;

   onSubmit(){
        this.getCustomerCouponsFromSpecificCategory(this.nameInputRef.nativeElement.value);
   }

   public getCustomerCouponsFromSpecificCategory(category: string): void {
       let observable = this.adminService.getCustomerCouponsFromSpecificCategory(category);
       observable.subscribe(coupons=> this.couponsFromSpecificCategory=coupons);
   }

  ngOnInit() {
  }

}
