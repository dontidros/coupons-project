package project.part2_beans;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Purchase implements Serializable {
	private int customerId;
	private int couponId;
	/**
	 * an object that facilitates communication
	 * with customers_vs_coupons table
	 * 
	 * 
	 */
	public Purchase(int customerId, int couponId) {
		setCustomerId(customerId);
		setCouponId(couponId);
	}
	public Purchase() {
		
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public int getCouponId() {
		return couponId;
	}
	public void setCouponId(int couponId) {
		this.couponId = couponId;
	}
	public void display() {
		System.out.println("Customer Id: " + getCustomerId() + " Coupon Id: " + getCouponId()) ;
	}
	
}
