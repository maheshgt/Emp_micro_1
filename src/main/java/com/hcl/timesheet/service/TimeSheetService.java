package com.hcl.timesheet.service;

import org.springframework.stereotype.Service;

import com.hcl.timesheet.dto.ApproveDto;
import com.hcl.timesheet.dto.MyTimeSheetResponceListDto;
import com.hcl.timesheet.dto.SubmitTimeSheetDto;
import com.hcl.timesheet.dto.SubmitTimeSheetResponceDto;
import com.hcl.timesheet.exception.ImporperDateException;

@Service
public interface TimeSheetService {

	SubmitTimeSheetResponceDto submitTimeSheet(int id, SubmitTimeSheetDto submitTimeSheetDto)
			throws ImporperDateException;

	ApproveDto approveTimeSheet(int id);

	MyTimeSheetResponceListDto viewList(int id);

}
