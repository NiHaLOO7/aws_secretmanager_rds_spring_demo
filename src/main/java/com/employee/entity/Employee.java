package com.employee.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="EMPLOYEE")
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="EMP_ID")
	private Long id;
	
	@Column(name="FIRST_NAME")
	private String firstName;
	
	@Column(name="LAST_NAME")
	private String lastName;
	
	@Column(name="AGE")
	private int age;
	
	@Column(name="MOBILE_NO")
	private String mobileNumber;

	@Column(name="EMAIL_ID")
	private String emailId;
	
	@Column(name="SALARY")
	private double salary;
	
	@Column(name="PROFILE")
	private String profile;

	public Employee() {
		super();
	}

	public Employee(long i, String firstName, String lastName, int age, String mobileNumber, String emailId,
			double salary, String profile) {
		super();
		this.id = i;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.mobileNumber = mobileNumber;
		this.emailId = emailId;
		this.salary = salary;
		this.profile = profile;
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", age=" + age
				+ ", mobileNumber=" + mobileNumber + ", emailId=" + emailId + ", salary=" + salary + ", profile="
				+ profile + "]";
	}
	
}
