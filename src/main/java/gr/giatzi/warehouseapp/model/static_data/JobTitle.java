package gr.giatzi.warehouseapp.model.static_data;

import gr.giatzi.warehouseapp.model.Employee;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "job_titles")
public class JobTitle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Getter(AccessLevel.PRIVATE)
    @OneToMany(mappedBy = "title")
    private Set<Employee> employees = new HashSet<>();

    public Set<Employee> getAllEmployees() {
        if (employees == null) employees = new HashSet<>();
        return Collections.unmodifiableSet(employees);
    }

    public void addEmployee(Employee employee) {
        if (employees == null) employees = new HashSet<>();
        employees.add(employee);
        employee.setTitle(this);
    }
}
