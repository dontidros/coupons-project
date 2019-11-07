package project.part8_services;


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
import project.part2_beans.Company;
import project.part2_beans.Customer;
import project.part5_businessLogistics.AdminFacade;
import project.part6_login.ClientType;
import project.part6_login.LoginManager;

@Path("admin")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AdminService {
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
				AdminFacade adminFacade = (AdminFacade) LoginManager.getInstance().login(ClientType.ADMINISTRATOR, loginFields.getEmail(), loginFields.getPassword());

				String message = "";
				if(adminFacade != null) {
					HttpSession session =request.getSession();
					session.setAttribute("adminFacade", adminFacade);
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
	@Path("company")
	@ResourceFilters(LoginFilter.class)
	public Response getAllCompanies() {

		String json = "";
		try {
			HttpSession session = request.getSession();
			AdminFacade adminFacade = (AdminFacade) session.getAttribute("adminFacade");
			json = adminFacade.getAllCompaniesJSON();
			System.out.println(json);
			return Response.ok(json).status(HttpServletResponse.SC_OK).build();
		}
		catch(Exception ex) {
			json = "{\"error\": \"" + ex.getMessage() + "\"}";
			return Response.serverError().entity(json).build();
		}
		
	}

	@GET
	@Path("/company/{companyId}")
	@ResourceFilters(LoginFilter.class)
	public Response getOneCompany(@PathParam("companyId")int id) {
		String json = "";
		try {
			HttpSession session = request.getSession();
			AdminFacade adminFacade = (AdminFacade) session.getAttribute("adminFacade");
			json = adminFacade.getOneCompanyJSON(id);
			return Response.ok(json).status(HttpServletResponse.SC_OK).build();
		}
		catch(Exception ex) {
			json = "{\"error\": \"" + ex.getMessage() + "\"}";
			return Response.serverError().entity(json).build();
		}
	}
	@GET
	@Path("customer")
	@ResourceFilters(LoginFilter.class)
	public Response getAllCustomers() {
		String json = "";
		try {
			HttpSession session = request.getSession();
			AdminFacade adminFacade = (AdminFacade) session.getAttribute("adminFacade");
			json = adminFacade.getAllCustomersJSON();
			return Response.ok(json).status(HttpServletResponse.SC_OK).build();
		}
		catch(Exception ex) {
			json = "{\"error\": \"" + ex.getMessage() + "\"}";
			return Response.serverError().entity(json).build();
		}
	}

	@GET
	@Path("/customer/{customerId}")
	@ResourceFilters(LoginFilter.class)
	public Response getAllCustomers(@PathParam("customerId")int id) {
		String json = "";
		try {
			HttpSession session = request.getSession();
			AdminFacade adminFacade = (AdminFacade) session.getAttribute("adminFacade");
			json = adminFacade.getOneCustomerJSON(id);
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
	public Response getAllIncomes() {
		try {
			String output = BusinessDelegate.getAllIncomes();
			return Response.ok(output).status(HttpServletResponse.SC_OK).build();
		}
		catch(Exception ex) {
			String json = "{\"error\": \"" + ex.getMessage() + "\"}";
			return Response.serverError().entity(json).build();
		}
	}
	
	@GET
	@Path("incomes-company")
	@ResourceFilters(LoginFilter.class)
	public Response getAllIncomesByCompanies() {
		try {
			String output = BusinessDelegate.getAllIncomesByCompanies();
			return Response.ok(output).status(HttpServletResponse.SC_OK).build();
		}
		catch(Exception ex) {
			String json = "{\"error\": \"" + ex.getMessage() + "\"}";
			return Response.serverError().entity(json).build();
		}
	}
	@GET
	@Path("incomes-customer")
	@ResourceFilters(LoginFilter.class)
	public Response getAllIncomesByCustomers() {
		try {
			String output = BusinessDelegate.getAllIncomesByCustomers();
			return Response.ok(output).status(HttpServletResponse.SC_OK).build();
		}
		catch(Exception ex) {
			String json = "{\"error\": \"" + ex.getMessage() + "\"}";
			return Response.serverError().entity(json).build();
		}
	}
	@GET
	@Path("incomes-company/{companyId}")
	@ResourceFilters(LoginFilter.class)
	public Response getIncomesBySpecificCompany(@PathParam("companyId")int companyId) {
		try {
			String output = BusinessDelegate.getIncomesBySpecificCompany(companyId);
			return Response.ok(output).status(HttpServletResponse.SC_OK).build();
		}
		catch(Exception ex) {
			String json = "{\"error\": \"" + ex.getMessage() + "\"}";
			return Response.serverError().entity(json).build();
		}
	}
	@GET
	@Path("incomes-customer/{customerId}")
	@ResourceFilters(LoginFilter.class)
	public Response getIncomesBySpecificCustomer(@PathParam("customerId")int customerId) {
		try {
			String output = BusinessDelegate.getIncomesBySpecificCustomer(customerId);
			return Response.ok(output).status(HttpServletResponse.SC_OK).build();
		}
		catch(Exception ex) {
			String json = "{\"error\": \"" + ex.getMessage() + "\"}";
			return Response.serverError().entity(json).build();
		}
	}
	
	@SuppressWarnings("unchecked")
	@POST
	@Path("company-add")
	@ResourceFilters(LoginFilter.class)
	public Response addCompany(CompanyProxy companyProxy) {
		String json = "";
		try {
			HttpSession session = request.getSession();
			AdminFacade adminFacade = (AdminFacade) session.getAttribute("adminFacade");
			Company addedCompany = new Company(1,
										  companyProxy.getName(),
										  companyProxy.getEmail(),
										  companyProxy.getPassword(),
										  null);
			adminFacade.addCompany(addedCompany);
			
			ArrayList<Company> allCompanies = adminFacade.getAllCompanies();
			Company company = new Company();
			for(int i = 0; i < allCompanies.size(); i++) {
				if(allCompanies.get(i).getEmail().equals(addedCompany.getEmail()) && allCompanies.get(i).getPassword().equals(addedCompany.getPassword())) {
					company = allCompanies.get(i);
				}
			}
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", company.getId());
			jsonObject.put("name", company.getName());
			jsonObject.put("email", company.getEmail());
			jsonObject.put("password", company.getPassword());
			jsonObject.put("coupons", company.getCouponsJSON());
			
			json = jsonObject.toJSONString();
			return Response.ok(json).status(HttpServletResponse.SC_CREATED).build();
			
		}
		catch(Exception ex) {
			json = "{\"error\": \"" + ex.getMessage() + "\"}";
			return Response.serverError().entity(json).build();
		}
	}
	
	@SuppressWarnings("unchecked")
	@PUT
	@Path("company-update")
	@ResourceFilters(LoginFilter.class)
	public Response updateCompany(CompanyProxy companyProxy) {
		String json = "";
		try {
			HttpSession session = request.getSession();
			AdminFacade adminFacade = (AdminFacade) session.getAttribute("adminFacade");
			Company updatedCompany = new Company(companyProxy.getId(),
												 companyProxy.getName(),
												 companyProxy.getEmail(),
												 companyProxy.getPassword(),
												 companyProxy.getCoupons());
			adminFacade.updateCompany(updatedCompany);
			
			ArrayList<Company> allCompanies = adminFacade.getAllCompanies();
			Company company = new Company();
			for(int i = 0; i < allCompanies.size(); i++) {
				if(allCompanies.get(i).getEmail().equals(updatedCompany.getEmail()) && allCompanies.get(i).getPassword().equals(updatedCompany.getPassword())) {
					company = allCompanies.get(i);
				}
			}
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", company.getId());
			jsonObject.put("name", company.getName());
			jsonObject.put("email", company.getEmail());
			jsonObject.put("password", company.getPassword());
			jsonObject.put("coupons", company.getCouponsJSON());
			
			json = jsonObject.toJSONString();
			return Response.ok(json).status(HttpServletResponse.SC_OK).build();
			
		}
		catch(Exception ex) {
			json = "{\"error\": \"" + ex.getMessage() + "\"}";
			return Response.serverError().entity(json).build();
		}
	}
	@SuppressWarnings("unchecked")
	@POST
	@Path("customer-add")
	@ResourceFilters(LoginFilter.class)
	public Response addCustomer(CustomerProxy customerProxy) {
		String json = "";
		try {
			HttpSession session = request.getSession();
			AdminFacade adminFacade = (AdminFacade) session.getAttribute("adminFacade");
			Customer addedCustomer = new Customer(1,
												  customerProxy.getFirstName(),
												  customerProxy.getLastName(),
												  customerProxy.getEmail(),
												  customerProxy.getPassword(),
												  null);
			
			adminFacade.addCustomer(addedCustomer);
			
			ArrayList<Customer> allCustomers = adminFacade.getAllCustomers();
			Customer customer = new Customer();
			for(int i = 0; i < allCustomers.size(); i++) {
				if(allCustomers.get(i).getEmail().equals(addedCustomer.getEmail()) && allCustomers.get(i).getPassword().equals(addedCustomer.getPassword())) {
					customer = allCustomers.get(i);
				}
			}
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", customer.getId());
			jsonObject.put("firstName", customer.getFirstName());
			jsonObject.put("lastName", customer.getLastName());
			jsonObject.put("email", customer.getEmail());
			jsonObject.put("password", customer.getPassword());
			jsonObject.put("coupons", customer.getCouponsJSON());
			
			json = jsonObject.toJSONString();
			return Response.ok(json).status(HttpServletResponse.SC_CREATED).build();
			
		}
		catch(Exception ex) {
			json = "{\"error\": \"" + ex.getMessage() + "\"}";
			return Response.serverError().entity(json).build();
		}
	}
	@SuppressWarnings("unchecked")
	@PUT
	@Path("customer-update")
	@ResourceFilters(LoginFilter.class)
	public Response updateCustomer(CustomerProxy customerProxy) {
		String json = "";
		try {
			HttpSession session = request.getSession();
			AdminFacade adminFacade = (AdminFacade) session.getAttribute("adminFacade");
			Customer updatedCustomer = new Customer(customerProxy.getId(),
													customerProxy.getFirstName(),
													customerProxy.getLastName(),
													customerProxy.getEmail(),
													customerProxy.getPassword(),
													customerProxy.getCoupons());
			adminFacade.updateCustomer(updatedCustomer);
			
			ArrayList<Customer> allCustomers = adminFacade.getAllCustomers();
			Customer customer = new Customer();
			for(int i = 0; i < allCustomers.size(); i++) {
				if(allCustomers.get(i).getEmail().equals(updatedCustomer.getEmail()) && allCustomers.get(i).getPassword().equals(updatedCustomer.getPassword())) {
					customer = allCustomers.get(i);
				}
			}
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", customer.getId());
			jsonObject.put("firstName", customer.getFirstName());
			jsonObject.put("lastName", customer.getLastName());
			jsonObject.put("email", customer.getEmail());
			jsonObject.put("password", customer.getPassword());
			jsonObject.put("coupons", customer.getCouponsJSON());
			
			json = jsonObject.toJSONString();
			return Response.ok(json).status(HttpServletResponse.SC_OK).build();
			
		}
		catch(Exception ex) {
			json = "{\"error\": \"" + ex.getMessage() + "\"}";
			return Response.serverError().entity(json).build();
		}
	}
	
	@DELETE
	@Path("/company/{companyId}")
	@ResourceFilters(LoginFilter.class)
	public Response deleteCompany(@PathParam("companyId")int companyId) {
		String json = "";
		try {
			HttpSession session = request.getSession();
			AdminFacade adminFacade = (AdminFacade) session.getAttribute("adminFacade");
			adminFacade.deleteCompany(companyId);
			String message = "delete succeeded";
			json = "{\"ok\": \"" + message + "\"}";
			return Response.ok(json).status(HttpServletResponse.SC_NO_CONTENT).build();
		}
		catch(Exception ex) {
			json = "{\"error\": \"" + ex.getMessage() + "\"}";
			return Response.serverError().entity(json).build();
		}
	}
	
	@DELETE
	@Path("/customer/{customerId}")
	@ResourceFilters(LoginFilter.class)
	public Response deleteCustomer(@PathParam("customerId")int customerId) {
		String json = "";
		try {
			HttpSession session = request.getSession();
			AdminFacade adminFacade = (AdminFacade) session.getAttribute("adminFacade");
			adminFacade.deleteCustomer(customerId);
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
