package project.exceptions;

@SuppressWarnings("serial")
public class CouponAlreadyBoughtException extends Exception {
	public String getMessage() {
		return "This coupon has already been bought by this customer";
	}

}
