package gr.giatzi.warehouseapp.repository;

import gr.giatzi.warehouseapp.model.Product;
import gr.giatzi.warehouseapp.model.static_data.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    Product findByProdId (Long id);

    List<Product> findByType(ProductType type);

    Product findByName(String name);
}
