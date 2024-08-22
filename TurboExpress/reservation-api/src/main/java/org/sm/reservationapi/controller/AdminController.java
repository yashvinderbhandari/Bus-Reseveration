package org.sm.reservationapi.controller;

import java.io.IOException;

import org.sm.reservationapi.dto.AdminRequest;
import org.sm.reservationapi.dto.AdminResponse;
import org.sm.reservationapi.dto.ResponseStrcture;
import org.sm.reservationapi.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/api/admins")
public class AdminController {
	@Autowired
	private AdminService adminService;

	@PostMapping
	public ResponseEntity<ResponseStrcture<AdminResponse>> addAdmin(@Valid @RequestBody AdminRequest adminRequest,
			HttpServletRequest request) {
		return adminService.add(adminRequest, request);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ResponseStrcture<AdminResponse>> updateAdmin(@RequestBody AdminRequest adminRequest,
			@PathVariable(name = "id") Integer id) {
		return adminService.update(adminRequest, id);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ResponseStrcture<AdminResponse>> findAdmin(@PathVariable(name = "id") Integer id) {
		return adminService.findAdmin(id);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStrcture<String>> deleteAdmin(@PathVariable(name = "id") Integer id) {
		return adminService.deleteAdmin(id);
	}

	@PostMapping("/verify-by-email")
	public ResponseEntity<ResponseStrcture<AdminResponse>> verifyAdmin(@RequestParam String email,
			@RequestParam String password) {
		return adminService.verifyAdmin(email, password);
	}

	@PostMapping("/verify-by-phone")
	public ResponseEntity<ResponseStrcture<AdminResponse>> verifyAdmin(@RequestParam Long phone,
			@RequestParam String password) {
		return adminService.verifyAdmin(phone, password);
	}

	@GetMapping("/activate")
	public String accountActivate(@RequestParam String token) {
		return adminService.activate(token);
	}

	@PostMapping("/verify-email")
	public String verifyEmailAddress(@RequestParam String email, HttpServletRequest request) {
		return adminService.forgotPassword(email, request);
	}

	@PostMapping("/forgot-password")
	public String forgotPassword(@RequestParam String email, HttpServletRequest request) {
		return adminService.forgotPassword(email, request);
	}

	@GetMapping("/verify-link")
	public void verifyPassword(@RequestParam String token, HttpServletRequest request, HttpServletResponse response) {
		AdminResponse adminResponse = adminService.verifyLink(token);
		if (adminResponse != null)
			try {
				HttpSession session = request.getSession();
				session.setAttribute("admin", adminResponse);
				response.addCookie(new Cookie("admin", adminResponse.getEmail()));
				response.sendRedirect("http://localhost:3000/resetpassword");
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

}
