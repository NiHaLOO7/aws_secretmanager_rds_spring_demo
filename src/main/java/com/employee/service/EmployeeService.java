package com.employee.service;

import java.util.List;

import com.employee.entity.Employee;
import com.employee.exception.ResourceNotFoundException;

public interface EmployeeService {
	
	Employee addEmployee(Employee employee);
	
	Employee updateEmployee(Employee employee) throws ResourceNotFoundException;
	
	List<Employee> getAllEmployees() throws ResourceNotFoundException;
	
	Employee getEmployeeById(long id) throws ResourceNotFoundException;
	
	void deleteEmployee(long id) throws ResourceNotFoundException; 
	
	List<Employee> getEmployeeBySalaryOrder() throws ResourceNotFoundException;
	

}
