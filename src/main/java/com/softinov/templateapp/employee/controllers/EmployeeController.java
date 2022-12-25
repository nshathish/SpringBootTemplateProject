package com.softinov.templateapp.employee.controllers;

import com.softinov.templateapp.employee.exceptions.EmployeeNotFoundException;
import com.softinov.templateapp.employee.repositories.EmployeeRepository;
import com.softinov.templateapp.employee.entities.Employee;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


@RestController
@RequestMapping("api/v1/employees")
public class EmployeeController {
    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping
    public Iterable<Employee> get() {
        return employeeRepository.findAll();
    }

    @GetMapping("/{id}")
    public EntityModel<Employee> get(@PathVariable Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));

        return EntityModel.of(employee,
                linkTo(methodOn(EmployeeController.class).get(id)).withSelfRel(),
                linkTo(methodOn(EmployeeController.class).get()).withRel("employees")
        );
    }

    @PostMapping
    public Employee create(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    @PutMapping("/{id}")
    public Employee update(@RequestBody Employee employee, @PathVariable Long id) {
        return employeeRepository.findById(id)
                .map(emp -> {
                    emp.setName(employee.getName());
                    emp.setRole(employee.getRole());
                    return employeeRepository.save(emp);
                })
                .orElseGet(() -> {
                    employee.setId(id);
                    return employeeRepository.save(employee);
                });
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        employeeRepository.deleteById(id);
    }
}
