package re.edu.bt16.dto.response.borrow;


import lombok.*;
import re.edu.bt16.entity.BorrowStatusEnum;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class BorrowRespone {
    private Long id;
    private String userName;
    private Long bookId;
    private BorrowStatusEnum status;
    private LocalDate returnDate;
}
