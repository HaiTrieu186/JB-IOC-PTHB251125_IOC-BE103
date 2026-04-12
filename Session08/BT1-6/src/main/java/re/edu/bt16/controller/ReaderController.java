package re.edu.bt16.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import re.edu.bt16.dto.ApiResponse;
import re.edu.bt16.dto.request.reader.ReaderCreateDTO;
import re.edu.bt16.dto.response.response.ReaderResponse;
import re.edu.bt16.service.IReaderService;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/readers")
public class ReaderController {
    private final IReaderService readerService;

    @PostMapping
    public ResponseEntity<?> createReader(@Valid @ModelAttribute ReaderCreateDTO dto) throws IOException {
        ApiResponse<ReaderResponse> response = new ApiResponse<>();

        ReaderResponse readerResponse= readerService.createReader(dto);
        response.setData(readerResponse);
        response.setMessage("Tạo mới thành công !");
        response.setStatus("SUCCESS");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


}
