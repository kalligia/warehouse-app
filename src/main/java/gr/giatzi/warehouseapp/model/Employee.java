package gr.giatzi.warehouseapp.model;

import gr.giatzi.warehouseapp.model.static_data.JobTitle;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "employees")
public class Employee extends AbstractEntity{

    @Id
    @Column(name = "emp_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long empId;

    @ManyToOne
    @JoinColumn(name = "title_id")
    private JobTitle title;

    private String firstname;

    private String lastname;

    @Column(unique = true)
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

}
