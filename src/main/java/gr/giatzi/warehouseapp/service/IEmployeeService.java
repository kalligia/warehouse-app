package gr.giatzi.warehouseapp.service;

import gr.giatzi.warehouseapp.core.exceptions.EntityAlreadyExistsException;
import gr.giatzi.warehouseapp.core.exceptions.EntityInvalidArgumentException;
import gr.giatzi.warehouseapp.core.exceptions.EntityNotFoundException;
import gr.giatzi.warehouseapp.dto.EmployeeInsertDTO;
import gr.giatzi.warehouseapp.dto.EmployeeReadOnlyDTO;
import gr.giatzi.warehouseapp.dto.EmployeeUpdateDTO;
import gr.giatzi.warehouseapp.model.Employee;
import org.springframework.data.domain.Page;

public interface IEmployeeService {
    Employee saveEmployee (EmployeeInsertDTO employeeInsertDTO)
        throws EntityAlreadyExistsException, EntityInvalidArgumentException ;

    Page<EmployeeReadOnlyDTO> getPaginatedEmployees(int page, int size);

    void deleteEmployee(Long id) throws EntityNotFoundException;

    Employee updateEmployee(EmployeeUpdateDTO updateDTO) throws EntityNotFoundException, EntityInvalidArgumentException, EntityAlreadyExistsException;

    Employee findById(Long id);
}
