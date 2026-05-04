package re.edu.lt15.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import re.edu.lt15.dto.request.ProductRequestDTO;
import re.edu.lt15.dto.response.PageResponse;
import re.edu.lt15.dto.response.ProductResponse;
import re.edu.lt15.entity.Product;
import re.edu.lt15.mapper.ProductMapper;
import re.edu.lt15.repository.ProductRepository;
import re.edu.lt15.service.IProductService;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;


    @Override
    public PageResponse<ProductResponse> getProducts(String keyword, Pageable pageable) {
        String keywordParam=null;

        if (keyword != null && !keyword.isBlank()) {
            keywordParam =  "%" + keyword.toLowerCase() + "%";
        }
        Page<ProductResponse> productPage= productRepository.getProducts(keywordParam, pageable);

        return productMapper.toPageResponse(productPage);
    }

    @Override
    @Transactional
    public ProductResponse createProduct(ProductRequestDTO dto) {
        Product product = productMapper.toEntity(dto);
        productRepository.save(product);
        return productMapper.toResponse(product);

    }

    @Override
    @Transactional
    public ProductResponse updateProduct(Long id, ProductRequestDTO dto) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Không tìm thấy sản phẩm tương ứng")
        );

        product.setName(dto.getName() == null ? product.getName() : dto.getName());
        product.setPrice(dto.getPrice() == null ? product.getPrice() : dto.getPrice());
        product.setDescription(dto.getDescription() == null ? product.getDescription() : dto.getDescription());
        product.setSize(dto.getSize()  == null ? product.getSize() :dto.getSize());
        product.setToppings(dto.getToppings() == null ? product.getToppings() :dto.getToppings());

        product = productRepository.save(product);
        return productMapper.toResponse(product);
    }

    @Override
    @Transactional
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Không tìm thấy sản phẩm tương ứng")
        );

        productRepository.delete(product);
    }


}
