package re.edu.bt16.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import re.edu.bt16.dto.request.borrow.BorrowCreateDTO;
import re.edu.bt16.dto.response.borrow.BorrowRespone;
import re.edu.bt16.entity.Book;
import re.edu.bt16.entity.BorrowStatusEnum;
import re.edu.bt16.entity.BorrowTicket;
import re.edu.bt16.exception.BookAlreadyReturnedException;
import re.edu.bt16.exception.OutOfStockException;
import re.edu.bt16.exception.ResourceNotFoundException;
import re.edu.bt16.mapper.BorrowMapper;
import re.edu.bt16.repository.IBookRepository;
import re.edu.bt16.repository.IBorrowRepository;
import re.edu.bt16.service.IBorrowService;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class BorrowService implements IBorrowService {
    private final IBorrowRepository borrowRepository;
    private final IBookRepository  bookRepository;

    @Override
    @Transactional
    public BorrowRespone createBorrow(BorrowCreateDTO dto) {
        BorrowTicket borrowTicket = new BorrowTicket();

        Book book = bookRepository.findById(dto.getBookId()).orElseThrow(
                () -> new ResourceNotFoundException("Lỗi: Không tìm thấy sách với id= "+dto.getBookId())
        );

        if (book.getStock()<=0){
            throw  new OutOfStockException("Lỗi: Không thể mượn do đã hết tồn kho");
        }

        book.setStock(book.getStock()-1);
        bookRepository.save(book);

        borrowTicket.setBook(book);
        borrowTicket.setUsername(dto.getUsername());
        borrowTicket.setStatus(BorrowStatusEnum.BORROWING);
        borrowTicket = borrowRepository.save(borrowTicket);

        return BorrowMapper.mapEntityToResponse(borrowTicket);
    }

    @Override
    @Transactional
    public BorrowRespone returnBook(Long ticketId) {
        BorrowTicket borrowTicket = borrowRepository.findById(ticketId).orElseThrow(
                () -> new ResourceNotFoundException("Lỗi: Không tìm thấy vé mượn với id= "+ticketId)
        );

        if (borrowTicket.getStatus() == BorrowStatusEnum.RETURNED) {
            throw  new BookAlreadyReturnedException("Lỗi: Phiếu mượn sách đã trả !");
        }

        Book book = borrowTicket.getBook();
        book.setStock(book.getStock()+1);
        bookRepository.save(book);

        borrowTicket.setStatus(BorrowStatusEnum.RETURNED);
        borrowTicket.setReturnDate(LocalDate.now());
        borrowTicket= borrowRepository.save(borrowTicket);

        return BorrowMapper.mapEntityToResponse(borrowTicket);
    }
}
