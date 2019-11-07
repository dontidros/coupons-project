package project.part5_businessLogistics;

import project.part4_DAO.CompaniesDAO;
import project.part4_DAO.CouponsDAO;
import project.part4_DAO.CustomersDAO;

public abstract class ClientFacade {
	protected CompaniesDAO companiesDAO;
	protected CustomersDAO customersDAO;
	protected CouponsDAO couponsDAO;
	
	public abstract boolean login(String email, String password) throws Exception;
	

}
