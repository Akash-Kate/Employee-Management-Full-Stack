package com.app.ems.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.app.ems.dto.DepartmentDto;
import com.app.ems.entity.Department;
import com.app.ems.exception.ResourceNotFoundException;
import com.app.ems.mapper.DepartmentMapper;
import com.app.ems.repository.IDepartmentRepository;


@Service
public class DepartmentServiceImpl implements IDepartmentService {

	
	private IDepartmentRepository departmentRepository;
	
	// Constructor based dependency injection
	public DepartmentServiceImpl(IDepartmentRepository departmentRepository) {
		super();
		this.departmentRepository = departmentRepository;
	}
	
	
	@Override
	public DepartmentDto createDepartment(DepartmentDto deptDto) {
		
		Department deptJpa = DepartmentMapper.mapToDepartment(deptDto);
		
		Department savedDept = departmentRepository.save(deptJpa);
		// savedDept = PERSISTENT POJO
		
		
		// Convert the PERSISTENT JAP Entity into DTO Again
		return DepartmentMapper.mapToDepartmentDto(savedDept);
	}


	@Override
	public DepartmentDto getDepartmentById(Long deptId) {
		
		Department dept = departmentRepository.findById(deptId).orElseThrow(
				() -> new ResourceNotFoundException("Department does not exist with a given id : "+ deptId)
			);
		
		// convert JPA object to dto and return
		return DepartmentMapper.mapToDepartmentDto(dept);
	}


	@Override
	public List<DepartmentDto> getAllDepartments() 
	{
		List<Department> departments  = departmentRepository.findAll();
		
		
		return departments.stream().map((department) -> DepartmentMapper.mapToDepartmentDto(department))
				.collect(Collectors.toList());
	}


	@Override
	public DepartmentDto updateDepartment(Long deptId, DepartmentDto updatedDept) {
		
		Department dept =  departmentRepository.findById(deptId).orElseThrow(
				
				  () -> new ResourceNotFoundException("Department does not exist with the given id "+deptId)
				);
		
		dept.setDepartmentName(updatedDept.getDepartmentName());
		dept.setDepartmentDescription(updatedDept.getDepartmentDescription());
		
		Department savedDept = departmentRepository.save(dept); // passing updated dept object
	
		// convert the savedDept(JPA Entity) into DTO and return it
		return DepartmentMapper.mapToDepartmentDto(savedDept);
	}


	@Override
	public void deleteDepartment(Long deptId) {
		
		departmentRepository.findById(deptId).orElseThrow(
				() ->  new ResourceNotFoundException("Department does not exist with given id "+deptId)
			);	

		departmentRepository.deleteById(deptId);
		
		
	}

	

}
