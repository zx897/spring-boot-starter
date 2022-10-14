package cn.thoughtworks.school.services;

import cn.thoughtworks.school.entities.Company;

import cn.thoughtworks.school.entities.Employee;
import cn.thoughtworks.school.repositories.CompanyRepository;
import java.util.Collections;

import cn.thoughtworks.school.repositories.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class CompanyService {
    @Autowired
    final CompanyRepository companyRepository;
    final EmployeeRepository employeeRepository;

    public List<Company> findAllcompanys() {
        return companyRepository.findAll();
    }

    public Company getCompanyById(Long id){
        Optional<Company> companyById = companyRepository.findById(id);
        return companyById.orElse(null);
    }
    public Company addCompany(Company company){
        return companyRepository.save(company);
    }

    public void deleteCompany(Long id){
        if (companyRepository.existsById(id)){
            companyRepository.deleteById(id);
        }
    }

    public Company updateCompanyById(Long id,Company newCompany){
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (optionalCompany.isPresent()) {
            Company company = optionalCompany.get();
            company.setCompanyName(newCompany.getCompanyName());
            companyRepository.save(company);
            return company;
        }
        return null;
    }

    public List<Employee> getEmployeeByCompanyId( Long companyId){
        return  employeeRepository.findAllByCompanyId(companyId);
    }

    public Page<Company> findAll(Pageable pageable){
        return companyRepository.findAll(pageable);
    }
}
