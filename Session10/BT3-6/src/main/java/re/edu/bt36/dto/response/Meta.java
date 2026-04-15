package re.edu.bt36.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Meta {
    private int page;
    private int size;
    private long totalItems;
    private int totalPages;
    private boolean isLast;
}
