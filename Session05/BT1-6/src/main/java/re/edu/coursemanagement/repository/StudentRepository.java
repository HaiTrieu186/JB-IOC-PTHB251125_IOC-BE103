package re.edu.coursemanagement.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import re.edu.coursemanagement.dto.student.StudentResponse;
import re.edu.coursemanagement.entity.Student;



@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query(value = """
       select new re.edu.coursemanagement.dto.student.StudentResponse(
            s.id, s.name, s.email
       )
       from Student s
       where (:keyword is null or lower(s.name) like :keyword)
""")
    public Page<StudentResponse> findAllStudents(
            @Param("keyword") String keyword,
            Pageable pageable
    );
}
