package com.example.demo.repositories;

import com.example.demo.entities.Company;
import com.example.demo.entities.Employee;
import com.example.demo.entities.Launch;
import com.example.demo.enums.ProfileEnum;
import com.example.demo.enums.TypeEnum;
import com.example.demo.utils.PasswordUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class LaunchRepositoryTest {

    @Autowired
    private LaunchRepository launchRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CompanyRepository companyRepository;

    private Long employeeId;

    @Before
    public void setUp() throws Exception {
        Company company = this.companyRepository.save(getDataCompany());

        Employee employee = this.employeeRepository.save(getDataEmployee(company));
        this.employeeId = employee.getId();

        this.launchRepository.save(getDataLaunch(employee));
        this.launchRepository.save(getDataLaunch(employee));
    }

    @After
    public void tearDown() throws Exception {
        this.companyRepository.deleteAll();
    }

    @Test
    public void testSearchLaunchByEmployeeId() {
        List<Launch> launch = this.launchRepository.findByEmployeeId(employeeId);

        assertEquals(2, launch.size());
    }

    @Test
    public void testSearchLaunchByEmployeeIdPaginate() {
        PageRequest page = PageRequest.of(0, 1);
        Page<Launch> launch = this.launchRepository.findByEmployeeId(employeeId, page);

        assertEquals(2, launch.getTotalElements());
    }

    private Launch getDataLaunch(Employee employee) {
        Launch launch = new Launch();
        launch.setDate(new Date());
        launch.setType(TypeEnum.INITIAL_LUNCH);
        launch.setEmployee(employee);
        return launch;
    }

    private Employee getDataEmployee(Company company) throws NoSuchAlgorithmException {
        Employee employee = new Employee();
        employee.setName("Fulano de Tal");
        employee.setProfile(ProfileEnum.ROLE_USER);
        employee.setPassword(PasswordUtils.generatorBcrypt("123456"));
        employee.setCpf("24291173474");
        employee.setEmail("email@email.com");
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
