package com.hcl.timesheet.controller;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.hcl.timesheet.dto.ApproveDto;
import com.hcl.timesheet.dto.MyTimeSheetResponceListDto;
import com.hcl.timesheet.dto.MyTimeSheetResponseDto;
import com.hcl.timesheet.dto.SubmitTimeSheetDto;
import com.hcl.timesheet.dto.SubmitTimeSheetResponceDto;
import com.hcl.timesheet.service.TimeSheetServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class TimeSheetControllerTest {

	@Mock
	TimeSheetServiceImpl timeSheetServiceImpl;

	@InjectMocks
	TimeSheetController timeSheetController;

	@Test
	public void testSubmitTimeSheet() {

		SubmitTimeSheetDto submitTimeSheetDto = new SubmitTimeSheetDto();
		submitTimeSheetDto.setFromDate(LocalDate.of(2019, 9, 12));
		submitTimeSheetDto.setToDate(LocalDate.of(2019, 9, 16));
		submitTimeSheetDto.setNoOfHrsPerDay(9);

		SubmitTimeSheetResponceDto submitTimeSheetResponceDto = new SubmitTimeSheetResponceDto();
		submitTimeSheetResponceDto.setFromDate(LocalDate.of(2019, 9, 12));

		Mockito.when(timeSheetServiceImpl.submitTimeSheet(1, submitTimeSheetDto))
				.thenReturn(submitTimeSheetResponceDto);
		ResponseEntity<SubmitTimeSheetResponceDto> submitTimeSheetResponceDto1 = timeSheetController.submitTimeSheet(1,
				submitTimeSheetDto);
		assertEquals(submitTimeSheetResponceDto1.getBody().getFromDate(), submitTimeSheetResponceDto.getFromDate());
		;
	}

	@Test
	public void testApproveTimeSheet() {

		ApproveDto approvedDto = new ApproveDto();
		approvedDto.setMessage("successfully");

		Mockito.when(timeSheetServiceImpl.approveTimeSheet(Mockito.anyInt())).thenReturn(approvedDto);
		ResponseEntity<ApproveDto> dto1 = timeSheetController.approveTimeSheet(1);
		assertEquals(dto1.getBody().getMessage(), approvedDto.getMessage());
	}

	@Test
	public void testViewMyTimeSheet() {

		MyTimeSheetResponceListDto list = new MyTimeSheetResponceListDto();
		MyTimeSheetResponseDto myTimeSheetResponseDto = new MyTimeSheetResponseDto();

		List<MyTimeSheetResponseDto> list1 = new ArrayList<>();
		myTimeSheetResponseDto.setFromDate(LocalDate.of(2019, 9, 16));
		myTimeSheetResponseDto.setToDate(LocalDate.of(2019, 9, 20));
		list1.add(myTimeSheetResponseDto);

		MyTimeSheetResponseDto myTimeSheetResponseDto1 = new MyTimeSheetResponseDto();
		myTimeSheetResponseDto1.setFromDate(LocalDate.of(2019, 9, 12));
		myTimeSheetResponseDto1.setToDate(LocalDate.of(2019, 9, 14));
		list1.add(myTimeSheetResponseDto1);
		list.setMyTimeSheetResponseDto(list1);

		Mockito.when(timeSheetServiceImpl.viewList(Mockito.anyInt())).thenReturn(list);
		ResponseEntity<MyTimeSheetResponceListDto> listA = timeSheetController.viewMyTimeSheet(1);
		assertEquals(listA.getBody().getMyTimeSheetResponseDto().size(), list.getMyTimeSheetResponseDto().size());

	}

}
