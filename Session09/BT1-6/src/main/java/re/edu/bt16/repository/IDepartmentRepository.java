package re.edu.bt16.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import re.edu.bt16.entity.Department;

@Repository
public interface IDepartmentRepository extends JpaRepository<Department, Long> {
}
