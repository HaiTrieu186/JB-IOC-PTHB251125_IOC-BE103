package re.edu.lt15.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UserRoleUpdateRequestDTO {

    @NotBlank(message = "Quyền (Role) không được để trống")
    private String role; // VD: ROLE_STAFF, ROLE_CUSTOMER, ROLE_ADMIN

}