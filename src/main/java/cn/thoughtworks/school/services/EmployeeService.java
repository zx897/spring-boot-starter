package cn.thoughtworks.school.services;


import cn.thoughtworks.school.entities.Employee;
import cn.thoughtworks.school.repositories.CompanyRepository;
import cn.thoughtworks.school.repositories.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    final EmployeeRepository employeeRepository;
    final CompanyRepository companyRepository;

    public EmployeeService(CompanyRepository companyRepository, EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
        this.companyRepository = companyRepository;
    }

    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeesById(Long id) {
        Optional<Employee> employeeById = employeeRepository.findById(id);
        return employeeById.map(Optional::of).orElse(null);
    }

    public void addEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    public Optional<Employee> deleteEmployee(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            employeeRepository.delete(employee.get());
            return employee;
        } else return Optional.empty();
    }

    public Employee updateEmployee(Long id, Employee newEmployee) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            employee.get().setAge(newEmployee.getAge());
            employee.get().setGender(newEmployee.getGender());
            employee.get().setName(newEmployee.getName());
            employee.get().setCompanyId(newEmployee.getCompanyId());
            return employeeRepository.save(employee.get());
        } else {
            return null;
        }

    }

    public List<Employee> getEmployeeByGender(String gender) {
        return employeeRepository.findByGender("male");
    }

    public Page<Employee> findAll(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

}
