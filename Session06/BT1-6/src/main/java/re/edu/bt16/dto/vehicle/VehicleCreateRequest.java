package re.edu.bt16.dto.vehicle;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import re.edu.bt16.entity.VehicleTypeEnum;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VehicleCreateRequest {
    private String licensePlate;
    private String color;
    private VehicleTypeEnum vehicleType;
}
