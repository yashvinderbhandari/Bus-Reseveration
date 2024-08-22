package org.sm.reservationapi.service;

import java.util.Optional;

import org.sm.reservationapi.dao.AdminDao;
import org.sm.reservationapi.dto.AdminRequest;
import org.sm.reservationapi.dto.AdminResponse;
import org.sm.reservationapi.dto.EmailConfiguration;
import org.sm.reservationapi.dto.ResponseStrcture;
import org.sm.reservationapi.exception.AdminNotFoundException;
import org.sm.reservationapi.model.Admin;
import org.sm.reservationapi.util.ApplicationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class AdminService {
	@Autowired
	private AdminDao adminDao;
	@Autowired
	private ReservationApiMailService mailService;
	@Autowired
	private LinkGeneratorService linkGeneratorService;
	@Autowired
	private EmailConfiguration emailConfiguration;

	private Admin mapAdmin(AdminRequest adminRequest) {
		return Admin.builder().name(adminRequest.getName()).phone(adminRequest.getPhone())
				.email(adminRequest.getEmail()).gst_number(adminRequest.getGst_number())
				.travels_name(adminRequest.getTravels_name()).password(adminRequest.getPassword()).build();
	}

	private AdminResponse mapAdminResponse(Admin admin) {
		return AdminResponse.builder().id(admin.getId()).name(admin.getName()).phone(admin.getPhone())
				.email(admin.getEmail()).gst_number(admin.getGst_number()).travels_name(admin.getTravels_name())
				.build();
	}

	public String activate(String token) {
		Optional<Admin> admin = adminDao.findByToken(token);
		if (admin.isEmpty()) {
			throw new AdminNotFoundException("invalid Token!!");
		}
		Admin a = admin.get();
		a.setStatus("ACTIVE");
		a.setToken(null);
		adminDao.saveAdmin(a);
		return "Your Account has been activated";
	}

	public ResponseEntity<ResponseStrcture<AdminResponse>> add(AdminRequest adminRequest, HttpServletRequest request) {
		ResponseStrcture<AdminResponse> strcture = new ResponseStrcture<>();
		Admin admin = mapAdmin(adminRequest);
		admin.setStatus(ApplicationStatus.IN_ACTIVE.toString());
		admin = adminDao.saveAdmin(admin);
		String activation_link = linkGeneratorService.getActivateAdmin(admin, request);
		emailConfiguration.setSubject("Activate Your Account.");
		emailConfiguration.setText("Click here : " + activation_link);
		emailConfiguration.setToAddress(admin.getEmail());
		strcture.setData(mapAdminResponse(admin));
		strcture.setMessage(mailService.sendMail(emailConfiguration));
		strcture.setStatus(HttpStatus.CREATED.value());
		return ResponseEntity.status(HttpStatus.CREATED).body(strcture);
	}

	public ResponseEntity<ResponseStrcture<AdminResponse>> update(AdminRequest adminRequest, Integer id) {
		ResponseStrcture<AdminResponse> strcture = new ResponseStrcture<>();
		Optional<Admin> data = adminDao.findById(id);
		if (data.isPresent()) {
			Admin a = data.get();
			a.setId(id);
			a.setName(adminRequest.getName());
			a.setPhone(adminRequest.getPhone());
			a.setEmail(adminRequest.getEmail());
			a.setGst_number(adminRequest.getGst_number());
			a.setPassword(adminRequest.getPassword());
			a.setTravels_name(adminRequest.getTravels_name());
			strcture.setData(mapAdminResponse(adminDao.saveAdmin(a)));
			strcture.setMessage("Admin updated");
			strcture.setStatus(HttpStatus.ACCEPTED.value());
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(strcture);
		}
		throw new AdminNotFoundException("Invalid Admin Id!");
	}

	public ResponseEntity<ResponseStrcture<AdminResponse>> findAdmin(Integer id) {
		ResponseStrcture<AdminResponse> strcture = new ResponseStrcture<>();
		Optional<Admin> data = adminDao.findById(id);
		if (data.isPresent()) {
			strcture.setData(mapAdminResponse(data.get()));
			strcture.setMessage("Admin Found");
			strcture.setStatus(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(strcture);
		}
		throw new AdminNotFoundException("Invalid Admin Id!");
	}

	public ResponseEntity<ResponseStrcture<String>> deleteAdmin(Integer id) {
		ResponseStrcture<String> strcture = new ResponseStrcture<>();
		Optional<Admin> data = adminDao.findById(id);
		if (data.isPresent()) {
			strcture.setData("Admin id: " + id + " Deleted.");
			adminDao.deleteById(id);
			strcture.setMessage("Admin Deleted");
			strcture.setStatus(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(strcture);
		}
		throw new AdminNotFoundException("Invalid Admin Id!");
	}

	public ResponseEntity<ResponseStrcture<AdminResponse>> verifyAdmin(Long phone, String password) {
		ResponseStrcture<AdminResponse> strcture = new ResponseStrcture<>();
		Optional<Admin> data = adminDao.verifyByphone(phone, password);
		if (data.isPresent()) {
			Admin admin = data.get();
			if (admin.getStatus().equals(ApplicationStatus.IN_ACTIVE.toString())) {
				throw new IllegalStateException("Please verify your Email Address.");
			} else {
				strcture.setData(mapAdminResponse(data.get()));
				strcture.setMessage("Admin Verified");
				strcture.setStatus(HttpStatus.OK.value());
				return ResponseEntity.status(HttpStatus.OK).body(strcture);
			}
		}
		throw new AdminNotFoundException("Invalid credentials.");
	}

	public ResponseEntity<ResponseStrcture<AdminResponse>> verifyAdmin(String email, String password) {
		ResponseStrcture<AdminResponse> strcture = new ResponseStrcture<>();
		Optional<Admin> data = adminDao.verifyByEmail(email, password);
		if (data.isPresent()) {
			Admin admin = data.get();
			if (admin.getStatus().equals(ApplicationStatus.IN_ACTIVE.toString())) {
				throw new IllegalStateException("Please verify your email Address First.");
			} else {
				strcture.setData(mapAdminResponse(data.get()));
				strcture.setMessage("Admin Verified");
				strcture.setStatus(HttpStatus.OK.value());
				return ResponseEntity.status(HttpStatus.OK).body(strcture);
			}
		}
		throw new AdminNotFoundException("Invalid Credentials!!!");
	}

	public String forgotPassword(String email, HttpServletRequest request) {
		Optional<Admin> optionalAdmin = adminDao.findByEmail(email);
		if (optionalAdmin.isEmpty()) {
			throw new AdminNotFoundException("Invalid Email Address.");
		}
		Admin admin = optionalAdmin.get();
		String resetLink = linkGeneratorService.getResetPasswordLink(admin, request);
		emailConfiguration.setToAddress(email);
		emailConfiguration.setSubject("Reset Password Link.");
		emailConfiguration.setText("click this following link to reset your password " + resetLink);
		mailService.sendMail(emailConfiguration);
		return "Reset Link has been sent to your email please verify it";
	}

	public AdminResponse verifyLink(String token) {
		Optional<Admin> optionalAdmin = adminDao.findByToken(token);
		if (optionalAdmin.isEmpty()) {
			throw new AdminNotFoundException("Link Has been Expired or invalid");
		}
		Admin admin = optionalAdmin.get();
		admin.setToken(null);
		adminDao.saveAdmin(admin);
		return mapAdminResponse(admin);
	}

}
