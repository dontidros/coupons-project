import { Component, OnInit } from '@angular/core';
import { AdminServiceService } from '../../admin-service.service';
import { Coupon } from '../../models/Coupon';
import { ViewChild, ElementRef } from '@angular/core';

@Component({
  selector: 'app-get-company-coupons-from-specific-category',
  templateUrl: './get-company-coupons-from-specific-category.component.html',
  styleUrls: ['./get-company-coupons-from-specific-category.component.css']
})
export class GetCompanyCouponsFromSpecificCategoryComponent implements OnInit {

  private adminService: AdminServiceService;
  public couponsFromSpecificCategory: Coupon[];  
  constructor(adminService: AdminServiceService) {
        this.adminService = adminService;
   }

   @ViewChild('categoryInput') nameInputRef: ElementRef;

   onSubmit(){
        this.getCompanyCouponsFromSpecificCategory(this.nameInputRef.nativeElement.value);
   }

   public getCompanyCouponsFromSpecificCategory(category: string): void {
       let observable = this.adminService.getCompanyCouponsFromSpecificCategory(category);
       observable.subscribe(coupons=> this.couponsFromSpecificCategory=coupons);
   }

  ngOnInit() {
  }

}
