package com.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.employee.entity.Employee;
import com.employee.exception.ResourceNotFoundException;
import com.employee.service.EmployeeService;

@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/employees")
	public ResponseEntity<List<Employee>> getAllEmployees() throws ResourceNotFoundException{
		return ResponseEntity.ok().body(employeeService.getAllEmployees());
	}
	
	@GetMapping("/employee/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable long id) throws ResourceNotFoundException{
		return ResponseEntity.ok().body(employeeService.getEmployeeById(id));
	}
	
	@PostMapping("/employee")
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee){
		return ResponseEntity.ok().body(this.employeeService.addEmployee(employee));
	}
	
	@PutMapping("/employee/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable long id, @RequestBody Employee employee) throws ResourceNotFoundException{
		employee.setId(id);
		return ResponseEntity.ok().body(this.employeeService.updateEmployee(employee));
	}
	
	@DeleteMapping("/employee/{id}")
	public ResponseEntity<?> deleteEmployee(@PathVariable long id){
		this.employeeService.deleteEmployee(id);
		return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.OK);
	}
	
	@GetMapping("/employees/salary")
	public List<Employee> orderEmployeeBySalary() throws ResourceNotFoundException{
		List<Employee> employee = employeeService.getEmployeeBySalaryOrder();
		return employee;

	}

}
