package com.app.ems.service;

import org.springframework.stereotype.Service;

import com.app.ems.dto.DepartmentDto;
import com.app.ems.entity.Department;
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

	

}
