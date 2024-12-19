package gr.giatzi.warehouseapp.repository;

import gr.giatzi.warehouseapp.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
   // Product findByProdId(Long id);
    Product findByProdId (Long id);
}
