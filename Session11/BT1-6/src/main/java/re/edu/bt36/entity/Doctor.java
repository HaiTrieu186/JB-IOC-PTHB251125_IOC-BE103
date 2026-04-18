package re.edu.bt36.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "doctors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String fullName;

    @Column(nullable = false, length = 100)
    private String specialty; // Chuyên khoa

    @Column(length = 10)
    private String phoneNumber;

    @Column(unique = true)
    private String email;

    @OneToMany(mappedBy = "doctor")
    private List<Appointment> appointments;
}
