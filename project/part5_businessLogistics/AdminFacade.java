package project.part5_businessLogistics;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import project.exceptions.CompanyEmailAlreadyExistsException;
import project.exceptions.CompanyNameAlreadyExistsException;
import project.exceptions.CustomerEmailAlreadyExistsException;
import project.exceptions.UpdateCompanyNameException;
import project.part2_beans.Company;
import project.part2_beans.Coupon;
import project.part2_beans.Customer;
import project.part2_beans.Purchase;
import project.part4_DAO.CompaniesDBDAO;
import project.part4_DAO.CouponsDBDAO;
import project.part4_DAO.CustomersDBDAO;

public class AdminFacade extends ClientFacade {
	public AdminFacade() {
		companiesDAO = new CompaniesDBDAO();
		customersDAO = new CustomersDBDAO();
		couponsDAO = new CouponsDBDAO();
	}
	
	/**
	 * allows an administrator to connect to the system 
	 * and gives him access to functions included in his business logistics
	 */
	public boolean login(String email, String password) {
		return email.equals("admin@admin.com") && password.equals("admin"); 
	}
	/**
	 *  adds a new company to the system. 
	 * (before adding it verifies that the name or e-mail
	 *  of the new company is not identical to other names or e-mails
	 *  that already exists in the system).
	 * 
	 * 
	 */
	public void addCompany(Company company)throws Exception{
		ArrayList<Company> allCompanies = companiesDAO.getAllCompanies();
		for (int i = 0; i < allCompanies.size(); i++) {
			if (allCompanies.get(i).getName().equals(company.getName())) {
				throw new CompanyNameAlreadyExistsException();
			}
			if(allCompanies.get(i).getEmail().equals(company.getEmail())) {
				throw new CompanyEmailAlreadyExistsException();
			}
		}
		companiesDAO.addCompany(company);
	}
	/**
	 * updates the details of a company that already exists in the system
	 *   
	 * (and does not allow the administrator to change its code or name).
	 * 
	 * 
	 */
	public void updateCompany(Company company)throws Exception{
		Company tester = companiesDAO.getOneCompany(company.getId());
		tester.setEmail(company.getEmail());
		if (!(tester.getName().equals(company.getName()))) {
			throw new UpdateCompanyNameException();
		}
		tester.setPassword(company.getPassword());
		tester.setCoupons(company.getCoupons());
		companiesDAO.updateCompany(tester);
	}
	/**
	 * deletes:
	 * one company
	 * all coupons made by that company
	 * all company's coupons purchases documentation
	 * 
	 * 
	 */
	public void deleteCompany(int companyID) throws Exception {
		
		ArrayList<Coupon> companyCoupons = couponsDAO.getAllCouponsMadeByOneCompany(companyID);
		ArrayList<Integer> companyCouponsId = new ArrayList<>();
		for (int i = 0; i < companyCoupons.size(); i++) {
			companyCouponsId.add(companyCoupons.get(i).getId());
		}
		


		ArrayList<Purchase> companyCouponsPurchases = new ArrayList<>();
		for (int i = 0; i < companyCouponsId.size(); i++) {
			companyCouponsPurchases.addAll(couponsDAO.getOneCouponPurchases(companyCouponsId.get(i)));
		}

		
		for (int i = 0; i < companyCouponsPurchases.size(); i++ ) {
			couponsDAO.deleteCouponPurchase(companyCouponsPurchases.get(i).getCustomerId(), companyCouponsPurchases.get(i).getCouponId());
		}
		for (int i = 0; i < companyCouponsId.size(); i++) {
			couponsDAO.deleteCoupon(companyCouponsId.get(i));
		}
		companiesDAO.deleteCompany(companyID);
	}
	/**
	 * gets all companies
	 * 
	 * 
	 */
	public ArrayList<Company> getAllCompanies() throws Exception{
		System.out.println(11);
		ArrayList<Company> allCompanies = companiesDAO.getAllCompanies();
		return allCompanies;
	}
	/**
	 * gets one company
	 * 
	 * 
	 * 
	 */
	public Company getOneCompany(int companyID) throws Exception{
		Company company = companiesDAO.getOneCompany(companyID);
		return company;
	}
	/**
	 * adds a new customer to the system and makes sure before
	 * adding that his or her e-mail address is not identical
	 * to another customer e-mail address that already exists
	 * in the system
	 * 
	 * 
	 */
	public void addCustomer(Customer customer)throws Exception{
		ArrayList<Customer> allCustomers = customersDAO.getAllCustomers();
		for (int i = 0; i < allCustomers.size(); i++) {
			if(allCustomers.get(i).getEmail().equals(customer.getEmail())) {
				throw new CustomerEmailAlreadyExistsException();
			}
		}
		customersDAO.addCustomer(customer);
	}
	/**
	 * updates the details of a customer that already exists
	 * in the system and does not allow the administrator 
	 * to change his or her code
	 * 
	 * 
	 */
	public void updateCustomer(Customer customer)throws Exception{

		Customer tester = customersDAO.getOneCustomer(customer.getId());
		tester.setFirstName(customer.getFirstName());
		tester.setLastName(customer.getLastName());
		tester.setEmail(customer.getEmail());
		tester.setPassword(customer.getPassword());
		tester.setCoupons(customer.getCoupons());
		customersDAO.updateCustomer(tester);
		
	}
	/**
	 * deletes one customer and also deletes 
	 * his or her purchases documentation.
	 * 
	 * 
	 */
	public void deleteCustomer(int customerID)throws Exception{
		
		ArrayList<Integer> customerCoupons = customersDAO.getCustomerCouponsIdByCustomerId(customerID);
		
		for(int i = 0; i < customerCoupons.size(); i++) {
			couponsDAO.deleteCouponPurchase(customerID, customerCoupons.get(i));
		}
		customersDAO.deleteCustomer(customerID);
		
		
	}
	/**
	 * gets all customers
	 * 
	 * 
	 */
	public ArrayList<Customer> getAllCustomers()throws Exception{
		ArrayList<Customer> allCustomers = new ArrayList<>();
		allCustomers = customersDAO.getAllCustomers();
		return allCustomers;
	}
	/**
	 * gets one customer
	 * 
	 * 
	 * 
	 */
	public Customer getOneCustomer(int customerID)throws Exception{
		Customer customer = customersDAO.getOneCustomer(customerID);
		return customer;
		
	}
	/**
	 * gets all purchases
	 * 
	 * 
	 */
	public ArrayList<Purchase> getAllPurchases()throws Exception{
		ArrayList<Purchase> allPurchases = couponsDAO.getAllCouponsPurchases();
		return allPurchases;
	}
	/**
	 * gets one coupon purchases
	 * 
	 * 
	 * 
	 */
	public ArrayList<Purchase> getOneCouponPurchases(int couponID)throws Exception{
		ArrayList<Purchase> oneCouponPurchases = couponsDAO.getOneCouponPurchases(couponID);
		return oneCouponPurchases;
	}
	
	
	/**
	 * displays all companies details
	 * 
	 */
	public void allCompaniesDisplay()throws Exception {
		ArrayList<Company> allCompanies = getAllCompanies();
		for (int i = 0; i < allCompanies.size(); i++) {
			allCompanies.get(i).display();
		}
	}
	/**
	 * displays one company details
	 * 
	 * 
	 */
	public void oneCompanyDisplay(int companyID)throws Exception{
		Company company = companiesDAO.getOneCompany(companyID);
		company.display();
	}
	/**
	 * displays all customers details
	 * 
	 */
	public void allCustomersDisplay()throws Exception{
		ArrayList<Customer> allCustomers = getAllCustomers();
		for (int i = 0; i < allCustomers.size(); i++) {
			allCustomers.get(i).display();
		}
	}
	/**
	 * displays one customer details
	 * 
	 * 
	 */
	public void oneCustomerDisplay(int customerID)throws Exception{
		Customer customer = customersDAO.getOneCustomer(customerID);
		customer.display();
	}
	/**
	 * displays the documentation of all coupons purchases
	 *
	 */
	public void allPurchasesDisplay()throws Exception{
		ArrayList<Purchase> allPurchases = getAllPurchases();
		for (int i = 0; i < allPurchases.size(); i++) {
			allPurchases.get(i).display();
		}
	}
	/**
	 * displays the documentation of one coupon purchases
	 * 
	 * 
	 */
	public void oneCouponPurchasesDisplay(int couponID)throws Exception{
		ArrayList<Purchase> oneCouponPurchases = couponsDAO.getOneCouponPurchases(couponID);
		for(int i = 0; i < oneCouponPurchases.size(); i++) {
			oneCouponPurchases.get(i).display();
		}
	}
	/**
	 * displays all coupons details
	 * 
	 */
	public void allCouponsDisplay()throws Exception{
		ArrayList<Coupon> allCoupons = couponsDAO.getAllCoupons();
		for (int i = 0; i < allCoupons.size(); i++) {
			allCoupons.get(i).display();
			
		}
	}
	/**
	 * a JSON version of getAllCompanies
	 */
	@SuppressWarnings("unchecked")
	public String getAllCompaniesJSON()throws Exception{
		System.out.println(1);
		ArrayList<Company> allCompanies = getAllCompanies();
		System.out.println(2);
		JSONArray jsonArray = new JSONArray();
		for(int i = 0; i < allCompanies.size(); i++) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", allCompanies.get(i).getId());
			jsonObject.put("name", allCompanies.get(i).getName());
			jsonObject.put("email", allCompanies.get(i).getEmail());
			jsonObject.put("password", allCompanies.get(i).getPassword());
			jsonObject.put("coupons", allCompanies.get(i).getCouponsJSON());
			jsonArray.add(jsonObject);
		}
		String json = jsonArray.toJSONString();
		return json;
	}
	
	/**
	 * a JSON version of get one company 
	 */
	@SuppressWarnings("unchecked")
	public String getOneCompanyJSON(int companyID)throws Exception {
		
		Company company = getOneCompany(companyID);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("id", company.getId());
		jsonObject.put("name", company.getName());
		jsonObject.put("email", company.getEmail());
		jsonObject.put("password", company.getPassword());
		jsonObject.put("coupons", company.getCouponsJSON());
		String json = jsonObject.toJSONString();
		return json;
	}
	
	/**
	 * a JSON version of getAllCustomers
	 */
	@SuppressWarnings("unchecked")
	public String getAllCustomersJSON()throws Exception {
		ArrayList<Customer> allCustomers = getAllCustomers();
		JSONArray jsonArray = new JSONArray();
		for(int i = 0; i < allCustomers.size(); i++) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", allCustomers.get(i).getId());
			jsonObject.put("firstName", allCustomers.get(i).getFirstName());
			jsonObject.put("lastName", allCustomers.get(i).getLastName());
			jsonObject.put("email", allCustomers.get(i).getEmail());
			jsonObject.put("password", allCustomers.get(i).getPassword());
			jsonObject.put("coupons", allCustomers.get(i).getCouponsJSON());
			jsonArray.add(jsonObject);
		}
		String json = jsonArray.toJSONString();
		return json;
	}
	
	/**
	 * a JSON version of getOneCustomer
	 */
	@SuppressWarnings("unchecked")
	public String getOneCustomerJSON(int customerID)throws Exception{
		Customer customer = getOneCustomer(customerID);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("id", customer.getId());
		jsonObject.put("firstName", customer.getFirstName());
		jsonObject.put("lastName", customer.getLastName());
		jsonObject.put("email", customer.getEmail());
		jsonObject.put("password", customer.getPassword());
		jsonObject.put("coupons", customer.getCouponsJSON());
		String json = jsonObject.toJSONString();
		return json;
	}
	public String testing() {
		String s = "{\"a\": 1, \"b\": 2 }";
		return s;
	}
}