package gr.giatzi.warehouseapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmployeeReadOnlyDTO {
    private Long empId;
    private String title;
    private String firstname;
    private String lastname;
    private String email;
    private String phoneNumber;
//    private LocalDateTime createdAt;
//    private LocalDateTime updatedAt;
}
