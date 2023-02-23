package com.employee.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.employee.dao.EmployeeDao;
import com.employee.entity.Employee;
import com.employee.exception.ResourceNotFoundException;

@Service
@Component
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
    EmployeeDao employeeDao;

	@Override
	public Employee addEmployee(Employee employee) {
		return employeeDao.save(employee);
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		Optional<Employee> employeedb = this.employeeDao.findById(employee.getId());
		
		if(employeedb.isPresent()) {
			Employee employeeUpdate = employeedb.get();
			employeeUpdate.setId(employee.getId());
			employeeUpdate.setFirstName(employee.getFirstName());
			employeeUpdate.setLastName(employee.getLastName());
			employeeUpdate.setAge(employee.getAge());
			employeeUpdate.setMobileNumber(employee.getMobileNumber());
			employeeUpdate.setEmailId(employee.getEmailId());
			employeeUpdate.setSalary(employee.getSalary());
			employeeUpdate.setProfile(employee.getProfile());
			employeeDao.save(employeeUpdate);
			return employeeUpdate;
		}else {
			throw new ResourceNotFoundException("Record not found with ID : " + employee.getId());
		}		
	}

	@Override
	public List<Employee> getAllEmployees() {
		return this.employeeDao.findAll();
	}

	@Override
	public Employee getEmployeeById(long employeeId) {
		
        Optional<Employee> employeedb = this.employeeDao.findById(employeeId);
		
		if(employeedb.isPresent()) {
		  return employeedb.get();
		}else {
			throw new ResourceNotFoundException("Record not found with ID : " + employeeId);
		}
	}

	@Override
	public void deleteEmployee(long employeeId) {
		
		Optional<Employee> employeedb = this.employeeDao.findById(employeeId);
		
		if(employeedb.isPresent()) {
		  this.employeeDao.delete(employeedb.get());
		}else {
			throw new ResourceNotFoundException("Record not found with ID : " + employeeId);
		}		
	}

	@Override
	public List<Employee> getEmployeeBySalaryOrder() throws ResourceNotFoundException {
		
		List<Employee> empSal = employeeDao.findByOrderBySalary();
		if (empSal.isEmpty()) {
			throw new ResourceNotFoundException("Sorry!! Employees does not exist!! ");
		}
		return empSal;
	}	
}
