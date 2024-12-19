package gr.giatzi.warehouseapp.service;

import gr.giatzi.warehouseapp.core.exceptions.EntityInvalidArgumentException;
import gr.giatzi.warehouseapp.core.exceptions.EntityNotFoundException;
import gr.giatzi.warehouseapp.dto.ProductInsertDTO;
import gr.giatzi.warehouseapp.dto.ProductReadOnlyDTO;
import gr.giatzi.warehouseapp.dto.ProductUpdateDTO;
import gr.giatzi.warehouseapp.mapper.Mapper;
import gr.giatzi.warehouseapp.model.Product;
import gr.giatzi.warehouseapp.model.static_data.ProductType;
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
    private final Mapper mapper;
    //private final UploadService uploadService;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Product saveProduct(ProductInsertDTO productInsertDTO)
            throws EntityInvalidArgumentException, IOException {
      //  String fileName = uploadService.saveFile(productInsertDTO.getPhoto());

        Product product = mapper.mapToProductEntity(productInsertDTO);

      //  product.setPhotoName(fileName);

        ProductType pt = productTypeRepository.findById(productInsertDTO.getTypeId())
                .orElseThrow(() -> new EntityInvalidArgumentException("ProductType", "Invalid product type id"));

        product.setType(pt);

        return productRepository.save(product);

        //TODO other methods like update and delete
    }

    @Override
    public List<ProductReadOnlyDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(mapper::mapToProductReadOnlyDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findByProdId(id);
    }

   // @Override
//    public Product updateProduct(ProductUpdateDTO updateDTO) throws EntityNotFoundException, EntityInvalidArgumentException {

//        Product product = mapper.mapToProductEntity(updateDTO);
//
//        return  productRepository.save(product);

        @Override
        public Product updateProduct(ProductUpdateDTO updateDTO) throws EntityNotFoundException, EntityInvalidArgumentException {
            // Fetch the existing product
            Product existingProduct = productRepository.findByProdId(updateDTO.getId());
            if (existingProduct == null) {
              //  throw new EntityNotFoundException("Product with ID " + updateDTO.getId() + " not found.");
            }

            // Update fields
            if (updateDTO.getQuantity() != null) {
                existingProduct.setQuantity(updateDTO.getQuantity());
            }

            // Save the updated product
            return productRepository.save(existingProduct);
        }


  //  }

//    @Override
//    public Product deleteProduct(ProductUpdateDTO updateDTO) {
//
//        Product product = mapper.mapToProductEntity(updateDTO);
//
//        product.setStatus("inactive");
//        product.setQuantity(Long.valueOf("0"));
//
//        return productRepository.save(product);
//
//    }
}