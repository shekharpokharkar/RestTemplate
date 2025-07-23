package com.example.demo.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.entity.EmployeeDTO;

@RestController
public class EmployeeConsumer {

	@Autowired
	public RestTemplate restTemplate;

	@GetMapping("/")
	public List<EmployeeDTO> getAllEmployee() {
		String url = "http://localhost:8080/v1/employee/";
		ResponseEntity<EmployeeDTO[]> forEntity = restTemplate.getForEntity(url, EmployeeDTO[].class);

		EmployeeDTO[] body = forEntity.getBody();

		System.out.println("Body" + body);
		System.out.println("Status Code" + forEntity.getStatusCode());
		System.out.println("Heasder" + forEntity.getHeaders());
		System.out.println("Sattus value" + forEntity.getStatusCodeValue());

		List<EmployeeDTO> asList = Arrays.asList(body);

		return asList;
	}

	@GetMapping("/{id}")
	public EmployeeDTO getEmployeeById(@PathVariable("id") Integer employeeId) {
		String url = "http://localhost:8080/v1/employee/{id}";
		ResponseEntity<EmployeeDTO> forEntity = restTemplate.getForEntity(url, EmployeeDTO.class, employeeId);

		EmployeeDTO body = forEntity.getBody();

		System.out.println("Body" + body);
		System.out.println("Status Code" + forEntity.getStatusCode());
		System.out.println("Heasder" + forEntity.getHeaders());
		System.out.println("Sattus value" + forEntity.getStatusCodeValue());

		return body;
	}

	@PostMapping("/")
	public EmployeeDTO addEmployee(@RequestBody EmployeeDTO employee) {
		String url = "http://localhost:8080/v1/employee/";
		EmployeeDTO postForObject = restTemplate.postForObject(url, employee, EmployeeDTO.class);

		System.out.println("Body" + postForObject);

		return postForObject;
	}

	@PostMapping("/1")
	public ResponseEntity<EmployeeDTO> addEmployeeByEntity(@RequestBody EmployeeDTO employee) {
		String url = "http://localhost:8080/v1/employee/";
		ResponseEntity<EmployeeDTO> postForEntity = restTemplate.postForEntity(url, employee, EmployeeDTO.class);

		System.out.println("Body" + postForEntity.getBody());
		System.out.println("Status Code" + postForEntity.getStatusCodeValue());
		System.out.println("Header" + postForEntity.getHeaders());
		System.out.println("Status" + postForEntity.getStatusCode());
		return postForEntity;
	}

	/*
	 * @PutMapping("/{id}") public String partialUpdateByput(@PathVariable("id")
	 * Integer employeeId, @RequestBody EmployeeDTO employee) { String url =
	 * "http://localhost:8080/v1/employee/{id}";
	 * 
	 * restTemplate.put(url, employee, employeeId);
	 * 
	 * return "Student Updated Successfully"; }
	 */

	@PutMapping("/emp/{id1}")
	public ResponseEntity<EmployeeDTO> partialUpdateByEntity(@PathVariable("id1") Integer employeeId,
			@RequestBody EmployeeDTO employee) {
		String url = "http://localhost:8080/v1/employee/{id}";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<EmployeeDTO> entity = new HttpEntity<>(employee, headers);

		ResponseEntity<EmployeeDTO> exchange = restTemplate.exchange(url, HttpMethod.PUT, entity, EmployeeDTO.class,
				employeeId);

		System.out.println("Body:" + exchange.getBody());
		return exchange;
	}

	@DeleteMapping("/emp/{id1}")
	public ResponseEntity<Void> deleteEmployeeId(@PathVariable("id1") Integer employeeId) {
		String url = "http://localhost:8080/v1/employee/{id}";

		HttpHeaders headers = new HttpHeaders();
		

		HttpEntity<Void> entity = new HttpEntity<>(headers);
		ResponseEntity<Void> exchange = restTemplate.exchange(url, HttpMethod.DELETE, entity, Void.class, employeeId);

	
		return exchange;
	}
}
