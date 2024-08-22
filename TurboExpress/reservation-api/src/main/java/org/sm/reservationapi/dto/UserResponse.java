package org.sm.reservationapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
	private Integer id;
	private String name;
	private Long phone;
	private String email;
	private Integer age;
	private String gender, password;
}
