package com.hcl.timesheet.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import com.hcl.timesheet.dto.ApproveDto;
import com.hcl.timesheet.dto.MyTimeSheetResponceListDto;
import com.hcl.timesheet.dto.MyTimeSheetResponseDto;
import com.hcl.timesheet.dto.SubmitTimeSheetDto;
import com.hcl.timesheet.dto.SubmitTimeSheetResponceDto;
import com.hcl.timesheet.entity.TimeSheet;
import com.hcl.timesheet.repository.TimeSheetRepository;

@RunWith(MockitoJUnitRunner.class)
public class TimeSheetServiceImplTest {

	@Mock
	TimeSheetRepository timeSheetRepository;

	@InjectMocks
	TimeSheetServiceImpl timeSheetServiceImpl;

	@Test
	public void testApproveTimeSheet() {

		ApproveDto approvedDto = new ApproveDto();
		approvedDto.setMessage("successfully");
		approvedDto.setStatusCode(HttpStatus.OK.value());

		TimeSheet time1 = new TimeSheet();
		TimeSheet time2 = new TimeSheet();

		List<TimeSheet> timeSheet = new ArrayList<>();
		timeSheet.add(time1);
		timeSheet.add(time2);

		Mockito.when(timeSheetRepository.findByRmId(Mockito.anyInt())).thenReturn(timeSheet);
		ApproveDto approvedDto1 = timeSheetServiceImpl.approveTimeSheet(1);
		assertEquals(approvedDto.getStatusCode(), approvedDto1.getStatusCode());
	}

	@Test
	public void testViewList() {

		TimeSheet time1 = new TimeSheet();
		TimeSheet time2 = new TimeSheet();

		MyTimeSheetResponceListDto dto = new MyTimeSheetResponceListDto();
		List<MyTimeSheetResponseDto> listDto = new ArrayList<>();
		List<TimeSheet> timeSheet = new ArrayList<>();
		timeSheet.add(time1);
		timeSheet.add(time2);

		MyTimeSheetResponseDto myTimeSheetResponseDto1 = new MyTimeSheetResponseDto();
		myTimeSheetResponseDto1.setFromDate(time1.getFromDate());
		MyTimeSheetResponseDto myTimeSheetResponseDto2 = new MyTimeSheetResponseDto();
		myTimeSheetResponseDto2.setFromDate(time2.getFromDate());
		listDto.add(myTimeSheetResponseDto1);
		listDto.add(myTimeSheetResponseDto2);
		dto.setMyTimeSheetResponseDto(listDto);

		Mockito.when(timeSheetRepository.findByUserId(Mockito.anyInt())).thenReturn(timeSheet);
		MyTimeSheetResponceListDto dto1 = timeSheetServiceImpl.viewList(1);
		assertEquals(dto1.getMyTimeSheetResponseDto().size(), dto.getMyTimeSheetResponseDto().size());

	}

}
