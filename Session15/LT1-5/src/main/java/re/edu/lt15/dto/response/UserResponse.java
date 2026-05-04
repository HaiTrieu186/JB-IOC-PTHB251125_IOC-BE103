package re.edu.lt15.dto.response;


import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserResponse {
    private Long id;
    private String email;
    private String phone;
    private String role;
}