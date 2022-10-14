package cn.thoughtworks.school.controllers;

import cn.thoughtworks.school.entities.Employee;
import cn.thoughtworks.school.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    //  /employees    #获取employee列表
    @GetMapping(value = "")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employeesList = employeeService.findAllEmployees();
        return new ResponseEntity<>(employeesList, HttpStatus.OK);
    }

    //  /employees/1  #获取某个具体employee
    @GetMapping(value = "/{id}")
    public Employee getEmployeeById(@PathVariable("id") Long id) {
        Optional<Employee> optionalEmployeeById = employeeService.getEmployeesById(id);
        return optionalEmployeeById.orElse(null);
    }

    //  /employees    #增加一个employee
    @PostMapping(value = "")
    public ResponseEntity<Map<String, String>> addEmployee(@RequestBody Employee employee) {
        employeeService.addEmployee(employee);
        Map<String, String> body = new HashMap<>();
        return new ResponseEntity<>(body, HttpStatus.CREATED);
    }

    //   /employees/1  #删除某个employee
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Employee> deleteEmployeeById(@PathVariable("id") Long id) {
        Optional<Employee> optionalDeletedEmployee = employeeService.deleteEmployee(id);
        return optionalDeletedEmployee.map(employee -> new ResponseEntity<>(employee, HttpStatus.NO_CONTENT)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    //  /employees/1  #更新某个employee
    @PutMapping(value = "/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee newEmployee) {
        Employee updetedEmployee = employeeService.updateEmployee(id, newEmployee);
        if(updetedEmployee != null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);}
        else return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    //  /employees/male   #筛选出所有男性Employee
    @GetMapping(value = "/male")
    public ResponseEntity<List<Employee>> getEmployeeByGender() {
        List<Employee> employeeList = employeeService.getEmployeeByGender("male");
        return new ResponseEntity<>(employeeList, HttpStatus.OK);
    }

    //      /employees/page/1/pageSize/5  #分页查询，page等于1，pageSize等于5
    @GetMapping(value = "/page/{page}/pageSize/{pageSize}")
    public ResponseEntity<Page<Employee>> getEmployeeByPage(@PathVariable int page, @PathVariable int pageSize) {
        Pageable pageable = new PageRequest(page - 1, pageSize);
        Page<Employee> employees = employeeService.findAll(pageable);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }


}

