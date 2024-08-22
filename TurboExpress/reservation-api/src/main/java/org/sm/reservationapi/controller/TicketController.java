package org.sm.reservationapi.controller;

import org.sm.reservationapi.dto.TicketResponse;
import org.sm.reservationapi.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/book-ticket")
public class TicketController {
	@Autowired
	private TicketService service;

	@PostMapping("/{userId}/{busId}/{noOfSeatOrder}")
	public TicketResponse bookTicket(@PathVariable Integer userId, @PathVariable Integer busId,
			@PathVariable Integer noOfSeatOrder) {
		return service.book(userId, busId, noOfSeatOrder);
	}
}
