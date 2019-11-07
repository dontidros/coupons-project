package project.part1_DB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import project.part3_connectionPool.ConnectionPool;

public class DB {
	protected static String connectionString = "jdbc:derby://localhost:1527/project2Parts-try2;create=true";
	
	public static void buildDB() throws SQLException, InterruptedException{
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();

				
				String sql = "create table COMPANIES(" +
				"ID integer not null primary key " +
				"generated always as identity (start with 1, increment by 1), " +
				"NAME varchar(50) not null, " +
				"EMAIL varchar(50) not null, " +
				"PASSWORD varchar(50) not null)";
				
				

				
				try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
					preparedStatement.executeUpdate();
				}
				
			

					
			
					
		}
		catch(SQLException ex) {
			if(ex.getSQLState().equals("X0Y32")) {
				return;
			}
			throw ex;
		}
		try {
			connection = ConnectionPool.getInstance().getConnection();
				
				
				String sql = "create table CUSTOMERS(" +
				"ID integer not null primary key " +
				"generated always as identity(start with 1, increment by 1), " +
				"FIRST_NAME varchar(50) not null, " +
				"LAST_NAME varchar(50) not null, " +
				"EMAIL varchar(50) not null, " +
				"PASSWORD varchar(50) not null)";
				
				try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
					preparedStatement.executeUpdate();
				}
				
			
			

			
		}
		catch(SQLException ex) {
			if(ex.getSQLState().equals("X0Y32")) {
				return;
			}
			throw ex;
		}
		try {
			connection = ConnectionPool.getInstance().getConnection();
				
				String sql = "create table CATEGORIES(" +
				"ID integer not null primary key " +
				"generated always as identity (start with 1, increment by 1), " +
				"Name varchar(50) not null)";
				
				try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
					preparedStatement.executeUpdate();
				}
			
		}
		catch(SQLException ex) {
			if(ex.getSQLState().equals("X0Y32")) {
				return;
			}
			throw ex;
		}
		try {
			connection = ConnectionPool.getInstance().getConnection();
				
				
				String sql = "create table COUPONS(" +
				"ID integer not null primary key " +
				"generated always as identity(start with 1, increment by 1), " +
				"COMPANY_ID integer not null, " +
				"CATEGORY varchar(50) not null, " +
				"TITLE varchar(50) not null, " +
				"DESCRIPTION varchar(50) not null, " +
				"START_DATE DATE not null, " +
				"END_DATE DATE not null, " +
				"AMOUNT integer not null, " +
				"PRICE double default 0, " +
				"IMAGE varchar(200) not null)";
				
				try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
					preparedStatement.executeUpdate();
				}
				
			

			
			
		}
		catch(SQLException ex) {
			if(ex.getSQLState().equals("X0Y32")) {
				return;
			}
			throw ex;
		}
		try {
			connection = ConnectionPool.getInstance().getConnection();
				
				
				String sql = "create table CUSTOMERS_VS_COUPONS(" +
				"CUSTOMER_ID numeric, " +
				"COUPON_ID numeric, " +
				"primary key (CUSTOMER_ID, COUPON_ID))";
				
				try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
					preparedStatement.executeUpdate();
				}
				
			

		}
		catch(SQLException ex) {
			if(ex.getSQLState().equals("X0Y32")) {
				return;
			}
			throw ex;
		}
		
		
		
		finally {
			ConnectionPool.getInstance().restoreConnection(connection);
		}
	}

}
