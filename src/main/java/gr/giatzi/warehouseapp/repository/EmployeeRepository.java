package gr.giatzi.warehouseapp.repository;

import gr.giatzi.warehouseapp.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> , JpaSpecificationExecutor<Employee> {
    List<Employee> findByTitleId(Long id);
    List<Employee> findByLastname(String lastname);
    Employee findByEmpId(Long empId);

}
