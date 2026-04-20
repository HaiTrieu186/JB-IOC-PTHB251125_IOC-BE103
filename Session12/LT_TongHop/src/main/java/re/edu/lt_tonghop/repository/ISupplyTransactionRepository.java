package re.edu.lt_tonghop.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import re.edu.lt_tonghop.dto.response.DailyExportResponse;
import re.edu.lt_tonghop.dto.response.TopExportResponse;
import re.edu.lt_tonghop.entity.SupplyTransaction;

import java.time.LocalDateTime;
import java.util.List;

public interface ISupplyTransactionRepository extends JpaRepository<SupplyTransaction,Long> {
    @Query(value = """
            select new re.edu.lt_tonghop.dto.response.DailyExportResponse(
                     st.medicalSupply.id,
                     st.medicalSupply.name,
                     sum(st.amount)   
            )
            from SupplyTransaction st
            where st.type = re.edu.lt_tonghop.entity.TransactionType.EXPORT 
                AND st.transactionDate >= :startOfDay
                AND st.transactionDate <= :endOfDay
            group by st.medicalSupply.id  
            """)
    // khi lọc theo khoảng rộng hơn thì chỉ cần truyền tham số xa hơn thôi
    List<DailyExportResponse> getDailyExport(
            @Param("startOfDay") LocalDateTime startOfDay,
            @Param("endOfDay") LocalDateTime endOfDay
    );



    @Query(value = """
            select new re.edu.lt_tonghop.dto.response.TopExportResponse(
                    st.medicalSupply.id,
                    st.medicalSupply.name,
                    sum(st.amount)
                )
            from SupplyTransaction st
            where st.type = re.edu.lt_tonghop.entity.TransactionType.EXPORT 
            group by st.medicalSupply.id
            order by sum(st.amount) desc 
    """)
    List<TopExportResponse> findTopExport(Pageable pageable);
}
