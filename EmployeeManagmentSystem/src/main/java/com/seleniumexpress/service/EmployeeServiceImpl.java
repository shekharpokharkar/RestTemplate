package com.seleniumexpress.service;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.seleniumexpress.Entity.Employee;
import com.seleniumexpress.Repository.EmployeeRepository;
import com.seleniumexpress.dto.EmployeeDTO;
import com.seleniumexpress.exception.EmployeeNotFoundException;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public List<EmployeeDTO> findAll() {
		return employeeRepository.findAll().stream().map(emp -> {
			EmployeeDTO dto = new EmployeeDTO();
			BeanUtils.copyProperties(emp, dto);
			return dto;

		}).toList();
	}

	@Override
	public EmployeeDTO findById(Integer employeeId) {
		return employeeRepository.findById(employeeId).map(emp -> {
			EmployeeDTO dto = new EmployeeDTO();
			BeanUtils.copyProperties(emp, dto);
			return dto;

		}).orElseThrow(() -> new EmployeeNotFoundException("Employee with given Id is not found"));
	}

	@Override
	public EmployeeDTO saveEmployee(EmployeeDTO employee) {

		Employee emp = new Employee();
		BeanUtils.copyProperties(employee, emp);
		EmployeeDTO dto = new EmployeeDTO();
		BeanUtils.copyProperties(employeeRepository.save(emp), dto);
		return dto;
	}

	@Override
	public EmployeeDTO updateEmployee(Integer employeeId, EmployeeDTO employee) {
		Employee employee2 = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee with given Id is not found"));

		BeanUtils.copyProperties(employee, employee2);

		employee2.setEmployeeId(employeeId);
		Employee save = employeeRepository.save(employee2);
		EmployeeDTO dto = new EmployeeDTO();
		BeanUtils.copyProperties(save, dto);
		return dto;
	}

	@Override
	public void deleteEmployeeById(Integer employeeId) {
		Employee employee2 = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee with given Id is not found"));

		employeeRepository.delete(employee2);
	}

	@Override
	public EmployeeDTO partialUpdateEmployee(Integer employeeId, Map<String, Object> empDetails) {

		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee with given Id is not found"));

		empDetails.forEach((jsonKey, value) -> {
			String fieldName = resolveFieldName(jsonKey); // Handles @JsonProperty
			Field field = findFieldRecursively(Employee.class, fieldName);

			if (field != null) {
				field.setAccessible(true);
				Object convertedValue = convertValue(value, field.getType());
				ReflectionUtils.setField(field, employee, convertedValue);
			}
		});

		System.out.println("Before employee" + employee);
		Employee save = employeeRepository.save(employee);

		System.out.println("After Saved employee" + save);
		EmployeeDTO dto = new EmployeeDTO();
		BeanUtils.copyProperties(employee, dto);
		return dto;

	}

	private Field findFieldRecursively(Class<?> clazz, String fieldName) {
		while (clazz != null) {
			for (Field field : clazz.getDeclaredFields()) {
				if (field.getName().equals(fieldName)) {
					return field;
				}
			}
			clazz = clazz.getSuperclass();
		}
		return null;
	}

	private Object convertValue(Object value, Class<?> targetType) {
		if (value == null)
			return null;

		if (targetType.equals(LocalDate.class)) {
			return LocalDate.parse(value.toString(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		}
		if (targetType.equals(Double.class) || targetType.equals(double.class)) {
			return Double.parseDouble(value.toString());
		}
		if (targetType.equals(Integer.class) || targetType.equals(int.class)) {
			return Integer.parseInt(value.toString());
		}
		if (targetType.equals(Boolean.class) || targetType.equals(boolean.class)) {
			return Boolean.parseBoolean(value.toString());
		}

		// Add more conversions if needed

		return value;
	}

	private String resolveFieldName(String jsonKey) {
		for (Field field : Employee.class.getDeclaredFields()) {
			JsonProperty annotation = field.getAnnotation(JsonProperty.class);
			if (annotation != null && annotation.value().equalsIgnoreCase(jsonKey)) {
				return field.getName();
			}
		}
		return jsonKey; // fallback
	}

}
