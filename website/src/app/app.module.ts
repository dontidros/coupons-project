import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterModule, Routes } from "@angular/router";
import { HttpClientModule } from "@angular/common/http";
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { HomeComponent } from './home/home.component';
import { MenuComponent } from './menu/menu.component';
import { AdminFacadeComponent } from './admin-facade/admin-facade.component';
import { CompanyFacadeComponent } from './company-facade/company-facade.component';
import { CustomerFacadeComponent } from './customer-facade/customer-facade.component';
import { GetAllCompaniesComponent } from './adminMethods/get-all-companies/get-all-companies.component';
import { GetAllCustomersComponent } from './adminMethods/get-all-customers/get-all-customers.component';
import { GetOneCompanyComponent } from './adminMethods/get-one-company/get-one-company.component';
import { GetOneCustomerComponent } from './adminMethods/get-one-customer/get-one-customer.component';
import { GetCompanyDetailsComponent } from './companyMethods/get-company-details/get-company-details.component';
import { GetCompanyCouponsComponent } from './companyMethods/get-company-coupons/get-company-coupons.component';
import { GetCompanyCouponsFromSpecificCategoryComponent } from './companyMethods/get-company-coupons-from-specific-category/get-company-coupons-from-specific-category.component';
import { GetCompanyCouponsAtOrBelowTheMaximumPriceComponent } from './companyMethods/get-company-coupons-at-or-below-the-maximum-price/get-company-coupons-at-or-below-the-maximum-price.component';
import { GetCustomerDetailsComponent } from './customerMethods/get-customer-details/get-customer-details.component';
import { GetCustomerCouponsComponent } from './customerMethods/get-customer-coupons/get-customer-coupons.component';
import { GetCustomerCouponsFromSpecificCategoryComponent } from './customerMethods/get-customer-coupons-from-specific-category/get-customer-coupons-from-specific-category.component';
import { GetCustomerCouponsAtOrBelowTheMaximumPriceComponent } from './customerMethods/get-customer-coupons-at-or-below-the-maximum-price/get-customer-coupons-at-or-below-the-maximum-price.component';
import { AddCompanyComponent } from './adminMethods/add-company/add-company.component';
import { AddCustomerComponent } from './adminMethods/add-customer/add-customer.component';
import { AddCouponComponent } from './companyMethods/add-coupon/add-coupon.component';
import { UpdateCompanyComponent } from './adminMethods/update-company/update-company.component';
import { UpdateCustomerComponent } from './adminMethods/update-customer/update-customer.component';
import { UpdateCouponComponent } from './companyMethods/update-coupon/update-coupon.component';
import { DeleteCompanyComponent } from './adminMethods/delete-company/delete-company.component';
import { DeleteCustomerComponent } from './adminMethods/delete-customer/delete-customer.component';
import { DeleteCouponComponent } from './companyMethods/delete-coupon/delete-coupon.component';
import { AdminLogoutComponent } from './adminMethods/admin-logout/admin-logout.component';
import { CompanyLogoutComponent } from './companyMethods/company-logout/company-logout.component';
import { CustomerLogoutComponent } from './customerMethods/customer-logout/customer-logout.component';
import { PurchaseCouponComponent } from './customerMethods/purchase-coupon/purchase-coupon.component';
import { GetAllIncomesComponent } from './adminMethods/get-all-incomes/get-all-incomes.component';
import { GetAllIncomesByCompaniesComponent } from './adminMethods/get-all-incomes-by-companies/get-all-incomes-by-companies.component';
import { GetAllIncomesByCustomersComponent } from './adminMethods/get-all-incomes-by-customers/get-all-incomes-by-customers.component';
import { GetIncomesBySpecificCompanyComponent } from './adminMethods/get-incomes-by-specific-company/get-incomes-by-specific-company.component';
import { GetIncomesBySpecificCustomerComponent } from './adminMethods/get-incomes-by-specific-customer/get-incomes-by-specific-customer.component';
import { GetCompanyExpensesComponent } from './companyMethods/get-company-expenses/get-company-expenses.component';
import { GetCustomerExpensesComponent } from './customerMethods/get-customer-expenses/get-customer-expenses.component';
import { AdminBusinessDelegateComponent } from './admin-business-delegate/admin-business-delegate.component';

const siteRoutes: Routes = [
    { path: "home", component: HomeComponent },
    { path: "admin-facade", component: AdminFacadeComponent },
    { path: "admin-business-delegate", component: AdminBusinessDelegateComponent},
    { path: "company-facade", component: CompanyFacadeComponent },
    { path: "customer-facade", component: CustomerFacadeComponent },
    { path: "", redirectTo: "/home", pathMatch: "full" }, // pathMatch = Only on full empty string - redirect to "/home"
];


@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    HomeComponent,
    MenuComponent,
    AdminFacadeComponent,
    CompanyFacadeComponent,
    CustomerFacadeComponent,
    GetAllCompaniesComponent,
    GetAllCustomersComponent,
    GetOneCompanyComponent,
    GetOneCustomerComponent,
    GetCompanyDetailsComponent,
    GetCompanyCouponsComponent,
    GetCompanyCouponsFromSpecificCategoryComponent,
    GetCompanyCouponsAtOrBelowTheMaximumPriceComponent,
    GetCustomerDetailsComponent,
    GetCustomerCouponsComponent,
    GetCustomerCouponsFromSpecificCategoryComponent,
    GetCustomerCouponsAtOrBelowTheMaximumPriceComponent,
    AddCompanyComponent,
    AddCustomerComponent,
    AddCouponComponent,
    UpdateCompanyComponent,
    UpdateCustomerComponent,
    UpdateCouponComponent,
    DeleteCompanyComponent,
    DeleteCustomerComponent,
    DeleteCouponComponent,
    AdminLogoutComponent,
    CompanyLogoutComponent,
    CustomerLogoutComponent,
    PurchaseCouponComponent,
    GetAllIncomesComponent,
    GetAllIncomesByCompaniesComponent,
    GetAllIncomesByCustomersComponent,
    GetIncomesBySpecificCompanyComponent,
    GetIncomesBySpecificCustomerComponent,
    GetCompanyExpensesComponent,
    GetCustomerExpensesComponent,
    AdminBusinessDelegateComponent

  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule,
    RouterModule.forRoot(siteRoutes),
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
