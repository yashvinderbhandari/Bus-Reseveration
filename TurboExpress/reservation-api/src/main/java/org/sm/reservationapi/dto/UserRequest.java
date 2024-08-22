package org.sm.reservationapi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRequest {
	@NotBlank(message = "Nane is Mandatory")
	private String name;
	private Long phone;
	@NotBlank(message = "Email is Mandatory & write it in proper format")
	@Email
	private String email;
	private Integer age;
	@NotBlank(message = "Gender is Mandatory")
	private String gender;
	@NotBlank(message = "Password is Mandatory & size must be bewtween 8-16 characters")
	@Size(min = 8, max = 16)
	private String password;
}
