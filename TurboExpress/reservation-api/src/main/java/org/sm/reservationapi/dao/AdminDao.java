package org.sm.reservationapi.dao;

import java.util.Optional;

import org.sm.reservationapi.model.Admin;
import org.sm.reservationapi.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AdminDao {
	@Autowired
	private AdminRepository adminRepository;

	public Admin saveAdmin(Admin admin) {
		return adminRepository.save(admin);
	}

	public Optional<Admin> findById(Integer id) {
		return adminRepository.findById(id);
	}

	public void deleteById(Integer id) {
		adminRepository.deleteById(id);
	}

	public Optional<Admin> verifyByEmail(String email, String Password) {
		return adminRepository.verifyByEmail(email, Password);
	}

	public Optional<Admin> verifyByphone(Long phone, String Password) {
		return adminRepository.verifyByPhone(phone, Password);
	}

	public Optional<Admin> findByToken(String token) {
		return adminRepository.findByToken(token);
	}

	public Optional<Admin> findByEmail(String eamil) {
		return adminRepository.findByEmail(eamil);
	}
}
