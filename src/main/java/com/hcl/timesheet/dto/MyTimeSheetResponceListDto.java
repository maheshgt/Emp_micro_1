package com.hcl.timesheet.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MyTimeSheetResponceListDto {
	private List<MyTimeSheetResponseDto> myTimeSheetResponseDto;
}
