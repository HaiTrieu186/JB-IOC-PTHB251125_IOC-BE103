package re.edu.bt16.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BookResponse {
    private Long id;
    private String title;
    private String author;
    private Integer stock;
    private String coverUrl;
}
