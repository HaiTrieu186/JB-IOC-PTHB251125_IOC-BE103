package re.edu.bt16.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import re.edu.bt16.dto.parking_ticket.TicketRequest;
import re.edu.bt16.dto.parking_ticket.TicketResponse;
import re.edu.bt16.dto.parking_ticket.TicketSummaryResponse;
import re.edu.bt16.entity.ParkingTicket;
import re.edu.bt16.entity.Vehicle;
import re.edu.bt16.entity.Zone;
import re.edu.bt16.repository.ParkingRepository;
import re.edu.bt16.repository.VehicleRepository;
import re.edu.bt16.repository.ZoneRepository;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ParkingService implements  IParkingService {
    private final ParkingRepository parkingRepository;
    private final ZoneRepository zoneRepository;
    private final VehicleRepository vehicleRepository;

    @Override
    @Transactional
    public TicketResponse checkIn(TicketRequest req) {
        Zone zone= zoneRepository.findById(req.getZoneId()).orElseThrow(
                () -> new RuntimeException("Lỗi: Không tìm thấy khu vực tương ứng !")
        );

       Vehicle vehicle= vehicleRepository.findById(req.getVehicleId()).orElseThrow(
                () -> new RuntimeException("Lỗi: Không tìm thấy phương tiện tương ứng !")
        );

       if (zone.getOccupiedSpots() >= zone.getCapacity()){
           throw new RuntimeException("Lỗi: Khu vực không còn đủ chỗ !");
       }

       ///  Tạo mới ticket
       ParkingTicket ticket =new ParkingTicket();
       ticket.setZone(zone);
       ticket.setVehicle(vehicle);
       ticket = parkingRepository.save(ticket);

       ///  Cộng chỗ ở Zone
       zone.setOccupiedSpots(zone.getOccupiedSpots() + 1);
       zoneRepository.save(zone);

       return maptoResponse(ticket, vehicle.getLicensePlate(), zone.getName());
    }

    @Override
    @Transactional
    public TicketResponse checkOut(Long vehicleId) {
        ParkingTicket ticket = parkingRepository.findParkingTicketByIdAndCheckInTimeIsNear(vehicleId)
                .orElseThrow( () -> new RuntimeException("Lỗi: Không tìm thầy ticket gần nhất hợp lệ"));

        ///  Lưu thời gian checkout
        ticket.setCheckOutTime(LocalDateTime.now());
        parkingRepository.save(ticket);

        ///  Trả lại ví trị cho zone
        ticket.getZone().setOccupiedSpots(ticket.getZone().getOccupiedSpots() - 1);
        zoneRepository.save(ticket.getZone());

        return maptoResponse(ticket, ticket.getVehicle().getLicensePlate(), ticket.getZone().getName());
    }

    @Override
    public List<TicketSummaryResponse> getTicketSummary() {
        // Thời điểm đầu ngày hôm nay (00:00:00)
        LocalDateTime startOfDay = LocalDateTime.now().with(LocalTime.MIN);

        // Thời điểm cuối ngày hôm nay (23:59:59.999...)
        LocalDateTime endOfDay = LocalDateTime.now().with(LocalTime.MAX);

        return parkingRepository.getSummary(startOfDay, endOfDay);
    }

    private TicketResponse maptoResponse(ParkingTicket ticket, String licensePlate, String zoneName) {
        return TicketResponse.builder()
                .id(ticket.getId())
                .licensePlate(licensePlate)
                .zoneName(zoneName)
                .checkInTime(ticket.getCheckInTime())
                .checkOutTime(ticket.getCheckOutTime())
                .build();
    }


}
