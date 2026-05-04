package re.edu.lt15.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import re.edu.lt15.dto.request.ProductRequestDTO;
import re.edu.lt15.dto.response.PageResponse;
import re.edu.lt15.dto.response.ProductResponse;
import re.edu.lt15.entity.Product;

public interface IProductService {
    PageResponse<ProductResponse> getProducts(String keyword, Pageable pageable);
    ProductResponse createProduct(ProductRequestDTO productRequestDTO);
    ProductResponse updateProduct(Long id, ProductRequestDTO productRequestDTO);
    void deleteProduct(Long id);
}
