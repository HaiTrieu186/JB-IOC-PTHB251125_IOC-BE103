package re.edu.bt36.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import re.edu.bt36.entity.Doctor;

@Repository
public interface IDoctorRepository extends JpaRepository<Doctor,Long> {
}
