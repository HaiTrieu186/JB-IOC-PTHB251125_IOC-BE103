package re.edu.lt_tonghop.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import re.edu.lt_tonghop.dto.request.InventoryRequestDTO;
import re.edu.lt_tonghop.dto.request.MedicalSupplyCreateDTO;
import re.edu.lt_tonghop.dto.request.MedicalSupplyUpdateDTO;
import re.edu.lt_tonghop.dto.response.DailyExportResponse;
import re.edu.lt_tonghop.dto.response.MedicalSupplyResponse;
import re.edu.lt_tonghop.dto.response.TopExportResponse;
import re.edu.lt_tonghop.entity.MedicalSupply;
import re.edu.lt_tonghop.entity.SupplyTransaction;
import re.edu.lt_tonghop.entity.TransactionType;
import re.edu.lt_tonghop.exception.ResourceNotFoundException;
import re.edu.lt_tonghop.mapper.MedicalSupplyMapper;
import re.edu.lt_tonghop.repository.IMedicalSupplyRepository;
import re.edu.lt_tonghop.repository.ISupplyTransactionRepository;
import re.edu.lt_tonghop.service.IMedicalSupplyService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class MedicalSupplyServiceImpl implements IMedicalSupplyService {
    private final IMedicalSupplyRepository medicalSupplyRepository;
    private final ISupplyTransactionRepository supplyTransactionRepository;
    private final MedicalSupplyMapper mapper;
    private static final Logger historyLog = LoggerFactory.getLogger("HISTORY_LOGGER");

    @Override
    public MedicalSupplyResponse createMedicalSupply(MedicalSupplyCreateDTO dto) {
        MedicalSupply entity = mapper.toEntity(dto);
        MedicalSupply saved = medicalSupplyRepository.save(entity);

        log.info("Đã tạo mới vật tư: {} với ID: {}", saved.getName(), saved.getId());

        return mapper.toResponse(saved);
    }

    @Override
    public MedicalSupplyResponse updateMedicalSupply(Long id, MedicalSupplyUpdateDTO dto) {
        if (dto.getId() != null || dto.getQuantity() != null) {
            log.warn("Người dùng vừa vi phạm khi gửi id và quantity và body, supply ID: {}", id);
            throw new IllegalArgumentException("Lỗi: Không được phép chỉnh sửa ID và Quantity !");
        }

        MedicalSupply entity = medicalSupplyRepository.findByIdAndIsDeletedIsFalse(id).orElseThrow(
                () -> new ResourceNotFoundException("Lỗi: Không tìm thấy vật tư với ID: " + id)
        );

        mapper.updateEntity(dto, entity);
        MedicalSupply saved = medicalSupplyRepository.save(entity);
        log.info("Đã cập nhật vật tư: {} với ID: {}", entity.getName(), entity.getId());


        return mapper.toResponse(saved);
    }

    @Override
    public void deleteMedicalSupply(Long id) {
        MedicalSupply entity = medicalSupplyRepository.findByIdAndIsDeletedIsFalse(id).orElseThrow(
                () -> new ResourceNotFoundException("Lỗi: Không tìm thấy vật tư với ID: " + id)
        );

        log.info("Đã xóa mềm vật tư: {} với ID: {}", entity.getName(), entity.getId());

        entity.setIsDeleted(true);
        medicalSupplyRepository.save(entity);
    }

    @Override
    public List<MedicalSupplyResponse> getAllMedicalSupply() {
        List<MedicalSupply> list = medicalSupplyRepository.findAllByIsDeletedIsFalse();

        log.info("Đã lấy danh  sách vật tư hiện có");
        log.debug("Truy vấn được {} bản ghi từ DB", list.size());

        return list.stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<MedicalSupplyResponse> getAllMedicalSupplyByName(String name) {

        // Nếu name null hoặc rỗng thì trả
        if (name == null || name.isBlank()) {
            log.info("Không tìm thấy vật tư nào khớp với từ khóa: {}", name);
            return List.of();
        }

        String keywordParam = "%" + name.toLowerCase() + "%";
        List<MedicalSupply> result = medicalSupplyRepository.findAllByName(keywordParam);

        if (result.isEmpty()) {
            log.info("Không tìm thấy vật tư nào khớp với từ khóa: {}", name);
        }

        return result.stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public MedicalSupplyResponse exportSupply(Long id, InventoryRequestDTO dto) {
        MedicalSupply entity = medicalSupplyRepository.findByIdAndIsDeletedIsFalse(id).orElseThrow(
                () -> new ResourceNotFoundException("Lỗi: Không tìm thấy vật tư với ID: " + id)
        );

        if (dto.getAmount() > entity.getQuantity()) {
            log.error("Thất bại khi xuất kho ID {}: Yêu cầu {}, hiện có {}",
                    id, dto.getAmount(), entity.getQuantity());
            throw new IllegalArgumentException("Số lượng tồn kho không đủ để xuất");
        }
        entity.setQuantity(entity.getQuantity() - dto.getAmount());
        MedicalSupply saved = medicalSupplyRepository.save(entity);

        SupplyTransaction supplyTransaction = SupplyTransaction.builder()
                .medicalSupply(saved)
                .amount(dto.getAmount())
                .type(TransactionType.EXPORT)
                .build();
        supplyTransactionRepository.save(supplyTransaction);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional
    public MedicalSupplyResponse importSupply(Long id, InventoryRequestDTO dto) {
        MedicalSupply entity = medicalSupplyRepository.findByIdAndIsDeletedIsFalse(id).orElseThrow(
                () -> new ResourceNotFoundException("Lỗi: Không tìm thấy vật tư với ID: " + id)
        );

        int oldQuantity = entity.getQuantity();

        entity.setQuantity(oldQuantity + dto.getAmount());
        MedicalSupply saved = medicalSupplyRepository.save(entity);

        SupplyTransaction supplyTransaction = SupplyTransaction.builder()
                .medicalSupply(saved)
                .amount(dto.getAmount())
                .type(TransactionType.IMPORT)
                .build();
        supplyTransactionRepository.save(supplyTransaction);

        historyLog.info("Nhập kho ID {}, số lượng +{}, tồn cũ {}",
                id, dto.getAmount(), oldQuantity);

        return mapper.toResponse(saved);
    }

    @Override
    public List<DailyExportResponse> getDailyExport() {
        log.info("Bắt đầu thống kê xuất kho trong ngày: {}", LocalDate.now());
        LocalDate today = LocalDate.now();
        LocalDateTime start = today.atStartOfDay(); // 00:00:00
        LocalDateTime end = today.atTime(LocalTime.MAX);
        List<DailyExportResponse> result = supplyTransactionRepository.getDailyExport(start, end);
        log.info("Hoàn thành thống kê, tổng {} loại vật tư đã xuất hôm nay", result.size()); // thêm
        return result;
    }

    @Override
    public TopExportResponse getTopExport() {
        Pageable pageable = PageRequest.of(0, 1);

        TopExportResponse top = supplyTransactionRepository.findTopExport(pageable).stream().findFirst().orElseThrow(
                () -> new ResourceNotFoundException("Lỗi: Chưa có dữ liệu giao dịch để thống kê")
        );


        return top;
    }


}
