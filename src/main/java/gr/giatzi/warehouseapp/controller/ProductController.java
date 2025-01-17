package gr.giatzi.warehouseapp.controller;

import gr.giatzi.warehouseapp.core.exceptions.EntityAlreadyExistsException;
import gr.giatzi.warehouseapp.core.exceptions.EntityInvalidArgumentException;
import gr.giatzi.warehouseapp.core.exceptions.EntityNotFoundException;
import gr.giatzi.warehouseapp.dto.ProductInsertDTO;
import gr.giatzi.warehouseapp.dto.ProductReadOnlyDTO;
import gr.giatzi.warehouseapp.dto.ProductUpdateDTO;
import gr.giatzi.warehouseapp.mapper.Mapper;
import gr.giatzi.warehouseapp.model.Product;
import gr.giatzi.warehouseapp.model.static_data.ProductType;
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
        Product savedProduct;

        if (bindingResult.hasErrors()) {
            model.addAttribute("materials", materialService.findAllMaterials());
            model.addAttribute("productTypes", productTypeService.findAllProductTypes());
            return "product-form";
        }

        try {
            savedProduct = productService.saveProduct(productInsertDTO);
            redirectAttributes.addFlashAttribute("successMessage", "Product added successfully!");
            LOGGER.info("Product with id {} added", savedProduct.getProdId());
        } catch (EntityAlreadyExistsException e) {
            LOGGER.error("Product not added");
            redirectAttributes.addFlashAttribute("warningMessage", "Product with name " + productInsertDTO.getName() + " already exists!");
            model.addAttribute("errorMessage", e.getMessage());
            return "redirect:/warehouse/products/add";
        } catch (IOException | EntityInvalidArgumentException e) {
            throw new RuntimeException(e);
        }

        return "redirect:/warehouse/products";
    }

    @GetMapping("/products")
    public String getProducts(@RequestParam(value = "type", required = false) ProductType type, Model model) {

        List<ProductReadOnlyDTO> products;

        if (type != null) {
            model.addAttribute("selectedType", type.getId());
            products = productService.findByType(type);
        } else {
            products = productService.getAllProducts();
        }

        model.addAttribute("products", products);
        model.addAttribute("productTypes", productTypeService.findAllProductTypes());
        return "products";
    }

    @GetMapping("/products/details/{id}")
    public String getProductForm(@PathVariable Long id, Model model) {

        try {
            ProductReadOnlyDTO productReadOnlyDTO = productService.findById(id);
            model.addAttribute("product", productReadOnlyDTO);
            return "product-details";
        } catch (EntityNotFoundException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "error/item-not-found";
        }
    }

    @GetMapping("/products/edit/{id}")
    public String getProductForm(@PathVariable Long id) {
        return "redirect:/warehouse/products/details/{id}";
    }

    @PostMapping("/products/edit/{id}")
    public String updateProducts(@Valid @ModelAttribute("product") ProductUpdateDTO productUpdateDTO,
                                 BindingResult bindingResult,
                                 Model model, RedirectAttributes redirectAttributes) {
        Product savedProduct;

        if (bindingResult.hasErrors()) {
            return "product-details";
        }

        try {
            savedProduct = productService.updateProduct(productUpdateDTO);
            redirectAttributes.addFlashAttribute("successMessage", "Quantity updated successfully!");
            LOGGER.info("Product with id {} updated", savedProduct.getProdId());
        } catch (EntityNotFoundException | EntityInvalidArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "error/item-not-found";
        }

        return "redirect:/warehouse/products/details/{id}";
    }

    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable Long id, RedirectAttributes redirectAttributes, Model model) {

        try {
            productService.deleteProduct(id);
            redirectAttributes.addFlashAttribute("successMessage", "Product deleted successfully!");
            return "redirect:/warehouse/products";
        } catch (EntityNotFoundException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "error/item-not-found";
        }
    }
}
