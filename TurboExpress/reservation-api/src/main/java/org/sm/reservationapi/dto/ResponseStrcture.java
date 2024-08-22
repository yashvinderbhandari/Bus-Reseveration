package org.sm.reservationapi.dto;

import lombok.Data;

@Data
public class ResponseStrcture<T>{
	private String message;
	private T data;
	private Integer status;

}
