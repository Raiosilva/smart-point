package com.example.demo.repositories;

import com.example.demo.entities.Company;
import com.example.demo.entities.Employee;
import com.example.demo.enums.ProfileEnum;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CompanyRepository companyRepository;

    private static final String EMAIL = "email@email.com";
    private static final String CPF = "24291173474";

    @Before
    public void setUp() throws Exception {
        Company company = this.companyRepository.save(getDataCompany());
        this.employeeRepository.save(getDataEmployee(company));
    }

    @After
    public final void tearDown() {
        this.companyRepository.deleteAll();
    }

    @Test
    public void testSearchEmployeeByEmail() {
        Employee employee = this.employeeRepository.findByEmail(EMAIL);

        assertEquals(EMAIL, employee.getEmail());
    }

    @Test
    public void testSearchEmployeeByCpf() {
        Employee employee = this.employeeRepository.findByCpf(CPF);

        assertEquals(CPF, employee.getCpf());
    }

    @Test
    public void testSearchEmployeeByEmailCpf() {
        Employee employee = this.employeeRepository.findByCpfOrEmail(CPF, EMAIL);

        assertNotNull(employee);
    }

    @Test
    public void testSearchEmployeeByEmailOrCpfToEmailInvalid() {
        Employee employee = this.employeeRepository.findByCpfOrEmail(CPF, "email@invalido.com");

        assertNotNull(employee);
    }

    @Test
    public void testSearchEmployeeByEmailAndCpfToCpfInvalid() {
        Employee employee = this.employeeRepository.findByCpfOrEmail("12345678901", EMAIL);

        assertNotNull(employee);
    }

    private Employee getDataEmployee(Company company) throws NoSuchAlgorithmException {
        Employee employee = new Employee();
        employee.setName("Fulano de Tal");
        employee.setProfile(ProfileEnum.ROLE_USER);
        employee.setCpf(CPF);
        employee.setEmail(EMAIL);
        employee.setCompany(company);
        return employee;
    }

    private Company getDataCompany() {
        Company company = new Company();
        company.setCompanyName("Empresa de exemplo");
        company.setCnpj("51463645000100");
        return company;
    }

}
