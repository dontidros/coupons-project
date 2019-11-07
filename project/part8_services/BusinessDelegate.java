package project.part8_services;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * A class that links the services of this project to the microservice
 * 
 *
 */
public class BusinessDelegate {
	public static synchronized String getAllIncomes() {
		try {
			Client client = Client.create();
			WebResource webResource = client.resource("http://localhost:8888/incomes");
			ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
			if(response.getStatus() >= 300) {
				throw new RuntimeException("field: http status code: " + response.getStatus());
			}
			String output = response.getEntity(String.class);
			return output;
		}
		catch(Exception ex) {
			return null;
		}
	}
	
	public static synchronized String getAllIncomesByCompanies() {
		try {
			Client client = Client.create();
			WebResource webResource = client.resource("http://localhost:8888/incomes/company");
			ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
			if(response.getStatus() >= 300) {
				throw new RuntimeException("field: http status code: " + response.getStatus());
			}
			String output = response.getEntity(String.class);
			return output;
		}
		catch(Exception ex) {
			return null;
		}
	}
	
	public static synchronized String getAllIncomesByCustomers() {
		try {
			Client client = Client.create();
			WebResource webResource = client.resource("http://localhost:8888/incomes/customer");
			ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
			if(response.getStatus() >= 300) {
				throw new RuntimeException("field: http status code: " + response.getStatus());
			}
			String output = response.getEntity(String.class);
			return output;
		}
		catch(Exception ex) {
			return null;
		}
	}
	
	public static synchronized String getIncomesBySpecificCompany(int companyId) {
		try {
			Client client = Client.create();
			WebResource webResource = client.resource("http://localhost:8888/incomes/company/" + companyId);
			ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
			if(response.getStatus() >= 300) {
				throw new RuntimeException("field: http status code: " + response.getStatus());
			}
			String output = response.getEntity(String.class);
			return output;
		}
		catch(Exception ex) {
			return null;
		}
	}
	
	public static synchronized String getIncomesBySpecificCustomer(int customerId) {
		try {
			Client client = Client.create();
			WebResource webResource = client.resource("http://localhost:8888/incomes/customer/" + customerId);
			ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
			if(response.getStatus() >= 300) {
				throw new RuntimeException("field: http status code: " + response.getStatus());
			}
			String output = response.getEntity(String.class);
			return output;
		}
		catch(Exception ex) {
			return null;
		}
	}
	
	public static synchronized void storeIncome(String incomeJson) {
		try {
			Client client = Client.create();
			WebResource webResource = client.resource("http://localhost:8888/incomes");
			ClientResponse response = webResource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, incomeJson);
			if(response.getStatus() >= 300) {
				throw new RuntimeException("faild: http error code: " + response.getStatus());
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public static synchronized String getCompanyExpenses(int companyId) {
		try {
			Client client = Client.create();
			WebResource webResource = client.resource("http://localhost:8888/incomes/company/" + companyId);
			ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
			if(response.getStatus() >= 300) {
				throw new RuntimeException("field: http status code: " + response.getStatus());
			}
			String output = response.getEntity(String.class);
			return output;
		}
		catch(Exception ex) {
			return null;
		}
	}
	
	public static synchronized String getCustomerExpenses(int customerId) {
		try {
			Client client = Client.create();
			WebResource webResource = client.resource("http://localhost:8888/incomes/customer/" + customerId);
			ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
			if(response.getStatus() >= 300) {
				throw new RuntimeException("field: http status code: " + response.getStatus());
			}
			String output = response.getEntity(String.class);
			return output;
		}
		catch(Exception ex) {
			return null;
		}
	}
	
	
	
}
