package re.edu.bt16.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class BookCreateDTO {
    @NotBlank(message = "Lỗi: không được để trống tên sách")
    private String title;
    @NotBlank(message = "Lỗi: không được để trống tác giả")
    private String author;
    @PositiveOrZero(message = "Lỗi: số lượng phải lớn hơn hoặc bằng 0")
    private Integer stock;
    private MultipartFile coverImage;
}
