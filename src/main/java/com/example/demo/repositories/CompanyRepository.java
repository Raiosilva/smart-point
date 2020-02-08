package com.example.demo.repositories;

import com.example.demo.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;


@Transactional(readOnly = true)
public interface CompanyRepository extends JpaRepository<Company, Long> {

    Company findByCnpj(String cnpj);
}
