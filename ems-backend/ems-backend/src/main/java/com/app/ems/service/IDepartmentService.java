package com.app.ems.service;

import java.util.List;

import com.app.ems.dto.DepartmentDto;

public interface IDepartmentService 
{
	DepartmentDto createDepartment(DepartmentDto deptDto);
	
	DepartmentDto getDepartmentById(Long deptId);
	
	List<DepartmentDto> getAllDepartments();
	
	DepartmentDto updateDepartment(Long deptId, DepartmentDto updatedDept);
	
	void deleteDepartment(Long deptId);
	
}
