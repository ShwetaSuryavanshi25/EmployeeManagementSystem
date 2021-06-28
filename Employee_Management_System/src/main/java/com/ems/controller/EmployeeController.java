package com.ems.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.ems.model.Employee;
import com.ems.service.EmployeeService;

@Controller
public class EmployeeController {

	@Autowired

	private EmployeeService employeeService;

	@GetMapping("/")
	public String viewPage(Model model) {
		model.addAttribute("list", employeeService.getAllEmployees());
		return "index";
	}

	@GetMapping("/Form")
	public String Form(Model model) {
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		return "newEmployee";
	}

	@PostMapping("/saveEmployee")
	public String saveEmp(@ModelAttribute("employee") Employee employee) {
		employeeService.saveEmployee(employee);
		return "redirect:/";
	}

	@GetMapping("/updateForm/{id}")
	public String update(@PathVariable(value = "id") Long id, Model model) {
		Employee employee = employeeService.getEmployeeById(id);
		model.addAttribute("employee", employee);
		return "update_employee";
	}

	@GetMapping("/deleteForm/{id}")
	public String deleteEmployee(@PathVariable(value = "id") Long id, Model model) {
		this.employeeService.deleteEmployee(id);
		return "redirect:/";
	}
}
