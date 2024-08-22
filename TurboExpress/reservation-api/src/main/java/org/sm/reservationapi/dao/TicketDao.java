package org.sm.reservationapi.dao;

import org.sm.reservationapi.model.Ticket;
import org.sm.reservationapi.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TicketDao {
	@Autowired
	private TicketRepository repository;

	public Ticket saveTicket(Ticket ticket) {
		return repository.save(ticket);
	}
}
