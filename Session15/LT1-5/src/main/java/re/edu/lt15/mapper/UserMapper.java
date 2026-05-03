package re.edu.lt15.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import re.edu.lt15.dto.request.RegisterRequestDTO;
import re.edu.lt15.dto.response.UserResponse;
import re.edu.lt15.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "password", ignore = true)
    User toEntity(RegisterRequestDTO requestDTO);

    UserResponse toResponse(User user);
}