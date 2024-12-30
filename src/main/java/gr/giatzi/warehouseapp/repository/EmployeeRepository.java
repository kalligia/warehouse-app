package gr.giatzi.warehouseapp.repository;

import gr.giatzi.warehouseapp.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EmployeeRepository extends JpaRepository<Employee, Long> , JpaSpecificationExecutor<Employee> {
    Employee findByEmpId(Long empId);

    Employee findByEmail(String email);

}
