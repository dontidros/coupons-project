package project.exceptions;

@SuppressWarnings("serial")
public class UpdateCouponCodeException extends Exception {
	public String getMessage() {
		return "Do not update the coupon code";
	}

}
