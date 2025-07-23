package com.seleniumexpress.service;

import java.util.List;
import java.util.Map;

import com.seleniumexpress.dto.EmployeeDTO;

public interface EmployeeService {

	void deleteEmployeeById(Integer employeeId);

	EmployeeDTO findById(Integer employeeId);

	List<EmployeeDTO> findAll();

	EmployeeDTO saveEmployee(EmployeeDTO employee);

	EmployeeDTO updateEmployee(Integer employeeId, EmployeeDTO employee);

	EmployeeDTO partialUpdateEmployee(Integer employeeId, Map<String, Object> empDetails);

}
