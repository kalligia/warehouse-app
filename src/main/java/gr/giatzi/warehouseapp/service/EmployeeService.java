package gr.giatzi.warehouseapp.service;

import gr.giatzi.warehouseapp.core.exceptions.EntityAlreadyExistsException;
import gr.giatzi.warehouseapp.core.exceptions.EntityInvalidArgumentException;
import gr.giatzi.warehouseapp.core.exceptions.EntityNotFoundException;
import gr.giatzi.warehouseapp.dto.EmployeeInsertDTO;
import gr.giatzi.warehouseapp.dto.EmployeeReadOnlyDTO;
import gr.giatzi.warehouseapp.dto.EmployeeUpdateDTO;
import gr.giatzi.warehouseapp.mapper.Mapper;
import gr.giatzi.warehouseapp.model.Employee;
import gr.giatzi.warehouseapp.model.static_data.JobTitle;
import gr.giatzi.warehouseapp.repository.EmployeeRepository;
import gr.giatzi.warehouseapp.repository.JobTitleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeService implements IEmployeeService{

    private final EmployeeRepository employeeRepository;
    private final JobTitleRepository jobTitleRepository;
    private final Mapper mapper;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Employee saveEmployee(EmployeeInsertDTO employeeInsertDTO)
            throws EntityAlreadyExistsException, EntityInvalidArgumentException {

        if (employeeRepository.findByAmka(employeeInsertDTO.getAmka()).isPresent()) {
            throw new EntityAlreadyExistsException("Employee", "Employee with amka: "
                    + employeeInsertDTO.getAmka() + " already exists.");
        }

        Employee employee = mapper.mapToEmployeeEntity(employeeInsertDTO);

        JobTitle jt = jobTitleRepository.findById(employeeInsertDTO.getTitleId())
                .orElseThrow(() -> new EntityInvalidArgumentException("JobTitle", "Invalid job title id"));

        employee.setTitle(jt);

        return employeeRepository.save(employee);

        //TODO other methods like update and delete
    }

    @Override
    @Transactional
    public Page<EmployeeReadOnlyDTO> getPaginatedEmployees(int page, int size) {
        // Create a Pageable object with the requested page number and size
        Pageable pageable = PageRequest.of(page, size);

        // Fetch paginated list of teachers
        Page<Employee> employeePage = employeeRepository.findAll(pageable);

        // Map each Teacher entity to TeacherReadOnlyDTO
        return employeePage.map(mapper::mapToEmployeeReadOnlyDTO);
    }

    @Override
    @Transactional
    public void deleteEmployee(Long id) throws EntityNotFoundException {
        if (!employeeRepository.existsById(id)) {
            throw new IllegalArgumentException("Employee with ID " + id + " does not exist.");
        }
        employeeRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Employee updateEmployee(EmployeeUpdateDTO updateDTO)
            throws EntityNotFoundException, EntityInvalidArgumentException {

        Employee employee = mapper.mapToEmployeeEntity(updateDTO);

        JobTitle jt = jobTitleRepository.findById(updateDTO.getTitleId())
                .orElseThrow(() -> new EntityInvalidArgumentException("JobTitle", "Invalid job title id"));

        employee.setTitle(jt);

        return  employeeRepository.save(employee);
    }

    @Override
    public Employee findById(Long id) {
        return employeeRepository.findByEmpId(id);
    }

}