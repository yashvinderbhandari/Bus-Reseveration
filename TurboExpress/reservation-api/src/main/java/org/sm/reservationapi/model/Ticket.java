package org.sm.reservationapi.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Ticket {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@CreationTimestamp
	private LocalDateTime ticketBookedTime;
	@Column(nullable = false)
	private Double cost;
	@Column(nullable = false)
	private String status;
	@Column(nullable = false)
	private Integer noOfSeatsBooked;
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "bus_id")
	private Bus bus;
	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonIgnore
	private User user;
}
