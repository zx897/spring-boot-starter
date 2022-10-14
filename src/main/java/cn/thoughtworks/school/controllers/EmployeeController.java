package cn.thoughtworks.school.controllers;

import cn.thoughtworks.school.entities.Employee;
import cn.thoughtworks.school.services.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/employees")
@ResponseStatus(HttpStatus.OK)
public class EmployeeController {
    private EmployeeService employeeService;

    //  /employees    #获取employee列表
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.findAllEmployees();
    }

    //  /employees/1  #获取某个具体employee
    @GetMapping(value = "/{id}")
    public Employee getEmployeeById(@PathVariable("id") Long id) {
        return employeeService.getEmployeesById(id);
    }

    @GetMapping(value = "/gender/{gender}")
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getEmployeeByGender(@PathVariable("gender") String gender) {
        return employeeService.getEmployeeByGender(gender);
    }

    //  /employees    #增加一个employee
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    //TODO
    public void addEmployee(@RequestBody Employee employee) {
        employeeService.addEmployee(employee);
    }

    //   /employees/1  #删除某个employee
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployeeById(@PathVariable("id") Long id) {
        employeeService.deleteEmployee(id);
    }

    //  /employees/1  #更新某个employee
    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee newEmployee) {
        return employeeService.updateEmployee(id, newEmployee);
    }

    //          /employees/page/1/pageSize/5  #分页查询，page等于1，pageSize等于5
    @GetMapping("/page")
    @ResponseStatus(HttpStatus.OK)
    public Page<Employee> getEmployeeByPage(@RequestParam int page, @RequestParam int pageSize) {
        Pageable pageable = new PageRequest(page - 1, pageSize);
        return employeeService.findAll(pageable);
    }


}

