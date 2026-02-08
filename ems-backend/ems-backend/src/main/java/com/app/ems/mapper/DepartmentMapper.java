package com.app.ems.mapper;

import com.app.ems.dto.DepartmentDto;
import com.app.ems.entity.Department;

public class DepartmentMapper 
{
	// convert department jpa entity into department dto
	public static DepartmentDto mapToDepartmentDto(Department dept)
	{
		return new DepartmentDto(
				dept.getId(),
				dept.getDepartmentName(),
				dept.getDepartmentDescription()
				
				);
	}
	
	// convert Department DTO to department JPA entity
	public static Department mapToDepartment(DepartmentDto deptDto)
	{
		return new Department(
				
				deptDto.getId(),
				deptDto.getDepartmentName(),
				deptDto.getDepartmentDescription()
				);
	}
}
