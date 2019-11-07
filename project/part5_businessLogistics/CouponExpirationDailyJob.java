package project.part5_businessLogistics;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import project.part2_beans.Coupon;



public class CouponExpirationDailyJob implements Runnable{

	
	private CompanyFacade companyFacade = new CompanyFacade();
	private boolean quit = true;
	public CouponExpirationDailyJob() {
		
	}
	/**
	 * starts to operate at 4:00 am every day and reviews
	 * all the coupons in the system in order to make sure
	 * that their expiration date has not passed.
	 * if some coupon expiration date has passed 
	 * this coupon will be deleted.
	 */
	public void run() {
		try {
			while(quit) {
				Thread.sleep(1000);
				if(isTimeToOparate()) {
					ArrayList<Coupon> allCoupons = companyFacade.couponsDAO.getAllCoupons();
					for(int i = 0; i < allCoupons.size(); i++) {
						Coupon coupon = allCoupons.get(i);
						String couponEndDate = coupon.getEndDate();
						LocalDate endDate = LocalDate.parse(couponEndDate);
						LocalDate localDate = LocalDate.now();

						if(localDate.isAfter(endDate)) {
						    int companyID = coupon.getCompanyId();
						    companyFacade.setCompanyId(companyID);
						    companyFacade.deleteCoupon(coupon.getId());
						}
					}
				}
			}
			
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		
	}
	/**
	 * deactivates run() function by changing quit variable value to false.   
	 */
	public void stop() {
	       quit = false;
	   }
	/**
	 * Checking the current time and returns true only if it's 4:00 am.
	 * 
	 */
	public boolean isTimeToOparate() {
		String timeToOparate = "04:00:00";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		LocalTime time = LocalTime.now();
		String f = formatter.format(time);
		if (!(f.equals(timeToOparate))) {
			return false;
		}
		return true;
	}
	
	

}
