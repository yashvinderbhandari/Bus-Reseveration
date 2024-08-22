package org.sm.reservationapi.repository;

import java.util.Optional;

import org.sm.reservationapi.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AdminRepository extends JpaRepository<Admin, Integer> {

	@Query("select a from Admin a where a.email=?1 and a.password=?2")
	public Optional<Admin> verifyByEmail(String email, String password);

	@Query("select a from Admin a where a.phone=?1 and a.password=?2")
	public Optional<Admin> verifyByPhone(Long phone, String password);

	public Optional<Admin> findByToken(String token);

	public Optional<Admin> findByEmail(String email);
}
