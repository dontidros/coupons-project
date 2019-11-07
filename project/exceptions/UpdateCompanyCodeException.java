package project.exceptions;

@SuppressWarnings("serial")
public class UpdateCompanyCodeException extends Exception {
	public String getMessage() {
		return "Do not update your company code";
	}

}
