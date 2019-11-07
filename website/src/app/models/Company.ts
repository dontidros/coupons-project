
import { Coupon } from '../models/Coupon';

export class Company {
    public id: number;
    public name: string;
    public email: string;
    public password: string;
    public coupons: Coupon[];

    public constructor(id?: number, name?: string, email?: string, password?: string, coupons?: Coupon[]){
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.coupons = coupons;
    }
}