package re.edu.lt15.service;

import re.edu.lt15.dto.request.LoginRequestDTO;
import re.edu.lt15.dto.request.RegisterRequestDTO;
import re.edu.lt15.dto.response.AuthResponseDTO;
import re.edu.lt15.dto.request.RefreshTokenRequestDTO;
import re.edu.lt15.dto.response.UserResponse;

public interface IAuthService {
    UserResponse register(RegisterRequestDTO request);
    AuthResponseDTO login(LoginRequestDTO request);
    AuthResponseDTO refreshToken(RefreshTokenRequestDTO request);
}
