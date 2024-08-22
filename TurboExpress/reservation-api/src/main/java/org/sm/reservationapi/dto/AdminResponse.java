package org.sm.reservationapi.dto;

import java.util.List;

import org.sm.reservationapi.model.Bus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminResponse {
	private Integer id;
	private String name, travels_name;
	private Long phone;
	private String gst_number, email;
	private String password;
	private List<Bus> bus;
}
