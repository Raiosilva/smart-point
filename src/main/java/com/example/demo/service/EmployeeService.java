package com.example.demo.service;

import com.example.demo.entities.Employee;

import java.util.Optional;

public interface EmployeeService {

    Employee post(Employee employee);

    Optional<Employee> searchByCpf(String cpf);

    Optional<Employee> searchByEmail(String email);

    Optional<Optional<Employee>> searchById(Long id);
}
