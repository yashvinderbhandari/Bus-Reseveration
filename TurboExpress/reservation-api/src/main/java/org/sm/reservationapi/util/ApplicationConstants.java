package org.sm.reservationapi.util;

public interface ApplicationConstants {
	public String ADMIN_VERIFY_LINK="/api/admins/activate?token=";
	public String USER_VERIFY_LINK="/api/users/activate?token=";
	public String ADMIN_RESET_PASSWORD_LINK="/api/admins/verify-link?token=";
	public String USER_RESET_PASSWORD_LINK="/api/users/verify-link?token=";
} 
