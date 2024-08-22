package org.sm.reservationapi.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false, unique = true)
	private Long phone;
	@Column(nullable = false, unique = true)
	private String email;
	@Column(nullable = false)
	private Integer age;
	@Column(nullable = false)
	private String gender, password;
	public String token;
	@Column(nullable = false)
	public String status;
	@OneToMany(mappedBy = "user")
	private List<Ticket> tickets;
}
