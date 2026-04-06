package re.edu.bt16.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import re.edu.bt16.entity.ParkingTicket;

import java.util.Optional;

public interface ParkingRepository extends JpaRepository<ParkingTicket,Long> {

    @Query(value = """
        select pt
        from ParkingTicket pt
        where (pt.vehicle.id = :vehicle_id) and (pt.checkOutTime is null)
        order by pt.checkInTime DESC
        limit 1
    """)
    public Optional<ParkingTicket> findParkingTicketByIdAndCheckInTimeIsNear(
            @Param("vehicle_id") Long vehicleId
    );
}
