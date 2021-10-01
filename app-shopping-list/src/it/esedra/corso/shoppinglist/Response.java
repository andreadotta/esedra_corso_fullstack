package it.esedra.corso.shoppinglist;

public class Response<T> {

	private T response;
	private String status;
	private String message;

	public static enum StatusType {
		ok, ko
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(StatusType status) {
		this.status = status.name();
	}

	public T getResponse() {
		return response;
	}

	public void setResponse(T response) {
		this.response = response;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
