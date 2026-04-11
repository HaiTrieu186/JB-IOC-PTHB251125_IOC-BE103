package re.edu.bt16.dto.request.book;

import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookUpdateStockDTO {
    @Min(value = 0, message = "Lỗi: số tồn kho không được là số âm")
    private Integer stock;
}
