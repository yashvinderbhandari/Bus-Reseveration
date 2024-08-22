package org.sm.reservationapi.exception;

@SuppressWarnings("serial")
public class BusNotFoundException extends RuntimeException {

	public BusNotFoundException(String message) {
		super(message);
	}

}
