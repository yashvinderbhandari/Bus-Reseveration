package org.sm.reservationapi.dto;

import org.springframework.stereotype.Controller;

import lombok.Data;

@Data
@Controller
public class EmailConfiguration {
	private String toAddress;
	private String subject, text;

}
