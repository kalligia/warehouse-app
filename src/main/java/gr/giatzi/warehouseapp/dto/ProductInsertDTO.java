package gr.giatzi.warehouseapp.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductInsertDTO {

    @NotNull(message = "Product type can not ne null.")
    private Long type;

    @NotNull(message = "Product type can not ne null.")
    private String name;

    @NotNull(message = "Color can not ne null.")
    private String color;

    @NotNull(message = "Product type can not ne null.")
    private Long quantity;

    @NotNull(message = "Product type can not ne null.")
    private Long material;

    @NotNull(message = "Product type can not ne null.")
    private MultipartFile photo;
}
