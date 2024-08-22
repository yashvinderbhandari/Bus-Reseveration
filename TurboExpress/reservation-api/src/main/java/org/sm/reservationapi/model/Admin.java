package org.sm.reservationapi.model;

import java.util.List;

import jakarta.persistence.CascadeType;
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

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Admin {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(nullable = false)
	private String name, travels_name;
	@Column(nullable = false, unique = true)
	private Long phone;
	@Column(nullable = false, unique = true)
	private String gst_number, email;
	@Column(nullable = false)
	private String password;
	@Column(unique = true)
	private String token;
	@Column(nullable = false)
	private String status;
	@OneToMany(mappedBy = "admin", cascade = CascadeType.ALL)
	private List<Bus> bus;
}
