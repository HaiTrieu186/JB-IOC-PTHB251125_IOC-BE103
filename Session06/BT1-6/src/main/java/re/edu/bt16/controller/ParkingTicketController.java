package re.edu.bt16.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import re.edu.bt16.dto.ApiResponse;
import re.edu.bt16.dto.parking_ticket.TicketRequest;
import re.edu.bt16.dto.parking_ticket.TicketResponse;
import re.edu.bt16.dto.parking_ticket.TicketSummaryResponse;
import re.edu.bt16.service.IParkingService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tickets")
public class ParkingTicketController {
    private final IParkingService parkingService;

    @PostMapping("/check-in")
    public ResponseEntity<?> checkIn(
            @RequestBody TicketRequest ticketRequest
    ) {
        ApiResponse<TicketResponse> response = new ApiResponse<>();

        try{
            TicketResponse ticketResponse = parkingService.checkIn(ticketRequest);
            response.setData(ticketResponse);
            response.setSuccess(true);
            response.setMessage("Checkin thành công !");

            return new ResponseEntity<>(response, HttpStatus.OK);

        }catch(Exception e){
            response.setMessage(e.getMessage());
            response.setSuccess(false);
            return  new  ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/summary")
    public ResponseEntity<?> getSummary() {
        ApiResponse<List<TicketSummaryResponse>>  response = new ApiResponse<>();

        List<TicketSummaryResponse> list = parkingService.getTicketSummary();
        response.setData(list);
        response.setSuccess(true);
        response.setMessage("Lấy danh sách vé xe thành công !");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PutMapping("/check-out/{vehicle_id}")
    public ResponseEntity<?> checkOut(
            @PathVariable("vehicle_id") Long vehicleId
    ){
        ApiResponse<TicketResponse> response = new ApiResponse<>();
        try{
            TicketResponse ticketResponse = parkingService.checkOut(vehicleId);
            response.setData(ticketResponse);
            response.setSuccess(true);
            response.setMessage("Checkout thành công !");

            return new ResponseEntity<>(response, HttpStatus.OK);

        }catch(Exception e){
            response.setMessage(e.getMessage());
            response.setSuccess(false);
            return  new  ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}


