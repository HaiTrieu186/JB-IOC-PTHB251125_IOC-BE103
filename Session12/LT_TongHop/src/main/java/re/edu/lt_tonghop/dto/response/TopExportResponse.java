package re.edu.lt_tonghop.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TopExportResponse {
    private Long supplyId;
    private String topSupplyName;
    private Long totalExportQuantity;
}
