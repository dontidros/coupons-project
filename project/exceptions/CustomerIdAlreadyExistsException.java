package project.exceptions;

@SuppressWarnings("serial")
public class CustomerIdAlreadyExistsException extends Exception {
	public String getMessage() {
		return "Customer id already exists";
	}

}
