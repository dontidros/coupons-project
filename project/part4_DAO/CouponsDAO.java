package project.part4_DAO;

import java.sql.SQLException;
import java.util.ArrayList;

import org.json.simple.JSONArray;

import project.part2_beans.Coupon;
import project.part2_beans.Purchase;

public interface CouponsDAO {
	void addCoupon(Coupon coupon) throws SQLException, InterruptedException;
	void updateCoupon(Coupon coupon) throws SQLException, InterruptedException;
	void deleteCoupon(int couponID) throws SQLException, InterruptedException;
	ArrayList<Coupon> getAllCoupons() throws SQLException, InterruptedException;
	Coupon getOneCoupon(int couponID) throws SQLException, InterruptedException;
	void addCouponPurchase(int customerID, int couponID) throws SQLException, InterruptedException;
	void deleteCouponPurchase(int customerID, int couponID) throws SQLException, InterruptedException;
	
	ArrayList<Coupon> getAllCouponsMadeByOneCompany(int companyID)throws SQLException, InterruptedException;
	

	ArrayList<Purchase> getAllCouponsPurchases()throws SQLException, InterruptedException;
	ArrayList<Purchase> getOneCouponPurchases(int couponID)throws SQLException, InterruptedException;
	
	JSONArray getAllCouponsMadeByOneCompanyJSON(int companyId) throws SQLException, InterruptedException;
	
}
