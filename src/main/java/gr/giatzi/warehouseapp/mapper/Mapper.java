package gr.giatzi.warehouseapp.mapper;

import gr.giatzi.warehouseapp.dto.*;
import gr.giatzi.warehouseapp.model.Employee;
import gr.giatzi.warehouseapp.model.Product;
import gr.giatzi.warehouseapp.service.UploadService;
import org.springframework.stereotype.Component;
import gr.giatzi.warehouseapp.model.User;
import gr.giatzi.warehouseapp.core.enums.Role;
import java.io.IOException;

@Component
public class Mapper {

    private final UploadService uploadService;

    public Mapper(UploadService uploadService) {
        this.uploadService = uploadService;
    }

    public Product mapToProductEntity(ProductInsertDTO productInsertDTO) throws IOException {
        Product product = new Product();
        product.setName(productInsertDTO.getName());
        product.setColor(productInsertDTO.getColor());
        product.setQuantity(productInsertDTO.getQuantity());
        product.setPhotoName(uploadService.saveFile(productInsertDTO.getPhoto()));
        return product;
    }

    public Product mapToProductEntity(ProductUpdateDTO productUpdateDTO) {
        Product product = new Product();
        product.setQuantity(productUpdateDTO.getQuantity());
        return product;
    }

    public ProductReadOnlyDTO mapToProductReadOnlyDTO(Product product) {
        return new ProductReadOnlyDTO(product.getProdId(), product.getName(), product.getType().getName(), product.getMaterial().getName(),
                product.getColor(),product.getQuantity(), product.getPhotoName());
    }

    public ProductUpdateDTO mapToProductUpdateDTO(Product product) {
        return new ProductUpdateDTO(product.getProdId(), product.getQuantity());
    }

    public Employee mapToEmployeeEntity(EmployeeInsertDTO employeeInsertDTO) {
        Employee employee = new Employee();
        employee.setFirstname(employeeInsertDTO.getFirstname());
        employee.setLastname(employeeInsertDTO.getLastname());
        employee.setEmail(employeeInsertDTO.getEmail());
        employee.setPhoneNumber(employeeInsertDTO.getPhoneNumber());
        return employee;
    }

    public Employee mapToEmployeeEntity(EmployeeUpdateDTO employeeUpdateDTO) {
        Employee employee = new Employee();
        employee.setEmpId(employeeUpdateDTO.getId());
        employee.setFirstname(employeeUpdateDTO.getFirstname());
        employee.setLastname(employeeUpdateDTO.getLastname());
        employee.setEmail(employeeUpdateDTO.getEmail());
        employee.setPhoneNumber(employeeUpdateDTO.getPhoneNumber());
        return employee;
    }

    public EmployeeReadOnlyDTO mapToEmployeeReadOnlyDTO(Employee employee) {
        return new EmployeeReadOnlyDTO(employee.getEmpId(), employee.getTitle().getName(), employee.getFirstname(),
                employee.getLastname(), employee.getEmail(), employee.getPhoneNumber(),
                 employee.getCreatedAt(), employee.getUpdatedAt());
    }

    public EmployeeUpdateDTO mapToEmployeeUpdateDTO(Employee employee) {
        return new EmployeeUpdateDTO(employee.getEmpId(), employee.getTitle().getId(), employee.getFirstname(),
                employee.getLastname(), employee.getEmail(), employee.getPhoneNumber());
    }

    public User mapToUserEntity(UserInsertDTO userInsertDTO) {
        return new User(null, userInsertDTO.getUsername(),
                userInsertDTO.getPassword(), Role.valueOf(userInsertDTO.getRole().toUpperCase()));
    }

}
