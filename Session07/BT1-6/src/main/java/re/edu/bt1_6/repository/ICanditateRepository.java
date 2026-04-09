package re.edu.bt1_6.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import re.edu.bt1_6.entity.Candidate;

@Repository
public interface ICanditateRepository extends JpaRepository<Candidate, Long> {
}
