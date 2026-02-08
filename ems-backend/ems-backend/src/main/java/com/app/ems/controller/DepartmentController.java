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

import com.app.ems.dto.DepartmentDto;
import com.app.ems.service.IDepartmentService;

@CrossOrigin
@RestController
@RequestMapping("/api/departments")
public class DepartmentController 
{
	private IDepartmentService departmentService;

	// Construstor based dependency injection
	public DepartmentController(IDepartmentService departmentService) {
		super();
		this.departmentService = departmentService;
	}
	
	
	// Build create or add department REST Api
	@PostMapping
	public ResponseEntity<DepartmentDto> createDepartment(@RequestBody DepartmentDto deptdto)
	{
		// The type of Response Entity is DepartmentDto
		// and also its input parameter is DepartmentDto
		// Why ? because whenever there will be data excahnge JSON exchange
		// From frontend to backend and vise versa it will be through DTO object
		// Not the JPA object
		
		
		// If the request body json attributes spelling is wrong then the api works but the value in DB will be NULL
		
		DepartmentDto dept =  departmentService.createDepartment(deptdto);
		
		return new ResponseEntity<>(dept,HttpStatus.CREATED);
		
	}
	
	
	// Build Get Department REST API
	@GetMapping("{id}")
	public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable("id") Long departmentId)
	{
		DepartmentDto deptDto =  departmentService.getDepartmentById(departmentId);
		
		return ResponseEntity.ok(deptDto);	
	}
	
	// Build get all departments rest api
	@GetMapping
	public ResponseEntity<List<DepartmentDto>> getAllDepartments()
	{
		List<DepartmentDto> deptsDto = departmentService.getAllDepartments();
		
		return ResponseEntity.ok(deptsDto);
		
	}
	
	// Build update department REST Api
	@PutMapping("{id}")
	public ResponseEntity<DepartmentDto> updateDepartment(@PathVariable("id") Long deptId, 
														  @RequestBody DepartmentDto updatedDept)
	{
		DepartmentDto deptDto =  departmentService.updateDepartment(deptId, updatedDept);
		
		return ResponseEntity.ok(deptDto);
	}
	
	
	// Build delete department REST Api
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteDepartment(@PathVariable("id") Long deptId)
	{
		departmentService.deleteDepartment(deptId);
		
		return ResponseEntity.ok("Department Deleted Successfully");
	}
	
	
	
}
