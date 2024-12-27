package gr.giatzi.warehouseapp.dto;

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
public class EmployeeUpdateDTO {

    private Long id;

    @NotNull(message = "Please select a job title.")
    private Long titleId;

    @NotNull(message = "Firstname can not be null.")
    @Size(min = 2, max = 100, message = "Firstname must be between 2 - 100 characters.")
    private String firstname;

    @NotNull(message = "Lastname can not be null.")
    @Size(min = 2, max = 100, message = "Lastname must be between 2 - 100 characters.")
    private String lastname;

    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$\n", message = "Wrong email format.")
    @NotNull(message = "Email can not be null.")
    private String email;

    @Pattern(regexp = "^\\+?[0-9\\s\\-()]{7,15}$\n", message = "Wrong phone format.")
    @NotNull(message = "Phone number can not be null.")
    private String phoneNumber;



}
