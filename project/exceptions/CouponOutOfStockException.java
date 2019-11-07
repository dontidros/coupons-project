package project.exceptions;

@SuppressWarnings("serial")
public class CouponOutOfStockException extends Exception {
	public String getMessage() {
		return "The coupon is out of stock";
	}

}
