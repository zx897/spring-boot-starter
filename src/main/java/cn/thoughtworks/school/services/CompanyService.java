package cn.thoughtworks.school.services;

import cn.thoughtworks.school.entities.Company;

import cn.thoughtworks.school.entities.Employee;
import cn.thoughtworks.school.repositories.CompanyRepository;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CompanyService {
    @Autowired
    final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<Company> findAllcompanys() {
        return companyRepository.findAll();
    }

    public Optional<Company> getCompanyById(Long id){
        return companyRepository.findById(id);
    }
    public Optional<Company> addCompany(Company company){
        companyRepository.save(company);
        return Optional.empty();
    }

    public Optional<Company> deleteCompany(Long id){
        if (companyRepository.existsById(id)){
            companyRepository.deleteById(id);
            return companyRepository.findById(id);
        }
        return Optional.empty();
    }

    public Optional<Company> updateCompanyById(Long id,Company newCompany){
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (optionalCompany.isPresent()) {
            Company company = optionalCompany.get();
            company.setCompanyName(newCompany.getCompanyName());
            companyRepository.save(company);
        }
        return Optional.empty();
    }

    public Set<Employee> getEmployeeByCompanyId( Long id){
        if(companyRepository.findById(id).isPresent()){
            Company company = companyRepository.findById(id).get();
            return  company.getEmployees();
        }
        return Collections.emptySet();
    }

    public Page<Company> findAll(Pageable pageable){
        return companyRepository.findAll(pageable);
    }
}
