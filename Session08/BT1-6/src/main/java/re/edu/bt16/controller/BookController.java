package re.edu.bt16.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import re.edu.bt16.dto.ApiResponse;
import re.edu.bt16.dto.request.BookCreateDTO;
import re.edu.bt16.dto.request.BookUpdateStockDTO;
import re.edu.bt16.dto.response.BookResponse;
import re.edu.bt16.service.IBookService;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/books")
public class BookController {
    private final IBookService bookService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findBookById(@PathVariable Long id){
        ApiResponse<BookResponse> apiResponse = new ApiResponse<>();

        BookResponse bookResponse = bookService.findBookById(id);
        apiResponse.setStatus("SUCCESS");
        apiResponse.setMessage("Tìm thấy sách tương ứng !");
        apiResponse.setData(bookResponse);

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> createBook(@Valid @ModelAttribute BookCreateDTO dto) throws IOException {
        ApiResponse<BookResponse> apiResponse = new ApiResponse<>();

        BookResponse bookResponse = bookService.createBook(dto);
        apiResponse.setStatus("SUCCESS");
        apiResponse.setMessage("Tạo mới thành công !");
        apiResponse.setData(bookResponse);

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PatchMapping("/update/{id}")
    public  ResponseEntity<?> updateBook(
            @PathVariable("id") Long id,
            @RequestBody @Valid BookUpdateStockDTO dto
    ){
        ApiResponse<BookResponse> apiResponse = new ApiResponse<>();
        BookResponse bookResponse = bookService.updateBook(id, dto);
        apiResponse.setStatus("SUCCESS");
        apiResponse.setMessage("Cập nhật thành công");
        apiResponse.setData(bookResponse);

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
