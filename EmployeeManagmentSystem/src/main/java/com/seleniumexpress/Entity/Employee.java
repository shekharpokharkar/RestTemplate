package com.seleniumexpress.Entity;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Employee_Rest")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Employee_Id")
	@JsonProperty(value = "Emp_ID")
	private Integer employeeId;

	@Column(name = "Employee_Name")
	@JsonProperty(value = "Emp_Name")
	private String employeeName;

	@Column(name = "Employee_Departmet")
	@JsonProperty(value = "Emp_Department")
	private String employeeDepartment;

	@Column(name = "Employee_Married")
	@JsonProperty(value = "Emp_Marriatal_Status")
	private Boolean married;

	@JsonFormat(pattern = "dd-MM-yyyy")
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@Column(name = "Employee_Joining_Date")
	@JsonProperty(value = "Emp_DOJ")
	private LocalDate joiningDate;

	@Column(name = "Employee_Salary")
	@JsonProperty(value = "Emp_Salary")
	private Double employeeSalary;

}
