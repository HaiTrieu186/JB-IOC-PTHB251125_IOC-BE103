package re.edu.lt_tonghop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import re.edu.lt_tonghop.dto.request.MedicalSupplyCreateDTO;
import re.edu.lt_tonghop.dto.request.MedicalSupplyUpdateDTO;
import re.edu.lt_tonghop.dto.response.MedicalSupplyResponse;
import re.edu.lt_tonghop.entity.MedicalSupply;

@Mapper(componentModel = "spring")
public interface MedicalSupplyMapper {

    // 1. CreateDTO -> Entity
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "quantity", constant = "0")
    @Mapping(target = "isDeleted", constant = "false")
    MedicalSupply toEntity(MedicalSupplyCreateDTO dto);

    // 2. Entity -> Response
    MedicalSupplyResponse toResponse(MedicalSupply entity);

    // 3. UpdateDTO -> Entity (khi update, chỉ ghi đè 3 field được phép)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "quantity", ignore = true)
    @Mapping(target = "isDeleted", ignore = true)
    @Mapping(target = "unit", ignore = true)
    void updateEntity(MedicalSupplyUpdateDTO dto, @MappingTarget MedicalSupply entity);
}