package cn.thoughtworks.school.controllers;

import cn.thoughtworks.school.entities.Employee;
import cn.thoughtworks.school.repositories.EmployeeRepository;
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
    public ResponseEntity<?> getAllEmployees() throws Exception {
        return employeeService.findAllEmployees();
    }

    //  /employees/1  #获取某个具体employee
    @GetMapping(value = "/{id}")
    public Employee getEmployeeById(@PathVariable("id") Long id) throws Exception {
        return employeeService.getEmployeesById(id).get();
    }

    //  /employees    #增加一个employee
    @PostMapping(value = "")
    public ResponseEntity<Map<String, String>> addEmployee(
            @RequestBody Employee employee) throws Exception {
//        employeeService.addEmployee(employee.getName(), employee.getAge(), employee.getGender(),employee.getCompanyId());
        employeeService.addEmployee(employee);

        Map<String, String> body = new HashMap<>();
        return new ResponseEntity<>(body, HttpStatus.CREATED);
    }

    //   /employees/1  #删除某个employee
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("id") Long id) throws Exception {
        if (employeeService.getEmployeesById(id) != null) {
            employeeService.deleteEmployee(id);
            return new ResponseEntity<>(employeeService.getEmployeesById(id), HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    //  /employees/1  #更新某个employee
    @PutMapping(value = "/{id}")
    public ResponseEntity updateEmployee(@PathVariable Long id, @RequestBody Employee newEmployee) throws Exception {
        Employee employee = employeeService.getEmployeesById(id).get();
       employee.setAge(newEmployee.getAge());
        employee.setGender(newEmployee.getGender());
        employee.setName(newEmployee.getName());
        employee.setCompanyId(newEmployee.getCompanyId());
        employeeService.updateEmployee(employee);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //  /employees/male   #筛选出所有男性Employee
    @GetMapping(value = "/male")
    public ResponseEntity getEmployeeByGender() throws Exception {
        List<Employee> employeeList = employeeService.getEmployeeByGender("male").get();
        return new ResponseEntity<>(employeeList, HttpStatus.OK);
    }

//      /employees/page/1/pageSize/5  #分页查询，page等于1，pageSize等于5
    @GetMapping(value = "/page/{page}/pageSize/{pageSize}")
    public ResponseEntity getEmployeeByPage(@PathVariable int page, @PathVariable int pageSize) throws Exception {
        Pageable pageable = new PageRequest(page-1, pageSize);
        Page employees = employeeService.findAll(pageable);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }



}

