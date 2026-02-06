package com.app.ems.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.app.ems.dto.EmployeeDto;
import com.app.ems.entity.Employee;
import com.app.ems.exception.ResourceNotFoundException;
import com.app.ems.mapper.EmployeeMapper;
import com.app.ems.repository.IEmployeeRepository;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

	
	private IEmployeeRepository employeeRepo;
	
	public EmployeeServiceImpl(IEmployeeRepository employeeRepo) {   // Constructor based dependency injection
		super();
		this.employeeRepo = employeeRepo;
	}
	
	
	@Override
	public EmployeeDto createEmployee(EmployeeDto employeeDto) {
		
		Employee employee = EmployeeMapper.mapToEmployee(employeeDto);  // Converting EmployeeDto into Employee entity object with the help of mapper class
		
		Employee saved_emp = employeeRepo.save(employee); // Calling readymade save method of JPA repository
		
		// Convert the saved Employee JPA Enitity (PERSISTENT POJO) into EmployeeDto again with the help of mapper class
		
		return EmployeeMapper.mapToEmployeeDto(saved_emp);
	}


	@Override
	public EmployeeDto getEmployeeById(Long employeeId) {
		
		Employee employee = employeeRepo.findById(employeeId)
							.orElseThrow( ()-> new ResourceNotFoundException("Employee does not exist !! with the given id : " + employeeId));
		
		
		return EmployeeMapper.mapToEmployeeDto(employee);   // Here employee is PERSISTENT
	}


	@Override
	public List<EmployeeDto> getAllEmployees() {
		
		List<Employee> emps = employeeRepo.findAll();
		
		return emps.stream().map((emp) -> EmployeeMapper.mapToEmployeeDto(emp))
						.collect(Collectors.toList());
	}


	@Override
	public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmp) {
		
		Employee persistentEmp = employeeRepo.findById(employeeId)
					.orElseThrow(() -> new ResourceNotFoundException("Employee does not exists with given id "+employeeId));
		
		persistentEmp.setFirstName(updatedEmp.getFirstName());
		persistentEmp.setLastName(updatedEmp.getLastName());
		persistentEmp.setEmail(updatedEmp.getEmail());
		
		Employee updatedEmpObj = employeeRepo.save(persistentEmp);   // If employee with id already exists then the save method performs update
		
		
		return EmployeeMapper.mapToEmployeeDto(updatedEmpObj);
	}


	@Override
	public void deleteEmployee(Long employeeId) {
		
		Employee employee = employeeRepo.findById(employeeId)
				.orElseThrow( ()-> new ResourceNotFoundException("Employee does not exist !! with the given id : " + employeeId));
		
		employeeRepo.deleteById(employeeId);
		
	}
	
	

}




















