package org.sm.reservationapi.dto;

import java.util.List;

import org.sm.reservationapi.model.Bus;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AdminRequest {
	@NotBlank(message = " Name is Mandatory!")
	private String name;
	@NotBlank(message = "Travels Name Is Mandatory!")
	private String travels_name;

	private Long phone;
	@Email(message = "Invalid Email Format.")
	private String email;
	@Size(min = 15, max = 15)
	@NotBlank(message = "GST Number Is Mandatory!")
	private String gst_number;
	@NotBlank(message = "Password Is Mandatory!")
	private String password;
	private List<Bus> bus;
}
