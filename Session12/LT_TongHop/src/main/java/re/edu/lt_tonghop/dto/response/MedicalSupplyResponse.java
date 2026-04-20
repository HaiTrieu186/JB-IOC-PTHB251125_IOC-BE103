package re.edu.lt_tonghop.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicalSupplyResponse {
    private Long id;
    private String name;
    private String provider;
    private String unit;
    private String specification;
    private Integer quantity;
}
