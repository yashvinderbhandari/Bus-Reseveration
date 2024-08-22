package org.sm.reservationapi.service;

import java.util.Optional;

import org.sm.reservationapi.dao.UserDao;
import org.sm.reservationapi.dto.EmailConfiguration;
import org.sm.reservationapi.dto.ResponseStrcture;
import org.sm.reservationapi.dto.UserRequest;
import org.sm.reservationapi.dto.UserResponse;
import org.sm.reservationapi.exception.AdminNotFoundException;
import org.sm.reservationapi.exception.UserNotFoundException;
import org.sm.reservationapi.model.User;
import org.sm.reservationapi.util.ApplicationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private ReservationApiMailService mailService;
	@Autowired
	private LinkGeneratorService generatorService;
	@Autowired
	private EmailConfiguration configuration;

	private User mapUser(UserRequest userRequest) {
		return User.builder().name(userRequest.getName()).phone(userRequest.getPhone()).email(userRequest.getEmail())
				.age(userRequest.getAge()).gender(userRequest.getGender()).password(userRequest.getPassword()).build();
	}

	private UserResponse mapUserRsponse(User user) {
		return UserResponse.builder().id(user.getId()).name(user.getName()).phone(user.getPhone())
				.email(user.getEmail()).age(user.getAge()).gender(user.getGender()).password(user.getPassword())
				.build();
	}

	public String activate(String token) {
		Optional<User> dbUser = userDao.findUserByToken(token);
		if (dbUser.isEmpty()) {
			throw new AdminNotFoundException("invalid Token!!");
		}
		User user = dbUser.get();
		user.setStatus("ACTIVE");
		user.setToken(null);
		userDao.saveUser(user);
		return "Your Account has been activated";
	}

	public ResponseEntity<ResponseStrcture<UserResponse>> addUser(UserRequest userRequest, HttpServletRequest request) {
		ResponseStrcture<UserResponse> strcture = new ResponseStrcture<>();
		User user = mapUser(userRequest);
		user.setStatus(ApplicationStatus.IN_ACTIVE.toString());
		user = userDao.saveUser(user);
		String activation_link = generatorService.getActivateUser(user, request);
		System.out.println(activation_link);
		configuration.setSubject("Verify Your Account.");
		configuration.setText("Click here To Verify: " + activation_link);
		configuration.setToAddress(user.getEmail());
		strcture.setData(mapUserRsponse(user));
		strcture.setMessage(mailService.sendMail(configuration));
		strcture.setStatus(HttpStatus.CREATED.value());
		return ResponseEntity.status(HttpStatus.CREATED).body(strcture);
	}

	public ResponseEntity<ResponseStrcture<UserResponse>> updateUser(Integer id, UserRequest userRequest) {
		ResponseStrcture<UserResponse> strcture = new ResponseStrcture<>();
		Optional<User> data = userDao.findById(id);
		if (data.isPresent()) {
			User user = data.get();
			user.setName(userRequest.getName());
			user.setPhone(userRequest.getPhone());
			user.setAge(userRequest.getAge());
			user.setEmail(userRequest.getEmail());
			user.setGender(userRequest.getGender());
			user.setPassword(userRequest.getPassword());
			strcture.setData(mapUserRsponse(userDao.saveUser(user)));
			strcture.setMessage("User Details Updated");
			strcture.setStatus(HttpStatus.ACCEPTED.value());
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(strcture);
		}
		throw new UserNotFoundException("Invalid User Id!");
	}

	public ResponseEntity<ResponseStrcture<UserResponse>> findUserById(Integer id) {
		ResponseStrcture<UserResponse> strcture = new ResponseStrcture<>();
		Optional<User> data = userDao.findById(id);
		if (data.isPresent()) {
			strcture.setData(mapUserRsponse(data.get()));
			strcture.setMessage("User Found");
			strcture.setStatus(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(strcture);
		}
		throw new AdminNotFoundException("Invalid User Id!");
	}

	public ResponseEntity<ResponseStrcture<String>> deleteUser(Integer id) {
		ResponseStrcture<String> strcture = new ResponseStrcture<>();
		Optional<User> data = userDao.findById(id);
		if (data.isPresent()) {
			strcture.setData("User id: " + id + " Deleted.");
			userDao.deleteUserById(id);
			strcture.setMessage("User Deleted");
			strcture.setStatus(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(strcture);
		}
		throw new AdminNotFoundException("Invalid User Id!");
	}

	public ResponseEntity<ResponseStrcture<UserResponse>> verifyAdmin(Long phone, String password) {
		ResponseStrcture<UserResponse> strcture = new ResponseStrcture<>();
		Optional<User> data = userDao.verifyByphone(phone, password);
		if (data.isPresent()) {
			strcture.setData(mapUserRsponse(data.get()));
			strcture.setMessage("Admin Verified");
			strcture.setStatus(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(strcture);
		}
		throw new AdminNotFoundException("Invalid Credentials!!!");
	}

	public ResponseEntity<ResponseStrcture<UserResponse>> verifyAdmin(String email, String password) {
		ResponseStrcture<UserResponse> strcture = new ResponseStrcture<>();
		Optional<User> data = userDao.verifyByEmail(email, password);
		if (data.isPresent()) {
			strcture.setData(mapUserRsponse(data.get()));
			strcture.setMessage("Admin Verified");
			strcture.setStatus(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(strcture);
		}
		throw new AdminNotFoundException("Invalid Credentials!!!");
	}
}
