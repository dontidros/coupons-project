import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Company } from './models/Company';
import { LoginFields } from './models/LoginFields';
import { LoginMessage } from './models/loginMessage';
import { Customer } from './models/Customer';
import { Coupon } from './models/Coupon';
import { GeneralMessage } from './models/GeneralMessage';
import { Income } from './models/Income';

@Injectable({
  providedIn: 'root'
})
export class AdminServiceService {




    private httpClient: HttpClient;

    public constructor(httpClient: HttpClient) {
        this.httpClient = httpClient;
    }


//                    admin methods           
    
    public adminLogin(loginFields: LoginFields): Observable<LoginMessage> {
        let url = "http://localhost:8080/project2Parts-try2/admin/login";
        return this.httpClient.post<LoginMessage>(url, loginFields, { withCredentials: true } );
    }

    public adminLogout(): Observable<GeneralMessage> {
        let url = "http://localhost:8080/project2Parts-try2/admin/logout";
        return this.httpClient.get<GeneralMessage>(url, { withCredentials: true } );
    }

    public getAllCompanies(): Observable<Company[]> {
        let url = "http://localhost:8080/project2Parts-try2/admin/company";
        return this.httpClient.get<Company[]>(url, { withCredentials: true } );
    }

    public getOneCompany(num: string): Observable<Company> {
        let url = "http://localhost:8080/project2Parts-try2/admin/company/" + num;
        return this.httpClient.get<Company>(url, { withCredentials: true } );
    }

    public addCompany(company: Company): Observable<Company> {
        let url = "http://localhost:8080/project2Parts-try2/admin/company-add";
        return this.httpClient.post<Company>(url, company, { withCredentials: true } );
    }

    public updateCompany(company: Company): Observable<Company> {
        let url = "http://localhost:8080/project2Parts-try2/admin/company-update";
        return this.httpClient.put<Company>(url, company, { withCredentials: true } );
    }

    public deleteCompany(num: string): Observable<GeneralMessage> {
        let url = "http://localhost:8080/project2Parts-try2/admin/company/" + num;
        return this.httpClient.delete<GeneralMessage>(url, { withCredentials: true } );
    }

    public getAllCustomers(): Observable<Customer[]> {
        let url = "http://localhost:8080/project2Parts-try2/admin/customer";
        return this.httpClient.get<Customer[]>(url, { withCredentials: true } );
    }

    public getOneCustomer(num: string): Observable<Customer> {
        let url = "http://localhost:8080/project2Parts-try2/admin/customer/" + num;
        return this.httpClient.get<Customer>(url, { withCredentials: true } );
    }
    public addCustomer(customer: Customer): Observable<Customer> {
        let url = "http://localhost:8080/project2Parts-try2/admin/customer-add";
        return this.httpClient.post<Customer>(url, customer, { withCredentials: true } );
    }

    public updateCustomer(customer: Customer): Observable<Customer> {
        let url = "http://localhost:8080/project2Parts-try2/admin/customer-update";
        return this.httpClient.put<Customer>(url, customer, { withCredentials: true } );
    }

    public deleteCustomer(num: string): Observable<GeneralMessage> {
        let url = "http://localhost:8080/project2Parts-try2/admin/customer/" + num;
        return this.httpClient.delete<GeneralMessage>(url, { withCredentials: true } );
    }


    public getAllIncomes(): Observable<Income[]> {
        let url = "http://localhost:8080/project2Parts-try2/admin/incomes";
        return this.httpClient.get<Income[]>(url, { withCredentials: true } );
    }

    public getAllIncomesByCompanies(): Observable<Income[]> {
        let url = "http://localhost:8080/project2Parts-try2/admin/incomes-company";
        return this.httpClient.get<Income[]>(url, { withCredentials: true } );
    }

    public getAllIncomesByCustomers(): Observable<Income[]> {
        let url = "http://localhost:8080/project2Parts-try2/admin/incomes-customer";
        return this.httpClient.get<Income[]>(url, { withCredentials: true } );
    }
    
    public getIncomesBySpecificCompany(num: string): Observable<Income[]> {
        let url = "http://localhost:8080/project2Parts-try2/admin/incomes-company/" + num;
        return this.httpClient.get<Income[]>(url, { withCredentials: true } );
    }
    public getIncomesBySpecificCustomer(num: string): Observable<Income[]> {
        let url = "http://localhost:8080/project2Parts-try2/admin/incomes-customer/" + num;
        return this.httpClient.get<Income[]>(url, { withCredentials: true } );
    }



//                    company methods

    public companyLogin(loginFields: LoginFields): Observable<LoginMessage> {
        let url = "http://localhost:8080/project2Parts-try2/company/login";
        return this.httpClient.post<LoginMessage>(url, loginFields, { withCredentials: true } );
    }

    public companyLogout(): Observable<GeneralMessage> {
        let url = "http://localhost:8080/project2Parts-try2/company/logout";
        return this.httpClient.get<GeneralMessage>(url, { withCredentials: true } );
    }

    public getCompanyDetails(): Observable<Company> {
        let url = "http://localhost:8080/project2Parts-try2/company";
        return this.httpClient.get<Company>(url, { withCredentials: true } );
    }

    public getCompanyCoupons(): Observable<Coupon[]> {
        let url = "http://localhost:8080/project2Parts-try2/company/coupon";
        return this.httpClient.get<Coupon[]>(url, { withCredentials: true } );

    }

    public getCompanyCouponsFromSpecificCategory(category: string): Observable<Coupon[]> {
        let url = "http://localhost:8080/project2Parts-try2/company/coupon-category/" + category;
        return this.httpClient.get<Coupon[]>(url, { withCredentials: true } );
    }

    public getCompanyCouponsAtOrBelowTheMaximumPrice(max: string): Observable<Coupon[]> {
        let url = "http://localhost:8080/project2Parts-try2/company/coupon-price/" + max;
        return this.httpClient.get<Coupon[]>(url, { withCredentials: true } );
    }

    public addCoupon(coupon: Coupon): Observable<Coupon> {
        let url = "http://localhost:8080/project2Parts-try2/company/coupon-add";
        return this.httpClient.post<Coupon>(url, coupon, { withCredentials: true } );
    }

    public updateCoupon(coupon: Coupon): Observable<Coupon> {
        let url = "http://localhost:8080/project2Parts-try2/company/coupon-update";
        return this.httpClient.put<Coupon>(url, coupon, { withCredentials: true } );
    }

    public deleteCoupon(num: string): Observable<GeneralMessage> {
        let url = "http://localhost:8080/project2Parts-try2/company/coupon/" + num;
        return this.httpClient.delete<GeneralMessage>(url, { withCredentials: true } );
    }

    public getCompanyExpenses(): Observable<Income[]> {
        let url = "http://localhost:8080/project2Parts-try2/company/incomes";
        return this.httpClient.get<Income[]>(url, { withCredentials: true } );
    }



//                    customer methods

    public customerLogin(loginFields: LoginFields): Observable<LoginMessage> {
        let url = "http://localhost:8080/project2Parts-try2/customer/login";
        return this.httpClient.post<LoginMessage>(url, loginFields, { withCredentials: true } );
    }

    public customerLogout(): Observable<GeneralMessage> {
        let url = "http://localhost:8080/project2Parts-try2/customer/logout";
        return this.httpClient.get<GeneralMessage>(url, { withCredentials: true } );
    }

    public getCustomerDetails(): Observable<Customer> {
        let url = "http://localhost:8080/project2Parts-try2/customer";
        return this.httpClient.get<Customer>(url, { withCredentials: true } );
    }

    public getCustomerCoupons(): Observable<Coupon[]> {
        let url = "http://localhost:8080/project2Parts-try2/customer/coupon";
        return this.httpClient.get<Coupon[]>(url, { withCredentials: true } );
    }

    public getCustomerCouponsFromSpecificCategory(category: string): Observable<Coupon[]> {
        let url = "http://localhost:8080/project2Parts-try2/customer/coupon-category/" + category;
        return this.httpClient.get<Coupon[]>(url, { withCredentials: true } );
    }

    public getCustomerCouponsAtOrBelowTheMaximumPrice(max: string): Observable<Coupon[]> {
        let url = "http://localhost:8080/project2Parts-try2/customer/coupon-price/" + max;
        return this.httpClient.get<Coupon[]>(url, { withCredentials: true } );
    }

    public getAllCoupons(): Observable<Coupon[]> {
        let url = "http://localhost:8080/project2Parts-try2/customer/coupon-all";
        return this.httpClient.get<Coupon[]>(url, { withCredentials: true } );
    }

    public getOneCoupon(num: string): Observable<Coupon> {
        let url = "http://localhost:8080/project2Parts-try2/customer/coupon-one/" + num;
        return this.httpClient.get<Coupon>(url, { withCredentials: true } );
    }

    public purchaseCoupon(num: string): Observable<Coupon> {
        let url = "http://localhost:8080/project2Parts-try2/customer/coupon-purchase/" + num;
        return this.httpClient.get<Coupon>(url, { withCredentials: true } );
    }

    public getCustomerExpenses(): Observable<Income[]> {
        let url = "http://localhost:8080/project2Parts-try2/customer/incomes";
        return this.httpClient.get<Income[]>(url, { withCredentials: true } );
    }











      
































































}
