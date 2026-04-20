package re.edu.lt_tonghop.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DailyExportResponse {
    private Long supplyId;
    private String supplyName;
    private Long totalExported;
}
