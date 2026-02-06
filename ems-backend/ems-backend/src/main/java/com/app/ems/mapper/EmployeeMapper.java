package com.app.ems.mapper;

import com.app.ems.dto.EmployeeDto;
import com.app.ems.entity.Employee;

public class EmployeeMapper {

	// Here we have created a class which is mapping employeeDTO to employee Entity
	// and Employee entity to Employee DTO

	public static EmployeeDto mapToEmployeeDto(Employee employee) {
		return new EmployeeDto(
				employee.getId(), 
				employee.getFirstName(), 
				employee.getLastName(), 
				employee.getEmail()
		);

	}

	public static Employee mapToEmployee(EmployeeDto employeeDto) {
		return new Employee(
				employeeDto.getId(), 
				employeeDto.getFirstName(), 
				employeeDto.getLastName(),
				employeeDto.getEmail()
			);

	}
}
