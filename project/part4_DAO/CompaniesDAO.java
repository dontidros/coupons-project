package project.part4_DAO;

import java.sql.SQLException;
import java.util.ArrayList;

import project.part2_beans.Company;
import project.part2_beans.Coupon;

public interface CompaniesDAO {
	boolean isCompanyExists(String email, String password) throws SQLException, InterruptedException;
	void addCompany(Company company) throws SQLException, InterruptedException;
	void updateCompany(Company company) throws SQLException, InterruptedException;
	void deleteCompany(int companyID) throws SQLException, InterruptedException;
	ArrayList<Company> getAllCompanies() throws SQLException, InterruptedException;
	Company getOneCompany(int companyID) throws SQLException, InterruptedException;
	ArrayList<Coupon> getCouponsByCompanyId(int companyID) throws SQLException, InterruptedException;
	
	

}
