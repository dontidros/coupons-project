package project.part4_DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import project.part2_beans.Category;
import project.part2_beans.Company;
import project.part2_beans.Coupon;
import project.part3_connectionPool.ConnectionPool;

public class CompaniesDBDAO implements CompaniesDAO {
	
	public boolean isCompanyExists(String email, String password) throws SQLException, InterruptedException {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			String sql = String.format("SELECT Count(*) AS Count FROM COMPANIES WHERE EMAIL = '%s' AND PASSWORD = '%s'", email, password);
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
	public void addCompany(Company company) throws SQLException, InterruptedException {
		Connection connection = null;
		try {
		connection = ConnectionPool.getInstance().getConnection();
			
			String sql = String.format("insert into COMPANIES(NAME, EMAIL, PASSWORD) values ('%s', '%s', '%s')", company.getName(), company.getEmail(), company.getPassword());
			
			try(PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)){
				preparedStatement.executeUpdate();
				
				try(ResultSet resultSet = preparedStatement.getGeneratedKeys()){
					resultSet.next();
					int id = resultSet.getInt(1);
					
					System.out.println("Companies insert succeeded. new created id: " + id);
				}
				

			}
		}
		finally {
			ConnectionPool.getInstance().restoreConnection(connection);
		}
			
			

		

		
	}

	public void updateCompany(Company company) throws SQLException, InterruptedException {
		Connection connection = null;
		try {
		connection = ConnectionPool.getInstance().getConnection();
			
			String sql = String.format("update COMPANIES set NAME = '%s', EMAIL = '%s', PASSWORD = '%s' where ID = %d", company.getName(), company.getEmail(), company.getPassword(), company.getId());
			
			try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
				preparedStatement.executeUpdate();
				
				System.out.println("Companies update succeeded");
			}
		}
		finally {
			ConnectionPool.getInstance().restoreConnection(connection);
		}
			

		

	}
	public void deleteCompany(int CompanyID) throws SQLException, InterruptedException {
		Connection connection = null;
		try {
		connection = ConnectionPool.getInstance().getConnection();
			
			String sql = String.format("delete from COMPANIES where ID = %d", CompanyID );
			
			try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
				preparedStatement.executeUpdate();
				
				System.out.println("Companies delete succeeded");
			}
		}
		finally {
			ConnectionPool.getInstance().restoreConnection(connection);
		}
			

		

		
		
	}
	public ArrayList<Company> getAllCompanies() throws SQLException, InterruptedException {
		System.out.println(111);
		ArrayList<Company> companies = new  ArrayList<>();
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			System.out.println(1111);
			String sql = "select * from COMPANIES";
			try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
				try(ResultSet resultSet = preparedStatement.executeQuery()){
					while(resultSet.next()) {
						Company company = new Company();
						company.setId(resultSet.getInt("id"));
						company.setName(resultSet.getString("name"));
						company.setEmail(resultSet.getString("email"));
						company.setPassword(resultSet.getString("password"));
						company.setCoupons(getCouponsByCompanyId(company.getId()));
						
						companies.add(company);
						
					}
				}
			}
			
		
		return companies;
		}
		finally {
			ConnectionPool.getInstance().restoreConnection(connection);
		}
		
		
	}
	public Company getOneCompany(int companyID) throws SQLException, InterruptedException {
		Company company = new Company();
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			String sql = String.format("select * from COMPANIES where id = %d", companyID);
			try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
				try(ResultSet resultSet = preparedStatement.executeQuery()){
					resultSet.next();
						company.setId(resultSet.getInt("id"));
						company.setName(resultSet.getString("name"));
						company.setEmail(resultSet.getString("email"));
						company.setPassword(resultSet.getString("password"));
						company.setCoupons(getCouponsByCompanyId(company.getId()));
					
				}
			}
			
		
		return company;
		}
		finally {
			ConnectionPool.getInstance().restoreConnection(connection);
		}
		
	}
	
	public ArrayList<Coupon> getCouponsByCompanyId(int companyID) throws SQLException, InterruptedException{
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			String sql = String.format("SELECT * FROM COUPONS WHERE COMPANY_ID = %d", companyID);
			try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
				try(ResultSet resultSet = preparedStatement.executeQuery()){
					ArrayList<Coupon> allCoupons = new ArrayList<Coupon>();
					
					while (resultSet.next()) {
						int id = resultSet.getInt("ID");
						String category = resultSet.getString("CATEGORY");
						Category c = Category.valueOf(category);
						String title = resultSet.getString("TITLE");
						String description = resultSet.getString("DESCRIPTION");
						String startDate = resultSet.getString("START_DATE");
						String endDate = resultSet.getString("END_DATE");
						int amount = resultSet.getInt("AMOUNT");
						double price = resultSet.getDouble("PRICE");
						String image = resultSet.getString("IMAGE");
						
						Coupon coupon = new Coupon(id, companyID, c, title, description, startDate, endDate, amount, price, image);
						allCoupons.add(coupon);
						
					}
					return allCoupons;
				}
			}
		}
		finally {
			ConnectionPool.getInstance().restoreConnection(connection);
		}
	}
	
	
	
	
	
	

}
