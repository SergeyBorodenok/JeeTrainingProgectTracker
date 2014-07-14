package org.training.siarhei_baradzionak.exceptions;

public class ExceptionDAO extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExceptionDAO() {
		super();
	}

	public ExceptionDAO(String message, Throwable cause) {
		super(message, cause);
	}

	public ExceptionDAO(String message) {
		super(message);
	}

	public ExceptionDAO(Throwable cause) {
		super(cause);
	}
}
