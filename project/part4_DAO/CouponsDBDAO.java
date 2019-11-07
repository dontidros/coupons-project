package project.part4_DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import project.part2_beans.Category;
import project.part2_beans.Coupon;
import project.part2_beans.Purchase;
import project.part3_connectionPool.ConnectionPool;

public class CouponsDBDAO implements CouponsDAO {
	
	public void addCoupon(Coupon coupon) throws SQLException, InterruptedException {
		Connection connection = null;
		try {
		connection = ConnectionPool.getInstance().getConnection();
			
			
			String sql = "insert into COUPONS(COMPANY_ID, CATEGORY, TITLE, DESCRIPTION, START_DATE, END_DATE, AMOUNT, PRICE, IMAGE) values (" + coupon.getCompanyId() + ", '" + coupon.getCategory() + "', '" + coupon.getTitle() + "', '" + coupon.getDescription() + "',  '" + coupon.getStartDate() + "', '" + coupon.getEndDate() + "', " + coupon.getAmount() + ",  " + coupon.getPrice() + ", '" + coupon.getImage() + "')";
			
			try(PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)){
				preparedStatement.executeUpdate();
				try(ResultSet resultSet = preparedStatement.getGeneratedKeys()){
					resultSet.next();
					int id = resultSet.getInt(1);
					
					System.out.println("Coupons insert succeeded. new created id: " + id);
				}

			}
		}
		finally {
			ConnectionPool.getInstance().restoreConnection(connection);
		}
			

		
	
		
		
		
	}
	public void updateCoupon(Coupon coupon)throws SQLException, InterruptedException {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			String sql = String.format("update COUPONS set COMPANY_ID = %d, CATEGORY = '%s', TITLE = '%s', DESCRIPTION = '%s',  START_DATE = '%s', END_DATE = '%s', AMOUNT = %d, PRICE = %.2f, IMAGE = '%s' where ID = %d", coupon.getCompanyId(), coupon.getCategory(), coupon.getTitle(), coupon.getDescription(), coupon.getStartDate(), coupon.getEndDate(), coupon.getAmount(), coupon.getPrice(), coupon.getImage(), coupon.getId());
			try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
				preparedStatement.executeUpdate();
				System.out.println("Coupons update succeeded");
			}
		}
		finally {
			ConnectionPool.getInstance().restoreConnection(connection);
		}
			
		
	}
	public void deleteCoupon(int couponID) throws SQLException, InterruptedException {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			String sql = String.format("delete from COUPONS where ID = %d", couponID);
			try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
				preparedStatement.executeUpdate();
				System.out.println("Coupons delete succeeded");
			}
		}
		finally {
			ConnectionPool.getInstance().restoreConnection(connection);
		}
		
	}
	public ArrayList<Coupon> getAllCoupons()throws SQLException, InterruptedException{
		ArrayList<Coupon> coupons = new ArrayList<>();
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			String sql = "select * from COUPONS";
			try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
				try(ResultSet resultSet = preparedStatement.executeQuery()){
					while(resultSet.next()) {
						Coupon coupon = new Coupon();
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
						
						coupons.add(coupon);
						
						
						
						
					}
				}
			}
			
		
		return coupons;
		}
		finally {
			ConnectionPool.getInstance().restoreConnection(connection);
		}
	}
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
	public void addCouponPurchase(int customerID, int couponID) throws SQLException, InterruptedException{
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			String sql = String.format("INSERT INTO CUSTOMERS_VS_COUPONS (CUSTOMER_ID, COUPON_ID) VALUES (%d, %d)", customerID, couponID);
			try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
				preparedStatement.executeUpdate();
			}
		}
		finally {
			ConnectionPool.getInstance().restoreConnection(connection);
		}
	}	
		
	public void deleteCouponPurchase(int customerID, int couponID)throws SQLException, InterruptedException {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			String sql = String.format("DELETE FROM CUSTOMERS_VS_COUPONS WHERE CUSTOMER_ID = %d AND COUPON_ID = %d", customerID, couponID);
			try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
				preparedStatement.executeUpdate();
			}
		}
		finally {
			ConnectionPool.getInstance().restoreConnection(connection);
		}
	}
	/**
	 * gets all coupons made by one company
	 */
	public ArrayList<Coupon> getAllCouponsMadeByOneCompany(int companyID)throws SQLException, InterruptedException{
		ArrayList<Coupon> coupons = new ArrayList<>();
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			String sql = String.format("select * from COUPONS where COMPANY_ID = %d", companyID);
			try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
				try(ResultSet resultSet = preparedStatement.executeQuery()){
					while(resultSet.next()) {
						Coupon coupon = new Coupon();
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
						
						coupons.add(coupon);
						
						
						
						
					}
				}
			}
			
		
		return coupons;
		}
		finally {
			ConnectionPool.getInstance().restoreConnection(connection);
		}
	}
	/**
	 * gets all purchases
	 */
	public ArrayList<Purchase> getAllCouponsPurchases()throws SQLException, InterruptedException{
		ArrayList<Purchase> allPurchases = new ArrayList<>();
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			String sql = "select * from CUSTOMERS_VS_COUPONS";
			try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
				try(ResultSet resultSet = preparedStatement.executeQuery()){
					while(resultSet.next()) {
						Purchase purchase = new Purchase();
						purchase.setCustomerId(resultSet.getInt("CUSTOMER_ID"));
						purchase.setCouponId(resultSet.getInt("COUPON_ID"));
						

						allPurchases.add(purchase);
						
						
						
						
						
					}
				}
			}
			
		
		return allPurchases;
		}
		finally {
			ConnectionPool.getInstance().restoreConnection(connection);
		}
	}
	/**
	 * gets all purchases of one coupon
	 */
	public ArrayList<Purchase> getOneCouponPurchases(int couponID)throws SQLException, InterruptedException{
		ArrayList<Purchase> couponAllPurchases = new ArrayList<>();
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			String sql = String.format("SELECT * from CUSTOMERS_VS_COUPONS WHERE COUPON_ID = %d", couponID);
			try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
				try(ResultSet resultSet = preparedStatement.executeQuery()){
					while(resultSet.next()) {
						Purchase purchase = new Purchase();
						purchase.setCustomerId(resultSet.getInt("CUSTOMER_ID"));
						purchase.setCouponId(resultSet.getInt("COUPON_ID"));
						

						couponAllPurchases.add(purchase);
						
						
						
						
						
					}
				}
			}
			
		
		return couponAllPurchases;
		}
		finally {
			ConnectionPool.getInstance().restoreConnection(connection);
		}
	}
	
	@SuppressWarnings("unchecked")
	public JSONArray getAllCouponsMadeByOneCompanyJSON(int companyId) throws SQLException, InterruptedException {
		ArrayList<Coupon> companyCoupons = getAllCouponsMadeByOneCompany(companyId);
		JSONArray jsonArray = new JSONArray();
			for(int i = 0; i < companyCoupons.size(); i++) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("id", companyCoupons.get(i).getId());
				jsonObject.put("companyId", companyCoupons.get(i).getCompanyId());
				jsonObject.put("category", companyCoupons.get(i).getCategory().name());
				jsonObject.put("title", companyCoupons.get(i).getTitle());
				jsonObject.put("description", companyCoupons.get(i).getDescription());
				jsonObject.put("startDate", companyCoupons.get(i).getStartDate());
				jsonObject.put("endDate", companyCoupons.get(i).getEndDate());
				jsonObject.put("amount", companyCoupons.get(i).getAmount());
				jsonObject.put("price", companyCoupons.get(i).getPrice());
				jsonObject.put("image", companyCoupons.get(i).getImage());
				jsonArray.add(jsonObject);
				
			}
			
			return jsonArray;
	
	}
}
