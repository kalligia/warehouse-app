package gr.giatzi.warehouseapp.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserInsertDTO {

    @Size(min = 3, max = 20, message = "Username must be between 3 - 20 characters.")
    private String username;

    @Pattern(regexp = "^(?=.*?[a-z])(?=.*?[A-Z])(?=.*?\\d)(?=.*?[@$!%*?&]).{5,}$", message = "Invalid password")
    private String password;

    @NotBlank(message = "Please select a role.")
    private String role;
}