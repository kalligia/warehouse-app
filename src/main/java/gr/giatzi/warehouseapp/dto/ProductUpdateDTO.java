package gr.giatzi.warehouseapp.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductUpdateDTO {

    private Long id;

    @NotNull(message = "Quantity can not be null.")
    private Long quantity;
}
