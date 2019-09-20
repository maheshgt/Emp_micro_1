package com.hcl.timesheet.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SubmitTimeSheetDto {

	private int rmId;
	private LocalDate fromDate;
	private LocalDate toDate;
	private int noOfHrsPerDay;
}
