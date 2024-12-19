package gr.giatzi.warehouseapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductReadOnlyDTO {

    private Long Id;
    private String name;
    private String type;
    private String material;
    private String color;
    private Long quantity;
    private String photoName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
