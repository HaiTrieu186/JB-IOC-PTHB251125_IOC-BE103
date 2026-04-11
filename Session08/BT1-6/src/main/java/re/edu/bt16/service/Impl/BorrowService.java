package re.edu.bt16.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import re.edu.bt16.dto.request.borrow.BorrowCreateDTO;
import re.edu.bt16.dto.response.borrow.BorrowRespone;
import re.edu.bt16.entity.Book;
import re.edu.bt16.entity.Borrow;
import re.edu.bt16.exception.ResourceNotFoundException;
import re.edu.bt16.mapper.BorrowMapper;
import re.edu.bt16.repository.IBookRepository;
import re.edu.bt16.repository.IBorrowRepository;
import re.edu.bt16.service.IBorrowService;

@Service
@RequiredArgsConstructor
public class BorrowService implements IBorrowService {
    private final IBorrowRepository borrowRepository;
    private final IBookRepository  bookRepository;

    @Override
    public BorrowRespone createBorrow(BorrowCreateDTO dto) {
        Borrow borrow = new Borrow();

        Book book = bookRepository.findById(dto.getBookId()).orElseThrow(
                () -> new ResourceNotFoundException("Lỗi: Không tìm thấy sách với id= "+dto.getBookId())
        );

        borrow.setBook(book);
        borrow.setUsername(dto.getUsername());
        borrow= borrowRepository.save(borrow);
        return BorrowMapper.mapEntityToResponse(borrow);
    }
}
