package re.edu.bt16.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import re.edu.bt16.dto.ApiResponse;
import re.edu.bt16.dto.request.BookCreateDTO;
import re.edu.bt16.dto.response.BookResponse;
import re.edu.bt16.service.IBookService;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/books")
public class BookController {
    private final IBookService bookService;

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> createBook(@Valid @ModelAttribute BookCreateDTO dto) throws IOException {
        ApiResponse<BookResponse> apiResponse = new ApiResponse<>();

        BookResponse bookResponse = bookService.createBook(dto);
        apiResponse.setStatus("SUCCESS");
        apiResponse.setMessage("Tạo mới thành công !");
        apiResponse.setData(bookResponse);

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
