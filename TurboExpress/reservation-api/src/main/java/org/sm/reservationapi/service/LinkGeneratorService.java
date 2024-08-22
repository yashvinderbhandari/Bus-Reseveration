package org.sm.reservationapi.service;

import org.sm.reservationapi.dao.AdminDao;
import org.sm.reservationapi.dao.UserDao;
import org.sm.reservationapi.model.Admin;
import org.sm.reservationapi.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import net.bytebuddy.utility.RandomString;
import static org.sm.reservationapi.util.ApplicationConstants.ADMIN_VERIFY_LINK;
import static org.sm.reservationapi.util.ApplicationConstants.USER_VERIFY_LINK;
import static org.sm.reservationapi.util.ApplicationConstants.ADMIN_RESET_PASSWORD_LINK;
import static org.sm.reservationapi.util.ApplicationConstants.USER_RESET_PASSWORD_LINK;

@Service
public class LinkGeneratorService {
	@Autowired
	private AdminDao adminDao;
	@Autowired
	private UserDao userDao;

	public String getActivateAdmin(Admin admin, HttpServletRequest request) {
		admin.setToken(RandomString.make(200));
		adminDao.saveAdmin(admin);
		String url = request.getRequestURL().toString();
		return url.replace(request.getServletPath(), ADMIN_VERIFY_LINK + admin.getToken());
	}

	public String getActivateUser(User user, HttpServletRequest request) {
		user.setToken(RandomString.make(200));
		userDao.saveUser(user);
		String url = request.getRequestURL().toString();
		return url.replace(request.getServletPath(), USER_VERIFY_LINK + user.getToken());
	}

	public String getResetPasswordLink(Admin admin, HttpServletRequest request) {
		admin.setToken(RandomString.make(90));
		adminDao.saveAdmin(admin);
		String url = request.getRequestURL().toString();
		return url.replace(request.getServletPath(), ADMIN_RESET_PASSWORD_LINK) + admin.getToken();
	}

	public String getResetPasswordLink(User user, HttpServletRequest request) {
		user.setToken(RandomString.make(88));
		userDao.saveUser(user);
		String url = request.getRequestURL().toString();
		return url.replace(request.getServletPath(), USER_RESET_PASSWORD_LINK) + user.getToken();
	}
}
