package re.edu.lt_tonghop.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "medical_supplies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicalSupply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name; // Tên vật tư

    private String provider; // Hãng sản xuất

    private String unit; // Đơn vị tính

    private String specification; // quy cách

    @Column(nullable = false)
    @Builder.Default// Dùng cho builder
    private Integer quantity = 0;

    @Column(nullable = false)
    @Builder.Default
    private Boolean isDeleted = false;//xóa mềm

}
