package project.exceptions;

@SuppressWarnings("serial")
public class CompanyDoesNotExistException extends Exception {
	public String getMessage() {
		return "Company does not exist";
	}
}
