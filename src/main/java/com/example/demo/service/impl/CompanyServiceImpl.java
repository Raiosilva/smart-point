package com.example.demo.service.impl;

import com.example.demo.entities.Company;
import com.example.demo.repositories.CompanyRepository;
import com.example.demo.service.CompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    private static final Logger log = LoggerFactory.getLogger(CompanyServiceImpl.class);

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public Optional<Company> searchByCnpj(String cnpj) {
        log.info("Buscando uma empresa para o CNPJ {}", cnpj);
        return Optional.ofNullable(companyRepository.findByCnpj(cnpj));
    }

    @Override
    public Company post(Company company) {
        log.info("Persistindo empresa: {}", company);
        return this.companyRepository.save(company);
    }
}
