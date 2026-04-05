package re.edu.bt16.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import re.edu.bt16.dto.vehicle.VehicleResponse;
import re.edu.bt16.entity.Vehicle;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    @Query(value = """
        select new re.edu.bt16.dto.vehicle.VehicleResponse(
                v.id, v.licensePlate, v.color, v.type
            )
        from Vehicle v
        where (:keyword is null or lower(v.licensePlate) like :keyword )
    
    """)
    Page<VehicleResponse> findAllByKeyword(
            @Param("keyword") String keyword,
            Pageable pageable
    );
}
