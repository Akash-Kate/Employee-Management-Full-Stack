package com.app.ems.service;

import java.util.List;

import com.app.ems.dto.EmployeeDto;

public interface IEmployeeService 
{
	EmployeeDto createEmployee(EmployeeDto employeeDto);
	
	EmployeeDto getEmployeeById(Long employeeId);
	
	List<EmployeeDto> getAllEmployees();
	
	EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmp);
	
	void deleteEmployee(Long employeeId);
}
