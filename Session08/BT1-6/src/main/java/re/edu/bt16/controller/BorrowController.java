package re.edu.bt16.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import re.edu.bt16.dto.ApiResponse;
import re.edu.bt16.dto.request.borrow.BorrowCreateDTO;
import re.edu.bt16.dto.response.borrow.BorrowRespone;
import re.edu.bt16.service.IBorrowService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/borrows")
public class BorrowController {
    private final IBorrowService borrowService;

    @PostMapping
    public ResponseEntity<?> createBorrow(
            @Valid @RequestBody BorrowCreateDTO dto
    ) {
        ApiResponse<BorrowRespone> response = new ApiResponse<>();
        BorrowRespone borrowRespone = borrowService.createBorrow(dto);
        response.setData(borrowRespone);
        response.setMessage("Tạo mới thành công !");
        response.setStatus("SUCCESS");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
