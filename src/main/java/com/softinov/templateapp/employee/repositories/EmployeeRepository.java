package com.softinov.templateapp.employee.repositories;

import com.softinov.templateapp.employee.entities.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
}
