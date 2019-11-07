package project.part4_DAO;

import java.sql.SQLException;
import java.util.ArrayList;

import project.part2_beans.Coupon;
import project.part2_beans.Customer;

public interface CustomersDAO {
	boolean isCustomerExists(String email, String password) throws SQLException, InterruptedException;
	void addCustomer(Customer customer) throws SQLException, InterruptedException;
	void updateCustomer(Customer customer) throws SQLException, InterruptedException;
	void deleteCustomer(int customerID) throws SQLException, InterruptedException;
	ArrayList<Customer> getAllCustomers() throws SQLException, InterruptedException;
	Customer getOneCustomer(int customerID) throws SQLException, InterruptedException;
	ArrayList<Integer> getCustomerCouponsIdByCustomerId(int customerID) throws SQLException, InterruptedException;
	Coupon getOneCoupon(int couponID)throws SQLException, InterruptedException;

}
