package re.edu.bt16.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import re.edu.bt16.dto.ApiResponse;
import re.edu.bt16.dto.PageResponse;
import re.edu.bt16.dto.vehicle.VehicleCreateRequest;
import re.edu.bt16.dto.vehicle.VehicleResponse;
import re.edu.bt16.service.IVehicleService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/vehicles")
public class VehicleController {
    private  final IVehicleService vehicleService;

    @GetMapping
    public ResponseEntity<?> getVehicles(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String direction,
            @RequestParam(required = false) String keyword
            ){

        ApiResponse<PageResponse<VehicleResponse>> apiResponse = new ApiResponse<>();
        PageResponse<VehicleResponse> pageResponse = vehicleService.getPagedVehicles(page,size,sortBy,direction,keyword);

        apiResponse.setData(pageResponse);
        apiResponse.setSuccess(true);
        apiResponse.setMessage("Lấy danh sách phương tiện thành công !");

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createVehicles(
            @RequestBody VehicleCreateRequest request
            ){

        ApiResponse<VehicleResponse> apiResponse = new ApiResponse<>();
        VehicleResponse response = vehicleService.createNewVehicle(request);

        apiResponse.setData(response);
        apiResponse.setSuccess(true);
        apiResponse.setMessage("Tạo mới phương tiện thành công !");

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }


}
