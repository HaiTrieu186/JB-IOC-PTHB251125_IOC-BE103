package re.edu.lt15.dto.response;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthResponseDTO {
    private String accessToken;
    private String refreshToken;
    private String email;
    private String role;
}