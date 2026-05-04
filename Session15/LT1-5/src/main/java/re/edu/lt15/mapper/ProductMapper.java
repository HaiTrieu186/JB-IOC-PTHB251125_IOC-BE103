package re.edu.lt15.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import re.edu.lt15.dto.request.ProductRequestDTO;
import re.edu.lt15.dto.response.PageResponse;
import re.edu.lt15.dto.response.ProductResponse;
import re.edu.lt15.entity.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    // 1. Map Entity ->  Response
    ProductResponse toResponse(Product product);

    // 2. Map DTO -> Entity
    Product toEntity(ProductRequestDTO dto);

}