package cn.thoughtworks.school.repositories;

import cn.thoughtworks.school.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<List<Employee>> findByGender(String male);
}