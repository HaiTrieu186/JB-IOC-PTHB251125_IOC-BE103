package re.edu.bt16.dto.vehicle;

import lombok.*;
import re.edu.bt16.entity.VehicleTypeEnum;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class VehicleResponse {
    private Long id;
    private String licensePlate;
    private String color;
    private VehicleTypeEnum vehicleType;
}
