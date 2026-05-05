package re.edu.lt15.service;

import re.edu.lt15.dto.request.UserRoleUpdateRequestDTO;
import re.edu.lt15.dto.response.UserResponse;
import re.edu.lt15.entity.User;

public interface IUserService {
    UserResponse updateRole(Long userId, UserRoleUpdateRequestDTO requestDTO);
    UserResponse getProfile();
}
