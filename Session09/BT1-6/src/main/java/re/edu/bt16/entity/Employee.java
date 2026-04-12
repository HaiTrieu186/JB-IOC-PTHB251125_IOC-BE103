package re.edu.bt16.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "employees")
@Getter
@Setter
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "full_name")
    private String fullName;
    private String email;
    private String phone;
    private BigDecimal salary;
    private String avatarUrl;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
}
