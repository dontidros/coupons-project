package ori.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ori.DAO.IncomesDBDAO;
import ori.beans.Error;
import ori.beans.Income;

@RestController
@RequestMapping("incomes")
public class IncomesController {
	
	@Autowired
	private IncomesDBDAO incomesDBDAO;
	
	@GetMapping
	public ResponseEntity<?> getAllIncomes(){
		try {
			return new ResponseEntity<>(incomesDBDAO.getAllIncomes(), HttpStatus.OK);
		}
		catch(Exception ex) {
			String message = ex.getMessage();
			if(message == null) {
				message = "";
			}
			Error error = new Error(message, HttpStatus.INTERNAL_SERVER_ERROR.value());
			return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("company")
	public ResponseEntity<?>getallIncomesByCompanies(){
		try {
			return new ResponseEntity<>(incomesDBDAO.getAllIncomesByCompanies(), HttpStatus.OK);
		}
		catch(Exception ex) {
			String message = ex.getMessage();
			if(message == null) {
				message = "";
			}
			Error error = new Error(message, HttpStatus.INTERNAL_SERVER_ERROR.value());
			return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("customer")
	public ResponseEntity<?> getAllIncomesByCustomers(){
		try {
			return new ResponseEntity<>(incomesDBDAO.getAllIncomesByCustomers(), HttpStatus.OK);
		}
		catch(Exception ex) {
			String message = ex.getMessage();
			if(message == null) {
				message = "";
			}
			Error error = new Error(message, HttpStatus.INTERNAL_SERVER_ERROR.value());
			return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("company/{companyId}")
	public ResponseEntity<?> getIncomesBySpecificCompany(@PathVariable("companyId")int companyId){
		try {
			return new ResponseEntity<>(incomesDBDAO.getIncomesBySpecificCompany(companyId), HttpStatus.OK);
		}
		catch(Exception ex) {
			String message = ex.getMessage();
			if(message == null) {
				message = "";
			}
			Error error = new Error(message, HttpStatus.INTERNAL_SERVER_ERROR.value());
			return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("customer/{customerId}")
	public ResponseEntity<?> getIncomesBySpecificCustomer(@PathVariable("customerId")int customerId){
		try {
			return new ResponseEntity<>(incomesDBDAO.getIncomesBySpecificCustomer(customerId), HttpStatus.OK);
		}
		catch(Exception ex) {
			String message = ex.getMessage();
			if(message == null) {
				message = "";
			}
			Error error = new Error(message, HttpStatus.INTERNAL_SERVER_ERROR.value());
			return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping
	public ResponseEntity<?> storeIncome(@RequestBody Income income){
		try {
			return new ResponseEntity<>(incomesDBDAO.storeIncome(income), HttpStatus.CREATED);
		}
		catch(Exception ex) {
			String message = ex.getMessage();
			if(message == null) {
				message = "";
			}
			Error error = new Error(message, HttpStatus.INTERNAL_SERVER_ERROR.value());
			return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
