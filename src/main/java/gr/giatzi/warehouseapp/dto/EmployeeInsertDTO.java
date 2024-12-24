package gr.giatzi.warehouseapp.dto;

import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "Job title can not be null.")
    private Long titleId;

    @NotNull(message = "Firstname can not be null.")
    @Size(min = 2, max = 100, message = "Firstname must be between 2 - 100 characters.")
    private String firstname;

    @NotNull(message = "Lastname can not be null.")
    @Size(min = 2, max = 100, message = "Lastname must be between 2 - 100 characters.")
    private String lastname;

    //@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$\n", message = "Wrong email format.")
    private String email;

    private String phoneNumber;

}
