import { Component, OnInit } from '@angular/core';
import { Coupon } from '../../models/Coupon';
import { AdminServiceService } from '../../admin-service.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ViewChild, ElementRef } from '@angular/core';


@Component({
  selector: 'app-add-coupon',
  templateUrl: './add-coupon.component.html',
  styleUrls: ['./add-coupon.component.css']
})
export class AddCouponComponent implements OnInit {
  private adminService: AdminServiceService;
  public coupon: Coupon;
  constructor(adminService: AdminServiceService) {
        this.adminService = adminService;
   }
  

   @ViewChild('companyIdInput') companyIdInputRef: ElementRef;
   @ViewChild('categoryInput') categoryInputRef: ElementRef;
   @ViewChild('titleInput') titleInputRef: ElementRef;
   @ViewChild('descriptionInput') descriptionInputRef: ElementRef;
   @ViewChild('startDateYearInput') startDateYearInputRef: ElementRef;
   @ViewChild('startDateMonthInput') startDateMonthInputRef: ElementRef;
   @ViewChild('startDateDayInput') startDateDayInputRef: ElementRef; 
   @ViewChild('endDateYearInput') endDateYearInputRef: ElementRef;
   @ViewChild('endDateMonthInput') endDateMonthInputRef: ElementRef;
   @ViewChild('endDateDayInput') endDateDayInputRef: ElementRef; 
   @ViewChild('amountInput') amountInputRef: ElementRef;
   @ViewChild('priceInput') priceInputRef: ElementRef;
   @ViewChild('imageInput') imageInputRef: ElementRef;

   form = new FormGroup ({
    companyId: new FormControl("", Validators.compose([
        Validators.required
    ]) ),
    category: new FormControl("", Validators.compose([
        Validators.required
    ])),
    title: new FormControl("", Validators.compose([
        Validators.required
    ])),
    description: new FormControl("", Validators.compose([
        Validators.required
    ])),
    startDateYear: new FormControl("", Validators.compose([
        Validators.required
    ])),
    startDateMonth: new FormControl("", Validators.compose([
        Validators.required
    ])),
    startDateDay: new FormControl("", Validators.compose([
        Validators.required
    ])),
    endDateYear: new FormControl("", Validators.compose([
        Validators.required
    ])),
    endDateMonth: new FormControl("", Validators.compose([
        Validators.required
    ])),
    endDateDay: new FormControl("", Validators.compose([
        Validators.required
    ])),

    amount: new FormControl("", Validators.compose([
        Validators.required
    ])),
    price: new FormControl("", Validators.compose([
        Validators.required
    ])),
    image: new FormControl("", Validators.compose([
        Validators.required
    ]))

});

onSubmit(){
    this.coupon = new Coupon(0, this.companyIdInputRef.nativeElement.value,
                                this.categoryInputRef.nativeElement.value,
                                this.titleInputRef.nativeElement.value,
                                this.descriptionInputRef.nativeElement.value,
                                this.startDateYearInputRef.nativeElement.value + '-' + this.startDateMonthInputRef.nativeElement.value + '-' + this.startDateDayInputRef.nativeElement.value,
                                this.endDateYearInputRef.nativeElement.value + '-' + this.endDateMonthInputRef.nativeElement.value + '-' + this.endDateDayInputRef.nativeElement.value,
                                this.amountInputRef.nativeElement.value,
                                this.priceInputRef.nativeElement.value,
                                this.imageInputRef.nativeElement.value);
                                
    console.log(this.coupon.companyId);
    let observable = this.adminService.addCoupon(this.coupon);
    observable.subscribe(coupon => this.coupon = coupon);
}


  ngOnInit() {
  }

}
