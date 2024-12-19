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

 //   private String name;

    @NotNull(message = "Id must exist.")
    private Long id;

//    @NotNull(message = "Product type can not ne null.")
//    private Long typeId;
//
//    @NotNull(message = "Color can not ne null.")
//    private String color;

    private Long quantity;

  //  private Long materialId;

  //  private MultipartFile photo;
}
