package project.part6_login;

import project.part5_businessLogistics.AdminFacade;
import project.part5_businessLogistics.ClientFacade;
import project.part5_businessLogistics.CompanyFacade;
import project.part5_businessLogistics.CustomerFacade;

public class LoginManager  {
	

	
	private LoginManager() {

	}
	private static LoginManager instance = new LoginManager();
	
	public static LoginManager getInstance() {
		return instance;
	}
	/**
	 * enables each type of client to connect to the system and receive 
	 * the business logistic that is adapted to him/her.
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	public ClientFacade login (ClientType clientType, String email, String password)throws Exception {
		ClientFacade client;
		if (clientType == ClientType.ADMINISTRATOR) {
			client = new AdminFacade();
			if(client.login(email, password)) {
				return client;
			}

		}
		if (clientType == ClientType.COMPANY) {
			client = new CompanyFacade();
			
			if(client.login(email, password)) {
				return client;
			}

		}
		if (clientType == ClientType.CUSTOMER) {
			client = new CustomerFacade();
			if(client.login(email, password)) {
				return client;
			}

		}
		return null;	
			

		
	}

}
