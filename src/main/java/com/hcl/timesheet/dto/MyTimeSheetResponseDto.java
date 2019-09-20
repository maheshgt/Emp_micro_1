package com.hcl.timesheet.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MyTimeSheetResponseDto {

	private LocalDate fromDate;
	private LocalDate toDate;
	private int noOfDays;
	private String status;
	private int noOfHrs;
}
