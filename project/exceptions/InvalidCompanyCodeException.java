package project.exceptions;

@SuppressWarnings("serial")
public class InvalidCompanyCodeException extends Exception {
	public String getMessage() {
		return "Invalid company code";
	}

}
