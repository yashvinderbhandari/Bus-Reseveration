package org.sm.reservationapi.dao;

import java.util.Optional;

import org.sm.reservationapi.model.User;
import org.sm.reservationapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
	@Autowired
	private UserRepository userRepository;

	public User saveUser(User user) {
		return userRepository.save(user);
	}

	public Optional<User> findById(Integer id) {
		return userRepository.findById(id);
	}

	public void deleteUserById(Integer id) {
		userRepository.deleteById(id);
	}

	public Optional<User> verifyByEmail(String email, String Password) {
		return userRepository.verifyByEmail(email, Password);
	}

	public Optional<User> verifyByphone(Long phone, String Password) {
		return userRepository.verifyByPhone(phone, Password);
	}
	public Optional<User> findUserByToken(String token) {
		return userRepository.findByToken(token);
	}
}
