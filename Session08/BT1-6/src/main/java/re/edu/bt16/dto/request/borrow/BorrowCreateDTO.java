package re.edu.bt16.dto.request.borrow;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import re.edu.bt16.validation.ExistingBookId;

@Getter
@Setter
public class BorrowCreateDTO {
    @NotBlank(message = "Lỗi: Không được để trống tên người mượn")
    private String username;
    @ExistingBookId
    private Long bookId;
}
