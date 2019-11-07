import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GetCompanyCouponsFromSpecificCategoryComponent } from './get-company-coupons-from-specific-category.component';

describe('GetCompanyCouponsFromSpecificCategoryComponent', () => {
  let component: GetCompanyCouponsFromSpecificCategoryComponent;
  let fixture: ComponentFixture<GetCompanyCouponsFromSpecificCategoryComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GetCompanyCouponsFromSpecificCategoryComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GetCompanyCouponsFromSpecificCategoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
