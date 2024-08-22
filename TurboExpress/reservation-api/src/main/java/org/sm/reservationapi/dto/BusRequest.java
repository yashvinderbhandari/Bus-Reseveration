package org.sm.reservationapi.dto;

//import java.time.LocalDate;
import java.time.LocalDateTime;

import org.sm.reservationapi.model.Admin;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BusRequest {
	@NotBlank(message = "Name is Mandatory")
	private String name;
	private LocalDateTime departure_date_time;
	@NotBlank(message = "Bus Number is Mandatory")
	@Size(min = 11, max = 12)
	private String busno;
	@NotBlank(message = "Location is Mandatory")
	private String fromLoc;
	@NotBlank(message = "Location is Mandatory")
	private String toLoc;
	private Integer noOfSeats;
	private String typeofbus;
	private Integer availableSeats;
	private Double cost;
	private Admin admin;
}
