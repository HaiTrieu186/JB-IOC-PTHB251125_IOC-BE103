package re.edu.bt16.service;

import re.edu.bt16.dto.request.borrow.BorrowCreateDTO;
import re.edu.bt16.dto.response.borrow.BorrowRespone;

public interface IBorrowService {
    BorrowRespone createBorrow(BorrowCreateDTO dto);
    BorrowRespone returnBook(Long ticketId);
}
