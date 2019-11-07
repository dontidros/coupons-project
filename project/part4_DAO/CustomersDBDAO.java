package project.part4_DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import project.part2_beans.Category;
import project.part2_beans.Coupon;
import project.part2_beans.Customer;
import project.part3_connectionPool.ConnectionPool;

public class CustomersDBDAO implements CustomersDAO {
	
	public boolean isCustomerExists(String email, String password)throws SQLException, InterruptedException {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			String sql = String.format("SELECT Count(*) AS Count FROM CUSTOMERS WHERE EMAIL = '%s' AND PASSWORD = '%s'", email, password);
			try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
				try(ResultSet resultSet = preparedStatement.executeQuery()){
					resultSet.next();
					int count = resultSet.getInt("Count");
					return count == 1;
				}
			}
		}
		finally {
			ConnectionPool.getInstance().restoreConnection(connection);
		}
		
	}
	public void addCustomer(Customer customer) throws SQLException, InterruptedException {
		Connection connection = null;
		try {
		connection = ConnectionPool.getInstance().getConnection();
			
			String sql = String.format("insert into CUSTOMERS(FIRST_NAME, LAST_NAME, EMAIL, PASSWORD) values ('%s', '%s', '%s', '%s')", customer.getFirstName(), customer.getLastName(), customer.getEmail(), customer.getPassword());
			
			try(PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)){
				preparedStatement.executeUpdate();
				
				try(ResultSet resultSet = preparedStatement.getGeneratedKeys()){
					resultSet.next();
					int id = resultSet.getInt(1);
					
					System.out.println("Customers insert succeeded. new created id: " + id);
				}

			}
		}
		finally {
			ConnectionPool.getInstance().restoreConnection(connection);
		}
			

		
	
		
		
	}
	public void updateCustomer(Customer customer) throws SQLException, InterruptedException {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			String sql = String.format("update CUSTOMERS set FIRST_NAME = '%s', LAST_NAME = '%s', EMAIL = '%s', PASSWORD = '%s' where ID = %d", customer.getFirstName(), customer.getLastName(), customer.getEmail(), customer.getPassword(), customer.getId());
			try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
				preparedStatement.executeUpdate();
				System.out.println("Customers update succeeded");
			}
		}
		finally {
			ConnectionPool.getInstance().restoreConnection(connection);
		}
			

		

	}
	public void deleteCustomer(int customerID) throws SQLException, InterruptedException {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			String sql = String.format("delete from CUSTOMERS where ID = %d", customerID);
			try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
				preparedStatement.executeUpdate();
				System.out.println("Customers delete succeeded");
			}
		}
		finally {
			ConnectionPool.getInstance().restoreConnection(connection);
		}
			

		

	}
	public ArrayList<Customer> getAllCustomers() throws SQLException, InterruptedException {

		ArrayList<Customer> customers = new ArrayList<>();
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			String sql = "select * from CUSTOMERS";
			try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
				try(ResultSet resultSet = preparedStatement.executeQuery()){
					while(resultSet.next()) {
						Customer customer = new Customer();
						ArrayList<Integer> customerCouponsId = new ArrayList<>();
						ArrayList<Coupon> customerCoupons = new ArrayList<>();
						customer.setId(resultSet.getInt("id"));
						customer.setFirstName(resultSet.getString("first_name"));
						customer.setLastName(resultSet.getString("last_name"));
						customer.setEmail(resultSet.getString("email"));
						customer.setPassword(resultSet.getString("password"));
						customerCouponsId = getCustomerCouponsIdByCustomerId(customer.getId());
						
						for (int i = 0; i < customerCouponsId.size(); i++) {
							Coupon coupon = getOneCoupon(customerCouponsId.get(i));
							customerCoupons.add(coupon);
						}
						customer.setCoupons(customerCoupons);
						customers.add(customer);
					}
				}
			}
			
		
		return customers;
		}
		finally {
			ConnectionPool.getInstance().restoreConnection(connection);
		}
		
	
		
	}
	public Customer getOneCustomer(int customerID)throws SQLException, InterruptedException {
		
		Customer customer = new Customer();
		ArrayList<Integer> customerCouponsId = new ArrayList<>();
		ArrayList<Coupon> customerCoupons = new ArrayList<>();
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			String sql = String.format("select * from CUSTOMERS where id = %d", customerID);
			try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
				try(ResultSet resultSet = preparedStatement.executeQuery()){
					resultSet.next();
						customer.setId(resultSet.getInt("id"));
						customer.setFirstName(resultSet.getString("first_name"));
						customer.setLastName(resultSet.getString("last_name"));
						customer.setEmail(resultSet.getString("email"));
						customer.setPassword(resultSet.getString("password"));
						customerCouponsId = getCustomerCouponsIdByCustomerId(customer.getId());
						
						for (int i = 0; i < customerCouponsId.size(); i++) {
							Coupon coupon = getOneCoupon(customerCouponsId.get(i));
							customerCoupons.add(coupon);
						}
						customer.setCoupons(customerCoupons);
					
				}
			}
			
		
		return customer;
		}
		finally {
			ConnectionPool.getInstance().restoreConnection(connection);
		}
		
	}
	/**
	 * gets customer's coupons codes
	 */
	public ArrayList<Integer> getCustomerCouponsIdByCustomerId(int customerID) throws SQLException, InterruptedException{
		ArrayList<Integer> couponsId = new ArrayList<>();
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			String sql = String.format("SELECT COUPON_ID FROM CUSTOMERS_VS_COUPONS WHERE CUSTOMER_ID = %s", customerID);
			try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
				try(ResultSet resultSet = preparedStatement.executeQuery()){
					while (resultSet.next()) {
						int couponId = resultSet.getInt("COUPON_ID");
						couponsId.add(couponId);
					}
				}
			}
			return couponsId;
		}
		finally {
			ConnectionPool.getInstance().restoreConnection(connection);
		}
	}
	/**
	 * gets one coupon by its code
	 */
	public Coupon getOneCoupon(int couponID)throws SQLException, InterruptedException {
		Coupon coupon = new Coupon();
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			String sql = String.format("select * from COUPONS where id = %d", couponID);
			try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
				try(ResultSet resultSet = preparedStatement.executeQuery()){
					resultSet.next();
						coupon.setId(resultSet.getInt("id"));
						coupon.setCompanyId(resultSet.getInt("company_id"));
						String category = resultSet.getString("category");
						coupon.setCategory(Category.valueOf(category));
						coupon.setTitle(resultSet.getString("title"));
						coupon.setDescription(resultSet.getString("description"));
						coupon.setStartDate(resultSet.getString("start_date"));
						coupon.setEndDate(resultSet.getString("end_date"));
						coupon.setAmount(resultSet.getInt("amount"));
						coupon.setPrice(resultSet.getDouble("price"));
						coupon.setImage(resultSet.getString("image"));
						
						
						
						
						
					
				}
			}
			
		
		return coupon;
		}
		finally {
			ConnectionPool.getInstance().restoreConnection(connection);
		}
	}


}
