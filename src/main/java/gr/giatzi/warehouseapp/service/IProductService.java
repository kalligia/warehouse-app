package gr.giatzi.warehouseapp.service;

import gr.giatzi.warehouseapp.core.exceptions.EntityAlreadyExistsException;
import gr.giatzi.warehouseapp.core.exceptions.EntityInvalidArgumentException;
import gr.giatzi.warehouseapp.core.exceptions.EntityNotFoundException;
import gr.giatzi.warehouseapp.dto.ProductInsertDTO;
import gr.giatzi.warehouseapp.dto.ProductReadOnlyDTO;
import gr.giatzi.warehouseapp.dto.ProductUpdateDTO;
import gr.giatzi.warehouseapp.model.Product;

import java.io.IOException;
import java.util.List;

public interface IProductService {
    Product saveProduct (ProductInsertDTO productInsertDTO)
            throws EntityAlreadyExistsException, EntityInvalidArgumentException, IOException;

    public List<ProductReadOnlyDTO> getAllProducts();

    ProductReadOnlyDTO findById(Long id);

    Product updateProduct(ProductUpdateDTO updateDTO) throws EntityNotFoundException, EntityInvalidArgumentException;

}
