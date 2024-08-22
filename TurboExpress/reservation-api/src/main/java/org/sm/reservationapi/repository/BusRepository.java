package org.sm.reservationapi.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.sm.reservationapi.model.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BusRepository extends JpaRepository<Bus, Integer> {
//	@Query("select b from Bus b where busno=?1")
	public Optional<Bus> findByBusno(String busNo);

	@Query("SELECT b FROM Bus b WHERE b.fromLoc=?1 AND b.toLoc=?2 AND DATE(b.departure_date_time) = ?3")
	public List<Bus> findByDestination(String from, String to, LocalDate date);
}
