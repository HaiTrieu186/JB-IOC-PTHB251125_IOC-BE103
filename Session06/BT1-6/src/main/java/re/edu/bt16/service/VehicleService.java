package re.edu.bt16.service;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import re.edu.bt16.dto.PageResponse;
import re.edu.bt16.dto.vehicle.VehicleCreateRequest;
import re.edu.bt16.dto.vehicle.VehicleResponse;
import re.edu.bt16.entity.Vehicle;
import re.edu.bt16.repository.VehicleRepository;

@Service
@RequiredArgsConstructor
public class VehicleService implements IVehicleService {
    private final VehicleRepository vehicleRepository;

    @Override
    public PageResponse<VehicleResponse> getPagedVehicles(int page, int size, String sortBy, String direction, String keyword) {
        Pageable pageable;
        if (sortBy !=null && direction!= null){
            Sort.Direction sortDirection = direction.equalsIgnoreCase("asc")
                    ? Sort.Direction.ASC
                    : Sort.Direction.DESC;

            pageable = PageRequest.of(page <0 ? 0: page, size, Sort.by( sortDirection, sortBy));
        } else
            pageable = PageRequest.of(page <0 ? 0: page, size);

        String keywordParam = (keyword != null) ? "%" + keyword.toLowerCase() + "%" : null;
        Page<VehicleResponse> p = vehicleRepository.findAllByKeyword(keywordParam, pageable);

        return mapToPageResponse(p);
    }

    @Override
    public VehicleResponse createNewVehicle(VehicleCreateRequest request) {
        Vehicle v = new Vehicle();

        v.setColor(request.getColor());
        v.setType(request.getVehicleType());
        v.setLicensePlate(request.getLicensePlate());

        v = vehicleRepository.save(v);
        return mapToResponse(v);
    }


    private PageResponse<VehicleResponse> mapToPageResponse(Page<VehicleResponse> page){
        return PageResponse.<VehicleResponse>builder()
                .items(page.getContent())
                .size(page.getSize())
                .page(page.getNumber())
                .totalPages(page.getTotalPages())
                .totalItems(page.getTotalElements())
                .isLast(page.isLast())
                .build();
    }

    private VehicleResponse mapToResponse(Vehicle vehicle) {
        return VehicleResponse.builder()
                .id(vehicle.getId())
                .licensePlate(vehicle.getLicensePlate())
                .color(vehicle.getColor())
                .vehicleType(vehicle.getType())
                .build();
    }
}
