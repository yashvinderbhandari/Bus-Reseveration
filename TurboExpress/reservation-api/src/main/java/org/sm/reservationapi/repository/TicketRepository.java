package org.sm.reservationapi.repository;

import org.sm.reservationapi.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Integer>{

}
