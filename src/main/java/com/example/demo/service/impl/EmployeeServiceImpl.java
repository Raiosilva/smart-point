package com.example.demo.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.demo.entities.Employee;
import com.example.demo.repositories.EmployeeRepository;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private static final Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee post(Employee employee) {
        log.info("Persistindo funcionário: {}", employee);
        return this.employeeRepository.save(employee);
    }

    @Override
    public Optional<Employee> searchByCpf(String cpf) {
        log.info("Buscando funcionário pelo CPF {}", cpf);
        return Optional.ofNullable(this.employeeRepository.findByCpf(cpf));
    }

    @Override
    public Optional<Employee> searchByEmail(String email) {
        log.info("Buscando funcionário pelo email {}", email);
        return Optional.ofNullable(this.employeeRepository.findByEmail(email));
    }

    @Override
    public Optional<Optional<Employee>> searchById(Long id) {
        log.info("Buscando funcionário pelo IDl {}", id);
        return Optional.ofNullable(this.employeeRepository.findById(id));
    }
}
