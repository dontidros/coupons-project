package ori.DAO;

import java.util.ArrayList;


import org.springframework.data.repository.CrudRepository;

import ori.beans.Income;

public interface IncomeRepository extends CrudRepository<Income, Integer>{
	ArrayList<Income> findIncomesByClientType(String clientType);
	ArrayList<Income> findIncomesByClientTypeAndClientId(String clientType, int clientId);
}
