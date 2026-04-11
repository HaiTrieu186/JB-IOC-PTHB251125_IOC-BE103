package re.edu.bt16.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import re.edu.bt16.dto.request.BookCreateDTO;
import re.edu.bt16.dto.request.BookUpdateStockDTO;
import re.edu.bt16.dto.response.BookResponse;
import re.edu.bt16.entity.Book;
import re.edu.bt16.exception.ResourceNotFoundException;
import re.edu.bt16.mapper.BookMapper;
import re.edu.bt16.repository.IBookRepository;
import re.edu.bt16.service.IBookService;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookService implements IBookService {
    private final IBookRepository bookRepository;
    private static final String UPLOAD_DIR = "src" + File.separator + "main" + File.separator + "resources" + File.separator + "uploads";

    @Override
    public BookResponse createBook(BookCreateDTO dto) throws IOException {
        File folder = new File(UPLOAD_DIR);

        // Tạo thư mục nếu chưa có
        if (!folder.exists()) {
            folder.mkdirs();
        }

        String filePath =null;
        MultipartFile file = dto.getCoverImage();
        if (file != null && !file.isEmpty()) {
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            filePath = UPLOAD_DIR + File.separator + fileName;
            String absoluteFilePath = folder.getAbsolutePath() + File.separator + fileName;
            file.transferTo(new File(absoluteFilePath));
        }

        Book book = new Book();
        book.setAuthor(dto.getAuthor());
        book.setTitle(dto.getTitle());
        book.setStock(dto.getStock());
        book.setCoverUrl(filePath);

        book = bookRepository.save(book);
        return BookMapper.EntityToResponse(book);
    }

    @Override
    public BookResponse updateBook(Long id, BookUpdateStockDTO dto) {
        Book book = bookRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Lỗi: Không tìm thấy sách với id= "+id)
        );

        book.setStock(dto.getStock()!= null ? dto.getStock(): book.getStock());

        book= bookRepository.save(book);
        return BookMapper.EntityToResponse(book);
    }
}
