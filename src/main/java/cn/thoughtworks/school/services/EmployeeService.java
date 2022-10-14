package cn.thoughtworks.school.services;


import cn.thoughtworks.school.entities.Employee;
import cn.thoughtworks.school.repositories.CompanyRepository;
import cn.thoughtworks.school.repositories.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeService {
    final EmployeeRepository employeeRepository;
    final CompanyRepository companyRepository;

    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeesById(Long id) {
        Optional<Employee> employeeById = employeeRepository.findById(id);
        return employeeById.orElse(null);
    }

    public void addEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    public Employee updateEmployee(Long id, Employee newEmployee) {
        Optional<Employee> getEmployee = employeeRepository.findById(id);
        if (getEmployee.isPresent()) {
            Employee employee = getEmployee.get();
            employee.setAge(newEmployee.getAge());
            employee.setGender(newEmployee.getGender());
            employee.setName(newEmployee.getName());
            employee.setCompanyId(newEmployee.getCompanyId());
            return employeeRepository.save(employee);
        } else {
            return null;
        }

    }

    public List<Employee> getEmployeeByGender(String gender) {
        return employeeRepository.findAllByGender(gender);
    }

    public Page<Employee> findAll(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

}
