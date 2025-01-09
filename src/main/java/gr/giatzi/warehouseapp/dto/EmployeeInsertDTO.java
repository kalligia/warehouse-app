package gr.giatzi.warehouseapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmployeeInsertDTO {

    private Long id;

    @NotNull(message = "Please select a job title.")
    private Long titleId;

    @NotBlank(message = "Firstname can not be null.")
    @Size(min = 2, max = 100, message = "Firstname must be between 2 - 100 characters.")
    private String firstname;

    @NotBlank(message = "Lastname can not be null.")
    @Size(min = 2, max = 100, message = "Lastname must be between 2 - 100 characters.")
    private String lastname;

    @NotBlank(message = "Email can not be null.")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Wrong email format.")
    private String email;

    @Pattern(regexp = "^\\+?[1-9]\\d{6,14}$", message = "Wrong phone number format.")
    @NotBlank(message = "Phone number can not be null.")
    private String phoneNumber;
}
