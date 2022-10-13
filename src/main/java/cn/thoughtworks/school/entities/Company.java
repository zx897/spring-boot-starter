package cn.thoughtworks.school.entities;


import javax.persistence.*;
import java.util.Set;

@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String companyName;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "companyId")
    private Set<Employee> employees;

    public Company() {
    }

    public Company(Long id) {
        this.id = id;
    }

    public  Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }
}
