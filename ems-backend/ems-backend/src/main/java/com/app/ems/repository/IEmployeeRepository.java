package com.app.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.ems.entity.Employee;

public interface IEmployeeRepository extends JpaRepository <Employee, Long> {  // First argument is the Entity type (Employee) and other is type of the primary key i.e (Long)

}
