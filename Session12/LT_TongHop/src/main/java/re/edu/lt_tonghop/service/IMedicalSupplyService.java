package re.edu.lt_tonghop.service;

import org.springframework.data.domain.Pageable;
import re.edu.lt_tonghop.dto.request.InventoryRequestDTO;
import re.edu.lt_tonghop.dto.request.MedicalSupplyCreateDTO;
import re.edu.lt_tonghop.dto.request.MedicalSupplyUpdateDTO;
import re.edu.lt_tonghop.dto.response.DailyExportResponse;
import re.edu.lt_tonghop.dto.response.MedicalSupplyResponse;
import re.edu.lt_tonghop.dto.response.TopExportResponse;

import java.util.List;

public interface IMedicalSupplyService {
    MedicalSupplyResponse createMedicalSupply(MedicalSupplyCreateDTO dto);
    MedicalSupplyResponse updateMedicalSupply(Long id, MedicalSupplyUpdateDTO dto);
    void deleteMedicalSupply(Long id);
    List<MedicalSupplyResponse> getAllMedicalSupply();
    List<MedicalSupplyResponse> getAllMedicalSupplyByName(String name);
    MedicalSupplyResponse exportSupply(Long id, InventoryRequestDTO dto);
    MedicalSupplyResponse importSupply(Long id, InventoryRequestDTO dto);
    List<DailyExportResponse> getDailyExport();
    TopExportResponse getTopExport();
}
