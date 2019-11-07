package project.part8_services;

import java.time.LocalDate;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
import project.part5_businessLogistics.CustomerFacade;
import project.part6_login.ClientType;
import project.part6_login.LoginManager;

@Path("customer")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CustomerService {
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
			CustomerFacade customerFacade = (CustomerFacade) LoginManager.getInstance().login(ClientType.CUSTOMER, loginFields.getEmail(), loginFields.getPassword());
			String message = "";
			if(customerFacade != null) {
				HttpSession session = request.getSession();
				session.setAttribute("customerFacade", customerFacade);
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
	public Response getCustomerDetails() {

		String json = "";
		try {
			HttpSession session = request.getSession();
			CustomerFacade customerFacade = (CustomerFacade) session.getAttribute("customerFacade");
			json = customerFacade.getCustomerDetailsJSON();
			return Response.ok(json).status(HttpServletResponse.SC_OK).build();
		}
		catch(Exception ex) {
			json = "{\"error\": \"" + ex.getMessage() + "\"}";
			return Response.serverError().entity(json).build();
		}
	}
	
	@GET
	@Path("coupon-all")
	@ResourceFilters(LoginFilter.class)
	public Response getAllCoupons() {
		String json = "";
		try {
			HttpSession session = request.getSession();
			CustomerFacade customerFacade = (CustomerFacade) session.getAttribute("customerFacade");
			json = customerFacade.getAllCouponsJSON();
			return Response.ok(json).status(HttpServletResponse.SC_OK).build();
		}
		catch(Exception ex) {
			json = "{\"error\": \"" + ex.getMessage() + "\"}";
			return Response.serverError().entity(json).build();
		}
	}
	@GET
	@Path("/coupon-one/{couponId}")
	@ResourceFilters(LoginFilter.class)
	public Response getOneCoupon(@PathParam("couponId")int id) {
		String json = "";
		try {
			HttpSession session = request.getSession();
			CustomerFacade customerFacade = (CustomerFacade) session.getAttribute("customerFacade");
			json = customerFacade.getOneCouponJSON(id);
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
	public Response getCustomerCoupons() {
		String json = "";
		try {
			HttpSession session = request.getSession();
			CustomerFacade customerFacade = (CustomerFacade) session.getAttribute("customerFacade");
			json = customerFacade.getCustomerCouponsJSON();
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
	public Response getCustomerCouponsFromSpecificCategory(@PathParam("category")String category) {
		String json = "";
		try {
			HttpSession session = request.getSession();
			CustomerFacade customerFacade = (CustomerFacade) session.getAttribute("customerFacade");
			json = customerFacade.getCustomerCouponsFromSpecificCategoryJSON(Category.valueOf(category));
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
	public Response getCustomerCouponsAtOrBelowTheMaximumPrice(@PathParam("maxPrice")double maxPrice) {
		String json = "";
		try {
			HttpSession session = request.getSession();
			CustomerFacade customerFacade = (CustomerFacade) session.getAttribute("customerFacade");
			json = customerFacade.getCustomerCouponsAtOrBelowTheMaximumPriceJSON(maxPrice);
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
	public Response getCustomerExpenses() {
		try {
			HttpSession session = request.getSession();
			CustomerFacade customerFacade = (CustomerFacade) session.getAttribute("customerFacade");
			int customerId = customerFacade.getCustomerId();
			String output = BusinessDelegate.getCustomerExpenses(customerId);
			return Response.ok(output).status(HttpServletResponse.SC_OK).build();
		}
		catch(Exception ex) {
			String json = "{\"error\": \"" + ex.getMessage() + "\"}";
			return Response.serverError().entity(json).build();
		}
	}

	
	
	@SuppressWarnings("unchecked")
	@GET
	@Path("/coupon-purchase/{couponId}")
	@ResourceFilters(LoginFilter.class)
	public Response purchaseCoupon(@PathParam("couponId")int id) {
		String json = "";
		try {
			HttpSession session = request.getSession();
			CustomerFacade customerFacade = (CustomerFacade) session.getAttribute("customerFacade");
			Coupon purchasedCoupon = customerFacade.getOneCoupon(id);
			customerFacade.purchaseCoupon(purchasedCoupon);
			ArrayList<Coupon> allCoupons = customerFacade.getCustomerCoupons();
			Coupon coupon = new Coupon();
			for(int i = 0; i < allCoupons.size(); i++) {
				if(allCoupons.get(i).getId() == purchasedCoupon.getId()) {
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
			String customerFullName = customerFacade.getCustomerDetails().getFirstName() + " " + customerFacade.getCustomerDetails().getLastName();
			Income income = new Income("customer", customerFacade.getCustomerId(), customerFullName, LocalDate.now(), IncomeType.CUSTOMER_PURCHASE, coupon.getPrice() );
			
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
