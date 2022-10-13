package cn.thoughtworks.school.services;



import cn.thoughtworks.school.entities.Company;
import cn.thoughtworks.school.entities.Employee;
import cn.thoughtworks.school.repositories.CompanyRepository;
import cn.thoughtworks.school.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EmployeeService {
    final EmployeeRepository employeeRepository;
    final CompanyRepository companyRepository;

    public EmployeeService(CompanyRepository companyRepository, EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
        this.companyRepository = companyRepository;
    }

    //controller -> service -> repository
    //              处理业务逻辑
    public ResponseEntity<List<Employee>> findAllEmployees() {
        List<Employee> employeesList = employeeRepository.findAll();
        return new ResponseEntity<>(employeesList, HttpStatus.OK);
    }

//    public List<Employee> findPageEmployee(Pageable pageable){
//
//    }

    public Optional<Employee> getEmployeesById(Long id) {
        return employeeRepository.findById(id);
    }

    public void addEmployee( Employee employee) {
//        Employee employee = new Employee(name, age, gender, companyId);
        employeeRepository.save(employee);
    }

    public ResponseEntity<?> deleteEmployee(Long id){
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee != null) {
            employeeRepository.delete(employeeRepository.findById(id).get());
            return new ResponseEntity<>(employeeRepository.findById(id), HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity updateEmployee(Long id, Employee newEmployee) throws Exception {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            employee.get().setAge(newEmployee.getAge());
            employee.get().setGender(newEmployee.getGender());
            employee.get().setName(newEmployee.getName());
            employee.get().setCompanyId(newEmployee.getCompanyId());
            employeeRepository.save(employee.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

    }

    public Optional<List<Employee>> getEmployeeByGender(String gender){
        return  employeeRepository.findByGender("male");
    }

    public Page<Employee> findAll(Pageable pageable){
        return employeeRepository.findAll(pageable);
    };

    public void updateEmployee( Employee employee) {
//        Employee employee = new Employee(name, age, gender, companyId);
        employeeRepository.save(employee);
    }






}
