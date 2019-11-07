package project.exceptions;

@SuppressWarnings("serial")
public class UpdateCompanyNameException extends Exception {
	public String getMessage() {
		return "Do not update company name";
	}
	

}
