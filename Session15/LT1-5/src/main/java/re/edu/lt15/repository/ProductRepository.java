package re.edu.lt15.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import re.edu.lt15.dto.response.PageResponse;
import re.edu.lt15.dto.response.ProductResponse;
import re.edu.lt15.entity.Product;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = """
    select new re.edu.lt15.dto.response.ProductResponse(
            p.id, p.name, p.description, p.price, p.size, p.toppings
        )
    from  Product p
    where (:keyword is null  or lower(p.name) like :keyword)
    """)
    Page<ProductResponse> getProducts(
            @Param("keyword") String keyword,
            Pageable pageable);

    Optional<Product> findById(Long id);
}
