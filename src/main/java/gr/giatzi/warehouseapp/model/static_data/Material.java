package gr.giatzi.warehouseapp.model.static_data;

import gr.giatzi.warehouseapp.model.Product;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "materials")
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Getter(AccessLevel.PRIVATE)
    @OneToMany(mappedBy = "material")
    private Set<Product> products = new HashSet<>();

    public Set<Product> getAllProducts() {
        if (products == null) products = new HashSet<>();
        return Collections.unmodifiableSet(products);
    }

    public void addProduct(Product product) {
        if (products == null) products = new HashSet<>();
        products.add(product);
        product.setMaterial(this);
    }
}
