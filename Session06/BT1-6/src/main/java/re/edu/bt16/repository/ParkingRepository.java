package re.edu.bt16.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import re.edu.bt16.dto.parking_ticket.TicketSummaryResponse;
import re.edu.bt16.entity.ParkingTicket;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ParkingRepository extends JpaRepository<ParkingTicket,Long> {

    @Query(value = """
        select pt
        from ParkingTicket pt
        where (pt.vehicle.id = :vehicle_id) and (pt.checkOutTime is null)
        order by pt.checkInTime DESC
        limit 1
    """)
    Optional<ParkingTicket> findParkingTicketByIdAndCheckInTimeIsNear(
            @Param("vehicle_id") Long vehicleId
    );

    @Query(value = """
        select new re.edu.bt16.dto.parking_ticket.TicketSummaryResponse(
                pk.id, pk.vehicle.licensePlate, pk.zone.name, pk.checkInTime, pk.checkOutTime
            )
        from ParkingTicket pk
        where pk.checkInTime between :start_date and :end_date
    """
    )
    List<TicketSummaryResponse> getSummary(
            @Param("start_date") LocalDateTime startDate,
            @Param("end_date") LocalDateTime endDate
    );

}
