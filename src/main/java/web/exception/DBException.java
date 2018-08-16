package web.exception;

public class DBException extends ApplicationException {
	
	private static final long serialVersionUID = 4255367271472981464L;

	public DBException() {
		super();
	}

	public DBException(String message, Throwable cause) {
		super(message, cause);
	}

}