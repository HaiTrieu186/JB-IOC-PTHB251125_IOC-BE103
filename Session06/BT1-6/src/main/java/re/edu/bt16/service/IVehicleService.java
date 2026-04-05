package re.edu.bt16.service;

import re.edu.bt16.dto.PageResponse;
import re.edu.bt16.dto.vehicle.VehicleCreateRequest;
import re.edu.bt16.dto.vehicle.VehicleResponse;

import java.util.Optional;

public interface IVehicleService {
    PageResponse<VehicleResponse> getPagedVehicles(int page, int size, String sortBy, String direction, String keyword);
//    Optional<VehicleResponse> getVehicleById(String id);
    VehicleResponse createNewVehicle(VehicleCreateRequest request);
}
