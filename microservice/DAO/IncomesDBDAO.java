package ori.DAO;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ori.beans.Income;

@Repository
public class IncomesDBDAO {
	
	@Autowired
	private IncomeRepository incomeRepository;
	
	public ArrayList<Income> getAllIncomes(){
		return (ArrayList<Income>) incomeRepository.findAll();
	}
	
	public ArrayList<Income> getAllIncomesByCompanies(){
		return incomeRepository.findIncomesByClientType("company");
	}
	
	public ArrayList<Income> getAllIncomesByCustomers(){
		return incomeRepository.findIncomesByClientType("customer");
	}
	
	public ArrayList<Income> getIncomesBySpecificCompany(int companyId){
		return incomeRepository.findIncomesByClientTypeAndClientId("company", companyId);
	}
	
	public ArrayList<Income> getIncomesBySpecificCustomer(int customerId){
		return incomeRepository.findIncomesByClientTypeAndClientId("customer", customerId);
	}

	public Income storeIncome(Income income) {
		income.setId(0);
		incomeRepository.save(income);
		return income;
	}
	
	
	
}
