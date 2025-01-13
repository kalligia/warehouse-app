package gr.giatzi.warehouseapp.model;

import gr.giatzi.warehouseapp.model.static_data.Material;
import gr.giatzi.warehouseapp.model.static_data.ProductType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "products")
public class Product extends AbstractEntity{

    @Id
    @Column(name = "prod_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long prodId;

    @Column(unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private ProductType type;

    private String color;

    @ManyToOne
    @JoinColumn(name = "material_id")
    private Material material;

    private Long quantity;

    private String photoName;
}
