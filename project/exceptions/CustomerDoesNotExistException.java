package project.exceptions;

@SuppressWarnings("serial")
public class CustomerDoesNotExistException extends Exception {
	public String getMessage() {
		return "Customer does not exist.";
	}

}
