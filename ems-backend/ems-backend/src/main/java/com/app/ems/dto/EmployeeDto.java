package com.app.ems.dto;



public class EmployeeDto {
	
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private Long deptId;
	
	
//	Why NOT put Department inside DTO?
//
//			If you did this:
//
//			private Department department;
//
//			You would face:
//
//			Infinite JSON recursion
//
//			LAZY loading exceptions
//
//			Tight coupling frontend ↔ DB
//
//			Bigger payloads
//
//			That’s why DTO uses IDs, not objects.
//	
	
	
	public EmployeeDto() {
		// TODO Auto-generated constructor stub
	}
	

	public EmployeeDto(Long id, String firstName, String lastName, String email, Long deptId) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.deptId = deptId;
	}

	public Long getDeptId() {
		return deptId;
	}


	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "EmployeeDto [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ "]";
	}
	
	
}
