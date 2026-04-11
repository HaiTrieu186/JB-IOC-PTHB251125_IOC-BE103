package re.edu.bt16.service;

import re.edu.bt16.dto.request.BookCreateDTO;
import re.edu.bt16.dto.response.BookResponse;

import java.io.IOException;

public interface IBookService {
    BookResponse createBook(BookCreateDTO dto) throws IOException;
}
