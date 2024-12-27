package gr.giatzi.warehouseapp.dto;

import gr.giatzi.warehouseapp.validation.ValidFile;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
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

    @NotNull(message = "Please select a product type.")
    private Long type;

    @NotBlank(message = "Product name can not be null.")
    private String name;

    @NotBlank(message = "Color can not be null.")
    private String color;

    @NotNull(message = "Quantity can not be null.")
    @PositiveOrZero(message = "Quantity can not be negative.")
    private Long quantity;

    @NotNull(message = "Please select a material.")
    private Long material;

    @ValidFile(message = "Image must not exceed 5MB. Allowed formats: .jpg, .jpeg, .png.")
    private MultipartFile photo;
}
