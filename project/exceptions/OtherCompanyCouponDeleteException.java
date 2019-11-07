package project.exceptions;

@SuppressWarnings("serial")
public class OtherCompanyCouponDeleteException extends Exception {
	public String getMessage() {
		return "Do not try to delete other company coupon";
	}

}
