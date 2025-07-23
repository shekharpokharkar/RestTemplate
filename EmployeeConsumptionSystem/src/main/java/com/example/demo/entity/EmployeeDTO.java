package com.example.demo.entity;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class EmployeeDTO {

	@JsonProperty(value = "Emp_ID")
	private Integer employeeId;

	@JsonProperty(value = "Emp_Name")
	private String employeeName;

	@JsonProperty(value = "Emp_Department")
	private String employeeDepartment;

	@JsonProperty(value = "Emp_Marriatal_Status")
	private Boolean married;

	@JsonFormat(pattern = "dd-MM-yyyy")
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@JsonProperty(value = "Emp_DOJ")
	private LocalDate joiningDate;

	@JsonProperty(value = "Emp_Salary")
	private Double employeeSalary;

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeeDepartment() {
		return employeeDepartment;
	}

	public void setEmployeeDepartment(String employeeDepartment) {
		this.employeeDepartment = employeeDepartment;
	}

	public Boolean getMarried() {
		return married;
	}

	public void setMarried(Boolean married) {
		this.married = married;
	}

	public LocalDate getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(LocalDate joiningDate) {
		this.joiningDate = joiningDate;
	}

	public Double getEmployeeSalary() {
		return employeeSalary;
	}

	public void setEmployeeSalary(Double employeeSalary) {
		this.employeeSalary = employeeSalary;
	}

	public EmployeeDTO(Integer employeeId, String employeeName, String employeeDepartment, Boolean married,
			LocalDate joiningDate, Double employeeSalary) {

		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.employeeDepartment = employeeDepartment;
		this.married = married;
		this.joiningDate = joiningDate;
		this.employeeSalary = employeeSalary;
	}

	public EmployeeDTO() {
	}

	@Override
	public String toString() {
		return "EmployeeDTO [employeeId=" + employeeId + ", employeeName=" + employeeName + ", employeeDepartment="
				+ employeeDepartment + ", married=" + married + ", joiningDate=" + joiningDate + ", employeeSalary="
				+ employeeSalary + "]";
	}

}
