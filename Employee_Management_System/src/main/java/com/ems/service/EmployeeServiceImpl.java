package com.ems.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ems.model.Employee;
import com.ems.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@org.springframework.beans.factory.annotation.Autowired(required = true)
	// @Autowired
	private EmployeeRepository repo;

	public EmployeeRepository getRepo() {
		return repo;
	}

	public void setRepo(EmployeeRepository repo) {
		this.repo = repo;
	}

	@Override
	public List<Employee> getAllEmployees() {

		return repo.findAll();
	}

	@Override
	public void saveEmployee(Employee employee) {
		repo.save(employee);

	}

	@Override
	public Employee getEmployeeById(long id) {
		Optional<Employee> optional = repo.findById(id);
		Employee employee = null;
		if (optional.isPresent()) {
			employee = optional.get();
		} else {
			throw new RuntimeException("employee not found " + id);
		}
		return employee;
	}

	@Override
	public void deleteEmployee(long id) {
		this.repo.deleteById(id);

	}

}
