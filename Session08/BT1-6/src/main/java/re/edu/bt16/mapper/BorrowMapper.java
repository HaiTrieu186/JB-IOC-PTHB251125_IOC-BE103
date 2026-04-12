package re.edu.bt16.mapper;

import re.edu.bt16.dto.response.borrow.BorrowRespone;
import re.edu.bt16.entity.BorrowTicket;

public class BorrowMapper {
    public static BorrowRespone mapEntityToResponse(BorrowTicket borrowTicket) {
        return BorrowRespone.builder()
                .id(borrowTicket.getId())
                .userName(borrowTicket.getUsername())
                .bookId(borrowTicket.getBook().getId())
                .status(borrowTicket.getStatus())
                .returnDate(borrowTicket.getReturnDate())
                .build();
    }
}
