package re.edu.bt16.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "vehicles")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "license_plate")
    private String licensePlate;

    private String color;

    @Enumerated(EnumType.STRING)
    private VehicleTypeEnum type;

    @OneToMany(mappedBy = "vehicle")
    private List<ParkingTicket> parkingTickets;

}
