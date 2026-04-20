package re.edu.lt_tonghop.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "supply_transactions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SupplyTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionType type;// loại giao dịch là xuất hay nhập kho


    @Column(nullable = false)
    private Integer amount;// Số lượng nhập hoặc xuất

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime transactionDate;

    @ManyToOne
    @JoinColumn(name = "supply_id", nullable = false)
    private MedicalSupply  medicalSupply;
}
