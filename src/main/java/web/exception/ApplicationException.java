package web.exception;

public class ApplicationException extends Exception {
	
	private static final long serialVersionUID = -1969494470565169699L;

	public ApplicationException() {
		super();
	}

	public ApplicationException(String message, Throwable cause) {
		super(message, cause);
	}

	public ApplicationException(String message) {
		super(message);
	}
}