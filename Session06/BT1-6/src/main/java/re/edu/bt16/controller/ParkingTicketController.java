package re.edu.bt16.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import re.edu.bt16.dto.ApiResponse;
import re.edu.bt16.dto.parking_ticket.TicketRequest;
import re.edu.bt16.dto.parking_ticket.TicketResponse;
import re.edu.bt16.service.IParkingService;

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


