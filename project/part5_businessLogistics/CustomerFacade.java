package project.part5_businessLogistics;

import java.time.LocalDate;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import project.exceptions.CouponAlreadyBoughtException;
import project.exceptions.CouponEndDateHasPassedException;
import project.exceptions.CouponOutOfStockException;
import project.exceptions.CustomerDoesNotExistException;
import project.part2_beans.Category;
import project.part2_beans.Coupon;
import project.part2_beans.Customer;
import project.part4_DAO.CompaniesDBDAO;
import project.part4_DAO.CouponsDBDAO;
import project.part4_DAO.CustomersDBDAO;

public class CustomerFacade extends ClientFacade {
	private int customerID;
	public CustomerFacade() {
		companiesDAO = new CompaniesDBDAO();
		customersDAO = new CustomersDBDAO();
		couponsDAO = new CouponsDBDAO();
		
		setCustomerId(customerID);
	}
	public int getCustomerId() {
		return customerID;
		
	}
	public void setCustomerId(int customerID) {
		this.customerID = customerID;
	}
	/**
	 * allows a customer to connect to the system 
	 * and access to customers business logistics functions
	 */
	public boolean login(String email, String password) throws Exception {
		boolean isExist = customersDAO.isCustomerExists(email, password);
		if(isExist == false) {
			throw new CustomerDoesNotExistException();
		}
		ArrayList<Customer> allCustomers = customersDAO.getAllCustomers();
		for (int i = 0; i < allCustomers.size(); i++) {
			if(allCustomers.get(i).getEmail().equals(email) && allCustomers.get(i).getPassword().equals(password)) {
				setCustomerId(allCustomers.get(i).getId());
			}
		}
		return true;
	}
	/**
	 * allows customer to purchase a coupon but before that verifies:
	 * 1. that the same coupon was not previously purchased by the same customer.
	 * 2. that the coupon is in stock.
	 * 3. that the expiration date of the coupon has not yet arrived.
	 * this function also takes care of lowering the amount of the coupon by 1 after the purchase.
	 */
	public void purchaseCoupon(Coupon coupon)throws Exception {
		ArrayList<Integer> customerCoupons = customersDAO.getCustomerCouponsIdByCustomerId(getCustomerId());
		for(int i = 0; i < customerCoupons.size(); i++) {
			if(customerCoupons.get(i) == coupon.getId()) {
				throw new CouponAlreadyBoughtException();
			}
		}
		int couponAmount = coupon.getAmount();
		if(couponAmount <= 0) {
			throw new CouponOutOfStockException();
		}
		String couponEndDate = coupon.getEndDate();
		LocalDate endDate = LocalDate.parse(couponEndDate);
		LocalDate localDate = LocalDate.now();

		if(localDate.isAfter(endDate)) {
		    throw new CouponEndDateHasPassedException();
		}
		
		couponsDAO.addCouponPurchase(getCustomerId(), coupon.getId());

		Coupon tester = couponsDAO.getOneCoupon(coupon.getId());
		int amount = tester.getAmount();
		tester.setAmount(amount-1);
		couponsDAO.updateCoupon(tester);
		
	}
	/**
	 * gets customer's coupons
	 * 
	 * 
	 */
	
	
	public ArrayList<Coupon> getCustomerCoupons()throws Exception{
		ArrayList<Coupon> customerCoupons = new ArrayList<>();
		ArrayList<Integer> customerCouponsId = customersDAO.getCustomerCouponsIdByCustomerId(getCustomerId());
		for (int i = 0; i < customerCouponsId.size(); i++) {
			customerCoupons.add(couponsDAO.getOneCoupon(customerCouponsId.get(i)));
			
		}
		return customerCoupons;
	}
	/**
	 * gets customer's coupons from specific category
	 * 
	 * 
	 * 
	 */
	public ArrayList<Coupon> getCustomerCouponsFromSpecificCategory(Category category)throws Exception{
		ArrayList<Coupon> customerCoupons = getCustomerCoupons();
		ArrayList<Coupon> customerCouponsFromSpecificCategory = new ArrayList<>();
		for (int i = 0; i < customerCoupons.size(); i++) {
			if(customerCoupons.get(i).getCategory().equals(category)) {
				customerCouponsFromSpecificCategory.add(customerCoupons.get(i));
			}
		}
		return customerCouponsFromSpecificCategory;
	}
	/**
	 * gets customer's coupons at or below the maximum price
	 * (that is set by the customer as a parameter).
	 * 
	 * 
	 * 
	 */
	public ArrayList<Coupon> getCustomerCouponsAtOrBelowTheMaximumPrice(double maxPrice)throws Exception{
		ArrayList<Coupon> customerCoupons = getCustomerCoupons();
		ArrayList<Coupon> customerCouponsAtOrBelowTheMaximumPrice = new ArrayList<>();
		for (int i = 0; i < customerCoupons.size(); i++) {
			if(customerCoupons.get(i).getPrice() <= maxPrice) {
				customerCouponsAtOrBelowTheMaximumPrice.add(customerCoupons.get(i));
				
			}
		}
		return customerCouponsAtOrBelowTheMaximumPrice;
	}
	/**
	 * 
	 * gets customer details
	 * 
	 */
	public Customer getCustomerDetails()throws Exception {
		Customer customer = customersDAO.getOneCustomer(getCustomerId());
		return customer;
	}
	
	/**
	 * gets customer's coupons and displays them
	 * 
	 */
	public void customersCouponsDisplay()throws Exception{
		ArrayList<Coupon> customerCoupons = getCustomerCoupons();
		for(int i = 0; i < customerCoupons.size(); i++) {
			customerCoupons.get(i).display();
		}
	}
	/**
	 * 
	 * displays customer's coupons from specific category
	 * 
	 */
	public void customerCouponsFromSpecificCategoryDisplay(Category category)throws Exception{
		ArrayList<Coupon> customerCouponsFromSpecificCategory = getCustomerCouponsFromSpecificCategory(category);
		for (int i = 0; i < customerCouponsFromSpecificCategory.size(); i++) {
			customerCouponsFromSpecificCategory.get(i).display();
		}
	}
	/**
	 * displays customer's coupons at or below some maximum price
	 * 
	 * 
	 */
	public void customerCouponsAtOrBelowTheMaximumPriceDisplay(double maxPrice)throws Exception{
		ArrayList<Coupon> customerCouponsAtOrBelowTheMaximumPrice = getCustomerCouponsAtOrBelowTheMaximumPrice(maxPrice);
		for (int i = 0; i < customerCouponsAtOrBelowTheMaximumPrice.size(); i++) {
			customerCouponsAtOrBelowTheMaximumPrice.get(i).display();
		}
	}
	/**
	 * displays customer's details
	 * 
	 */
	public void customerDetailsDisplay()throws Exception{
		Customer customer = getCustomerDetails();
		customer.display();
	}
	
	/**
	 * a JSON version of getCustomerDetails
	 */
	@SuppressWarnings("unchecked")
	public String getCustomerDetailsJSON()throws Exception {
		Customer customer = getCustomerDetails();
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
	
	/**
	 * a JSON version of getCustomerCoupons
	 */
	@SuppressWarnings("unchecked")
	public String getCustomerCouponsJSON()throws Exception{
		ArrayList<Coupon> customerCoupons = getCustomerCoupons();
		JSONArray jsonArray = new JSONArray();
		for(int i = 0; i < customerCoupons.size(); i++) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", customerCoupons.get(i).getId());
			jsonObject.put("companyId", customerCoupons.get(i).getCompanyId());
			jsonObject.put("category", customerCoupons.get(i).getCategory().name());
			jsonObject.put("title", customerCoupons.get(i).getTitle());
			jsonObject.put("description", customerCoupons.get(i).getDescription());
			jsonObject.put("startDate", customerCoupons.get(i).getStartDate());
			jsonObject.put("endDate", customerCoupons.get(i).getEndDate());
			jsonObject.put("amount", customerCoupons.get(i).getAmount());
			jsonObject.put("price", customerCoupons.get(i).getPrice());
			jsonObject.put("image", customerCoupons.get(i).getImage());
			jsonArray.add(jsonObject);
		}
		String json = jsonArray.toJSONString();
		return json;
	}
	
	/**
	 * a JSON version of getCustomerCouponsFromSpecificCategory
	 */
	@SuppressWarnings("unchecked")
	public String getCustomerCouponsFromSpecificCategoryJSON(Category category)throws Exception{
		ArrayList<Coupon> customerCouponsFromSpecificCategory = getCustomerCouponsFromSpecificCategory(category);
		JSONArray jsonArray = new JSONArray();
		for(int i = 0; i < customerCouponsFromSpecificCategory.size(); i++) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", customerCouponsFromSpecificCategory.get(i).getId());
			jsonObject.put("companyId", customerCouponsFromSpecificCategory.get(i).getCompanyId());
			jsonObject.put("category", customerCouponsFromSpecificCategory.get(i).getCategory().name());
			jsonObject.put("title", customerCouponsFromSpecificCategory.get(i).getTitle());
			jsonObject.put("description", customerCouponsFromSpecificCategory.get(i).getDescription());
			jsonObject.put("startDate", customerCouponsFromSpecificCategory.get(i).getStartDate());
			jsonObject.put("endDate", customerCouponsFromSpecificCategory.get(i).getEndDate());
			jsonObject.put("amount", customerCouponsFromSpecificCategory.get(i).getAmount());
			jsonObject.put("price", customerCouponsFromSpecificCategory.get(i).getPrice());
			jsonObject.put("image", customerCouponsFromSpecificCategory.get(i).getImage());
			jsonArray.add(jsonObject);
		}
		String json = jsonArray.toJSONString();
		return json;
	}
	
	/**
	 * a JSON version of getCustomerCouponsAtOrBelowTheMaximumPrice
	 */
	@SuppressWarnings("unchecked")
	public String getCustomerCouponsAtOrBelowTheMaximumPriceJSON(double maxPrice)throws Exception{
		ArrayList<Coupon> customerCouponsAtOrBelowTheMaximumPrice = getCustomerCouponsAtOrBelowTheMaximumPrice(maxPrice);
		JSONArray jsonArray = new JSONArray();
		for(int i = 0; i < customerCouponsAtOrBelowTheMaximumPrice.size(); i++) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", customerCouponsAtOrBelowTheMaximumPrice.get(i).getId());
			jsonObject.put("companyId", customerCouponsAtOrBelowTheMaximumPrice.get(i).getCompanyId());
			jsonObject.put("category", customerCouponsAtOrBelowTheMaximumPrice.get(i).getCategory().name());
			jsonObject.put("title", customerCouponsAtOrBelowTheMaximumPrice.get(i).getTitle());
			jsonObject.put("description", customerCouponsAtOrBelowTheMaximumPrice.get(i).getDescription());
			jsonObject.put("startDate", customerCouponsAtOrBelowTheMaximumPrice.get(i).getStartDate());
			jsonObject.put("endDate", customerCouponsAtOrBelowTheMaximumPrice.get(i).getEndDate());
			jsonObject.put("amount", customerCouponsAtOrBelowTheMaximumPrice.get(i).getAmount());
			jsonObject.put("price", customerCouponsAtOrBelowTheMaximumPrice.get(i).getPrice());
			jsonObject.put("image", customerCouponsAtOrBelowTheMaximumPrice.get(i).getImage());
			jsonArray.add(jsonObject);
		}
		String json = jsonArray.toJSONString();
		return json;
	}
	
	public ArrayList<Coupon> getAllCoupons() throws Exception{
		ArrayList<Coupon> allCoupons = couponsDAO.getAllCoupons();
		return allCoupons;
	}
	@SuppressWarnings("unchecked")
	public String getAllCouponsJSON() throws Exception{
		ArrayList<Coupon> allCoupons = getAllCoupons();
		JSONArray jsonArray = new JSONArray();
		for(int i = 0; i < allCoupons.size(); i++) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", allCoupons.get(i).getId());
			jsonObject.put("companyId", allCoupons.get(i).getCompanyId());
			jsonObject.put("category", allCoupons.get(i).getCategory().name());
			jsonObject.put("title", allCoupons.get(i).getTitle());
			jsonObject.put("description", allCoupons.get(i).getDescription());
			jsonObject.put("startDate", allCoupons.get(i).getStartDate());
			jsonObject.put("endDate", allCoupons.get(i).getEndDate());
			jsonObject.put("amount", allCoupons.get(i).getAmount());
			jsonObject.put("price", allCoupons.get(i).getPrice());
			jsonObject.put("image", allCoupons.get(i).getImage());
			jsonArray.add(jsonObject);
		}
		String json = jsonArray.toJSONString();
		return json;
	}
	public Coupon getOneCoupon(int couponID)throws Exception{
		Coupon coupon = couponsDAO.getOneCoupon(couponID);
		return coupon;
	}
	@SuppressWarnings("unchecked")
	public String getOneCouponJSON(int couponID)throws Exception{
		Coupon coupon = getOneCoupon(couponID);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("id", coupon.getId());
		jsonObject.put("companyId", coupon.getCompanyId());
		jsonObject.put("category", coupon.getCategory().name());
		jsonObject.put("title", coupon.getTitle());
		jsonObject.put("description", coupon.getDescription());
		jsonObject.put("startDate", coupon.getStartDate());
		jsonObject.put("endDate", coupon.getEndDate());
		jsonObject.put("amount", coupon.getAmount());
		jsonObject.put("price", coupon.getPrice());
		jsonObject.put("image", coupon.getImage());
		String json = jsonObject.toJSONString();
		return json;
	}
	
}
