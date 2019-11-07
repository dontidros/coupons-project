package project.exceptions;

@SuppressWarnings("serial")
public class CompanyIdAlreadyExistsException extends Exception {
	public String getMessage() {
		return "Company id already exists.";
	}

}
