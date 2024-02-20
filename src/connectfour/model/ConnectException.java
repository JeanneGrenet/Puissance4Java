package connectfour.model;

public class ConnectException extends Exception {

	private static final long serialVersionUID = 1L;

	public ConnectException (String error) {
		super(error);
	}
}
