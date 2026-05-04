package re.edu.lt15.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import re.edu.lt15.entity.Order;
import re.edu.lt15.entity.User;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findOrderByUser(User user);
    Page<Order> findAll(Pageable pageable);
}
