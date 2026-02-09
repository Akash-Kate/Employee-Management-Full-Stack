package com.app.ems.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.ems.dto.EmployeeDto;
import com.app.ems.service.IEmployeeService;


@CrossOrigin("*")
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final DepartmentController departmentController;
	
	
	private IEmployeeService employeeService;
	

	public EmployeeController(IEmployeeService employeeService, DepartmentController departmentController) {
		super();
		this.employeeService = employeeService;
		this.departmentController = departmentController;
	}

	// Build add employee rest api
	
	@PostMapping
	public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto empDto)
	// @RequestBody Extract JSON object sent from the client and convert it into the EmployeeDto object
	// When you pass JSON body from postman or react make sure that the attributes match the EmployeeDto class fields
	// Because we are using @RequestBody annotation and extracting the JOSn into EmployeeDto object
	{
		System.out.println("Check Department id "+empDto.getId());
		EmployeeDto detachedEmp  = employeeService.createEmployee(empDto);
		
		return new ResponseEntity<>(detachedEmp, HttpStatus.CREATED);
	}
	
	
	// Build Get Employee Rest API
	@GetMapping("{id}") // URI template variable
	public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") Long employeeId)
	// URI Template var is id and method argument name id employeeId, so need to pass id as a PathVariable
	{
		EmployeeDto empDto = employeeService.getEmployeeById(employeeId);
		return ResponseEntity.ok(empDto);
	}
	
	
	// Build get all employees rest API
	@GetMapping
	public ResponseEntity<List<EmployeeDto>> getAllEmployees()
	{
		List<EmployeeDto> employees =  employeeService.getAllEmployees();
		
		return ResponseEntity.ok(employees);
	}
	
	
	// Build update employee rest api
	@PutMapping("{id}")
	public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") Long empId, @RequestBody EmployeeDto updatedEmp){
		
		EmployeeDto empDto = employeeService.updateEmployee(empId, updatedEmp);
		
		return ResponseEntity.ok(empDto);
		
	}
	
	
	// Build delete employee rest api
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long employeeId)
	{
		employeeService.deleteEmployee(employeeId);
		
		return ResponseEntity.ok("Employee Deleted Successfully ! ...");
		
	}
	
	
}






























