package re.edu.bt16.dto.parking_ticket;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TicketRequest {
    private Long  vehicleId;
    private Long  zoneId;
}
