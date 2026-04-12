package re.edu.bt16.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import re.edu.bt16.entity.Employee;

@Repository
public interface IEmployeeRepository extends JpaRepository<Employee, Long> {
}
