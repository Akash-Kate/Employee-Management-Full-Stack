package com.app.ems.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.app.ems.dto.EmployeeDto;
import com.app.ems.entity.Department;
import com.app.ems.entity.Employee;
import com.app.ems.exception.ResourceNotFoundException;
import com.app.ems.mapper.EmployeeMapper;
import com.app.ems.repository.IDepartmentRepository;
import com.app.ems.repository.IEmployeeRepository;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

	
	private IEmployeeRepository employeeRepo;
	
	private IDepartmentRepository departmentRepo;
	
	
	
	public EmployeeServiceImpl(IEmployeeRepository employeeRepo, IDepartmentRepository departmentRepo) {   // Constructor based dependency injection
		super();
		this.employeeRepo = employeeRepo;
		this.departmentRepo = departmentRepo;
	}
	
	
	@Override
	public EmployeeDto createEmployee(EmployeeDto employeeDto) {
		
		// Whenever we save the employee object to the database we have to save the department to that employee object
		
		Employee employee = EmployeeMapper.mapToEmployee(employeeDto);  // Converting EmployeeDto into Employee entity object with the help of mapper class
		
		Department department = departmentRepo.findById(employeeDto.getDeptId()).orElseThrow(() -> 
							new ResourceNotFoundException("Department does not exist with id "+employeeDto.getDeptId()));
		
		employee.setDepartment(department);  // Actual linking between Employee Entity and Department Entity
		
		
		Employee saved_emp = employeeRepo.save(employee); // Calling readymade save method of JPA repository // FK insertion will take place
		// Hibernate will run insert query and put dept_id inside the employee table as FK
		
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
		
		
		Department department = departmentRepo.findById(updatedEmp.getDeptId()).orElseThrow(() -> 
		new ResourceNotFoundException("Department does not exist with id "+updatedEmp.getDeptId()));

		persistentEmp.setDepartment(department);

		
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




















