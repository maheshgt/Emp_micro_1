package com.hcl.timesheet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.google.common.base.Optional;
import com.hcl.timesheet.entity.TimeSheet;

@Repository
public interface TimeSheetRepository extends JpaRepository<TimeSheet, Integer>{

	List<TimeSheet> findByRmId(int id);

	List<TimeSheet> findByUserId(int id);

}
