package gr.giatzi.warehouseapp.controller;

import gr.giatzi.warehouseapp.core.exceptions.EntityAlreadyExistsException;
import gr.giatzi.warehouseapp.core.exceptions.EntityInvalidArgumentException;
import gr.giatzi.warehouseapp.core.exceptions.EntityNotFoundException;
import gr.giatzi.warehouseapp.dto.EmployeeInsertDTO;
import gr.giatzi.warehouseapp.dto.EmployeeReadOnlyDTO;
import gr.giatzi.warehouseapp.dto.EmployeeUpdateDTO;
import gr.giatzi.warehouseapp.mapper.Mapper;
import gr.giatzi.warehouseapp.model.Employee;
import gr.giatzi.warehouseapp.service.EmployeeService;
import gr.giatzi.warehouseapp.service.JobTitleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/warehouse")
@RequiredArgsConstructor
public class EmployeeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);
    private final JobTitleService jtService;
    private final Mapper mapper;
    private final EmployeeService employeeService;


    @GetMapping("/employees/add")
    public String getEmployeeForm(Model model) {
        model.addAttribute("employee", new EmployeeInsertDTO());
        model.addAttribute("jobTitles", jtService.findAllJobTitles());
        return "employee-form";
    }

    @PostMapping("/employees/add")
    public String saveEmployee(@Valid @ModelAttribute("employee")
                              EmployeeInsertDTO employeeInsertDTO,
                              BindingResult bindingResult,
                              Model model, RedirectAttributes redirectAttributes) {
       Employee savedEmployee ;

        if (bindingResult.hasErrors()) {
            return "employee-form";
        }

        try {
            savedEmployee = employeeService.saveEmployee(employeeInsertDTO);
            redirectAttributes.addFlashAttribute("successMessage", "Employee added successfully!");

            LOGGER.info("Employee with id {} added", savedEmployee.getEmpId());
        } catch (EntityAlreadyExistsException | EntityInvalidArgumentException e) {
         //   LOGGER.error("Employee with amka {} not added", employeeInsertDTO.getAmka());
            model.addAttribute("errorMessage", e.getMessage());
            return "employee-form";
        }

//
//        EmployeeReadOnlyDTO employeeReadOnlyDTO = mapper.mapToEmployeeReadOnlyDTO(savedEmployee);
//        model.addAttribute("employee", savedEmployee);
        return "redirect:/warehouse/employees";

    }

    @GetMapping("/employees")
    public String getPaginatedEmployees(
            @RequestParam(defaultValue = "0") int page,  // Default to the first page (0-indexed)
            @RequestParam(defaultValue = "5") int size,  // Default page size
            Model model) {

        // Get paginated TeacherReadOnlyDTOs
        Page<EmployeeReadOnlyDTO> employeesPage = employeeService.getPaginatedEmployees(page, size);

        // Add the page of teachers and pagination info to the model
        model.addAttribute("employeesPage", employeesPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", employeesPage.getTotalPages());

        return "employees";  // Return Thymeleaf view (teachers.html)
    }

    @GetMapping("/employees/delete/{id}")
    public String deleteEmployee(@PathVariable Long id, RedirectAttributes redirectAttributes)
            throws EntityNotFoundException {
        employeeService.deleteEmployee(id);
        redirectAttributes.addFlashAttribute("successMessage", "Employee deleted successfully!");
        return "redirect:/warehouse/employees";
    }

    @GetMapping("/employees/edit/{id}")
    public String getEmployeeForm(@PathVariable Long id, Model model) {
        Employee employee = employeeService.findById(id);
        EmployeeUpdateDTO employeeUpdateDTO = mapper.mapToEmployeeUpdateDTO(employee);
        model.addAttribute("employee", employeeUpdateDTO);
        model.addAttribute("jobTitles", jtService.findAllJobTitles());
        return "employee-form";
    }

    @PostMapping("/employees/edit/{id}")
    public String updateEmployee(@Valid @ModelAttribute("employee")
                               EmployeeUpdateDTO employeeUpdateDTO,
                               BindingResult bindingResult,
                               Model model, RedirectAttributes redirectAttributes) {
        Employee savedEmployee ;

        if (bindingResult.hasErrors()) {
            return "employee-form";
        }

        try {
            savedEmployee = employeeService.updateEmployee(employeeUpdateDTO);
            redirectAttributes.addFlashAttribute("successMessage", "Employee edited successfully!");
            LOGGER.info("Employee with id {} added", savedEmployee.getEmpId());
        } catch (EntityNotFoundException | EntityInvalidArgumentException e) {
        //    LOGGER.error("Employee with amka {} not added", employeeUpdateDTO.getAmka());
            model.addAttribute("errorMessage", e.getMessage());
            return "employee-form"; }

        EmployeeReadOnlyDTO employeeReadOnlyDTO = mapper.mapToEmployeeReadOnlyDTO(savedEmployee);
        model.addAttribute("employee", savedEmployee);
        return "redirect:/warehouse/employees/edit/{id}";
    }
}



//    @GetMapping("/employees/edit/{id}")
//    public String editEmployee(@Valid @ModelAttribute("employeeUpdateDTO")
//                                   EmployeeUpdateDTO employeeUpdateDTO,
//                               BindingResult bindingResult,
//                               Model model) {
//        Employee editedEmployee ;
//
//        if (bindingResult.hasErrors()) {
//            return "employee-form";
//        }
//
//        try {
//            editedEmployee = employeeService.updateEmployee(employeeUpdateDTO);
//            LOGGER.info("Employee with id {} added", editedEmployee.getEmpId());
//        } catch (EntityInvalidArgumentException | EntityNotFoundException e) {
//            LOGGER.error("Employee with amka {} not added", employeeUpdateDTO.getAmka());
//            model.addAttribute("errorMessage", e.getMessage());
//            return "employee-form";
//        }
//
//
//        EmployeeReadOnlyDTO employeeReadOnlyDTO = mapper.mapToEmployeeReadOnlyDTO(editedEmployee);
//        model.addAttribute("employee", editedEmployee);
//        return "redirect:/warehouse/employees/add";








