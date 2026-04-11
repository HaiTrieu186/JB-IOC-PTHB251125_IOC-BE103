package re.edu.bt16.mapper;

import re.edu.bt16.dto.response.borrow.BorrowRespone;
import re.edu.bt16.entity.Borrow;

public class BorrowMapper {
    public static BorrowRespone mapEntityToResponse(Borrow borrow) {
        return BorrowRespone.builder()
                .id(borrow.getId())
                .userName(borrow.getUsername())
                .build();
    }
}
