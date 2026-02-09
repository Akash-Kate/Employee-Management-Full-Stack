package com.app.ems.mapper;

import com.app.ems.dto.EmployeeDto;
import com.app.ems.entity.Employee;

public class EmployeeMapper {

	// Here we have created a class which is mapping employeeDTO to employee Entity
	// and Employee entity to Employee DTO

	public static EmployeeDto mapToEmployeeDto(Employee employee) {
		return new EmployeeDto(employee.getId(), 
				employee.getFirstName(), 
				employee.getLastName(), 
				employee.getEmail(),
				employee.getDepartment().getId()   // We have added the deptId inside the EmployeeDto class so here we need that id
				);

	}

	public static Employee mapToEmployee(EmployeeDto employeeDto) {
		Employee employee = new Employee();

		employee.setId(employeeDto.getId());
		employee.setFirstName(employeeDto.getFirstName());
		employee.setLastName(employeeDto.getLastName());
		employee.setEmail(employeeDto.getEmail());
		
		return employee;

	}
}
