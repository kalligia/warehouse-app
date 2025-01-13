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

    private String username;

    @NotNull
   // @Pattern(regexp = "^(?=.*?[a-z])(?=.*?[A-Z])(?=.*?\\d)(?=.*?[@$!%*?&]).{5,}$", message = "Invalid password")
    private String password;

    @NotNull(message = "Please select a role.")
    private String role;
}