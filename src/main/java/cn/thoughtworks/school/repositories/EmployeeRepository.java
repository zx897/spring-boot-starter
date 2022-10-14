package cn.thoughtworks.school.repositories;

import cn.thoughtworks.school.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findAllByGender(String gender);

    List<Employee> findAllByCompanyId(Long companyId);
}