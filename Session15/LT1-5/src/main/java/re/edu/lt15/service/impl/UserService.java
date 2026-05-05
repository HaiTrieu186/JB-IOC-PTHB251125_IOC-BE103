package re.edu.lt15.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import re.edu.lt15.config.security.UserPrincipal;
import re.edu.lt15.dto.request.UserRoleUpdateRequestDTO;
import re.edu.lt15.dto.response.UserResponse;
import re.edu.lt15.entity.User;
import re.edu.lt15.mapper.UserMapper;
import re.edu.lt15.repository.UserRepository;
import re.edu.lt15.service.IUserService;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private User getCurrentUser() {
        UserPrincipal principal = (UserPrincipal) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        return principal.getUser();
    }

    @Override
    public UserResponse updateRole(Long userId, UserRoleUpdateRequestDTO requestDTO) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new RuntimeException("Không tìm thấy user với id: "+userId)
        );

        user.setRole(requestDTO.getRole());
        user = userRepository.save(user);

        return userMapper.toResponse(user);
    }

    @Override
    public UserResponse getProfile() {
        User user = getCurrentUser();
        return userMapper.toResponse(user);
    }
}
