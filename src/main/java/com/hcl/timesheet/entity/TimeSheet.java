package com.hcl.timesheet.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "timesheet")
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
public class TimeSheet {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private LocalDate submitDate;
	private int noOfHrs;
	private LocalDate fromDate;
	private LocalDate toDate;
	private int userId;
	private int rmId;
	private int noOfDays;
	private String status;
}
