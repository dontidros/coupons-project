package project.exceptions;

@SuppressWarnings("serial")
public class CouponEndDateHasPassedException extends Exception {
	public String getMessage() {
		return "Coupon end date has passed";
	}

}
