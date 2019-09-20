package com.hcl.timesheet.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.hcl.timesheet.dto.ApproveDto;
import com.hcl.timesheet.dto.MyTimeSheetResponceListDto;
import com.hcl.timesheet.dto.MyTimeSheetResponseDto;
import com.hcl.timesheet.dto.SubmitTimeSheetDto;
import com.hcl.timesheet.dto.SubmitTimeSheetResponceDto;
import com.hcl.timesheet.entity.TimeSheet;
import com.hcl.timesheet.exception.ImporperDateException;
import com.hcl.timesheet.repository.TimeSheetRepository;
import com.hcl.timesheet.util.TimeSheetUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TimeSheetServiceImpl implements TimeSheetService {

	@Autowired
	TimeSheetRepository timeSheetRepository;

	@Override
	public SubmitTimeSheetResponceDto submitTimeSheet(int id, SubmitTimeSheetDto submitTimeSheetDto)
			throws ImporperDateException {

		log.info("user sumbmit time sheet service method");

		int noOfDays = 0;
		TimeSheet timeSheet = new TimeSheet();
		timeSheet.setUserId(id);
		timeSheet.setSubmitDate(LocalDate.now());

		if (submitTimeSheetDto.getFromDate().isAfter(LocalDate.now())
				|| submitTimeSheetDto.getToDate().isAfter(LocalDate.now()))
			throw new ImporperDateException(TimeSheetUtil.IMPROPER_DATE_EXCEPTION1);

		if (submitTimeSheetDto.getFromDate().isEqual(submitTimeSheetDto.getToDate())) {
			if ((submitTimeSheetDto.getFromDate().getDayOfWeek() == DayOfWeek.SATURDAY)
					|| submitTimeSheetDto.getFromDate().getDayOfWeek() == DayOfWeek.SUNDAY)
				throw new ImporperDateException(TimeSheetUtil.IMPROPER_DATE_EXCEPTION);
			timeSheet.setNoOfHrs(submitTimeSheetDto.getNoOfHrsPerDay());
			timeSheet.setNoOfDays(1);
		} else {
			LocalDate start = submitTimeSheetDto.getFromDate();
			LocalDate end = submitTimeSheetDto.getToDate();
			for (LocalDate date = start; date.isBefore(end); date = date.plusDays(1)) {
				if (!(date.getDayOfWeek().name().equals(TimeSheetUtil.WEEKEND1)
						|| date.getDayOfWeek().name().equals(TimeSheetUtil.WEEKEND2))) {
					++noOfDays;
				}
			}
			timeSheet.setNoOfDays(noOfDays);
			int hrs = (noOfDays * submitTimeSheetDto.getNoOfHrsPerDay());
			timeSheet.setNoOfHrs(hrs);
		}

		BeanUtils.copyProperties(submitTimeSheetDto, timeSheet);
		timeSheet.setStatus(TimeSheetUtil.submitStatus);
		timeSheetRepository.save(timeSheet);

		SubmitTimeSheetResponceDto submitTimeSheetResponceDto = new SubmitTimeSheetResponceDto();
		BeanUtils.copyProperties(timeSheet, submitTimeSheetResponceDto);
		submitTimeSheetResponceDto.setNoOfDays(timeSheet.getNoOfDays());
		submitTimeSheetResponceDto.setTotalHrs(timeSheet.getNoOfHrs());
		return submitTimeSheetResponceDto;
	}

	@Override
	public ApproveDto approveTimeSheet(int id) {

		log.info("rm approve time sheet service method");

		List<TimeSheet> listTimeSheet = timeSheetRepository.findByRmId(id);
		listTimeSheet.forEach(list -> {
			list.setStatus(TimeSheetUtil.approveStatus);
			timeSheetRepository.save(list);
		});

		ApproveDto approveDto = new ApproveDto();
		approveDto.setMessage(TimeSheetUtil.approveMessage);
		approveDto.setStatusCode(HttpStatus.OK.value());
		return approveDto;
	}

	@Override
	public MyTimeSheetResponceListDto viewList(int id) {

		log.info("user view his submitted time sheet service method");

		List<TimeSheet> listTimeSheet = timeSheetRepository.findByUserId(id);
		MyTimeSheetResponceListDto dto = new MyTimeSheetResponceListDto();
		List<MyTimeSheetResponseDto> listDto = new ArrayList<>();

		listTimeSheet.forEach(list -> {
			MyTimeSheetResponseDto myTimeSheetResponseDto = new MyTimeSheetResponseDto();
			BeanUtils.copyProperties(list, myTimeSheetResponseDto);
			listDto.add(myTimeSheetResponseDto);
		});

		dto.setMyTimeSheetResponseDto(listDto);
		return dto;
	}

}
