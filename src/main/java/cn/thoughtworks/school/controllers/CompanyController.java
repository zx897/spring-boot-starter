package cn.thoughtworks.school.controllers;


import cn.thoughtworks.school.entities.Company;
import cn.thoughtworks.school.entities.Employee;
import cn.thoughtworks.school.services.CompanyService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
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

    @GetMapping("api/examples/{id}")
    public ResponseEntity<Company> findOne(@PathVariable Long id) {
        if(companyService.getCompanyById(id).isPresent()){
            return ResponseEntity.ok(companyService.getCompanyById(id).get());
        }
        return null;
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Company> getAllCompanies(){
        return companyService.findAllcompanys();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable("id") Long id){
        Company company = companyService.getCompanyById(id);
        return new ResponseEntity<>(company, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity addCompany(@RequestBody Company company){
        companyService.addCompany(company);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Optional<Company>> deleteCompany(@PathVariable("id") Long id) {

        if (companyService.getCompanyById(id).isPresent()) {
            companyService.deleteCompany(id);
            return new ResponseEntity<>(companyService.getCompanyById(id), HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity updateCompany(@PathVariable("id") Long id, @RequestBody Company newCompany) {

        Optional<Company> company = companyService.getCompanyById(id);
        if (company.isPresent()) {
            company.get().setCompanyName(newCompany.getCompanyName());
            companyService.addCompany(company.get());
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @GetMapping(value = "/{id}/employees")
    public ResponseEntity<Set<Employee>> getEmployeeByCompanyId(@PathVariable Long id){
        if (companyService.getCompanyById(id).isPresent()) {
            Company company = companyService.getCompanyById(id).get();
            Set<Employee> employees = company.getEmployees();
            return new ResponseEntity<>(employees, HttpStatus.OK);
        }
        return new ResponseEntity(null, HttpStatus.OK);
    }

    @GetMapping(value = "/page/{page}/pageSize/{pageSize}")
    public ResponseEntity<Page<Company>> getCompanyPage(@PathVariable int page, @PathVariable int pageSize){
        Pageable pageable = new PageRequest(page - 1, pageSize);
        Page<Company> companies = companyService.findAll(pageable);
        return new ResponseEntity<>(companies, HttpStatus.OK);
    }
}

