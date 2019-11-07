package project.part8_services;

import java.time.LocalDate;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.simple.JSONObject;
import com.sun.jersey.spi.container.ResourceFilters;
import project.part2_beans.Category;
import project.part2_beans.Coupon;
import project.part2_beans.Income;
import project.part2_beans.IncomeType;
import project.part5_businessLogistics.CompanyFacade;
import project.part6_login.ClientType;
import project.part6_login.LoginManager;

@Path("company")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CompanyService {
	@Context
	private HttpServletRequest request;
	
	static {
		try {
			System.out.println("Trying to load the driver...");
			Class.forName("org.apache.derby.jdbc.ClientDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("Error in Class.forName: " + e.getMessage());
		}
	}
	

	
	
	@POST
	@Path("login")
	public Response login(LoginFields lg) {
		String json = "";
		try {
			LoginFields loginFields = new LoginFields();
			loginFields.setEmail(lg.getEmail());
			loginFields.setPassword(lg.getPassword());
			CompanyFacade companyFacade = (CompanyFacade) LoginManager.getInstance().login(ClientType.COMPANY, loginFields.getEmail(), loginFields.getPassword());
			String message = "";
			if(companyFacade != null) {
				HttpSession session = request.getSession();
				session.setAttribute("companyFacade", companyFacade);
				message = "login successfully";
				json = "{\"ok\": \"" + message + "\"}";
			}
			else {
				message = "login failed";
				json = "{\"ok\": \"" + message + "\"}";
			}
			return Response.ok(json).status(HttpServletResponse.SC_CREATED).build();
		}
		catch(Exception ex) {
			json = "{\"error\": \"" + ex.getMessage() + "\"}";
			return Response.serverError().entity(json).build();
		}
	}
	@GET
	@Path("")
	@ResourceFilters(LoginFilter.class)
	public Response getCompanyDetails() {
		String json = "";
		try {
			HttpSession session = request.getSession();
			CompanyFacade companyFacade = (CompanyFacade) session.getAttribute("companyFacade");
			json = companyFacade.getCompanyDetailsJSON();
			return Response.ok(json).status(HttpServletResponse.SC_OK).build();
		}
		catch(Exception ex) {
			json = "{\"error\": \"" + ex.getMessage() + "\"}";
			return Response.serverError().entity(json).build();
		}
	}
	
	@GET
	@Path("coupon")
	@ResourceFilters(LoginFilter.class)
	public Response getCompanyCoupons() {
		String json = "";
		try {
			HttpSession session = request.getSession();
			CompanyFacade companyFacade = (CompanyFacade) session.getAttribute("companyFacade");
			json = companyFacade.getCompanyCouponsJSON();
			return Response.ok(json).status(HttpServletResponse.SC_OK).build();
		}
		catch(Exception ex) {
			json = "{\"error\": \"" + ex.getMessage() + "\"}";
			return Response.serverError().entity(json).build();
		}
	}
	@GET
	@Path("/coupon-category/{category}")
	@ResourceFilters(LoginFilter.class)
	public Response getCompanyCouponsFromSpecificCategory(@PathParam("category")String category) {
		String json = "";
		try {
			HttpSession session = request.getSession();
			CompanyFacade companyFacade = (CompanyFacade) session.getAttribute("companyFacade");
			json = companyFacade.getCompanyCouponsFromSpecificCategoryJSON(Category.valueOf(category));
			return Response.ok(json).status(HttpServletResponse.SC_OK).build();
		}
		catch(Exception ex) {
			json = "{\"error\": \"" + ex.getMessage() + "\"}";
			return Response.serverError().entity(json).build();
		}
	}
	
	@GET
	@Path("/coupon-price/{maxPrice}")
	@ResourceFilters(LoginFilter.class)
	public Response getCompanyCouponsAtOrBelowTheMaximumPrice(@PathParam("maxPrice")double maxPrice) {
		String json = "";
		try {
			HttpSession session = request.getSession();
			CompanyFacade companyFacade = (CompanyFacade) session.getAttribute("companyFacade");
			json = companyFacade.getCompanyCouponsAtOrBelowTheMaximumPriceJSON(maxPrice);
			return Response.ok(json).status(HttpServletResponse.SC_OK).build();
		}
		catch(Exception ex) {
			json = "{\"error\": \"" + ex.getMessage() + "\"}";
			return Response.serverError().entity(json).build();
		}
	}
	@GET
	@Path("incomes")
	@ResourceFilters(LoginFilter.class)
	public Response getCompanyExpenses() {
		try {
			HttpSession session = request.getSession();
			CompanyFacade companyFacade = (CompanyFacade) session.getAttribute("companyFacade");
			int companyId = companyFacade.getCompanyId();
			String output = BusinessDelegate.getCompanyExpenses(companyId);
			return Response.ok(output).status(HttpServletResponse.SC_OK).build();
		}
		catch(Exception ex) {
			String json = "{\"error\": \"" + ex.getMessage() + "\"}";
			return Response.serverError().entity(json).build();
		}
	}
	
	@SuppressWarnings("unchecked")
	@POST
	@Path("coupon-add")
	@ResourceFilters(LoginFilter.class)
	public Response addCoupon(CouponProxy couponProxy) {
		String json = "";
		try {
			HttpSession session = request.getSession();
			CompanyFacade companyFacade = (CompanyFacade) session.getAttribute("companyFacade");
			Coupon addedCoupon = new Coupon(1,
											couponProxy.getCompanyId(),
											Category.valueOf(couponProxy.getCategory()),
											couponProxy.getTitle(),
											couponProxy.getDescription(),
											couponProxy.getStartDate(),
											couponProxy.getEndDate(),
											couponProxy.getAmount(),
											couponProxy.getPrice(),
											couponProxy.getImage());
			companyFacade.addCoupon(addedCoupon);
			
			ArrayList<Coupon> allCoupons = companyFacade.getCompanyCoupons();
			Coupon coupon = new Coupon();
			for(int i = 0; i < allCoupons.size(); i++) {
				if(allCoupons.get(i).getTitle().equals(addedCoupon.getTitle())) {
					coupon = allCoupons.get(i);
				}
			}
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
			
			json = jsonObject.toJSONString();
			
			String companyName = companyFacade.getCompanyDetails().getName();
			Income income = new Income("company", companyFacade.getCompanyId(), companyName, LocalDate.now(), IncomeType.COMPANY_NEW_COUPON, 100);
			JSONObject incomeJSONObject = new JSONObject();
			incomeJSONObject.put("clientType", income.getClientType());
			incomeJSONObject.put("clientId", income.getClientId());
			incomeJSONObject.put("name", income.getName());
			incomeJSONObject.put("date", income.getDate().toString());
			incomeJSONObject.put("description", income.getDescription().name());
			incomeJSONObject.put("amount", income.getAmount());
			String incomeJson = incomeJSONObject.toJSONString();
			BusinessDelegate.storeIncome(incomeJson);
			return Response.ok(json).status(HttpServletResponse.SC_CREATED).build();
			
			
		}
		catch(Exception ex) {
			json = "{\"error\": \"" + ex.getMessage() + "\"}";
			return Response.serverError().entity(json).build();
		}
	}
	
	@SuppressWarnings("unchecked")
	@PUT
	@Path("coupon-update")
	@ResourceFilters(LoginFilter.class)
	public Response updateCoupon(CouponProxy couponProxy) {
		String json = "";
		try {
			HttpSession session = request.getSession();
			CompanyFacade companyFacade = (CompanyFacade) session.getAttribute("companyFacade");
			Coupon updatedCoupon = new Coupon(couponProxy.getId(),
											  couponProxy.getCompanyId(),
											  Category.valueOf(couponProxy.getCategory()),
											  couponProxy.getTitle(),
											  couponProxy.getDescription(),
											  couponProxy.getStartDate(),
											  couponProxy.getEndDate(),
											  couponProxy.getAmount(),
											  couponProxy.getPrice(),
											  couponProxy.getImage());
			companyFacade.updateCoupon(updatedCoupon);
			
			ArrayList<Coupon> allCoupons = companyFacade.getCompanyCoupons();
			Coupon coupon = new Coupon();
			for(int i = 0; i < allCoupons.size(); i++) {
				if(allCoupons.get(i).getTitle().equals(updatedCoupon.getTitle())) {
					coupon = allCoupons.get(i);
				}
			}
			
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
			
			json = jsonObject.toJSONString();
			
			String companyName = companyFacade.getCompanyDetails().getName();
			Income income = new Income("company", companyFacade.getCompanyId(), companyName, LocalDate.now(), IncomeType.COMPANY_UPDATE_COUPON, 10);
			JSONObject incomeJSONObject = new JSONObject();
			incomeJSONObject.put("clientType", income.getClientType());
			incomeJSONObject.put("clientId", income.getClientId());
			incomeJSONObject.put("name", income.getName());
			incomeJSONObject.put("date", income.getDate().toString());
			incomeJSONObject.put("description", income.getDescription().name());
			incomeJSONObject.put("amount", income.getAmount());
			String incomeJson = incomeJSONObject.toJSONString();
			
			BusinessDelegate.storeIncome(incomeJson);
			return Response.ok(json).status(HttpServletResponse.SC_OK).build();
			
		}
		catch(Exception ex) {
			json = "{\"error\": \"" + ex.getMessage() + "\"}";
			return Response.serverError().entity(json).build();
		}
	}
	
	@DELETE
	@Path("/coupon/{couponId}")
	@ResourceFilters(LoginFilter.class)
	public Response deleteCoupon(@PathParam("couponId")int couponId) {
		String json = "";
		try {
			HttpSession session = request.getSession();
			CompanyFacade companyFacade = (CompanyFacade) session.getAttribute("companyFacade");
			companyFacade.deleteCoupon(couponId);
			String message = "delete succeeded";
			json = "{\"ok\": \"" + message + "\"}";
			return Response.ok(json).status(HttpServletResponse.SC_NO_CONTENT).build();
		}
		catch(Exception ex) {
			json = "{\"error\": \"" + ex.getMessage() + "\"}";
			return Response.serverError().entity(json).build();
		}
	}
	
	@GET
	@Path("logout")
	@ResourceFilters(LoginFilter.class)
	public Response logout() {
		String json = "";
		try {
			HttpSession session = request.getSession();
			session.invalidate();
			request.logout();
			String message = "logout successfully";
			json = "{\"ok\": \"" + message + "\"}";
			return Response.ok(json).status(HttpServletResponse.SC_OK).build();
		}
		catch(Exception ex) {
			json = "{\"error\": \"" + ex.getMessage() + "\"}";
			return Response.serverError().entity(json).build();
		}
	}
	
	
	
	
}
