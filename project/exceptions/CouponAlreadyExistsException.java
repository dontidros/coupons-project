package project.exceptions;

@SuppressWarnings("serial")
public class CouponAlreadyExistsException extends Exception {
	public String getMessage() {
		return "Coupon already exists";
	}

}
