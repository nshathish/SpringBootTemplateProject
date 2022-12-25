package com.softinov.templateapp.employee.config;

import com.softinov.templateapp.employee.entities.Employee;
import com.softinov.templateapp.employee.repositories.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(EmployeeRepository repository) {
        return args -> {
            log.info("Preloading: " + repository.save(new Employee("Bilbo Bagins", "burglar")));
            log.info("Preloading: " + repository.save(new Employee("Frodo Bagins", "thief")));
        };
    }
}
