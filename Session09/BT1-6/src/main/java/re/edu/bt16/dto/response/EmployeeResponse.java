package re.edu.bt16.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class EmployeeResponse {
    private Long id;
    private String fullName;
    private String email;
    private String phone;
    private BigDecimal salary;
    private Long departmentId;
    private String avatarUrl;
}
