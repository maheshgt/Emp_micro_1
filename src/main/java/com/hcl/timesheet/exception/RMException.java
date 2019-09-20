package com.hcl.timesheet.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RMException extends RuntimeException {

	private static final long serialVersionUID = 1L;	
	private String message;
	
}
