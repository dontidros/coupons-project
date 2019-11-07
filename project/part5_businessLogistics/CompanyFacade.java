package project.part5_businessLogistics;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import project.exceptions.CompanyDoesNotExistException;
import project.exceptions.CouponAlreadyExistsException;
import project.exceptions.InvalidCompanyCodeException;
import project.exceptions.OtherCompanyCouponDeleteException;
import project.exceptions.UpdateCompanyCodeException;
import project.part2_beans.Category;
import project.part2_beans.Company;
import project.part2_beans.Coupon;
import project.part2_beans.Purchase;
import project.part4_DAO.CompaniesDBDAO;
import project.part4_DAO.CouponsDBDAO;
import project.part4_DAO.CustomersDBDAO;

public class CompanyFacade extends ClientFacade {
	private int companyID;
	public CompanyFacade() {
		companiesDAO = new CompaniesDBDAO();
		customersDAO = new CustomersDBDAO();
		couponsDAO = new CouponsDBDAO();
		setCompanyId(companyID);
	}
	public int getCompanyId() {
		
		return companyID;
	}
	public void setCompanyId(int companyID) {
		this.companyID = companyID;
	}
	
	/**
	 * allows a company to connect to the system 
	 * and access to companies business logistics functions.
	 */
	public boolean login(String email, String password)throws Exception {
		
		boolean isExist = companiesDAO.isCompanyExists(email, password);
		if (isExist == false) {
			throw new CompanyDoesNotExistException();
		}
		ArrayList<Company> allCompanies = companiesDAO.getAllCompanies();
		for(int i = 0; i < allCompanies.size();i++) {
			if(allCompanies.get(i).getEmail().equals(email) && allCompanies.get(i).getPassword().equals(password)) {
				setCompanyId(allCompanies.get(i).getId());
			}
		}
		return true;
		
	}
	/**
	 * adds a coupon to the system and verifies that the title 
	 * of the coupon is not the same as the title of another coupon
	 * of the company that already exists in the system.
	 * In addition, it makes sure that the new coupon code
	 * is identical to the company code that adds it.
	 * 
	 * 
	 */
	public void addCoupon(Coupon coupon)throws Exception {
		int companyID = getCompanyId();
		ArrayList<Coupon> allCoupons = couponsDAO.getAllCouponsMadeByOneCompany(companyID);
		for(int i = 0; i < allCoupons.size(); i++) {
			if(allCoupons.get(i).getTitle().equals(coupon.getTitle())) {
				throw new CouponAlreadyExistsException();
			}
		}
		if(coupon.getCompanyId() != companyID) {
			throw new InvalidCompanyCodeException();
		}
		couponsDAO.addCoupon(coupon);
	}
	/**
	 * updates the details of a coupon that exists in the system
	 * and before the update makes sure that the company code
	 * remains the same.
	 * 
	 * 
	 */
	public void updateCoupon(Coupon coupon)throws Exception{

		Coupon tester = couponsDAO.getOneCoupon(coupon.getId());
		if(tester.getCompanyId() != coupon.getCompanyId()) {
			throw new UpdateCompanyCodeException();
		}
		tester.setCategory(coupon.getCategory());
		tester.setTitle(coupon.getTitle());
		tester.setDescription(coupon.getDescription());
		tester.setStartDate(coupon.getStartDate());
		tester.setEndDate(coupon.getEndDate());
		tester.setAmount(coupon.getAmount());
		tester.setPrice(coupon.getPrice());
		tester.setImage(coupon.getImage());
		
		couponsDAO.updateCoupon(tester);
	}
	/**
	 * deletes a coupon and also deletes its purchases records
	 * (Before that it verifies that the coupon really belongs to the company that is trying to delete it).
	 * 
	 * 
	 */
	public void deleteCoupon(int couponID)throws Exception {
		Coupon coupon = couponsDAO.getOneCoupon(couponID);
		if(coupon.getCompanyId() != getCompanyId()) {
			throw new OtherCompanyCouponDeleteException();
		}
		ArrayList<Purchase> couponPurchases = couponsDAO.getOneCouponPurchases(couponID);
		for(int i = 0; i < couponPurchases.size(); i++) {
			couponsDAO.deleteCouponPurchase(couponPurchases.get(i).getCustomerId(), couponPurchases.get(i).getCouponId());
		}
		couponsDAO.deleteCoupon(couponID);
		
	}
	/**
	 * gets company's coupons
	 * 
	 * 
	 */
	public ArrayList<Coupon> getCompanyCoupons()throws Exception{
		int companyID = getCompanyId();
		ArrayList<Coupon> allCompanyCoupons = couponsDAO.getAllCouponsMadeByOneCompany(companyID);
		return allCompanyCoupons;
		
		
	}
	/**
	 * gets company's coupons from specific category
	 * 
	 * 
	 * 
	 */
	public ArrayList<Coupon> getCompanyCouponsFromSpecificCategory(Category category)throws Exception{
		int companyID = getCompanyId();
		ArrayList<Coupon> allCompanyCoupons = couponsDAO.getAllCouponsMadeByOneCompany(companyID);
		ArrayList<Coupon> allCompanyCouponsFromSpecificCategory = new ArrayList<Coupon>();
		for (int i = 0; i < allCompanyCoupons.size(); i++) {
			if(allCompanyCoupons.get(i).getCategory().equals(category)) {
			
				allCompanyCouponsFromSpecificCategory.add(allCompanyCoupons.get(i));
			}
		}
		return allCompanyCouponsFromSpecificCategory;

	}
	/**
	 * gets company's coupons at or below the maximum price
	 * (that is set by the company as a parameter).
	 * 
	 * 
	 * 
	 */
	public ArrayList<Coupon> getCompanyCouponsAtOrBelowTheMaximumPrice(double maxPrice)throws Exception{
		
		int companyID = getCompanyId();
		ArrayList<Coupon> allCompanyCoupons = couponsDAO.getAllCouponsMadeByOneCompany(companyID);
		
		ArrayList<Coupon> AllCompanyCouponsAtOrBelowTheMaximumPrice = new ArrayList<>();
		for (int i = 0; i < allCompanyCoupons.size(); i++) {
			if(allCompanyCoupons.get(i).getPrice() <= maxPrice) {
				AllCompanyCouponsAtOrBelowTheMaximumPrice.add(allCompanyCoupons.get(i));
				
			}
		}
		
		return AllCompanyCouponsAtOrBelowTheMaximumPrice;
		
	}
	/**
	 * gets company's details
	 * 
	 * 
	 */
	public Company getCompanyDetails()throws Exception {
		Company company = companiesDAO.getOneCompany(getCompanyId());
		return company;
	}
	/**
	 * displays all company's coupons
	 * 
	 */
	public void allCompanyCouponsDisplay()throws Exception {
		ArrayList<Coupon> allCompanyCoupons = getCompanyCoupons();
		for(int i = 0; i < allCompanyCoupons.size(); i++) {
			allCompanyCoupons.get(i).display();
		}
	}
	/**
	 * displays company's coupons from specific category
	 * 
	 * 
	 */
	public void companyCouponsFromSpecificCategoryDisplay(Category category)throws Exception{
		ArrayList<Coupon> companyCouponsFromSpecificCategory = getCompanyCouponsFromSpecificCategory(category);
		for(int i = 0; i < companyCouponsFromSpecificCategory.size(); i++) {
			companyCouponsFromSpecificCategory.get(i).display();
		}
	}
	/**
	 * displays company's coupons at or below the maximum price
	 * (that set by the company as a parameter).
	 * 
	 * 
	 */
	public void companyCouponsAtOrBelowTheMaximumPriceDisplay(double price)throws Exception{
		ArrayList<Coupon> companyCouponsAtOrBelowTheMaximumPrice = getCompanyCouponsAtOrBelowTheMaximumPrice(price);
		for(int i = 0; i < companyCouponsAtOrBelowTheMaximumPrice.size(); i++) {
			companyCouponsAtOrBelowTheMaximumPrice.get(i).display();
		}
	}
	/**
	 * 
	 * displays one coupon
	 * 
	 */
	public void oneCouponDisplay(int couponID)throws Exception{
		Coupon coupon = couponsDAO.getOneCoupon(couponID);
		coupon.display();
	}
	/**
	 * displays company's details
	 * 
	 */
	public void companyDetailsDisplay()throws Exception{
		Company company = getCompanyDetails();
		company.display();
	}
	
	/**
	 * a JSON version of getCompanyDetails
	 */
	@SuppressWarnings("unchecked")
	public String getCompanyDetailsJSON()throws Exception{
		Company company = getCompanyDetails();
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
	 * a JSON version of getCompanyCoupons
	 */
	@SuppressWarnings("unchecked")
	public String getCompanyCouponsJSON()throws Exception {
		ArrayList<Coupon> allCompanyCoupons = getCompanyCoupons();
		JSONArray jsonArray = new JSONArray();
		for(int i = 0; i < allCompanyCoupons.size(); i++) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", allCompanyCoupons.get(i).getId());
			jsonObject.put("companyId", allCompanyCoupons.get(i).getCompanyId());
			jsonObject.put("category", allCompanyCoupons.get(i).getCategory().name());
			jsonObject.put("title", allCompanyCoupons.get(i).getTitle());
			jsonObject.put("description", allCompanyCoupons.get(i).getDescription());
			jsonObject.put("startDate", allCompanyCoupons.get(i).getStartDate());
			jsonObject.put("endDate", allCompanyCoupons.get(i).getEndDate());
			jsonObject.put("amount", allCompanyCoupons.get(i).getAmount());
			jsonObject.put("price", allCompanyCoupons.get(i).getPrice());
			jsonObject.put("image", allCompanyCoupons.get(i).getImage());
			jsonArray.add(jsonObject);
		}
		String json = jsonArray.toJSONString();
		return json;
	}
	
	/**
	 * a JSON version of getCompanyCouponsFromSpecificCategory
	 */
	@SuppressWarnings("unchecked")
	public String getCompanyCouponsFromSpecificCategoryJSON(Category category)throws Exception{
		ArrayList<Coupon> companyCouponsFromSpecificCategory = getCompanyCouponsFromSpecificCategory(category);
		JSONArray jsonArray = new JSONArray();
		for(int i = 0; i < companyCouponsFromSpecificCategory.size(); i++) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", companyCouponsFromSpecificCategory.get(i).getId());
			jsonObject.put("companyId", companyCouponsFromSpecificCategory.get(i).getCompanyId());
			jsonObject.put("category", companyCouponsFromSpecificCategory.get(i).getCategory().name());
			jsonObject.put("title", companyCouponsFromSpecificCategory.get(i).getTitle());
			jsonObject.put("description", companyCouponsFromSpecificCategory.get(i).getDescription());
			jsonObject.put("startDate", companyCouponsFromSpecificCategory.get(i).getStartDate());
			jsonObject.put("endDate", companyCouponsFromSpecificCategory.get(i).getEndDate());
			jsonObject.put("amount", companyCouponsFromSpecificCategory.get(i).getAmount());
			jsonObject.put("price", companyCouponsFromSpecificCategory.get(i).getPrice());
			jsonObject.put("image", companyCouponsFromSpecificCategory.get(i).getImage());
			jsonArray.add(jsonObject);
		}
		String json = jsonArray.toJSONString();
		return json;
	}
	
	/**
	 * a JSON version of getCompanyCouponsAtOrBelowTheMaximumPrice
	 */
	@SuppressWarnings("unchecked")
	public String getCompanyCouponsAtOrBelowTheMaximumPriceJSON(double maxPrice)throws Exception{
		ArrayList<Coupon> companyCouponsAtOrBelowTheMaximumPrice = getCompanyCouponsAtOrBelowTheMaximumPrice(maxPrice);
		JSONArray jsonArray = new JSONArray();
		for(int i = 0; i < companyCouponsAtOrBelowTheMaximumPrice.size(); i++) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", companyCouponsAtOrBelowTheMaximumPrice.get(i).getId());
			jsonObject.put("companyId", companyCouponsAtOrBelowTheMaximumPrice.get(i).getCompanyId());
			jsonObject.put("category", companyCouponsAtOrBelowTheMaximumPrice.get(i).getCategory().name());
			jsonObject.put("title", companyCouponsAtOrBelowTheMaximumPrice.get(i).getTitle());
			jsonObject.put("description", companyCouponsAtOrBelowTheMaximumPrice.get(i).getDescription());
			jsonObject.put("startDate", companyCouponsAtOrBelowTheMaximumPrice.get(i).getStartDate());
			jsonObject.put("endDate", companyCouponsAtOrBelowTheMaximumPrice.get(i).getEndDate());
			jsonObject.put("amount", companyCouponsAtOrBelowTheMaximumPrice.get(i).getAmount());
			jsonObject.put("price", companyCouponsAtOrBelowTheMaximumPrice.get(i).getPrice());
			jsonObject.put("image", companyCouponsAtOrBelowTheMaximumPrice.get(i).getImage());
			jsonArray.add(jsonObject);
		}
		String json = jsonArray.toJSONString();
		return json;
	}
	
}
