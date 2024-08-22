package org.sm.reservationapi.controller;

import org.sm.reservationapi.dto.ResponseStrcture;
import org.sm.reservationapi.dto.UserRequest;
import org.sm.reservationapi.dto.UserResponse;
import org.sm.reservationapi.service.UserService;
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

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
@CrossOrigin
@RestController
@RequestMapping("/api/users")
public class UserController {
	@Autowired
	private UserService service;

	@PostMapping()
	public ResponseEntity<ResponseStrcture<UserResponse>> save(@Valid @RequestBody UserRequest userRequest,
			HttpServletRequest request) {
		return service.addUser(userRequest, request);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ResponseStrcture<UserResponse>> update(@PathVariable(name = "id") Integer id,
			@RequestBody UserRequest userRequest) {
		return service.updateUser(id, userRequest);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ResponseStrcture<UserResponse>> findbyid(@PathVariable(name = "id") Integer id) {
		return service.findUserById(id);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStrcture<String>> delete(@PathVariable(name = "id") Integer id) {
		return service.deleteUser(id);
	}

	@PostMapping("/verify-by-email")
	public ResponseEntity<ResponseStrcture<UserResponse>> verifyAdmin(@RequestParam String email,
			@RequestParam String password) {
		return service.verifyAdmin(email, password);
	}

	@PostMapping("/verify-by-phone")
	public ResponseEntity<ResponseStrcture<UserResponse>> verifyAdmin(@RequestParam Long phone,
			@RequestParam String password) {
		return service.verifyAdmin(phone, password);
	}

	@GetMapping("/activate")
	public String accountActivate(@RequestParam String token) {
		return service.activate(token);
	}
}
