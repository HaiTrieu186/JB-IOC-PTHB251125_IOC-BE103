package re.edu.bt36.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "prescription")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String medicationDetails; // Chi tiết thuốc (hoặc có thể làm 1 bảng Medication riêng nếu hệ thống phức tạp hơn)

    @Column(length = 500)
    private String instructions; // Hướng dẫn sử dụng

    private LocalDateTime prescribedDate; // Ngày kê đơn

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
}
