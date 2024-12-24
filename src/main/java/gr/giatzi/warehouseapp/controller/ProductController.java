package gr.giatzi.warehouseapp.controller;

import gr.giatzi.warehouseapp.core.exceptions.EntityInvalidArgumentException;
import gr.giatzi.warehouseapp.core.exceptions.EntityNotFoundException;
import gr.giatzi.warehouseapp.dto.ProductInsertDTO;
import gr.giatzi.warehouseapp.dto.ProductReadOnlyDTO;
import gr.giatzi.warehouseapp.dto.ProductUpdateDTO;
import gr.giatzi.warehouseapp.mapper.Mapper;
import gr.giatzi.warehouseapp.model.Product;
import gr.giatzi.warehouseapp.service.MaterialService;
import gr.giatzi.warehouseapp.service.ProductService;
import gr.giatzi.warehouseapp.service.ProductTypeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;


@Controller
@RequestMapping("/warehouse")
@RequiredArgsConstructor
public class ProductController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
    private final ProductTypeService productTypeService;
    private final MaterialService materialService;
    private final Mapper mapper;
    private final ProductService productService;



    @GetMapping("/products/add")
    public String getProductForm(Model model) {
        model.addAttribute("productInsertDTO", new ProductInsertDTO());
        model.addAttribute("materials", materialService.findAllMaterials());
        model.addAttribute("productTypes", productTypeService.findAllProductTypes());
        return "product-form";
    }

    @PostMapping("/products/add")
    public String saveProduct(@Valid @ModelAttribute("productInsertDTO") ProductInsertDTO productInsertDTO,
                               BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        Product savedProduct ;

        if (bindingResult.hasErrors()) {
            return "product-form";
        }

        try {
            savedProduct = productService.saveProduct(productInsertDTO);
            redirectAttributes.addFlashAttribute("successMessage", "Product added successfully!");
            LOGGER.info("Product with id {} added", savedProduct.getProdId());
        } catch (EntityInvalidArgumentException e) {
            LOGGER.error("Product not added");
            model.addAttribute("errorMessage", e.getMessage());
            return "product-form";
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//
//        ProductReadOnlyDTO productReadOnlyDTO = mapper.mapToProductReadOnlyDTO(savedProduct);
//        model.addAttribute("product", productReadOnlyDTO);
//        redirectAttributes.addFlashAttribute("successMessage", "Product added successfully!");

        return "redirect:/warehouse/products";

    }

    @GetMapping("/products")
    public String getProducts(Model model) {
        // Retrieve the list of products
        List<ProductReadOnlyDTO> products = productService.getAllProducts();

        // Add the list of products to the model
        model.addAttribute("products", products);

        // Return the name of the HTML view (products.html)
        return "products";
    }

    @GetMapping("/products/details/{id}")
    public String getProductForm(@PathVariable Long id, Model model) {
        ProductReadOnlyDTO productReadOnlyDTO = productService.findById(id);
        model.addAttribute("product", productReadOnlyDTO);
        return "product-details";
    }

    @PostMapping("/products/edit/{id}")
    public String updateProducts(@Valid @ModelAttribute("product") ProductUpdateDTO productUpdateDTO,
                                 BindingResult bindingResult,
                                 Model model, RedirectAttributes redirectAttributes
                                 ) {
        Product savedProduct ;

        if (bindingResult.hasErrors()) {
            return "product-details";
        }

        try {
            savedProduct = productService.updateProduct(productUpdateDTO);
            redirectAttributes.addFlashAttribute("successMessage", "Quantity edited successfully!");
            //  LOGGER.info("Employee with id {} added", savedEmployee.getEmpId());
        } catch (EntityNotFoundException | EntityInvalidArgumentException e) {
        //    LOGGER.error("Employee with amka {} not added", employeeUpdateDTO.getAmka());
            model.addAttribute("errorMessage", e.getMessage());
            return "product-details"; }

//        ProductReadOnlyDTO productReadOnlyDTO = mapper.mapToProductReadOnlyDTO(savedProduct);
//        model.addAttribute("product", savedProduct);
        return "redirect:/warehouse/products/details/{id}";
    }

//    @GetMapping("/products/delete/{id}")
//    public String deleteProduct(@PathVariable Long id, Model model) {
//        Product product = productService.findById(id);
//        ProductUpdateDTO productUpdateDTO = mapper.mapToProductUpdateDTO(product);
//        model.addAttribute("product", productUpdateDTO);
//        return "redirect:/warehouse/products";
//    }

//    @GetMapping("/products")
//    public String getPaginatedEmployees(
//            @RequestParam(defaultValue = "0") int page,  // Default to the first page (0-indexed)
//            @RequestParam(defaultValue = "5") int size,  // Default page size
//            Model model) {
//
//        // Get paginated TeacherReadOnlyDTOs
//        Page<EmployeeReadOnlyDTO> employeesPage = productService.getPaginatedEmployees(page, size);
//
//        // Add the page of teachers and pagination info to the model
//        model.addAttribute("employeesPage", employeesPage);
//        model.addAttribute("currentPage", page);
//        model.addAttribute("totalPages", employeesPage.getTotalPages());
//
//        return "employees";  // Return Thymeleaf view (teachers.html)
//    }

}
