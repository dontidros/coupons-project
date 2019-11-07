
package project.exceptions;
	
@SuppressWarnings("serial")
public class CompanyNameAlreadyExistsException extends Exception{
	public String getMessage() {
		return "Company name already exists.";
	}
}
