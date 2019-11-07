import { Component, OnInit } from '@angular/core';
import { AdminServiceService } from '../../admin-service.service';
import { GeneralMessage } from '../../models/GeneralMessage';
import { ViewChild, ElementRef } from '@angular/core';

@Component({
  selector: 'app-delete-coupon',
  templateUrl: './delete-coupon.component.html',
  styleUrls: ['./delete-coupon.component.css']
})
export class DeleteCouponComponent implements OnInit {
    private adminService: AdminServiceService;
    public deleteSucceededMessage: GeneralMessage;
    constructor(adminService: AdminServiceService) { 
        this.adminService = adminService;
    }


    @ViewChild('idInput') idInputRef: ElementRef;
     


    onSubmit() {

       if (confirm('Are you sure you want to delete this coupon?')) {
           console.log(this.idInputRef.nativeElement.value);
           this.deleteCoupon(this.idInputRef.nativeElement.value);
       } else {
           return;
       }

 
     }

    public deleteCoupon(num: string): void {
        let observable = this.adminService.deleteCoupon(num);
        observable.subscribe(deleteSucceededMessage => this.deleteSucceededMessage = deleteSucceededMessage);
    }

    ngOnInit() {
    }

}
