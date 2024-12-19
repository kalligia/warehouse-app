package gr.giatzi.warehouseapp.service;

import gr.giatzi.warehouseapp.model.static_data.ProductType;

import java.util.List;

public interface IProductTypeService {
    List<ProductType> findAllProductTypes();
}
