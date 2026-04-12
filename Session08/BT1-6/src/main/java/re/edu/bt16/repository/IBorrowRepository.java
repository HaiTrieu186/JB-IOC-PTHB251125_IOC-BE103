package re.edu.bt16.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import re.edu.bt16.entity.BorrowTicket;

@Repository
public interface IBorrowRepository extends JpaRepository<BorrowTicket, Long> {

}
