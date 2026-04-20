package re.edu.lt_tonghop.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import re.edu.lt_tonghop.dto.response.DailyExportResponse;
import re.edu.lt_tonghop.entity.MedicalSupply;

import java.util.List;
import java.util.Optional;

public interface IMedicalSupplyRepository extends JpaRepository<MedicalSupply,Long> {
    Optional<MedicalSupply> findByIdAndIsDeletedIsFalse(Long id);
    List<MedicalSupply> findAllByIsDeletedIsFalse();

    @Query(value = """
    select ms
    from MedicalSupply ms
    where ms.isDeleted= false and lower(ms.name) like lower(:keyword)
    """)
    List<MedicalSupply> findAllByName(@Param("keyword") String keyword);
}
