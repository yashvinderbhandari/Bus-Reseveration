package org.sm.reservationapi.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketResponse {
	private int id;
	private LocalDateTime ticketBookedTime;
	private Double cost;
	private String status;
	private Integer noOfSeatsBooked;
	private String userName;
	private Long phone;
	private String email;
	private String gender;
	private Integer age;
	private String busName;
	private String source;
	private String destination;
	private String busNumber;
	private String typeofbus;
}
