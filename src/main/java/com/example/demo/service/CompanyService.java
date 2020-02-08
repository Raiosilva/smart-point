package com.example.demo.service;

import com.example.demo.entities.Company;

import java.util.Optional;

public interface CompanyService {

    Optional<Company> searchByCnpj(String cnpj);

    Company post(Company company);
}
