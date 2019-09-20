package com.hcl.timesheet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.timesheet.dto.ApproveDto;
import com.hcl.timesheet.dto.MyTimeSheetResponceListDto;
import com.hcl.timesheet.dto.SubmitTimeSheetDto;
import com.hcl.timesheet.dto.SubmitTimeSheetResponceDto;
import com.hcl.timesheet.exception.ImporperDateException;
import com.hcl.timesheet.service.TimeSheetService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class TimeSheetController {

	@Autowired
	TimeSheetService timeSheetService;

	/**
	 * @param id
	 * @return
	 */
	@PostMapping("/submit/{id}")
	public ResponseEntity<SubmitTimeSheetResponceDto> submitTimeSheet(@PathVariable int id,
			@RequestBody SubmitTimeSheetDto submitTimeSheetDto) throws ImporperDateException {
		log.info("user sumbmit time sheet controller method");
		return new ResponseEntity<>(timeSheetService.submitTimeSheet(id, submitTimeSheetDto), HttpStatus.OK);
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/approve/{id}")
	public ResponseEntity<ApproveDto> approveTimeSheet(@PathVariable int id) {
		log.info("rm approve time sheet controller method");
		return new ResponseEntity<>(timeSheetService.approveTimeSheet(id), HttpStatus.OK);
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/view/{id}")
	public ResponseEntity<MyTimeSheetResponceListDto> viewMyTimeSheet(@PathVariable int id) {
		log.info("user view his sumbmitted time sheet controller method");
		return new ResponseEntity<>(timeSheetService.viewList(id), HttpStatus.OK);
	}
}
