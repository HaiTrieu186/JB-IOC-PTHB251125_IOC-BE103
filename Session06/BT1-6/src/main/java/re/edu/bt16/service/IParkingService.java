package re.edu.bt16.service;

import re.edu.bt16.dto.parking_ticket.TicketRequest;
import re.edu.bt16.dto.parking_ticket.TicketResponse;

public interface IParkingService {
    TicketResponse checkIn(TicketRequest req);
    TicketResponse checkOut(Long vehicleId);
}
