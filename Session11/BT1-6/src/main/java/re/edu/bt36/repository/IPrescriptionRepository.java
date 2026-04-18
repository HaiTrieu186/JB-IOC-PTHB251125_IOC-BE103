package re.edu.bt36.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import re.edu.bt36.entity.Prescription;

@Repository
public interface IPrescriptionRepository extends JpaRepository<Prescription,Long> {
}
