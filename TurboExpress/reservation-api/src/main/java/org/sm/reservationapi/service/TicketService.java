package org.sm.reservationapi.service;

import java.util.Optional;

import org.sm.reservationapi.dao.BusDao;
import org.sm.reservationapi.dao.TicketDao;
import org.sm.reservationapi.dao.UserDao;
import org.sm.reservationapi.dto.TicketResponse;
import org.sm.reservationapi.exception.BusNotFoundException;
import org.sm.reservationapi.exception.UserNotFoundException;
import org.sm.reservationapi.model.Bus;
import org.sm.reservationapi.model.Ticket;
import org.sm.reservationapi.model.User;
import org.sm.reservationapi.util.ApplicationStatus;
import org.sm.reservationapi.util.TicketStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketService {
	@Autowired
	private TicketDao ticketDao;
	@Autowired
	private BusDao busDao;
	@Autowired
	private UserDao userDao;

	public TicketResponse book(Integer userId, Integer busId, Integer noOfSeatOrder) {
		Optional<Bus> dbBus = busDao.findById(busId);
		Optional<User> dbUser = userDao.findById(userId);
		if (dbBus.isEmpty()) {
			throw new BusNotFoundException("Invalid Bus Id!");
		}
		if (dbUser.isEmpty()) {
			throw new UserNotFoundException("Invalid User Id!");
		}
		User user = dbUser.get();
		if (user.getStatus().equals(ApplicationStatus.IN_ACTIVE.toString()))
			throw new IllegalStateException("Please Activate Your Account, Then book tickets");
		Bus bus = dbBus.get();
		if (bus.getAvailableSeats() < noOfSeatOrder) {
			throw new IllegalStateException("Please Enter a valid seat number!");
		}
		Ticket ticket = new Ticket();
		ticket.setCost(noOfSeatOrder * bus.getCost());
		ticket.setStatus(TicketStatus.BOOKED.toString());
		ticket.setBus(bus);
		ticket.setUser(user);
		ticket.setNoOfSeatsBooked(noOfSeatOrder);
		bus.setAvailableSeats(bus.getAvailableSeats() - noOfSeatOrder);
		busDao.save(bus);
		userDao.saveUser(user);
		ticket = ticketDao.saveTicket(ticket);
		return mapToTicket(ticket, bus, user);
	}

	public TicketResponse mapToTicket(Ticket ticket, Bus bus, User user) {
		TicketResponse response = new TicketResponse();
		response.setUserName(user.getName());
		response.setAge(user.getAge());
		response.setGender(user.getGender());
		response.setPhone(user.getPhone());
		response.setEmail(user.getEmail());
		
		response.setBusName(bus.getName());
		response.setSource(bus.getFromLoc());
		response.setDestination(bus.getToLoc());
		response.setTypeofbus(bus.getTypeofbus());
		response.setBusNumber(bus.getBusno());
		
		response.setCost(ticket.getCost());
		response.setId(ticket.getId());
		response.setNoOfSeatsBooked(ticket.getNoOfSeatsBooked());
		response.setStatus(ticket.getStatus());
		response.setTicketBookedTime(ticket.getTicketBookedTime());
		return response;
	}
}
