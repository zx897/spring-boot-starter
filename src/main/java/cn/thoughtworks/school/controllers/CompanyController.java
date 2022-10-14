package cn.thoughtworks.school.controllers;


import cn.thoughtworks.school.entities.Company;
import cn.thoughtworks.school.entities.Employee;
import cn.thoughtworks.school.services.CompanyService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/companies")
public class CompanyController {
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Company> getAllCompanies() {
        return companyService.findAllcompanys();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Company getCompanyById(@PathVariable("id") Long id) {
        return companyService.getCompanyById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Company addCompany(@RequestBody Company company) {
        return companyService.addCompany(company);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCompany(@PathVariable("id") Long id) {
        companyService.deleteCompany(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Company updateCompanyById(@PathVariable("id") Long id, @RequestBody Company newCompany) {
        return companyService.updateCompanyById(id, newCompany);
    }

    @GetMapping(value = "/{id}/employees")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public List<Employee> getEmployeeByCompanyId(@PathVariable Long id) {
        return companyService.getEmployeeByCompanyId(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<Company> getCompanyPage(@RequestBody int page, @RequestBody int pageSize) {
        Pageable pageable = new PageRequest(page - 1, pageSize);
        return companyService.findAll(pageable);
    }
}

