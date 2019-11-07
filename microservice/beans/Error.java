package ori.beans;

public class Error {
	private String message;
	private int status;
	public Error(String message, int status) {
		setMessage(message);
		setStatus(status);
	}
	public Error() {
		
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}
