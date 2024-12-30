package gr.giatzi.warehouseapp.service;

import gr.giatzi.warehouseapp.core.exceptions.EntityInvalidArgumentException;
import gr.giatzi.warehouseapp.core.exceptions.EntityNotFoundException;
import gr.giatzi.warehouseapp.dto.ProductInsertDTO;
import gr.giatzi.warehouseapp.dto.ProductReadOnlyDTO;
import gr.giatzi.warehouseapp.dto.ProductUpdateDTO;
import gr.giatzi.warehouseapp.mapper.Mapper;
import gr.giatzi.warehouseapp.model.Product;
import gr.giatzi.warehouseapp.model.static_data.Material;
import gr.giatzi.warehouseapp.model.static_data.ProductType;
import gr.giatzi.warehouseapp.repository.MaterialRepository;
import gr.giatzi.warehouseapp.repository.ProductRepository;
import gr.giatzi.warehouseapp.repository.ProductTypeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {

    private final ProductRepository productRepository;
    private final ProductTypeRepository productTypeRepository;
    private final MaterialRepository materialRepository;
    private final Mapper mapper;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Product saveProduct(ProductInsertDTO productInsertDTO)
            throws EntityInvalidArgumentException, IOException {

        Product product = mapper.mapToProductEntity(productInsertDTO);
        ProductType pt = productTypeRepository.findById(productInsertDTO.getType())
                .orElseThrow(() -> new EntityInvalidArgumentException("ProductType", "Invalid product type id"));

        product.setType(pt);

        Material material = materialRepository.findById(productInsertDTO.getMaterial())
                .orElseThrow(() -> new EntityInvalidArgumentException("Material", "Invalid material id"));

        product.setMaterial(material);

        return productRepository.save(product);

    }

    @Override
    public List<ProductReadOnlyDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(mapper::mapToProductReadOnlyDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductReadOnlyDTO findById(Long id) {
        return mapper.mapToProductReadOnlyDTO(productRepository.findByProdId(id));
    }

    @Override
    public void deleteProduct(Long id) throws EntityNotFoundException {
        if (!productRepository.existsById(id)) {
            throw new IllegalArgumentException("Product with ID " + id + " does not exist.");
        }
        productRepository.deleteById(id);
    }


    @Override
    public Product updateProduct(ProductUpdateDTO updateDTO) throws EntityNotFoundException, EntityInvalidArgumentException {

        Product existingProduct = productRepository.findByProdId(updateDTO.getId());

        if (existingProduct == null) {
          //    throw new EntityNotFoundException("Product with ID " + updateDTO.getId() + " not found.");
        }

        if (updateDTO.getQuantity() != null) {
            existingProduct.setQuantity(updateDTO.getQuantity());
        }

        return productRepository.save(existingProduct);
    }

    public List<ProductReadOnlyDTO> findByType(ProductType type) {
        return productRepository.findByType(type).stream()
                .map(mapper::mapToProductReadOnlyDTO)
                .collect(Collectors.toList());
    }

}
