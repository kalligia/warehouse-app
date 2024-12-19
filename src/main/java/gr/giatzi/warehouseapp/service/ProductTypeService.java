package gr.giatzi.warehouseapp.service;

import gr.giatzi.warehouseapp.model.static_data.ProductType;
import gr.giatzi.warehouseapp.repository.ProductTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductTypeService implements IProductTypeService{
    private final ProductTypeRepository productTypeRepository;

    @Override
    public List<ProductType> findAllProductTypes() {
        return productTypeRepository.findAll();
    }
}
