package org.sm.reservationapi.dto;

//import java.time.LocalDate;
import java.time.LocalDateTime;

import org.sm.reservationapi.model.Admin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BusResponse {
	private Integer id;
	private String name;
	private LocalDateTime departure_date_time;
	private String busno;
	private String fromLoc, toLoc;
	private String typeofbus;
	private Integer noOfSeats;
	private Integer availableSeats;
	private Double cost;
	private Admin admin;
}
