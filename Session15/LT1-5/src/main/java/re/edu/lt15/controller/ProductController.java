package re.edu.lt15.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import re.edu.lt15.dto.request.ProductRequestDTO;
import re.edu.lt15.dto.response.ApiResponse;
import re.edu.lt15.dto.response.PageResponse;
import re.edu.lt15.dto.response.ProductResponse;
import re.edu.lt15.service.IProductService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {
    private final IProductService productService;

    @GetMapping
    public ResponseEntity<?> getProducts(
            @RequestParam(required = false) String keyword,
            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable
    ) {

        PageResponse<ProductResponse> pageResponse = productService.getProducts(keyword, pageable);

        ApiResponse<PageResponse<ProductResponse>> response = new ApiResponse<>(
                "SUCCESS",
                HttpStatus.OK.value(),
                "Lấy danh sách sản phẩm thành công",
                pageResponse
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PostMapping
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_STAFF')")
    public ResponseEntity<?> createProduct(
            @Valid @RequestBody ProductRequestDTO requestDTO
    ) {
        ProductResponse productResponse = productService.createProduct(requestDTO);

        ApiResponse<ProductResponse> response = new ApiResponse<>(
                "SUCCESS",
                HttpStatus.CREATED.value(),
                "Thêm mới sản phẩm thành công",
                productResponse
        );

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_STAFF')")
    public ResponseEntity<?> updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody ProductRequestDTO requestDTO
    ) {
        ProductResponse productResponse = productService.updateProduct(id, requestDTO);

        ApiResponse<ProductResponse> response = new ApiResponse<>(
                "SUCCESS",
                HttpStatus.OK.value(),
                "Cập nhật sản phẩm thành công",
                productResponse
        );

        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_STAFF')")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {

        productService.deleteProduct(id);

        ApiResponse<Void> response = new ApiResponse<>(
                "SUCCESS",
                HttpStatus.OK.value(),
                "Xóa sản phẩm thành công",
                null
        );

        return ResponseEntity.ok(response);
    }
}
