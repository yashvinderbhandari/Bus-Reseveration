package org.sm.reservationapi.model;

//import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bus {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false, name = "depratureDateTime")
	private LocalDateTime departure_date_time;
	@Column(nullable = false, unique = true)
	private String busno;
	@Column(nullable = false)
	private String fromLoc, toLoc;
	@Column(nullable = false)
	private String typeofbus;
	@Column(nullable = false)
	private Integer noOfSeats;
	@Column(nullable = false)
	private Integer availableSeats;
	@Column(nullable = false)
	private Double cost;
	@ManyToOne
	@JoinColumn(name = "admin_id")
	@JsonIgnore
	private Admin admin;
}
