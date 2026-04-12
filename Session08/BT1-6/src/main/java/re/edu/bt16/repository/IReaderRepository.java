package re.edu.bt16.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import re.edu.bt16.entity.Reader;

@Repository
public interface IReaderRepository extends JpaRepository<Reader, Long> {
    public Boolean existsByEmail(String email);
}
