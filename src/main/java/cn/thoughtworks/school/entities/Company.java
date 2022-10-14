package cn.thoughtworks.school.entities;


import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String companyName;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "companyId")
    private Set<Employee> employees;

}
