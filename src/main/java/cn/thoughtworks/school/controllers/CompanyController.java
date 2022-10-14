package cn.thoughtworks.school.controllers;


import cn.thoughtworks.school.entities.Company;
import cn.thoughtworks.school.entities.Employee;
import cn.thoughtworks.school.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping(value = "/companies")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanies() {
        List<Company> companies = companyService.findAllcompanys();
        return new ResponseEntity<>(companies, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable("id") Long id) {
        Optional<Company> companyById = companyService.getCompanyById(id);
        return companyById.map(ResponseEntity::ok).orElse(null);
    }

    @PostMapping
    public ResponseEntity<Company> addCompany(@RequestBody Company company) {
        Optional<Company> addCompany = companyService.addCompany(company);
        if (!addCompany.isPresent()) {
            return null;
        } else {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Company> deleteCompany(@PathVariable("id") Long id) {
        Optional<Company> optionalCompany = companyService.deleteCompany(id);
        if (optionalCompany.isPresent())
            return null;
         else {
             return new ResponseEntity<>(HttpStatus.NOT_FOUND);}
    }

    @PutMapping("/{id}")
    public ResponseEntity<Company> updateCompanyById(@PathVariable("id") Long id, @RequestBody Company newCompany) {

        Optional<Company> optionalCompany = companyService.updateCompanyById(id, newCompany);
        if(optionalCompany.isPresent()){
            return null;
        }
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);

    }

    @GetMapping(value = "/{id}/employees")
    public ResponseEntity<Set<Employee>> getEmployeeByCompanyId(@PathVariable Long id) {
        Set<Employee> employeesList = companyService.getEmployeeByCompanyId(id);
        if (employeesList != null) {
        return  new ResponseEntity<>(employeesList, HttpStatus.OK);
        }
        return null;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Page<Company>> getCompanyPage(@RequestBody int page, @RequestBody int pageSize) {
        Pageable pageable = new PageRequest(page - 1, pageSize);
        Page<Company> companies = companyService.findAll(pageable);
        return new ResponseEntity<>(companies, HttpStatus.OK);
    }
}

